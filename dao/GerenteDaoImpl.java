package dao;

import IO.IOTool;
import interfaces.InterfaceGerente.GerenteMedicamento;

import java.io.FileNotFoundException;


public class GerenteDaoImpl implements GerenteDao {

    private IOTool<GerenteMedicamento> io = new IOTool();
    private String nomeArquivo;

    public GerenteDaoImpl(String nome) {
        this.nomeArquivo = nome;
    }

    @Override
    public void gravarGerente(GerenteMedicamento gm) {
        io.gravarObjeto(gm, nomeArquivo);
    }

    @Override
    public GerenteMedicamento lerGerente() {
        try {
            return io.lerObjeto(nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo não existe");
        }
        return null;
    }

    @Override
    public void removerGerente() {
        try {
            io.removerObjeto(nomeArquivo);
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo ja não mais existia");
        }
    }
}
