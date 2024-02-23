package com.uab.sante.service;

import com.uab.sante.entities.QuestionnaireMedical;
import com.uab.sante.repository.QuestionnaireMedicalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireMedicalService {
    @Autowired
    private QuestionnaireMedicalRepository questionnaireMedicalRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireMedicalService.class);


    public QuestionnaireMedical save(QuestionnaireMedical questionnaireMedical){
        logger.info("ajout questionnaireMedical en cours dans le service");

        questionnaireMedical = questionnaireMedicalRepository.save(questionnaireMedical);
        logger.info("========== save questionnairemedical  Service ===============" + questionnaireMedical);
        return questionnaireMedical;
    }

    public List<QuestionnaireMedical> findAll(){
        List<QuestionnaireMedical> questionnaireMedicals = questionnaireMedicalRepository.findAllByIsDeletedFalse();
        logger.info("========== List questionnaireMedical en cours dans le service traiter ===============\" + questionnaireMedical");
        return questionnaireMedicals;
    }


    public QuestionnaireMedical update(Long id, QuestionnaireMedical questionnaireMedicalDetails) {

        QuestionnaireMedical exitingQuestionnaireMedical = questionnaireMedicalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Le  questionnaireMedical avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés du questionnairMedical existante avec les détails du nouveau questionnairemedical
        exitingQuestionnaireMedical.setQuestion1(questionnaireMedicalDetails.getQuestion1()); // par exemple, si 'question1' est un champ de l'entité QuestionnaireMedical
        exitingQuestionnaireMedical.setQuestion2(questionnaireMedicalDetails.getQuestion2()); // et ainsi de suite pour les autres propriétés...
        exitingQuestionnaireMedical.setQuestion3(questionnaireMedicalDetails.getQuestion3());
        exitingQuestionnaireMedical.setQuestion4(questionnaireMedicalDetails.getQuestion4());
        exitingQuestionnaireMedical.setQuestion4(questionnaireMedicalDetails.getQuestion5());
      //  exitingQuestionnaireMedical.setDetails(questionnaireMedicalDetails.getDetails());
        // Enregistrez le questionnaireMedical mise à jour dans la base de données
        return questionnaireMedicalRepository.save(exitingQuestionnaireMedical);
    }

    public QuestionnaireMedical delete(Long id, QuestionnaireMedical questionnaireMedicalDetails) {

        QuestionnaireMedical exixtingQuestionnaireMedical = questionnaireMedicalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Le questionnaireMedical avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés du questionnaireMedical existante avec les détails du nouveau questionnaireMedical
        exixtingQuestionnaireMedical.setQuestion1(questionnaireMedicalDetails.getQuestion1()); // par exemple, si 'nom' est un champ de l'entité Banque
        exixtingQuestionnaireMedical.setQuestion2(questionnaireMedicalDetails.getQuestion2()); // et ainsi de suite pour les autres propriétés...
        exixtingQuestionnaireMedical.setQuestion3(questionnaireMedicalDetails.getQuestion3());
        exixtingQuestionnaireMedical.setQuestion4(questionnaireMedicalDetails.getQuestion4());
        exixtingQuestionnaireMedical.setQuestion4(questionnaireMedicalDetails.getQuestion5());
      //  exixtingQuestionnaireMedical.setDetails(questionnaireMedicalDetails.getDetails());
        exixtingQuestionnaireMedical.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return questionnaireMedicalRepository.save(exixtingQuestionnaireMedical);
    }




}
