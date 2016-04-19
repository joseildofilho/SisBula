package IO;

import interfaces.interfaceIO.FerramentaGravacao;

import java.io.*;

public class IOTool <T> implements FerramentaGravacao <T>{

    private boolean exists(final String NOME) {
        return new File(NOME).exists();
    }

    @Override
    public void gravarObjeto(T o, final String NOME) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME))) {
            oos.writeObject(o);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T lerObjeto(final String NOME) throws FileNotFoundException {
        if (!exists(NOME)) throw new FileNotFoundException();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void removerObjeto(final String NOME) throws FileNotFoundException {
        if(!exists(NOME)) throw new FileNotFoundException();
        new File(NOME).delete();
    }


}
