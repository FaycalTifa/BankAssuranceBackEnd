package com.uab.sante.service;

import com.uab.sante.entities.*;
import com.uab.sante.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SouscriptionService {


    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private DetailsCreditRepository detailsCreditRepository;
    @Autowired
    private PersonneService personneService;
    @Autowired
    private DetailsCreditService detailsCreditService;
@Autowired
    private SouscriptionRepository souscriptionRepository;
@Autowired
    private QuestionnaireMedicalRepository questionnaireMedicalRepository;
@Autowired
    private MandataireRepository mandataireRepository;
@Autowired
    private InformationEmploiRepository informationEmploiRepository;



    public Souscription save(Souscription souscription){

        Personne personne = souscription.getPersonne();
        DetailsCredit detailsCredit = souscription.getDetailsCredit();
        QuestionnaireMedical questionnaireMedical = souscription.getQuestionnaireMedical();
        Mandataire mandataire = souscription.getMandataire();
        InformationEmploi informationEmploi = souscription.getInformationEmploi();

        // Enregistrer la personne et les détails du crédit
        personne = personneRepository.save(personne);
        detailsCredit = detailsCreditRepository.save(detailsCredit);
        questionnaireMedical = questionnaireMedicalRepository.save(questionnaireMedical);
        mandataire = mandataireRepository.save(mandataire);
        informationEmploi = informationEmploiRepository.save(informationEmploi);

        // Mettre à jour la souscription avec les références aux enregistrements nouvellement créés
        souscription.setPersonne(personne);
        souscription.setDetailsCredit(detailsCredit);
        souscription.setQuestionnaireMedical(questionnaireMedical);
        souscription.setMandataire(mandataire);
        souscription.setInformationEmploi(informationEmploi);

        // Enregistrer la souscription complète
        return souscriptionRepository.save(souscription);
    }
}
