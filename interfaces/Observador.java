package interfaces;

/**
 * Created by joseildo on 13/04/16.
 */
public interface Observador {
    void attach(Observavel observavel);
    void detach(Observavel observavel);
    void gravarse();
    void carregase();
}
