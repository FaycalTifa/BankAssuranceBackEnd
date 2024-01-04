package com.uab.sante.resource;


import com.uab.sante.entities.Banque;
import com.uab.sante.service.BanqueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/banque")
public class BanqueResource {

    @Autowired
    private BanqueService banqueService;
    private static final Logger logger = LoggerFactory.getLogger(BanqueResource.class);


    /**
     *
     * @param banque
     * @return
     */
    @PostMapping
    public ResponseEntity<Banque> createProduct(@RequestBody Banque banque) {
        logger.info("+++++++++++++ ajout banque en cours dans le service ++++++++++++");
        if (banque != null) {
            banque = banqueService.save(banque);
            logger.info("========== save Banque Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(banque);
        } else {
            logger.warn("Requête invalide : banque est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Banque>> findAll() {
        List<Banque> banqueList = banqueService.findAll();
        logger.info("+++++++++++++ list banque en cours dans le service ++++++++++++");
        /*if (banqueList != null) {

            logger.info("========== list Banque Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(banqueList);
        } else {
            logger.warn("Requête invalide : banque est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }*/
         return ResponseEntity.ok(banqueList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banque> updatePoste(@PathVariable Long id) {
        logger.info("+++++++++++++ upadate banque en cours dans la resource ++++++++++++");
        if (id != null) {
            Banque update = banqueService.deleted(id);
            logger.info("========== update Banque Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(update);
        } else {
            logger.warn("Requête invalide : banque est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


 /*@PutMapping("/{id}")
    public ResponseEntity<Banque> deleted(@PathVariable Long id) {
        logger.info("+++++++++++++ delete banque en cours dans la resource ++++++++++++");
        if (id != null) {
            Banque update = banqueService.deleted(id);
            logger.info("========== delete Banque Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(update);
        } else {
            logger.warn("Requête invalide : banque est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
*/
}
