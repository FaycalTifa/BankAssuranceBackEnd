package com.uab.sante.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "InformationEmploi")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString

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
    private Long telEmployeur;

    @Column(name = "Numero_CNSS")
    private Long numeroCNSS;

    @Column(name = "Numero_RCCM_IFU")

    private Long numeroRCCMIFU;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;


}




