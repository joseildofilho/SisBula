package gerente;

import IO.Gravador;
import IO.IOTool;
import entidades.Sintoma;
import excecoes.JaExisteException;
import excecoes.NaoAchouException;
import interfaces.interfaceIO.FerramentaGravacao;
import interfaces.InterfaceGerente.GerenteSintoma;
import interfaces.interfaceIO.Observavel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joseildo on 12/04/16.
 */
public class GerenteSintomaImpl implements GerenteSintoma, Observavel {
    private Map<String, Sintoma> sintomaMap;

    public GerenteSintomaImpl() {
        sintomaMap = new HashMap<>();
        Gravador.getInstance().attach(this);
    }


    @Override
    public void cadastrarSintoma(Sintoma sintoma) throws JaExisteException {
        if (sintomaMap.containsValue(sintoma)) throw new JaExisteException("Ja existe este Sintoma");
        sintomaMap.put(sintoma.getNome(), sintoma);
        System.out.println("Sintoma Cadastrada: "+sintoma+" Descrição: "+sintoma.getDescricao());
    }

    @Override
    public List<Sintoma> getTodosSintomas() {
        List<Sintoma> temp = new ArrayList<>();
        for (Sintoma s : sintomaMap.values()) temp.add(s);
        return temp;
    }

    @Override
    public Sintoma getSintoma(String sintoma) throws NaoAchouException {
        if (!sintomaMap.containsKey(sintoma)) throw new NaoAchouException();
        return sintomaMap.get(sintoma);
    }

    private final static String NOME_ARQUIVO = "GerenteSintoma.sisB";
    private FerramentaGravacao<Map<String, Sintoma>> io = new IOTool<>();
    @Override
    public void gravarse() {
        io.gravarObjeto(sintomaMap,NOME_ARQUIVO);
    }

    @Override
    public void carregase() {
        try {
            sintomaMap = io.lerObjeto(NOME_ARQUIVO);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
}
