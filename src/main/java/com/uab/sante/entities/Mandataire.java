package com.uab.sante.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Mandataire")
public class Mandataire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Capital_Assurer")
    private Double capitalAssurer;
    @Column(name = "Prime_Garantie_Deces_ou_IAD")
    private Double primeGarantieDecesOuIAD;
    @Column(name = "Prime_Garantie_Perte_Emploi")
    private Double primeGarantiePerteEmploi;
    @Column(name = "Prime_Totale")
    private Double primeTotale;
    @Column(name = "Numero_De_Compte_UAB_Vie")
    private int numeroDeCompteUABVie;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    public Mandataire(Double capitalAssurer, Double primeGarantieDecesOuIAD, Double primeGarantiePerteEmploi, Double primeTotale, int numeroDeCompteUABVie, boolean isDeleted) {
        this.capitalAssurer = capitalAssurer;
        this.primeGarantieDecesOuIAD = primeGarantieDecesOuIAD;
        this.primeGarantiePerteEmploi = primeGarantiePerteEmploi;
        this.primeTotale = primeTotale;
        this.numeroDeCompteUABVie = numeroDeCompteUABVie;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public Double getCapitalAssurer() {
        return capitalAssurer;
    }

    public Double getPrimeGarantieDecesOuIAD() {
        return primeGarantieDecesOuIAD;
    }

    public Double getPrimeGarantiePerteEmploi() {
        return primeGarantiePerteEmploi;
    }

    public Double getPrimeTotale() {
        return primeTotale;
    }

    public Number getNumeroDeCompteUABVie() {
        return numeroDeCompteUABVie;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCapitalAssurer(Double capitalAssurer) {
        this.capitalAssurer = capitalAssurer;
    }

    public void setPrimeGarantieDecesOuIAD(Double primeGarantieDecesOuIAD) {
        this.primeGarantieDecesOuIAD = primeGarantieDecesOuIAD;
    }

    public void setPrimeGarantiePerteEmploi(Double primeGarantiePerteEmploi) {
        this.primeGarantiePerteEmploi = primeGarantiePerteEmploi;
    }

    public void setPrimeTotale(Double primeTotale) {
        this.primeTotale = primeTotale;
    }


    public void setNumeroDeCompteUABVie(Number numeroDeCompteUABVie) {
        this.numeroDeCompteUABVie = (int) numeroDeCompteUABVie;

    }
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mandataire mandataire = (Mandataire) o;
        return isDeleted == mandataire.isDeleted &&
                Objects.equals(id, mandataire.id) &&
                Objects.equals(capitalAssurer, mandataire.capitalAssurer) &&
                Objects.equals(primeGarantieDecesOuIAD, mandataire.primeGarantieDecesOuIAD)&&
                Objects.equals(primeGarantiePerteEmploi, mandataire.primeGarantiePerteEmploi) &&
                Objects.equals(primeTotale, mandataire.primeTotale) &&
                Objects.equals(numeroDeCompteUABVie, mandataire.numeroDeCompteUABVie);


    }
    @Override
    public int hashCode() {
        return Objects.hash(id, capitalAssurer, primeGarantieDecesOuIAD, primeGarantiePerteEmploi, primeTotale, numeroDeCompteUABVie, isDeleted);

    }
    public Mandataire() {super();}


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

