package com.uab.sante.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Gestionnaire")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Gestionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;
    @Column(name = "telephone")
    private String telephone;

    @Column(name = "LocalDate")
    private LocalDate dateDuJour = LocalDate.now();

    // Relation Many-to-One avec Agence
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agence_id")
    private Agence agence;


}

