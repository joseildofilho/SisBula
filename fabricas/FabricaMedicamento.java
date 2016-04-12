package fabricas;

import entidades.Fabricante;
import entidades.Medicamento;


public class FabricaMedicamento {
    private FabricaMedicamento() {
        throw new AssertionError();
    }

    public static Medicamento getMedicamento() {
        return new Medicamento();
    }

    public static Medicamento getMedicamento(String nome, Fabricante fab) {
        return new Medicamento(nome,fab);
    }
}
