package com.uab.sante.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private Long taille;

    @Column(name = "Poids")
    private Long poids;

    @Column(name = "Tension")
    private Long tension;

    @Column(name = "Profession_actuelle")
    private String professionActuelle;

    @Column(name = "Employeur")
    private String employeur;

    @Column(name = "Numero_piece_passeport")
    private Long numeroPiecePasseport;

    @Column(name = "Date_etablissement")
    private LocalDate dateEtablissement;

    @Column(name = "Lieu_etablissement")
    private String lieuEtablissement;

    @Column(name = "Adresse_postale")
    private String adressePostale;

    @Column(name = "Telephone")
    private Long telephone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Telephone_secours")
    private Long telephoneSecours;

    @Column(name = "Email_secours")
    private String emailSecours;

    @Column(name = "Adresse_secours")
    private String adresseSecours;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;



}
