package gerente;

import IO.Gravador;
import IO.IOTool;
import entidades.Doenca;
import excecoes.JaExisteException;
import excecoes.NaoAchouException;
import interfaces.InterfaceGerente.GerenteDoenca;
import interfaces.Observador;
import interfaces.Observavel;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by joseildo on 14/04/16.
 */
public class GerenteDoencaImpl implements GerenteDoenca, Observavel {

    private Map<String, Doenca> doencaMap;

    public GerenteDoencaImpl() {
        Gravador.getInstance().attach(this);
        doencaMap = new HashMap<>();
    }

    @Override
    public void cadastrarDoenca(Doenca doenca) throws JaExisteException {
        if (doencaMap.containsValue(doenca)) throw new JaExisteException("Ja existe esta doença");
        doencaMap.put(doenca.getNome(), doenca);
        System.out.println("Doenca cadastrada: "+doenca.getNome());
    }

    @Override
    public List<Doenca> getTodasDoencas() {
        List<Doenca> temp = new Vector<>();
        for (Doenca d : doencaMap.values()) temp.add(d);
        return temp;
    }

    @Override
    public Doenca getDoenca(String nome) throws NaoAchouException {
        if (!doencaMap.containsKey(nome)) throw new NaoAchouException();
        return doencaMap.get(nome);
    }


    private IOTool<Map<String, Doenca>> io = new IOTool();
    private static final String NOME_ARQUIVO = "GerenteDoenca.sisB";

    @Override
    public void gravarse() {
        io.gravarObjeto(doencaMap, NOME_ARQUIVO);
    }

    @Override
    public void carregase() {
        try {
            doencaMap = io.lerObjeto(NOME_ARQUIVO);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
}
