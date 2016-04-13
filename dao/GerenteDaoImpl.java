package dao;

import IO.IOTool;
import interfaces.Dao;
import interfaces.InterfaceGerente.GerenteMedicamento;

import java.io.FileNotFoundException;

/**
 * Implementação da interface GerenteDao faz o armazenamento do gerente em arquivo
 * apartir da Serializable, utilizando uma classe chamada IOTool para realizar o processo de Grvação
 * basicamente esta classe serve como uma fachada.
 * */
public class GerenteDaoImpl implements Dao {

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
