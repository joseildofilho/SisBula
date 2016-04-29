package gerente;

import IO.Gravador;
import entidades.Causa;
import excecoes.JaExisteException;
import fabrica.Fabrica;
import interfaces.InterfaceGerente.DaoCausa;
import interfaces.interfaceIO.Observavel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joseildo on 26/04/16.
 */
public class DaoCausaImpl implements DaoCausa, Observavel {

    private Map<String, Causa> causaMap;

    public DaoCausaImpl() {
        causaMap = new HashMap<>();
        Gravador.getInstance().attach(this);
    }

    @Override
    public void cadastrarCausa(Causa causa) throws JaExisteException {
        if (causaMap.containsKey(causa)) throw new JaExisteException("Ja existe esta Causa");
        causaMap.put(causa.getTipo(), causa);
    }

    @Override
    public List<Causa> getTodasCausas() {
        List<Causa> temp = new ArrayList<>();
        for (Causa c : causaMap.values()) temp.add(c);
        return temp;
    }

    @Override
    public Causa getCausa(String causa) {
        if (causaMap.containsKey(causa))
            return causaMap.get(causa);
        Causa c = Fabrica.getCausa(causa);
        causaMap.put(c.getTipo(),c);
        return c;
    }

    @Override
    public void gravarse() {

    }

    @Override
    public void carregase() {

    }
}
