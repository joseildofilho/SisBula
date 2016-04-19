package interfaces.interfaceIO;

import java.io.FileNotFoundException;

/**
 * Created by joseildo on 19/04/16.
 */
public interface FerramentaGravacao <T> {

    void gravarObjeto(T o, String NOME);

    T lerObjeto(String NOME) throws FileNotFoundException;

    void removerObjeto(String NOME) throws FileNotFoundException;
}
