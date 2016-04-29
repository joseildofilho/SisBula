package interfaces.InterfaceGerente;

import entidades.Causa;
import excecoes.JaExisteException;

import java.util.List;

/**
 * Created by joseildo on 26/04/16.
 */
public interface DaoCausa {
    /**
     * Devera cadastrar a causa conforme a estrategia adotada
     *
     * @param causa causa a ser cadastrada.
     * @throws JaExisteException
     */
    void cadastrarCausa(Causa causa) throws JaExisteException;

    /**
     * Retorna todas as Cuasas cadastradas
     *
     * @return List com todas as causas
     */
    List<Causa> getTodasCausas();

    /**
     * Recupera a Causa pesquisada
     *
     * @param causa nome da causa
     * @return Causa pesquisada
     */
    Causa getCausa(String causa);

    /**
     * Pesquisa
     * */
}
