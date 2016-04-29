package interfaces.InterfaceGerente;

import entidades.Causa;
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
     * Cadastrar a doença pela String o metodo cria a doenca
     *
     * @param doenca nome da doença para ser cadastrada
     * @throws JaExisteException caso a doença ja exista
     * */
    void cadastrarDoenca(String doenca) throws JaExisteException;

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
    Doenca getDoenca(String nome);

    /**
     * Retorna Todas a doenças com a mesma causa
     *
     * @param nome do tipo da causa
     * @return List com as causas
     * */
    List<Doenca> pesquisaDoencasCausadasPor(String nome);

    List<Causa> pesquisaPossiveisCausasDe(String nome);
}
