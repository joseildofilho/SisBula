package gerente;

import IO.Gravador;
import IO.IOTool;
import entidades.Causa;
import entidades.Doenca;
import excecoes.JaExisteException;
import excecoes.NaoAchouException;
import fabrica.Fabrica;
import gerente.io.GerenteGravacaoImpl;
import interfaces.interfaceIO.FerramentaGravacao;
import interfaces.InterfaceGerente.GerenteDoenca;
import interfaces.interfaceIO.Observavel;

import java.io.FileNotFoundException;
import java.util.*;

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
        System.out.println("Doenca cadastrada: " + doenca.getNome());
    }

    @Override
    public void cadastrarDoenca(String doenca) throws JaExisteException {
        Doenca don = Fabrica.getDoenca(doenca);
        cadastrarDoenca(don);
    }


    @Override
    public List<Doenca> getTodasDoencas() {
        List<Doenca> temp = new Vector<>();
        for (Doenca d : doencaMap.values()) temp.add(d);
        return temp;
    }

    @Override
    public Doenca getDoenca(String nome) {

        return doencaMap.get(nome);
    }

    @Override
    public List<Doenca> pesquisaDoencasCausadasPor(String nome) {
        List<Doenca> temp = new ArrayList<>();
        for (Doenca d : doencaMap.values()) {
            if (d.possuiCausa(nome)) temp.add(d);
        }
        return temp;
    }

    @Override
    public List<Causa> pesquisaPossiveisCausasDe(String nome) {
        List<Causa> temp = new ArrayList<>();
        for (Causa c : getDoenca(nome).getCausas().values()) {
            temp.add(c);
        }
        return temp;
    }

    //TODO qual das 2 opções é a melhor ?

    private FerramentaGravacao<Map<String, Doenca>> io = new IOTool();
    private static final String NOME_ARQUIVO = "GerenteDoenca.sisB";

    private GerenteGravacaoImpl<Map<String, Doenca>> gerenteGravacao = new GerenteGravacaoImpl<>(NOME_ARQUIVO);

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
