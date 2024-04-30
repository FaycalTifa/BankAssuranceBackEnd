package com.uab.sante.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TypeContrat")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class TypeContrat implements Serializable {
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
