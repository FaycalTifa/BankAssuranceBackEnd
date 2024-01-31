package com.uab.sante.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Personne")
public class Personne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Nom")
    private String nom;
    @Column(name = "Prenom")
    private String prenom;
    @Column(name = "Nom_de_jeune_fille")
    private String nomDeJeuneFille;
    @Column(name = "Date_de_naissance")
    private String dateDeNaissance;
    @Column(name = "Lieu_de_naissance")
    private String lieuDeNaissance;
    @Column(name = "Taille")
    private Double taille;
    @Column(name = "Poids")
    private Double poids;
    @Column(name = "Tension")
    private Double tension;
    @Column(name = "Profession_actuelle")
    private String professionActuelle;
    @Column(name = "Employeur")
    private String employeur;
    @Column(name = "Numero_piece_passeport")
    private String numeroPiecePasseport;

    @Column(name = "Date_etablissement")
    private String dateEtablissement;
    @Column(name = "Lieu_etablissement")
    private String lieuEtablissement;
    @Column(name = "Adresse_postale")
    private String adressePostale;
    @Column(name = "Telephone")
    private Number telephone;
    @Column(name = "Email")
    private String email;
    @Column(name = "Telephone_secours")
    private Number telephoneSecours;
    @Column(name = "Email_secours")
    private String emailSecours;
    @Column(name = "Adresse_secours")
    private String adresseSecours;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;




    public Personne(String nom, String prenom, String nomDeJeuneFille, String dateDeNaissance, String lieuDeNaissance, Double taille, Double poids, Double tension, String professionActuelle,
                    String employeur, String numeroPiecePasseport, String dateEtablissement, String lieuEtablissement, String adressePostale, Number telephone, String email,
                    Number telephoneSecours, String emailSecours, String adresseSecours, boolean isDeleted){
        this.nom = nom;
        this.prenom = prenom;
        this.nomDeJeuneFille = nomDeJeuneFille;
        this.dateDeNaissance = dateDeNaissance;
        this.lieuDeNaissance = lieuDeNaissance;
        this.taille = taille;
        this.poids = poids;
        this.tension = tension;
        this.professionActuelle = professionActuelle;
        this.employeur = employeur;
        this.numeroPiecePasseport = numeroPiecePasseport;
        this.dateEtablissement = dateEtablissement;
        this.lieuEtablissement = lieuEtablissement;
        this.adressePostale = adressePostale;
        this.telephone = telephone;
        this.email = email;
        this.telephoneSecours = telephoneSecours;
        this.emailSecours = emailSecours;
        this.adresseSecours = adresseSecours;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNomDeJeuneFille() {
        return nomDeJeuneFille;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public Double getTaille() {
        return taille;
    }

    public Double getPoids() {
        return poids;
    }

    public Double getTension() {
        return tension;
    }

    public String getProfessionActuelle() {
        return professionActuelle;
    }

    public String getEmployeur() {
        return employeur;
    }

    public String getNumeroPiecePasseport() {
        return numeroPiecePasseport;
    }

    public String getDateEtablissement() {
        return dateEtablissement;
    }

    public String getLieuEtablissement() {
        return lieuEtablissement;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public Number getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public Number getTelephoneSecours() {
        return telephoneSecours;
    }

    public String getEmailSecours() {
        return emailSecours;
    }

    public String getAdresseSecours() {
        return adresseSecours;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNomDeJeuneFille(String nomDeJeuneFille) {
        this.nomDeJeuneFille = nomDeJeuneFille;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public void setTaille(Double taille) {
        this.taille = taille;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public void setTension(Double tension) {
        this.tension = tension;
    }

    public void setProfessionActuelle(String professionActuelle) {
        this.professionActuelle = professionActuelle;
    }

    public void setEmployeur(String employeur) {
        this.employeur = employeur;
    }

    public void setNumeroPiecePasseport(String numeroPiecePasseport) {
        this.numeroPiecePasseport = numeroPiecePasseport;
    }

    public void setDateEtablissement(String dateEtablissement) {
        this.dateEtablissement = dateEtablissement;
    }

    public void setLieuEtablissement(String lieuEtablissement) {
        this.lieuEtablissement = lieuEtablissement;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public void setTelephone(Number telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephoneSecours(Number telephoneSecours) {
        this.telephoneSecours = telephoneSecours;
    }

    public void setEmailSecours(String emailSecours) {
        this.emailSecours = emailSecours;
    }

    public void setAdresseSecours(String adresseSecours) {
        this.adresseSecours = adresseSecours;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return isDeleted == personne.isDeleted &&
                Objects.equals(id, personne.id) &&
                Objects.equals(nom, personne.nom) &&
                Objects.equals(prenom, personne.prenom)&&
                Objects.equals(nomDeJeuneFille, personne.nomDeJeuneFille) &&
                Objects.equals(dateDeNaissance, personne.dateDeNaissance) &&
                Objects.equals(lieuDeNaissance, personne.lieuDeNaissance) &&
                Objects.equals(taille, personne.taille) &&
                Objects.equals(poids, personne.poids) &&
                Objects.equals(tension, personne.tension) &&
                Objects.equals(professionActuelle, personne.professionActuelle) &&
                Objects.equals(employeur, personne.employeur) &&
                Objects.equals(numeroPiecePasseport, personne.numeroPiecePasseport) &&
                Objects.equals(dateEtablissement, personne.dateEtablissement) &&
                Objects.equals(adressePostale, personne.adressePostale) &&
                Objects.equals(telephone, personne.telephone) &&
                Objects.equals(email, personne.email) &&
                Objects.equals(telephoneSecours, personne.telephoneSecours) &&
                Objects.equals(emailSecours, personne.emailSecours) &&
                Objects.equals(adresseSecours, personne.adresseSecours);

    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, nomDeJeuneFille, dateDeNaissance, lieuDeNaissance, taille, poids, tension, professionActuelle,
                employeur, numeroPiecePasseport, dateEtablissement, lieuEtablissement, adressePostale, telephone, email, telephoneSecours, emailSecours, adresseSecours, isDeleted);
    }
    public  Personne() {super();}

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
