package IO;

import entidades.Doenca;

import java.io.*;
import java.util.Map;

public class IOTool <T>{

    private boolean exists(final String NOME) {
        return new File(NOME).exists();
    }

    public void gravarObjeto(T o,final String NOME) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME))) {
            oos.writeObject(o);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T lerObjeto(final String NOME) throws FileNotFoundException {
        if (!exists(NOME)) throw new FileNotFoundException();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removerObjeto(final String NOME) throws FileNotFoundException {
        if(!exists(NOME)) throw new FileNotFoundException();
        new File(NOME).delete();
    }


}
