package com.uab.sante.resource;

import com.uab.sante.entities.QuestionnaireMedical;
import com.uab.sante.service.QuestionnaireMedicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questionnaireMedical")
public class QuestionnaireMedicalResource {
    @Autowired
    private QuestionnaireMedicalService questionnaireMedicalService;
    private static final Logger logger = LoggerFactory.getLogger(QuestionnaireMedicalResource.class);


    /**
     *
     * @param questionnaireMedical
     * @return
     */
    @PostMapping
    public ResponseEntity<QuestionnaireMedical> createProduct(@RequestBody QuestionnaireMedical questionnaireMedical) {
        logger.info("+++++++++++++ ajout questionnaireMedical en cours dans le service ++++++++++++");
        if (questionnaireMedical != null) {
            questionnaireMedical = questionnaireMedicalService.save(questionnaireMedical);
            logger.info("========== save QuestionnaireMedical Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(questionnaireMedical);
        } else {
            logger.warn("Requête invalide : questionnaireMedical est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<QuestionnaireMedical>> findAll() {
        List<QuestionnaireMedical> questionnaireMedicalList = questionnaireMedicalService.findAll();
        logger.info("+++++++++++++ list questionnaireMdical en cours dans le service ++++++++++++");
        return ResponseEntity.ok(questionnaireMedicalList);
    }

    /**
     *
     * @param id
     * @param questionnaireMedicalDetails
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<QuestionnaireMedical> updateQuestionnaireMedical(@PathVariable Long id, @RequestBody QuestionnaireMedical questionnaireMedicalDetails) {
        logger.info("+++++++++++++ UPDATE QUESTIONNAIREMEDICAL RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( questionnaireMedicalDetails.getQuestion1());
        System.out.println( questionnaireMedicalDetails.getQuestion2());
        System.out.println( questionnaireMedicalDetails.getQuestion3());
        System.out.println( questionnaireMedicalDetails.getQuestion4());
        System.out.println( questionnaireMedicalDetails.getQuestion5());
        //System.out.println( questionnaireMedicalDetails.getDetail());
        System.out.println("=========== RESSOURCE +===============");
        QuestionnaireMedical updatedQuestionnaireMedical = questionnaireMedicalService.update(id, questionnaireMedicalDetails);
        return new ResponseEntity<>(updatedQuestionnaireMedical, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param questionnaireMedicalDetails
     * @return
     */
    @PutMapping("/deleteQuestionnaireMedical/{id}")
    public ResponseEntity<QuestionnaireMedical> delete(@PathVariable Long id, @RequestBody QuestionnaireMedical questionnaireMedicalDetails) {
        logger.info("+++++++++++++ DELETE QUESTIONNAIREMEDICALRESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( questionnaireMedicalDetails.getQuestion1());
        System.out.println( questionnaireMedicalDetails.getQuestion2());
        System.out.println( questionnaireMedicalDetails.getQuestion3());
        System.out.println( questionnaireMedicalDetails.getQuestion4());
        System.out.println( questionnaireMedicalDetails.getQuestion5());
     //   System.out.println( questionnaireMedicalDetails.getDetails());

        System.out.println("=========== DELETE RESSOURCE +===============");
        QuestionnaireMedical updatedQuestionnaireMedical = questionnaireMedicalService.delete(id, questionnaireMedicalDetails);
        return new ResponseEntity<>(updatedQuestionnaireMedical, HttpStatus.OK);
    }





}
