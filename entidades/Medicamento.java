package entidades;

import excecoes.JaExisteException;

import java.io.Serializable;
import java.util.*;

/**
 * Entidade principal do sistema, recebe
 * */

public class Medicamento implements Serializable {

    private String nome;
    private Map<String, Medicamento> interacoesMedicamentosas;
    private Map<String, Sintoma> indicadoSintoma;
    private Map<String, Doenca> indicadoDoenca;
    private Map<String, Substancia> substancias;
    private Fabricante fabricante;

    public Medicamento() {
        this("sem nome", Fabricante.SemFabricante);
    }

    public Medicamento(String nome, Fabricante fab) {
        this.nome = nome;
        this.fabricante = fab;
        this.indicadoDoenca = new HashMap<>();
        this.indicadoSintoma = new HashMap<>();
        this.interacoesMedicamentosas = new HashMap<>();
        this.substancias = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "nome='" + nome + '\'' +
                '}';
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Medicamento> getInteracoesMedicamentosas() {
        return interacoesMedicamentosas;
    }

    public void cadastrarInteracao(Medicamento m) {
        if (interacoesMedicamentosas.containsKey(m.keyMap())) throw new IllegalArgumentException("ele ja interage com este medicamento");
        interacoesMedicamentosas.put(m.keyMap(), m);
        m.interacoesMedicamentosas.put(this.keyMap(), this);
    }

    public boolean indicadoParaDoenca(Doenca i) {
        return indicadoDoenca.containsValue(i);
    }

    public boolean indicadoParaSintoma(Sintoma i) {
        return indicadoSintoma.containsValue(i);
    }

    public boolean ECompostoPor(Substancia s) {
        return substancias.containsValue(s);
    }

    public void cadastrarDoencaNoMedicamento(Doenca i) {
        if (indicadoParaDoenca(i)) throw new IllegalArgumentException("ja existe esta Doença");
        indicadoDoenca.put(i.getNome(), i);
    }

    public void cadastrarSintomaNoMedicamento(Sintoma i) {
        if (indicadoParaSintoma(i)) throw new IllegalArgumentException("ja existe este Sintoma");
        indicadoSintoma.put(i.getNome(), i);
    }

    public String keyMap() {
        return getNome() + fabricante.name();
    }

    public void cadastrarSubstancia(Substancia s) throws JaExisteException{
        if(ECompostoPor(s)) throw new JaExisteException("Ja existe essa Substancia");
        substancias.put(s.getNome(),s);
        s.getCompoe().put(this.keyMap(),this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicamento)) return false;

        Medicamento that = (Medicamento) o;

        if (!getNome().equals(that.getNome())) return false;
        return fabricante == that.fabricante;

    }

    @Override
    public int hashCode() {
        int result = getNome().hashCode();
        result = 31 * result + fabricante.hashCode();
        return result;
    }
}
