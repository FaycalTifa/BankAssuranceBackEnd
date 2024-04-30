package com.uab.sante.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Mandataire")
public class Mandataire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Capital_Assurer")
    private Long capitalAssurer;

    @Column(name = "Prime_Garantie_Deces_ou_IAD")
    private Long primeGarantieDecesOuIAD;

    @Column(name = "Prime_Garantie_Perte_Emploi")
    private Long primeGarantiePerteEmploi;

    @Column(name = "Prime_Totale")
    private Long primeTotale;

    @Column(name = "Prime_simple")
    private Long primeSimple;

    @Column(name = "Prime_Differe")
    private Long primeDiffere;

    @Column(name = "Prime_Decouvert")
    private Long primeDecouvert;

    @Column(name = "tauxAmortissement")
    private Double tauxAmortissement;

    @Column(name = "tauxDecouvert")
    private Double tauxDecouvert;

    @Column(name = "Numero_De_Compte_UAB_Vie")
    private Long numeroDeCompteUABVie;

    @Column(name = "isDeleted")
    private Boolean isDeleted = false;

    @Column(name = "LocalDate")
    private LocalDate dateDuJour = LocalDate.now();
}

