package com.uab.sante.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Agence")
@Data
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Libelle")
    private String libelle;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    // Relation Many-to-One avec Banque
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banqueId")
    private Banque banque;
}
