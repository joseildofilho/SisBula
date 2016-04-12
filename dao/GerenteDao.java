package dao;

import gerente.GerenteMedicamentoImpl;
import interfaces.InterfaceGerente.GerenteMedicamento;


public interface GerenteDao {
    void gravarGerente(GerenteMedicamento gm);
    GerenteMedicamento lerGerente();
    void removerGerente();
}
