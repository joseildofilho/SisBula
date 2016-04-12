package fabricas;

import entidades.Substancia;

public class FabricaSubstancia {

    private FabricaSubstancia() {
        throw new AssertionError();
    }

    public static Substancia getsubstancia() {
        return new Substancia();
    }

    public static Substancia getSubstancia(String nome) {
        return new Substancia(nome);
    }
}
