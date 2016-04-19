package IO;

import interfaces.interfaceIO.Observador;
import interfaces.interfaceIO.Observavel;

import java.util.Vector;

/**
 * Created by joseildo on 13/04/16.
 */
public class Gravador implements Observador {

    private Vector<Observavel> observaveis;
    private static Gravador gravador = new Gravador();

    private Gravador() {
        this.observaveis = new Vector<>();

    }

    public static Gravador getInstance() {
        return gravador;
    }

    @Override
    public void attach(Observavel observavel) {
        assert observavel != null;
        this.observaveis.add(observavel);
    }

    @Override
    public void detach(Observavel observavel) {
        assert observavel != null;
        this.observaveis.remove(observavel);
    }

    @Override
    public void gravar() {
        for (Observavel o : observaveis) {
            o.gravarse();
        }
    }

    @Override
    public void carregar() {
        for (Observavel o : observaveis) {
            o.carregase();
        }
    }
}
