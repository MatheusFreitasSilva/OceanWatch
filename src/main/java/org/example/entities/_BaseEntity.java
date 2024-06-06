package org.example.entities;

/**
 * Classe abstrata _BaseEntity que serve como base para outras entidades.
 * Possui um identificador único (id) e métodos para obter e definir este identificador.
 * Inclui um método toString() para representação em forma de string do objeto.
 */
public abstract class _BaseEntity {
    private int id;

    public _BaseEntity(){}

    public _BaseEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "_BaseEntity{" +
                "id=" + id +
                '}';
    }
}
