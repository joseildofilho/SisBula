package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public Doenca() {
        this("sem nome");
    }

    public Doenca(String nome) {
        this.nome = nome;
        this.sintomas = new HashMap<>();
    }

    public Map<String, Sintoma> getSintomas() {
        return sintomas;
    }

    public void adicionarSintoma(Sintoma s) {
        sintomas.put(nome, s);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
