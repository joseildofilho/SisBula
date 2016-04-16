package interfaces.InterfaceGerente;

import entidades.Sintoma;
import excecoes.JaExisteException;
import excecoes.NaoAchouException;

import java.util.List;

/**
 * Created by joseildo on 12/04/16.
 */
public interface GerenteSintoma {
    /**
     * Cadastra o Sintoma na forma da implementação
     *
     * @param sintoma
     * @throws excecoes.JaExisteException
     */
    void cadastrarSintoma(Sintoma sintoma) throws JaExisteException;

    /**
     * Retorna a Lista de todos os Sintoma cadastrados no sistema
     *
     * @return List , a lista sera retornada vazia caso não exista nada
     */
    List<Sintoma> getTodosSintomas();
    /**
     *Retorna o Sintoma
     *
     * @param sintoma , o nome do sintoma
     * @return Sintoma o sintoma cadastrado
     * @throws excecoes.NaoAchouException
     * */
    Sintoma getSintoma(String sintoma) throws NaoAchouException;
}
