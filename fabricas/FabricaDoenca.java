package fabricas;

import entidades.Doenca;

public class FabricaDoenca {

    private FabricaDoenca() {
        throw new AssertionError();
    }

    public static Doenca getDoenca() {
        return new Doenca();
    }

    public static Doenca getDoenca(String nome) {
        return new Doenca(nome);
    }
}
