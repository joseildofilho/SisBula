package interfaces.interfaceIO;

/**
 * Os Objetos que estiverem a ser gravados devem esta implementados com a @Observavel.
 */
public interface Observador {
    /**
     * Inclui algum objecto a que tera seu conteudo gravado
     * */
    void attach(Observavel observavel);
    /**
     * Retira o Objeto da area de gravação
     * */
    void detach(Observavel observavel);
    /**
     * Ordena ao objetos para gravarem seus conteudos
     * */
    void gravar();
    /**
     * ordena ao objetos para carregarem seus conteudos
     * */
    void carregar();
}
