package interfaces.InterfaceGerente;

import entidades.*;
import excecoes.JaExisteException;
import fabrica.Fabrica;

import java.io.Serializable;
import java.util.List;

/**
 * O Gerente Medicamento cuidara das manipulaçoes com o Medicamento.
 * @Author Joseildo Filho
 */
public interface GerenteMedicamento extends Serializable{
    /**
    * Cadastra no sistema um Medicamento.
    * @param m  Medicamento a ser cadastrado
    * @throws JaExisteException caso o medicamento ja exista
    * */
    void cadastrarMedicamento(Medicamento m) throws JaExisteException;
    /**
     * Cria o medicamento e o cadastra caso não exista no sistema
     * @param m Nome do medicamento a ser criado
     * @throws JaExisteException caso o medicamento ja exista
     * */
    void cadastrarMedicamento(String m) throws JaExisteException;

    /**
     * Pesquisa medicamento para doença ou para Sintoma
     * @param nome doença ou sintoma
     * @return List de medicamentos para os casos
     * */
    List<Medicamento> pesquisarPara(String nome);

    /**
     * Pesquisa o medicamento pelo nome
     *
     * @param nome nome do cmedicamento a ser pesquisado
     * @throws JaExisteException
     * */
    Medicamento pesquisarMedicamento(String nome);

    /**
     * Pesquisa o medicamento pelo nome e pelo fabricante
     * @param m Nome do Medicamento
     * @param fab Fabricante do medicamento
     * @return medicamento pesquisa || null caso não tenha nada
     * */
    Medicamento pesquisarMedicamento(String m, Fabricante fab);

    /**
    * Pesquisa um Medicamento no sistema para o sintoma.
    * @param i o Medicamento a ser pesquisado
    * @return List Null se não houver nenhum item na pesquisa
    * */
    List<Medicamento> pesquisarMedicamentoParaSintoma(Sintoma i);

    /**
     * Pesquisa um Medicamento no sistema para o doenca.
     * @param i o Medicamento a ser pesquisado
     * @return List Null se não houver nenhum item na pesquisa
     * */
    List<Medicamento> pesquisarMedicamentoParaDoenca(Doenca i);

    /**
     * Pesquisa todas os Medicamentos pelo fabricante
     * @param fab o Fabricante para ser pesquisado
     * @return List de Medicamentos
     * */
    List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab);

    /**
     * Remove o Medicamento do sistema.
     * @param m Medicamento para ser Removido
     */
    void removerMedicamento(Medicamento m);

    /**
     * Verifica se existe Medicamento.
     * @param m Medicamento testado
     * @return <tt>true</tt> caso exista
     * */
    boolean existeMedicamento(Medicamento m);

    /**
     * Pesquisa medicamentos com a mesma susbtancia
     * @param s Susbtancia
     * @return List de todos os remedios e null para nenhum encontrado
     * */
    List<Medicamento> pesquisarRemediosComSusbstancia(Substancia s);

    /**
     * Pesquisa todos os medicamentos que interagem com o medicamento
     * @param medicamento medicamento que sera pesquisado
     * @return List de medicamentos que interagem
     * */
    List<Medicamento> pesquisarPorInteracao(Medicamento medicamento);

    /**
     * Retorna uma lista de Medicamentos
     * @return List de Medicamento
     * */
    List<Medicamento> getListMedicamento();
}
