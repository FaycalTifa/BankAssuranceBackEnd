package com.uab.sante.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Agence")
@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banque_id")
    private Banque banque;
}
