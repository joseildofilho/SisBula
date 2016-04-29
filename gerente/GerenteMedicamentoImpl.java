package gerente;

import IO.Gravador;
import IO.IOTool;
import entidades.*;
import excecoes.JaExisteException;
import fabrica.Fabrica;
import interfaces.InterfaceGerente.GerenteMedicamento;
import interfaces.interfaceIO.FerramentaGravacao;
import interfaces.interfaceIO.Observavel;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;

public class GerenteMedicamentoImpl implements GerenteMedicamento, Observavel {

    private Map<String, Medicamento> medicamentoMap;

    public GerenteMedicamentoImpl() {
        medicamentoMap = new HashMap<>();
        Gravador.getInstance().attach(this);
    }

    @Override
    public void cadastrarMedicamento(Medicamento m) throws JaExisteException, NullPointerException {
        if (existeMedicamento(m)) throw new JaExisteException("Medicamento ja existe");
        medicamentoMap.put(m.keyMap(), m);
    }

    @Override
    public void cadastrarMedicamento(String m) throws JaExisteException {
        Medicamento med = Fabrica.getMedicamento();
        med.setNome(m);
        cadastrarMedicamento(med);
    }

    @Override
    public List<Medicamento> pesquisarPara(String nome) {
        List<Medicamento> temp = new ArrayList<>();

        for(Medicamento m: medicamentoMap.values()) {
            if(m.indicadoParaSintoma(nome) || m.indicadoParaDoenca(nome)) temp.add(m);
        }
        return temp;
    }

    @Override
    public Medicamento pesquisarMedicamento(String nome) {
        for(Medicamento m : medicamentoMap.values()) {
            if(m.getNome().equals(nome)) return m;
        }
        return null;
    }

    @Override
    public Medicamento pesquisarMedicamento(String m, Fabricante fab) {
        return medicamentoMap.get(m+fab.name());
    }


    @Override
    public List<Medicamento> pesquisarMedicamentoParaSintoma(Sintoma i) {
        assert i != null;
        List<Medicamento> list = new ArrayList<>();
        for (Medicamento m : medicamentoMap.values()) {
            if (m.indicadoParaSintoma(i)) list.add(m);
        }
        return list;
    }

    @Override
    public List<Medicamento> pesquisarMedicamentoParaDoenca(Doenca i) {
        assert i != null;
        List<Medicamento> list = new ArrayList<>();
        for (Medicamento m : medicamentoMap.values()) {
            if (m.indicadoParaDoenca(i)) list.add(m);
        }
        return list;
    }

    @Override
    public List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab) {
        List<Medicamento> temp = new ArrayList<>();
        for(Medicamento m:medicamentoMap.values()) {
            if(m.getFabricante().name().equals(fab.name())) temp.add(m);
        }
        return temp;
    }

    @Override
    public void removerMedicamento(Medicamento m) {
        if (existeMedicamento(m))
            medicamentoMap.remove(m.keyMap());
    }

    public boolean existeMedicamento(Medicamento m) {
        return medicamentoMap.containsValue(m);
    }

    @Override
    public List<Medicamento> pesquisarRemediosComSusbstancia(Substancia s) {
        Iterator i = s.getCompoe().values().iterator();
        List<Medicamento> med = new ArrayList<>();
        while (i.hasNext()) {
            med.add((Medicamento) i.next());
        }
        return med;
    }

    @Override
    public List<Medicamento> pesquisarPorInteracao(Medicamento medicamento) {
        Iterator i = medicamento.getInteracoesMedicamentosas().values().iterator();
        List<Medicamento> med = new ArrayList<>();
        while (i.hasNext())
            med.add((Medicamento) i.next());

        return med;
    }

    @Override
    public List<Medicamento> getListMedicamento() {
        List<Medicamento> list = new ArrayList<>();
        for(Medicamento m: medicamentoMap.values()) {
            list.add(m);
        }
        return list;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GerenteMedicamentoImpl{");
        sb.append("medicamentoMap=").append(medicamentoMap);
        for(Medicamento m : medicamentoMap.values()) {
            sb.append(m.toString());
        }
        sb.append('}');
        return sb.toString();
    }

    private final static String NOME_ARQUIVO = "GerenteMedicamento.sisB";
    private FerramentaGravacao<Map<String, Medicamento>> io = new IOTool<>();

    @Override
    public void gravarse() {
        io.gravarObjeto(medicamentoMap,NOME_ARQUIVO);
    }

    @Override
    public void carregase() {
        try {
            medicamentoMap = io.lerObjeto(NOME_ARQUIVO);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }
}
