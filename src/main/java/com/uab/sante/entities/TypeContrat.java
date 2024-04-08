package com.uab.sante.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TypeContrat")
public class TypeContrat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Code")
    private String code;
    @Column(name = "Libelle")
    private String libelle;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    public TypeContrat(String code, String libelle, boolean isDeleted) {
        this.code = code;
        this.libelle = libelle;
        this.isDeleted = isDeleted;
    }



    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeContrat typeContrat = (TypeContrat) o;
        return isDeleted == typeContrat.isDeleted &&
                Objects.equals(id, typeContrat.id) &&
                Objects.equals(code, typeContrat.code) &&
                Objects.equals(libelle, typeContrat.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, libelle, isDeleted);
    }

    public TypeContrat() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
