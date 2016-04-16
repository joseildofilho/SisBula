package interfaces.InterfaceGerente;

import entidades.Doenca;
import excecoes.JaExisteException;
import excecoes.NaoAchouException;

import java.util.List;

/**
 * Created by joseildo on 14/04/16.
 */
public interface GerenteDoenca {
    /**
     * Cadastrar doenca dentro das estruturas de com ela for implementada
     *
     * @param doenca sera subentendido que o parametro não sera nulo
     * @throws excecoes.JaExisteException caso o valor ja esteja cadastrado
     * */
    void cadastrarDoenca(Doenca doenca) throws JaExisteException;
    /**
     * Retorna todas as intancias cadastradas do sistema
     *
     * @return List
     * */
    List<Doenca> getTodasDoencas();
    /**
     * Retorna a doença requesitada
     *
     * @return Doenca
     * @throws excecoes.NaoAchouException
     * */
    Doenca getDoenca(String nome) throws NaoAchouException;
}
