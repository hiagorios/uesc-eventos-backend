package br.uesc.eventos.entity;

import br.uesc.eventos.enums.PermissaoEnum;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Permissao extends BaseEntity {

    @Column(unique = true)
    private String key;

    private PermissaoEnum value;

    public Permissao() {

    }

    public Permissao(String key, PermissaoEnum value) {
        this.key = key;
        this.value = value;
    }

    public Permissao(PermissaoEnum permissaoEnum) {
        this.key = permissaoEnum.name();
        this.value = permissaoEnum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PermissaoEnum getValue() {
        return value;
    }

    public void setValue(PermissaoEnum value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permissao permissao = (Permissao) o;

        if (!key.equals(permissao.key)) return false;
        return value.equals(permissao.value);
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
