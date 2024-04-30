package com.uab.sante.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "PeriodicitePaiementPrime")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeriodicitePaiementPrime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Code")
    private String code;
    @Column(name = "Libelle")
    private String libelle;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    @Column(name = "LocalDate")
    private LocalDate dateDuJour = LocalDate.now();


}
