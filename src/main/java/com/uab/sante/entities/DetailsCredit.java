package com.uab.sante.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "DatailsCredit")
public class DetailsCredit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Montant_credit_assurer")
    private Double montantCreditAssurer;
    @Column(name = "Montant_credit_decouvert")
    private Double montantCreditDecouvert;
    @Column(name = "Nombre_de_remboursement")
    private int nombreDeRemboursement;
    @Column(name = "Montant_des_termes")
    private Double montantDesTermes;
    @Column(name = "Numero_compte_client")
    private int numeroCompteClient;
    @Column(name = "Duree_totale_credit")
    private int dureeTotaleCredit;
    @Column(name = "Differer_amortissement")
    private int differerAmortissement;
    @Column(name = "Date_premier_remboursement_terme")
    private String datePremierRemboursementTerme;
    @Column(name = "Date_effet")
    private String dateEffet;
    @Column(name = "Date_echeance")
    private String dateEcheance;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    public DetailsCredit(Double montantCreditAssurer, Double montantCreditDecouvert, int nombreDeRemboursement, Double montantDesTermes,
                         int numeroCompteClient, int dureeTotaleCredit, int differerAmortissement, String  datePremierRemboursementTerme, String dateEffet,
                         String dateEcheance, boolean isDeleted) {

        this.montantCreditAssurer = montantCreditAssurer;
        this.montantCreditDecouvert = montantCreditDecouvert;
        this.nombreDeRemboursement = (int) nombreDeRemboursement;
        this.montantDesTermes = montantDesTermes;
        this.numeroCompteClient = (int) numeroCompteClient;
        this.dureeTotaleCredit = (int) dureeTotaleCredit;
        this.differerAmortissement = (int) differerAmortissement;
        this.datePremierRemboursementTerme = datePremierRemboursementTerme;
        this.dateEffet = dateEffet;
        this.dateEcheance = dateEcheance;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public Double getMontantCreditAssurer() {
        return montantCreditAssurer;
    }

    public Double getMontantCreditDecouvert() {
        return montantCreditDecouvert;
    }

    public int getNombreDeRemboursement() {
        return nombreDeRemboursement;
    }

    public Double getMontantDesTermes() {
        return montantDesTermes;
    }

    public Number getNumeroCompteClient() {
        return numeroCompteClient;
    }

    public Number getDureeTotaleCredit() {
        return dureeTotaleCredit;
    }

    public Number getDiffererAmortissement() {
        return differerAmortissement;
    }

    public String getDatePremierRemboursementTerme() {
        return datePremierRemboursementTerme;
    }

    public String getDateEffet() {
        return dateEffet;
    }

    public String getDateEcheance() {
        return dateEcheance;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMontantCreditAssurer(Double montantCreditAssurer) {
        this.montantCreditAssurer = montantCreditAssurer;
    }

    public void setMontantCreditDecouvert(Double montantCreditDecouvert) {
        this.montantCreditDecouvert = montantCreditDecouvert;
    }



    public void setMontantDesTermes(Double montantDesTermes) {
        this.montantDesTermes = montantDesTermes;
    }

    public void setNumeroCompteClient(Number numeroCompteClient) {
        this.numeroCompteClient = (int) numeroCompteClient;
    }

    public void setDureeTotaleCredit(Number dureeTotaleCredit) {
        this.dureeTotaleCredit = (int) dureeTotaleCredit;
    }

    public void setDiffererAmortissement(Number differerAmortissement) {
        this.differerAmortissement = (int) differerAmortissement;
    }

    public void setDatePremierRemboursementTerme(String datePremierRemboursementTerme) {
        this.datePremierRemboursementTerme = datePremierRemboursementTerme;
    }

    public void setNombreDeRemboursement(int nombreDeRemboursement) {
        this.nombreDeRemboursement = nombreDeRemboursement;
    }

    public void setNumeroCompteClient(int numeroCompteClient) {
        this.numeroCompteClient = numeroCompteClient;
    }

    public void setDureeTotaleCredit(int dureeTotaleCredit) {
        this.dureeTotaleCredit = dureeTotaleCredit;
    }

    public void setDiffererAmortissement(int differerAmortissement) {
        this.differerAmortissement = differerAmortissement;
    }

    public void setDateEffet(String dateEffet) {
        this.dateEffet = dateEffet;
    }

    public void setDateEcheance(String dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailsCredit detailsCredit = (DetailsCredit) o;
        return isDeleted == detailsCredit.isDeleted &&
                Objects.equals(id, detailsCredit.id) &&
                Objects.equals(montantCreditAssurer, detailsCredit.montantCreditAssurer) &&
                Objects.equals(montantCreditDecouvert, detailsCredit.montantCreditDecouvert)&&
                Objects.equals(nombreDeRemboursement, detailsCredit.nombreDeRemboursement) &&
                Objects.equals(montantDesTermes, detailsCredit.montantDesTermes) &&
                Objects.equals(numeroCompteClient, detailsCredit.numeroCompteClient)&&
                Objects.equals(dureeTotaleCredit, detailsCredit.dureeTotaleCredit) &&
                Objects.equals(differerAmortissement, detailsCredit.differerAmortissement) &&
                Objects.equals(datePremierRemboursementTerme, detailsCredit.datePremierRemboursementTerme)&&
                Objects.equals(dateEffet, detailsCredit.dateEffet) &&
                Objects.equals(dateEcheance, detailsCredit.dateEcheance) ;

    }



    @Override
    public int hashCode() {
        return Objects.hash(id, montantCreditAssurer, montantCreditDecouvert, nombreDeRemboursement, montantDesTermes, numeroCompteClient, dureeTotaleCredit, differerAmortissement,
                datePremierRemboursementTerme, dateEffet, dateEcheance, isDeleted);

    }
    public DetailsCredit() {super();}

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

