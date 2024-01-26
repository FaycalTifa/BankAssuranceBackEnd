package com.uab.sante.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "DatailsCredit")
public class DetailsCredit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Montant_credit_assurer")
    private BigDecimal montantCreditAssurer;
    @Column(name = "Montant_credit_decouvert")
    private BigDecimal montantCreditDecouvert;
    @Column(name = "Nombre_de_remboursement")
    private Integer nombreDeRemboursement;
    @Column(name = "Montant_des_termes")
    private BigDecimal montantDesTermes;
    @Column(name = "Numero_compte_client")
    private Integer numeroCompteClient;
    @Column(name = "Duree_totale_credit")
    private Integer dureeTotaleCredit;
    @Column(name = "Differer_amortissement")
    private Integer differerAmortissement;
    @Column(name = "Date_premier_remboursement_terme")
    private Date datePremierRemboursementTerme;
    @Column(name = "Date_effet")
    private Date dateEffet;
    @Column(name = "Date_echeance")
    private Date dateEcheance;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    public DetailsCredit(BigDecimal montantCreditAssurer, BigDecimal montantCreditDecouvert, Integer nombreDeRemboursement, BigDecimal montantDesTermes,
                         Integer numeroCompteClient, Integer dureeTotaleCredit, Integer differerAmortissement, Date datePremierRemboursementTerme, Date dateEffet,
                         Date dateEcheance, boolean isDeleted) {

        this.montantCreditAssurer = montantCreditAssurer;
        this.montantCreditDecouvert = montantCreditDecouvert;
        this.nombreDeRemboursement = nombreDeRemboursement;
        this.montantDesTermes = montantDesTermes;
        this.numeroCompteClient = numeroCompteClient;
        this.dureeTotaleCredit = dureeTotaleCredit;
        this.differerAmortissement = differerAmortissement;
        this.datePremierRemboursementTerme = datePremierRemboursementTerme;
        this.dateEffet = dateEffet;
        this.dateEcheance = dateEcheance;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getMontantCreditAssurer() {
        return montantCreditAssurer;
    }

    public BigDecimal getMontantCreditDecouvert() {
        return montantCreditDecouvert;
    }

    public Integer getNombreDeRemboursement() {
        return nombreDeRemboursement;
    }

    public BigDecimal getMontantDesTermes() {
        return montantDesTermes;
    }

    public Integer getNumeroCompteClient() {
        return numeroCompteClient;
    }

    public Integer getDureeTotaleCredit() {
        return dureeTotaleCredit;
    }

    public Integer getDiffererAmortissement() {
        return differerAmortissement;
    }

    public Date getDatePremierRemboursementTerme() {
        return datePremierRemboursementTerme;
    }

    public Date getDateEffet() {
        return dateEffet;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public boolean isDeleted() {
        return isDeleted;
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
                datePremierRemboursementTerme, dateEffet, dateEcheance);

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

