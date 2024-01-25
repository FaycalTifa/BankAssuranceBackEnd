package com.uab.sante.resource;

import com.uab.sante.entities.Agence;
import com.uab.sante.entities.Banque;
import com.uab.sante.service.AgenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/agence")
public class AgenceResource {


    @Autowired
    private AgenceService agenceService;

    private static final Logger logger = LoggerFactory.getLogger(BanqueResource.class);


    /**
     *
     * @param agence
     * @return
     */
    @PostMapping
    public ResponseEntity<Agence> createProduct(@RequestBody Agence agence) {
        logger.info("+++++++++++++ ajout Ressource en cours dans le service ++++++++++++");
        logger.info("========== save agence  Ressource ===============" + agence);
        System.out.println("=====================saveAgence Ressource========================");
        System.out.println(agence);
        System.out.println("=====================saveAgence Ressource========================");
        if (agence != null) {
           // agence.getBanque().setId(this.);
            agence = agenceService.save(agence);
            System.out.println("11111111111111111 saveAgence Ressource========================");
            System.out.println(agence);
            System.out.println("11111111111111111 saveAgence Ressource========================");
            logger.info("========== save Banque Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(agence);
        } else {
            logger.warn("Requête invalide : banque est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Agence>> findAll() {
        List<Agence> agenceList = agenceService.findAll();
        logger.info("+++++++++++++ list Agence en cours dans le service ++++++++++++");
        System.out.println("=====================agenceList========================");
        System.out.println(agenceList);
        System.out.println("=====================agenceList========================");
        return ResponseEntity.ok(agenceList);
    }

    /**
     *
     * @param id
     * @param agence
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Agence> updateBanque(@PathVariable Long id, @RequestBody Agence agence) {
        logger.info("+++++++++++++ UPDATE Agence RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( agence.getLibelle());
        System.out.println( agence.getBanque());
        System.out.println("=========== RESSOURCE +===============");
        Agence updatedAgence = agenceService.update(id, agence);
        return new ResponseEntity<>(updatedAgence, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param agence
     * @return
     */
    @PutMapping("/deleteAgence/{id}")
    public ResponseEntity<Agence> delete(@PathVariable Long id, @RequestBody Agence agence) {
        logger.info("+++++++++++++ DELETE BANQUE RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( agence.getBanque());
        System.out.println( agence.getLibelle());
        System.out.println("=========== DELETE RESSOURCE +===============");
        Agence updatedAgence = agenceService.delete(id, agence);
        return new ResponseEntity<>(updatedAgence, HttpStatus.OK);
    }

}
