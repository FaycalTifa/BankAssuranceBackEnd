package com.uab.sante.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "InformationEmploi")

public class InformationEmploi implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Employeur")
    private String employeur;
    @Column(name = "Date_Embauche")
    private String dateEmbauche;
    @Column(name = "Adresse_Employeur")
    private String adresseEmployeur;
    @Column(name = "Profession_Actuelle")
    private String professionActuelle;
    @Column(name = "Type_De_Contrat")
    private String typeDeContrat;
    @Column(name = "Tel_Employeur")
    private int telEmployeur;
    @Column(name = "Numero_CNSS")
    private int numeroCNSS;
    @Column(name = "Numero_RCCM_IFU")
    private int numeroRCCMIFU;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    public InformationEmploi(String employeur, String dateEmbauche, String adresseEmployeur, String professionActuelle, String typeDeContrat, int telEmployeur, int numeroCNSS, int numeroRCCMIFU, boolean isDeleted) {
        this.employeur = employeur;
        this.dateEmbauche = dateEmbauche;
        this.adresseEmployeur = adresseEmployeur;
        this.professionActuelle = professionActuelle;
        this.typeDeContrat = typeDeContrat;
        this.telEmployeur = telEmployeur;
        this.numeroCNSS = numeroCNSS;
        this.numeroRCCMIFU = numeroRCCMIFU;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getEmployeur() {
        return employeur;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }

    public String getAdresseEmployeur() {
        return adresseEmployeur;
    }

    public String getProfessionActuelle() {
        return professionActuelle;
    }

    public String getTypeDeContrat() {
        return typeDeContrat;
    }

    public int getTelEmployeur() {
        return telEmployeur;
    }

    public int getNumeroCNSS() {
        return numeroCNSS;
    }

    public int getNumeroRCCMIFU() {
        return numeroRCCMIFU;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public void setAdresseEmployeur(String adresseEmployeur) {
        this.adresseEmployeur = adresseEmployeur;
    }

    public void setProfessionActuelle(String professionActuelle) {
        this.professionActuelle = professionActuelle;
    }

    public void setTypeDeContrat(String typeDeContrat) {
        this.typeDeContrat = typeDeContrat;
    }

    public void setTelEmployeur(int telEmployeur) {
        this.telEmployeur = telEmployeur;
    }

    public void setNumeroCNSS(int numeroCNSS) {
        this.numeroCNSS = numeroCNSS;
    }

    public void setNumeroRCCMIFU(int numeroRCCMIFU) {
        this.numeroRCCMIFU = numeroRCCMIFU;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
    @Override

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationEmploi informationEmploi = (InformationEmploi) o;
        return isDeleted == informationEmploi.isDeleted &&
                Objects.equals(id, informationEmploi.id) &&
                Objects.equals(employeur, informationEmploi.employeur) &&
                Objects.equals(dateEmbauche, informationEmploi.dateEmbauche)&&
                Objects.equals(adresseEmployeur, informationEmploi.adresseEmployeur) &&
                Objects.equals(professionActuelle, informationEmploi.professionActuelle) &&
                Objects.equals(typeDeContrat, informationEmploi.typeDeContrat) &&
                Objects.equals(telEmployeur, informationEmploi.telEmployeur) &&
                Objects.equals(numeroCNSS, informationEmploi.numeroCNSS) &&
                Objects.equals(numeroRCCMIFU, informationEmploi.numeroRCCMIFU);


    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeur, dateEmbauche, adresseEmployeur, professionActuelle, typeDeContrat, telEmployeur, numeroCNSS, numeroRCCMIFU, isDeleted);
    }
    public  InformationEmploi() {super();}


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




