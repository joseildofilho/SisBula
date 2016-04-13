package interfaces;

import interfaces.InterfaceGerente.GerenteMedicamento;

/**
 * Interface que define O comportamento do dao que gerecia o IO do gerente
 * */
public interface Dao {
    /**
     * Grava o gerente
     * @param gm Gerente a ser processado, @NotNUll
     * */
    void gravarGerente(GerenteMedicamento gm);
    /**
     * Ler o gerente Gravado
     * @return Retorna o gerente armazenado || null
     * */
    GerenteMedicamento lerGerente();
    /**
     * Apaga o gerente da memoria
     * */
    void removerGerente();
}
