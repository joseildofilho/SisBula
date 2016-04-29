package entidades;

/**
 * Created by joseildo on 26/04/16.
 */
public class Causa {

    private String tipo;

    public Causa() {
        this.tipo = "Sem tipo";
    }

    public Causa(final String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Causa)) return false;

        Causa causa = (Causa) o;

        return getTipo().equals(causa.getTipo());

    }

    @Override
    public int hashCode() {
        return getTipo().hashCode();
    }
}
