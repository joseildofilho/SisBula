package tests;

import entidades.*;
import excecoes.JaExisteException;
import excecoes.NaoAchouException;
import fabrica.Fabrica;
import logica.SisBulaFacade;
import org.junit.*;

import static org.junit.Assert.*;

public class SisBulaTesteTest {

    SisBulaFacade sis;
    Medicamento medicamento1, medicamento2, medicamento3;
    Doenca doenca1, doenca2, doenca3;
    Sintoma sintoma1, sintoma2, sintoma3, sintoma4, sintoma5;
    Substancia substancia1, substancia2;
    int i = 0;


    @Before
    public void setUp() {
        System.out.println("preparando");
        sis = new SisBulaFacade();
        //Criação de Medicamentos
        medicamento1 = Fabrica.getMedicamento("cafe", Fabricante.Ache);
        medicamento2 = Fabrica.getMedicamento("Coquetel de Remedios", Fabricante.Ache);
        medicamento3 = Fabrica.getMedicamento("Paracetamol", Fabricante.Medley);


        doenca1 = Fabrica.getDoenca("Sono");
        doenca2 = Fabrica.getDoenca("AIDS");
        doenca3 = Fabrica.getDoenca("Dengue");

        sintoma1 = Fabrica.getSintoma("Sonolencia", "escorar em qualquer canto e dormir");
        sintoma2 = Fabrica.getSintoma("Perca de inunidade", "Sistema imunologico fica debilitado");
        sintoma3 = Fabrica.getSintoma("Dor muscular", "Dores nos musculos");
        sintoma4 = Fabrica.getSintoma("febre", "aumento de temperatura no corpo");
        sintoma5 = Fabrica.getSintoma("dor de cabeça", "dor forte em alguma região na cabeça");

        substancia1 = Fabrica.getSubstancia("Paracetamol");
        substancia2 = Fabrica.getSubstancia("cafeina");

        // Medicamento interage com o outro
        medicamento1.cadastrarInteracao(medicamento2);

        //cadastro do sintomas com as donecas
        doenca1.adicionarSintoma(sintoma1);

        doenca2.adicionarSintoma(sintoma2);

        doenca3.adicionarSintoma(sintoma3);
        doenca3.adicionarSintoma(sintoma4);

        //cadastro das doenças e sintomas que os remedios cuidam
        medicamento1.cadastrarDoencaNoMedicamento(doenca1);
        medicamento1.cadastrarSintomaNoMedicamento(sintoma1);

        medicamento2.cadastrarDoencaNoMedicamento(doenca2);
        medicamento2.cadastrarSintomaNoMedicamento(sintoma2);

        medicamento3.cadastrarDoencaNoMedicamento(doenca3);
        medicamento3.cadastrarSintomaNoMedicamento(sintoma3);
        medicamento3.cadastrarSintomaNoMedicamento(sintoma4);
        medicamento3.cadastrarSintomaNoMedicamento(sintoma5);

        try {
            medicamento1.cadastrarSubstancia(substancia2);
            medicamento3.cadastrarSubstancia(substancia1);
            medicamento3.cadastrarSubstancia(substancia2);
        } catch (JaExisteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCadastroSintoma() {

        try {
            sis.cadastrarSintoma(sintoma1);
            sis.cadastrarSintoma(sintoma2);
            sis.cadastrarSintoma(sintoma3);
            sis.cadastrarSintoma(sintoma4);
            sis.cadastrarSintoma(sintoma5);
            sis.cadastrarSintoma(sintoma1);
        } catch (JaExisteException e) {
        }
        try {
            assertEquals(sintoma1, sis.getSintoma(sintoma1.getNome()));
            assertEquals(sintoma2, sis.getSintoma(sintoma2.getNome()));
            assertEquals(sintoma3, sis.getSintoma(sintoma3.getNome()));
            assertEquals(sintoma4, sis.getSintoma(sintoma4.getNome()));
            assertEquals(sintoma5, sis.getSintoma(sintoma5.getNome()));
            assertNotEquals(sintoma5, sis.getSintoma(sintoma1.getNome()));
        } catch (NaoAchouException e) {
        }
    }

    @Test
    public void testCadastroDoenca() {
        try {
            sis.cadastrarDoenca(doenca1);
            sis.cadastrarDoenca(doenca2);
            sis.cadastrarDoenca(doenca3);
            sis.cadastrarDoenca(doenca1);
        } catch (JaExisteException e) {
        }
        try {
            assertEquals(doenca1,sis.getDoenca(doenca1.getNome()));
            assertEquals(doenca2,sis.getDoenca(doenca2.getNome()));
            assertEquals(doenca3,sis.getDoenca(doenca3.getNome()));
            assertEquals(doenca3,sis.getSintoma(doenca1.getNome()));
        } catch (NaoAchouException e) {
        }

    }

    @Test
    public void testPesquisa() throws JaExisteException {

        testCadastroMedicamento();

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
    public void testCadastroMedicamento() {

        //cadastro no SisBula
        try {
            sis.cadastrarMedicamento(medicamento1);
            sis.cadastrarMedicamento(medicamento2);
            sis.cadastrarMedicamento(medicamento3);
        } catch (JaExisteException e) {
            fail("Falhou no teste cadastro de medicamentos");
        }
        assertEquals(true, sis.existeMedicamento(medicamento1));
        assertEquals(true, sis.existeMedicamento(medicamento2));
        assertEquals(true, sis.existeMedicamento(medicamento3));

    }

    @Test(expected = NullPointerException.class)
    public void testCadastroNull() throws NullPointerException, JaExisteException {
        sis.cadastrarMedicamento(null);
    }

    @Test(expected = JaExisteException.class)
    public void testCadastraMedicamentoRepetido() throws JaExisteException {
        //cadastro no SisBula

        sis.cadastrarMedicamento(medicamento1);
        sis.cadastrarMedicamento(medicamento1);

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
        testCadastroMedicamento();
        testCadastroSintoma();
        testCadastroDoenca();
        sis.gravarTodos();
    }

    @Test
    public void testLeitura() throws JaExisteException {
        sis.carregarTodos();

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
        //testes Doenca
        try {
            assertEquals(doenca1,sis.getDoenca(doenca1.getNome()));
            assertEquals(doenca2,sis.getDoenca(doenca2.getNome()));
            assertEquals(doenca3,sis.getDoenca(doenca3.getNome()));
            assertEquals(doenca3,sis.getSintoma(doenca1.getNome()));
        } catch (NaoAchouException e) {
        }
        //teste Sintoma
        try {
            assertEquals(sintoma1, sis.getSintoma(sintoma1.getNome()));
            assertEquals(sintoma2, sis.getSintoma(sintoma2.getNome()));
            assertEquals(sintoma3, sis.getSintoma(sintoma3.getNome()));
            assertEquals(sintoma4, sis.getSintoma(sintoma4.getNome()));
            assertEquals(sintoma5, sis.getSintoma(sintoma5.getNome()));
            assertNotEquals(sintoma5, sis.getSintoma(sintoma1.getNome()));
        } catch (NaoAchouException e) {
        }
    }
}
