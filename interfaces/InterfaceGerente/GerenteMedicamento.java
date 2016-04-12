package interfaces.InterfaceGerente;

import entidades.Doenca;
import entidades.Medicamento;
import entidades.Sintoma;
import entidades.Substancia;
import excecoes.JaExisteException;

import java.awt.*;
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
