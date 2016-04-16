package interfaces;

import entidades.Doenca;
import entidades.Medicamento;
import entidades.Sintoma;
import entidades.Substancia;
import excecoes.JaExisteException;
import excecoes.NaoAchouException;
import interfaces.InterfaceGerente.GerenteMedicamento;
import interfaces.InterfaceGerente.GerenteSintoma;

import java.util.List;

public interface SisBula {
    void cadastrarMedicamento(Medicamento m) throws JaExisteException;

    List<Medicamento> pesquisarMedicamentoParaSintoma(Sintoma i);

    List<Medicamento> pesquisarMedicamentoParaDoenca(Doenca i);

    void removerMedicamento(Medicamento m);

    boolean existeMedicamento(Medicamento m);

    List<Medicamento> pesquisarRemediosComSusbstancia(Substancia s);

    List<Medicamento> pesquisarPorInteracao(Medicamento medicamento);

    List<Medicamento> getListMedicamento();

    void cadastrarSintoma(Sintoma sintoma) throws JaExisteException;

    List<Sintoma> getTodosSintomas();

    Sintoma getSintoma(String sintoma) throws NaoAchouException;

    void cadastrarDoenca(Doenca doenca) throws JaExisteException;

    List<Doenca> getTodasDoencas();

    Doenca getDoenca(String nome) throws NaoAchouException;

    /**
     * Grava todos as entidades de deus gerentes em algo
     * */
    void carregarTodos();

    /**
     * Carrega todos as entidades em seus respectivos gerentes
     * */
    void gravarTodos();
}
