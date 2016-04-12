package tests;

import entidades.*;
import excecoes.JaExisteException;
import fabricas.FabricaDoenca;
import fabricas.FabricaMedicamento;
import fabricas.FabricaSintoma;
import fabricas.FabricaSubstancia;
import logica.SisBulaMemory;
import org.junit.*;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SisBulaTesteTest {

    SisBulaMemory sis;
    Medicamento medicamento1, medicamento2, medicamento3;
    Doenca doenca1, doenca2, doenca3;
    Sintoma sintoma1, sintoma2, sintoma3, sintoma4, sintoma5;
    Substancia substancia1, substancia2;
    int i = 0;


    @Before
    public void setUp() {
        System.out.println("preparando");
        sis = new SisBulaMemory();
        //Criação de Medicamentos
        medicamento1 = FabricaMedicamento.getMedicamento("cafe", Fabricante.Ache);
        medicamento2 = FabricaMedicamento.getMedicamento("Coquetel de Remedios", Fabricante.Ache);
        medicamento3 = FabricaMedicamento.getMedicamento("Paracetamol", Fabricante.Medley);


        doenca1 = FabricaDoenca.getDoenca("Sono");
        doenca2 = FabricaDoenca.getDoenca("AIDS");
        doenca3 = FabricaDoenca.getDoenca("Dengue");

        sintoma1 = FabricaSintoma.getSintoma("Sonolencia", "escorar em qualquer canto e dormir");
        sintoma2 = FabricaSintoma.getSintoma("Perca de inunidade", "Sistema imunologico fica debilitado");
        sintoma3 = FabricaSintoma.getSintoma("Dor muscular", "Dores nos musculos");
        sintoma4 = FabricaSintoma.getSintoma("febre", "aumento de temperatura no corpo");
        sintoma5 = FabricaSintoma.getSintoma("dor de cabeça", "dor forte em alguma região na cabeça");

        substancia1 = FabricaSubstancia.getSubstancia("Paracetamol");
        substancia2 = FabricaSubstancia.getSubstancia("cafeina");

        // Medicamento interage com o outro
        medicamento1.cadastrarInteracao(medicamento2);

        //cadastro do sintomas com as donecas
        doenca1.adicionarSintoma(sintoma1);

        doenca2.adicionarSintoma(sintoma2);

        doenca3.adicionarSintoma(sintoma3);
        doenca3.adicionarSintoma(sintoma4);

        //cadastro das doenças e sintomas que os remedios cuidam
        medicamento1.cadastrarDoenca(doenca1);
        medicamento1.cadastrarSintoma(sintoma1);

        medicamento2.cadastrarDoenca(doenca2);
        medicamento2.cadastrarSintoma(sintoma2);

        medicamento3.cadastrarDoenca(doenca3);
        medicamento3.cadastrarSintoma(sintoma3);
        medicamento3.cadastrarSintoma(sintoma4);
        medicamento3.cadastrarSintoma(sintoma5);

        try {
            medicamento1.cadastrarSubstancia(substancia2);
            medicamento3.cadastrarSubstancia(substancia1);
            medicamento3.cadastrarSubstancia(substancia2);
        } catch (JaExisteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPesquisa() throws JaExisteException {

        testCadastro();

        //Pesquisas no sisBula

        //assertEquals(false, sis.pesquisarMedicamentoParaDoenca(null));
        //assertEquals(false, sis.pesquisarMedicamentoParaSintoma(null));
        //devo fazer esse teste ?

        //testes no medicamento 1
        assertEquals(true, sis.pesquisarMedicamentoParaDoenca(doenca1).contains(medicamento1));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma1).contains(medicamento1));
        assertEquals(false, sis.pesquisarMedicamentoParaDoenca(doenca2).contains(medicamento1));
        assertEquals(false, sis.pesquisarMedicamentoParaSintoma(sintoma5).contains(medicamento1));
        //teste medicamento 2
        assertEquals(true, sis.pesquisarMedicamentoParaDoenca(doenca2).contains(medicamento2));
        assertEquals(false, sis.pesquisarMedicamentoParaDoenca(doenca1).contains(medicamento2));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma2).contains(medicamento2));
        assertEquals(false, sis.pesquisarMedicamentoParaSintoma(sintoma5).contains(medicamento2));
        //testes medicamento 3
        assertEquals(true, sis.pesquisarMedicamentoParaDoenca(doenca3).contains(medicamento3));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma3).contains(medicamento3));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma4).contains(medicamento3));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma5).contains(medicamento3));
        assertEquals(false, sis.pesquisarMedicamentoParaSintoma(sintoma1).contains(medicamento3));
        assertEquals(false, sis.pesquisarMedicamentoParaDoenca(doenca1).contains(medicamento3));
        assertEquals(false, sis.pesquisarMedicamentoParaSintoma(sintoma1).contains(medicamento3));
    }

    @Test
    public void testCadastro() {

        //cadastro no SisBula
        try {
            sis.cadastrarMedicamento(medicamento1);
            sis.cadastrarMedicamento(medicamento2);
            sis.cadastrarMedicamento(medicamento3);
        } catch (JaExisteException e) {
            e.printStackTrace();
        }
        assertEquals(true, sis.existeMedicamento(medicamento1));
        assertEquals(true, sis.existeMedicamento(medicamento2));
        assertEquals(true, sis.existeMedicamento(medicamento3));

    }

    @Test
    public void testCadastroNull() {
        try {
            sis.cadastrarMedicamento(null);
        } catch (Exception e) {
            System.out.println("lançou um null pointer: " + e.toString());
        }
    }

    @Test
    public void testCadastraMedicamentoRepetido() {
        //cadastro no SisBula
        try {
            sis.cadastrarMedicamento(medicamento1);
        } catch (JaExisteException e) {
            assertTrue(true);
        }

        assertEquals(true, sis.existeMedicamento(medicamento1));

        try {
            sis.cadastrarMedicamento(medicamento1);
            fail();
        } catch (JaExisteException e) {
        }
    }

    @Test
    public void testDelete() {
        sis.removerMedicamento(medicamento1);
        assertEquals(0, sis.pesquisarMedicamentoParaDoenca(doenca1).size());
        sis.removerMedicamento(null);
    }

    @Test
    public void testPesquisaSubstancia() {
        assertEquals(1, sis.pesquisarRemediosComSusbstancia(substancia1).size());
        assertEquals(2, sis.pesquisarRemediosComSusbstancia(substancia2).size());
        assertEquals(true, sis.pesquisarRemediosComSusbstancia(substancia2).contains(medicamento1));
        assertEquals(false, sis.pesquisarRemediosComSusbstancia(substancia2).contains(medicamento2));
        assertEquals(true, sis.pesquisarRemediosComSusbstancia(substancia2).contains(medicamento3));
        assertEquals(true, sis.pesquisarRemediosComSusbstancia(substancia1).contains(medicamento3));


    }

    @Test
    public void testPesquisaComInteracao() {
        assertEquals(true, sis.pesquisarPorInteracao(medicamento1).contains(medicamento2));
        assertEquals(1, sis.pesquisarPorInteracao(medicamento1).size());
    }

    @Test
    public void testGravacao() {
        testCadastro();
        sis.gravarGerente();
    }

    @Test
    public void testLeitura() throws JaExisteException {
        sis.carregarGerente();

        assertEquals(true, sis.pesquisarMedicamentoParaDoenca(doenca1).contains(medicamento1));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma1).contains(medicamento1));
        assertEquals(false, sis.pesquisarMedicamentoParaDoenca(doenca2).contains(medicamento1));
        assertEquals(false, sis.pesquisarMedicamentoParaSintoma(sintoma5).contains(medicamento1));
        //teste medicamento 2
        assertEquals(true, sis.pesquisarMedicamentoParaDoenca(doenca2).contains(medicamento2));
        assertEquals(false, sis.pesquisarMedicamentoParaDoenca(doenca1).contains(medicamento2));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma2).contains(medicamento2));
        assertEquals(false, sis.pesquisarMedicamentoParaSintoma(sintoma5).contains(medicamento2));
        //testes medicamento 3
        assertEquals(true, sis.pesquisarMedicamentoParaDoenca(doenca3).contains(medicamento3));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma3).contains(medicamento3));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma4).contains(medicamento3));
        assertEquals(true, sis.pesquisarMedicamentoParaSintoma(sintoma5).contains(medicamento3));
        assertEquals(false, sis.pesquisarMedicamentoParaSintoma(sintoma1).contains(medicamento3));
        assertEquals(false, sis.pesquisarMedicamentoParaDoenca(doenca1).contains(medicamento3));
        assertEquals(false, sis.pesquisarMedicamentoParaSintoma(sintoma1).contains(medicamento3));
    }
}
