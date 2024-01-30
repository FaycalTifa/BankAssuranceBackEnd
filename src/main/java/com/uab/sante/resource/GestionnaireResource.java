package com.uab.sante.resource;

import com.uab.sante.entities.Gestionnaire;
import com.uab.sante.service.GestionnaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gestionnaire")
public class GestionnaireResource {
    @Autowired
    private GestionnaireService gestionnaireService;

    private static final Logger logger = LoggerFactory.getLogger(AgenceResource.class);


    /**
     *
     * @param gestionnaire
     * @return
     */
    @PostMapping
    public ResponseEntity<Gestionnaire> createProduct(@RequestBody Gestionnaire gestionnaire) {
        logger.info("+++++++++++++ ajout gestionnaire en cours dans le service ++++++++++++");
        if (gestionnaire != null) {
            gestionnaire = gestionnaireService.save(gestionnaire);
            logger.info("========== save Gestionnaire Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(gestionnaire);
        } else {
            logger.warn("Requête invalide : gestionnaire est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Gestionnaire>> findAll() {
        List<Gestionnaire> gestionnaireList = gestionnaireService.findAll();
        logger.info("+++++++++++++ list Gestionnaire en cours dans le service ++++++++++++");
        System.out.println("=====================gestionnaireList========================");
        System.out.println(gestionnaireList);
        System.out.println("=====================gestionnaireList========================");
        return ResponseEntity.ok(gestionnaireList);
    }

    /**
     *
     * @param id
     * @param gestionnaire
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Gestionnaire> updateAgence(@PathVariable Long id, @RequestBody Gestionnaire gestionnaire) {
        logger.info("+++++++++++++ UPDATE Gestionnaire RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( gestionnaire.getLibelle());
        System.out.println( gestionnaire.getAgence());
        System.out.println("=========== RESSOURCE +===============");
        Gestionnaire updatedGestionnaire = gestionnaireService.update(id, gestionnaire);
        return new ResponseEntity<>(updatedGestionnaire, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param gestionnaire
     * @return
     */
    @PutMapping("/deleteGestionnaire/{id}")
    public ResponseEntity<Gestionnaire> delete(@PathVariable Long id, @RequestBody Gestionnaire gestionnaire) {
        logger.info("+++++++++++++ DELETE GESTIONNAIRE RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( gestionnaire.getAgence());
        System.out.println( gestionnaire.getLibelle());
        System.out.println("=========== DELETE RESSOURCE +===============");
        Gestionnaire updatedGestionnaire = gestionnaireService.delete(id, gestionnaire);
        return new ResponseEntity<>(updatedGestionnaire, HttpStatus.OK);
    }

}
