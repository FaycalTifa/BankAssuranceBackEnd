package com.uab.sante.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Souscription")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Souscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personne_id")
    private Personne personne;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "detailsCredit_id")
    private DetailsCredit detailsCredit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionnaireMedical_id")
    private QuestionnaireMedical questionnaireMedical;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mandataire_id")
    private Mandataire mandataire;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "informationEmploi_id")
    private InformationEmploi informationEmploi;
}
