package fachada;

import IO.Gravador;
import entidades.*;
import excecoes.NaoAchouException;
import fabrica.Fabrica;
import gerente.DaoCausaImpl;
import gerente.GerenteDoencaImpl;
import gerente.GerenteSintomaImpl;
import excecoes.JaExisteException;
import gerente.GerenteMedicamentoImpl;
import interfaces.InterfaceGerente.DaoCausa;
import interfaces.InterfaceGerente.GerenteDoenca;
import interfaces.InterfaceGerente.GerenteMedicamento;
import interfaces.InterfaceGerente.GerenteSintoma;
import interfaces.interfaceIO.Observador;
import interfaces.SisBula;

import java.util.List;

public class SisBulaFacade implements SisBula {

    private GerenteMedicamento gerenteMedicamento;
    private GerenteSintoma gerenteSintoma;
    private GerenteDoenca gerenteDoenca;
    private DaoCausa daoCausa;
    private Observador gravador = Gravador.getInstance();

    public SisBulaFacade() {
        gerenteMedicamento = new GerenteMedicamentoImpl();
        gerenteSintoma = new GerenteSintomaImpl();
        gerenteDoenca = new GerenteDoencaImpl();
        daoCausa = new DaoCausaImpl();
    }

    @Override
    public void cadastrarMedicamento(Medicamento m) throws JaExisteException {
        gerenteMedicamento.cadastrarMedicamento(m);
    }

    public void cadastrarMedicamento(String m) throws JaExisteException {
        gerenteMedicamento.cadastrarMedicamento(m);
    }

    @Override
    public void cadastrarMedicamentoParaDoenca(String nome, String doenca) {

        gerenteMedicamento.pesquisarMedicamento(nome).cadastrarDoencaNoMedicamento(gerenteDoenca.getDoenca(doenca));

    }

    @Override
    public void cadastrarMedicamentoParaSintoma(String medicamento, String sintoma) {
        gerenteMedicamento.pesquisarMedicamento(medicamento).cadastrarSintomaNoMedicamento(gerenteSintoma.getSintoma(sintoma));
    }

    @Override
    public Medicamento pesquisarMedicamento(String m, Fabricante fab) {
        return gerenteMedicamento.pesquisarMedicamento(m, fab);
    }

    @Override
    public Medicamento pesquisarMedicamento(String nome) {
        return gerenteMedicamento.pesquisarMedicamento(nome);
    }

    @Override
    public List<Medicamento> pesquisarMedicamentoParaSintoma(Sintoma i) {
        return gerenteMedicamento.pesquisarMedicamentoParaSintoma(i);
    }

    @Override
    public List<Medicamento>
    pesquisarMedicamentoParaDoenca(Doenca i) {
        return gerenteMedicamento.pesquisarMedicamentoParaDoenca(i);
    }

    @Override
    public List<Medicamento> pesquisaMedicamentosDoFabricante(Fabricante fab) {
        return gerenteMedicamento.pesquisaMedicamentosDoFabricante(fab);
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
    public List<Medicamento> pesquisarMedicamentosPara(String nome) {
        return gerenteMedicamento.pesquisarPara(nome);
    }

    @Override
    public List<Medicamento> getListMedicamento() {
        return gerenteMedicamento.getListMedicamento();
    }

    @Override
    public void cadastrarSintoma(String nome) throws JaExisteException {
        gerenteSintoma.cadastrarSintoma(nome);
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
    public void cadastrarDoenca(String doenca) throws JaExisteException {
        gerenteDoenca.cadastrarDoenca(doenca);
    }

    @Override
    public void cadastrarDoenca(Doenca doenca) throws JaExisteException {
        gerenteDoenca.cadastrarDoenca(doenca);
    }

    @Override
    public List<Doenca> getTodasDoencas() {
        return gerenteDoenca.getTodasDoencas();
    }

    @Override
    public void cadastraSintomaDeDoenca(String doenca, String sintoma) throws JaExisteException {
        Doenca don = gerenteDoenca.getDoenca(doenca);
        Sintoma sin = gerenteSintoma.getSintoma(sintoma);
        don.adicionarSintoma(sin);
    }

    @Override
    public Doenca getDoenca(String nome) {
        return gerenteDoenca.getDoenca(nome);
    }


    @Override
    public void gravarTodos() {
        gravador.gravar();
    }

    @Override
    public void carregarTodos() {
        gravador.carregar();
    }

    @Override
    public void cadastraPossivelCausaDeDoenca(String doenca, String causa) throws JaExisteException {
        Doenca d = gerenteDoenca.getDoenca(doenca);
        Causa c = daoCausa.getCausa(causa);
        d.adicionarCausa(c);
    }

    public List<Doenca> pesquisaDoencasCausadasPor(String causa) {
        return gerenteDoenca.pesquisaDoencasCausadasPor(causa);
    }

    public List<Causa> pesquisaPossiveisCausasDe(String nome) {
        return gerenteDoenca.pesquisaPossiveisCausasDe(nome);
    }
}
