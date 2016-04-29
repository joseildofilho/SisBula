package interfaces;

import entidades.*;
import excecoes.JaExisteException;
import excecoes.NaoAchouException;

import java.util.List;

public interface SisBula {

    void cadastrarMedicamento(Medicamento m) throws JaExisteException;

    void cadastrarMedicamento(String m) throws JaExisteException;

    void cadastrarMedicamentoParaDoenca(String nome,String doenca);

    void cadastrarMedicamentoParaSintoma(String medicamento, String sintoma);

    Medicamento pesquisarMedicamento(String m, Fabricante fab);

    Medicamento pesquisarMedicamento(String nome);

    List<Medicamento> pesquisarMedicamentoParaSintoma(Sintoma i);

    List<Medicamento> pesquisarMedicamentoParaDoenca(Doenca i);

    List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab);

    void removerMedicamento(Medicamento m);

    boolean existeMedicamento(Medicamento m);

    List<Medicamento> pesquisarRemediosComSusbstancia(Substancia s);

    List<Medicamento> pesquisarPorInteracao(Medicamento medicamento);

    public List<Medicamento> pesquisarMedicamentosPara(String nome);

    List<Medicamento> getListMedicamento();

    void cadastrarSintoma(String nome) throws JaExisteException;

    void cadastrarSintoma(Sintoma sintoma) throws JaExisteException;

    List<Sintoma> getTodosSintomas();

    Sintoma getSintoma(String sintoma) throws NaoAchouException;

    void cadastrarDoenca(String doenca) throws JaExisteException;

    void cadastrarDoenca(Doenca doenca) throws JaExisteException;

    List<Doenca> getTodasDoencas();

    void cadastraSintomaDeDoenca(String doenca, String sintoma) throws JaExisteException;

    Doenca getDoenca(String nome);

    /**
     * Grava todos as entidades de deus gerentes em algo
     */
    void carregarTodos();

    /**
     * Carrega todos as entidades em seus respectivos gerentes
     */
    void gravarTodos();

    void cadastraPossivelCausaDeDoenca(String doenca, String causa) throws JaExisteException;
}
