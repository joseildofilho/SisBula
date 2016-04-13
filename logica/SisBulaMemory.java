package logica;

import IO.Gravador;
import excecoes.NaoAchouException;
import gerente.GerenteSintomaImpl;
import interfaces.Dao;
import dao.GerenteDaoImpl;
import entidades.Doenca;
import entidades.Medicamento;
import entidades.Sintoma;
import entidades.Substancia;
import excecoes.JaExisteException;
import gerente.GerenteMedicamentoImpl;
import interfaces.InterfaceGerente.GerenteMedicamento;
import interfaces.InterfaceGerente.GerenteSintoma;
import interfaces.Observador;
import interfaces.SisBula;

import java.util.List;

public class SisBulaMemory implements SisBula {

    private GerenteMedicamento gerenteMedicamento;
    private GerenteSintoma gerenteSintoma;
    private Observador gravador = Gravador.getInstance();

    public SisBulaMemory() {
        gerenteMedicamento = new GerenteMedicamentoImpl();
        gerenteSintoma = new GerenteSintomaImpl();
    }

    @Override
    public void cadastrarMedicamento(Medicamento m) throws JaExisteException {
        gerenteMedicamento.cadastrarMedicamento(m);
    }

    @Override
    public List<Medicamento> pesquisarMedicamentoParaSintoma(Sintoma i) {
        return gerenteMedicamento.pesquisarMedicamentoParaSintoma(i);
    }

    @Override
    public List<Medicamento> pesquisarMedicamentoParaDoenca(Doenca i) {
        return gerenteMedicamento.pesquisarMedicamentoParaDoenca(i);
    }

    @Override
    public void removerMedicamento(Medicamento m) {
        gerenteMedicamento.removerMedicamento(m);
    }

    @Override
    public boolean existeMedicamento(Medicamento m) {
        return gerenteMedicamento.existeMedicamento(m);
    }

    @Override
    public List<Medicamento> pesquisarRemediosComSusbstancia(Substancia s) {
        return gerenteMedicamento.pesquisarRemediosComSusbstancia(s);
    }

    @Override
    public List<Medicamento> pesquisarPorInteracao(Medicamento medicamento) {
        return gerenteMedicamento.pesquisarPorInteracao(medicamento);
    }

    @Override
    public List<Medicamento> getListMedicamento() {
        return gerenteMedicamento.getListMedicamento();
    }

    @Override
    public void cadastrarSintoma(Sintoma sintoma) throws JaExisteException {
        gerenteSintoma.cadastrarSintoma(sintoma);
    }

    @Override
    public List<Sintoma> getTodosSintomas() {
        return gerenteSintoma.getTodosSintomas();
    }

    @Override
    public Sintoma getSintoma(String sintoma) throws NaoAchouException {
        return gerenteSintoma.getSintoma(sintoma);
    }

    @Override
    public void gravarTodos() {
        gravador.gravarse();
    }

    @Override
    public void carregarTodos() {
        gravador.carregase();
    }
}
