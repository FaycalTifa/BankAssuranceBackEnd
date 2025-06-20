package com.uab.sante.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Agence")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Agence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Libelle")
    private String libelle;
    @Column(name = "isDeleted")
    private boolean isDeleted = false;
    @Column(name = "LocalDate")
    private LocalDate dateDuJour = LocalDate.now();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gestionnaire_id")
    private Gestionnaire gestionnaire;
    public Agence( String libelle, boolean isDeleted) {
        this.libelle = libelle;
        this.isDeleted = isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    // Relation Many-to-One avec Banque
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banque_id")
    private Banque banque;
}
