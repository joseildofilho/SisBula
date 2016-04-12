package logica;

import dao.GerenteDaoImpl;
import entidades.Doenca;
import entidades.Medicamento;
import entidades.Sintoma;
import entidades.Substancia;
import excecoes.JaExisteException;
import gerente.GerenteMedicamentoImpl;
import interfaces.InterfaceGerente.GerenteMedicamento;
import interfaces.SisBula;

import java.io.FileNotFoundException;
import java.util.List;

public class SisBulaMemory implements SisBula {

    GerenteMedicamento gerenteMedicamento;
    GerenteDaoImpl gdi = new GerenteDaoImpl("gerente.sisB");

    public SisBulaMemory() {
        gerenteMedicamento = new GerenteMedicamentoImpl();
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
    public void gravarGerente() {
        gdi.gravarGerente(gerenteMedicamento);
    }

    @Override
    public void carregarGerente() {
        gerenteMedicamento = gdi.lerGerente();
        System.out.println(gerenteMedicamento.toString());
    }

    @Override
    public void deletarGerenteGravado() {
        gdi.removerGerente();
    }


}
