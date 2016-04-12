package fabricas;

import entidades.Sintoma;

public class FabricaSintoma {
    private FabricaSintoma() {
        throw new AssertionError();
    }

    public static Sintoma getSintoma() {
        return new Sintoma();
    }

    public static Sintoma getSintoma(String nome) {
        return new Sintoma(nome);
    }

    public static Sintoma getSintoma(String nome,String descricao) {
        return new Sintoma(nome,descricao);
    }
}
