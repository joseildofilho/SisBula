package entidades;

import java.io.Serializable;

public class Sintoma implements Serializable {

    private String descricao, nome;

    public Sintoma() {
        this("Sem nome");
    }

    public Sintoma(String nome) {
        this(nome, "Sem descrição");
    }

    public Sintoma(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sintoma)) return false;

        Sintoma sintoma = (Sintoma) o;

        return getNome().equals(sintoma.getNome());

    }

    @Override
    public int hashCode() {
        return getNome().hashCode();
    }

    @Override
    public String toString() {
        return "Sintoma{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
