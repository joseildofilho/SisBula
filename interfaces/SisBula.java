package interfaces;

import interfaces.InterfaceGerente.GerenteMedicamento;
import interfaces.InterfaceGerente.GerenteSintoma;

public interface SisBula extends GerenteMedicamento,GerenteSintoma {
    /**
     * Grava todos as entidades de deus gerentes em algo
     * */
    void carregarTodos();

    /**
     * Carrega todos as entidades em seus respectivos gerentes
     * */
    void gravarTodos();
}
