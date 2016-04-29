package entidades;

import excecoes.JaExisteException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Entidade Doença
 * Classe que sera usada em Medicamente para indicar uma doença,
 * @see Medicamento
 * @see Sintoma
 * */

public class Doenca implements Serializable {

    private String nome;
    private Map<String, Sintoma> sintomas;

    private Map<String, Causa> causas;

    public Doenca() {
        this("sem nome");
    }

    public Doenca(String nome) {
        this.nome = nome;
        this.sintomas = new HashMap<>();
        this.causas =  new HashMap<>();
    }

    public Map<String, Sintoma> getSintomas() {
        return sintomas;
    }

    public void adicionarSintoma(Sintoma s) throws JaExisteException {
        if(sintomas.containsValue(s)) throw new JaExisteException("Ja existe este Sintoma");
        sintomas.put(s.getNome(), s);
    }

    public boolean possuiCausa(String nome) {
        return causas.containsKey(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Causa> getCausas() {
        return causas;
    }

    public void adicionarCausa(Causa causa) throws JaExisteException {
        if(causas.containsValue(causa)) throw new JaExisteException("Ja existe esta causa");
        causas.put(causa.getTipo(),causa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Doenca)) return false;

        Doenca doenca = (Doenca) o;

        return getNome().equals(doenca.getNome());

    }

    @Override
    public String toString() {
        return "Doenca{" +
                "nome='" + nome + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return getNome().hashCode();
    }
}
