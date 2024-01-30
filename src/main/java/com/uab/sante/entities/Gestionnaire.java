package com.uab.sante.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    // Relation Many-to-One avec Agence
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agence_id")
    private Agence agence;
}

