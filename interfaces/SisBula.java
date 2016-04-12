package interfaces;

import interfaces.InterfaceGerente.GerenteMedicamento;

public interface SisBula extends GerenteMedicamento {
    /**
     * Guarda o Gerente em Disco.
     * */
    void gravarGerente();
    /**
     * Carrega o gerente que sera usado no sistema.
     * */
    void carregarGerente();

    /**
     * apaga o gerente
     */
    void deletarGerenteGravado();


}
