package entidades;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Substancia implements Serializable {
    private String nome;

    private Map<String, Medicamento> compoe;

    public Substancia() {
        this("Desconhecida");
    }

    public Substancia(String nome) {
        this.nome = nome;
        compoe = new HashMap<>();
    }

    public Map<String, Medicamento> getCompoe() {
        return compoe;
    }

    public void setCompoe(Map<String, Medicamento> compoe) {
        this.compoe = compoe;
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
        if (!(o instanceof Substancia)) return false;

        Substancia that = (Substancia) o;

        return getNome().equals(that.getNome());

    }

    @Override
    public int hashCode() {
        return getNome().hashCode();
    }
}
