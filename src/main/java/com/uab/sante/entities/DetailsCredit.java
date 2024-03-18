package com.uab.sante.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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

    @Column(name = "Duree_totale_credit")
    private Long dureeTotaleCredit;

    @Column(name = "Differer_amortissement")
    private Long differerAmortissement;

    @Column(name = "Date_premier_remboursement_terme")
    private LocalDate datePremierRemboursementTerme;

    @Column(name = "Date_effet")
    private LocalDate dateEffet;

    @Column(name = "Date_echeance")
    private LocalDate dateEcheance;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;


}

