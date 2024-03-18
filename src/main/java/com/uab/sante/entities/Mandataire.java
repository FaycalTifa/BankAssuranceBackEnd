package com.uab.sante.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
    private Double capitalAssurer;

    @Column(name = "Prime_Garantie_Deces_ou_IAD")
    private Long primeGarantieDecesOuIAD;

    @Column(name = "Prime_Garantie_Perte_Emploi")
    private Long primeGarantiePerteEmploi;

    @Column(name = "Prime_Totale")
    private Long primeTotale;

    @Column(name = "Numero_De_Compte_UAB_Vie")
    private Long numeroDeCompteUABVie;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;


}

