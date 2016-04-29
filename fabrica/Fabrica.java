package fabrica;

import entidades.*;

/**
 * Created by joseildo on 19/04/16.
 */
public class Fabrica {

    private Fabrica() {
        throw new AssertionError();
    }

    public static Doenca getDoenca() {
        return new Doenca();
    }

    public static Medicamento getMedicamento() {
        return new Medicamento();
    }

    public static Medicamento getMedicamento(String nome, Fabricante fab) {
        return new Medicamento(nome, fab);
    }

    public static Doenca getDoenca(String nome) {
        return new Doenca(nome);
    }

    public static Sintoma getSintoma() {
        return new Sintoma();
    }

    public static Sintoma getSintoma(String nome) {
        return new Sintoma(nome);
    }

    public static Sintoma getSintoma(String nome, String descricao) {
        return new Sintoma(nome, descricao);
    }

    public static Substancia getsubstancia() {
        return new Substancia();
    }

    public static Substancia getSubstancia(String nome) {
        return new Substancia(nome);
    }

    public static Causa getCausa() {
        return new Causa();
    }

    public static Causa getCausa(String tipo) {
        return new Causa(tipo);
    }
}
