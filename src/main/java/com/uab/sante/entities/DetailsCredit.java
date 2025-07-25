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
@Table(name = "DatailsCredit")
public class DetailsCredit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Montant_credit_assurer")
    private Long montantCreditAssurer;

    @Column(name = "Montant_credit_decouvert")
    private Long montantCreditDecouvert;

    @Column(name = "Nombre_de_remboursement")
    private Long nombreDeRemboursement;

    @Column(name = "Montant_des_termes")
    private Long montantDesTermes;

    @Column(name = "Numero_compte_client")
    private Long numeroCompteClient;

    @Column(name = "Duree_totale_credit_mois")
    private Long dureeTotaleCreditMois;

    @Column(name = "sur_mortalite")
    private Long surMortalite;

 @Column(name = "Duree_totale_credit_annee")
    private Long dureeTotaleCreditAnnee;

    @Column(name = "Differer_amortissement")
    private Long differerAmortissement;

    @Column(name = "Date_premier_remboursement_terme")
    private String datePremierRemboursementTerme;

    @Column(name = "Date_effet")
    private Date dateEffet;

    @Column(name = "Date_echeance")
    private Date dateEcheance;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

    @Column(name = "LocalDate")
    private LocalDate dateDuJour = LocalDate.now();

    @Column(name = "isDiffere")
    private Boolean isDiffere ;

    @Column(name = "isSurMortalite")
    private Boolean isSurMortalite ;

    @Column(name = "isDecouvert")
    private Boolean isDecouvert ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "periodicite_remboursement_id") // Assurez-vous de remplacer "periodicite_remboursement_id" par le nom réel de la colonne dans votre table DetailsCredit qui fait référence à la table PeriodiciteRemboursement.
    private PeriodiciteRemboursement periodiciteRemboursement;




}

