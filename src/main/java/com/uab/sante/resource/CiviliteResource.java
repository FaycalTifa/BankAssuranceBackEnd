package com.uab.sante.resource;

import com.uab.sante.entities.Banque;
import com.uab.sante.entities.Civilite;
import com.uab.sante.service.BanqueService;
import com.uab.sante.service.CiviliteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/civilite")
public class CiviliteResource {
    @Autowired
    private CiviliteService civiliteService;
    private static final Logger logger = LoggerFactory.getLogger(CiviliteResource.class);


    /**
     *
     * @param civilite
     * @return
     */
    @PostMapping
    public ResponseEntity<Civilite> createProduct(@RequestBody Civilite civilite) {
        logger.info("+++++++++++++ ajout civilite en cours dans le service ++++++++++++");
        if (civilite != null) {
            civilite = civiliteService.save(civilite);
            logger.info("========== save Civilite Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(civilite);
        } else {
            logger.warn("Requête invalide : civilite est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Civilite>> findAll() {
        List<Civilite> civiliteList = civiliteService.findAll();
        logger.info("+++++++++++++ list civilite en cours dans le service ++++++++++++");
        return ResponseEntity.ok(civiliteList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Civilite> updateBanque(@PathVariable Long id, @RequestBody Civilite civiliteDetails) {
        logger.info("+++++++++++++ UPDATE CIVILITE RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( civiliteDetails.getCode());
        System.out.println( civiliteDetails.getLibelle());
        System.out.println("=========== RESSOURCE +===============");
        Civilite updatedCivilite = civiliteService.update(id, civiliteDetails);
        return new ResponseEntity<>(updatedCivilite, HttpStatus.OK);
    }

    @PutMapping("/deleteCivilite/{id}")
    public ResponseEntity<Civilite> delete(@PathVariable Long id, @RequestBody Civilite civiliteDetails) {
        logger.info("+++++++++++++ DELETE CIVILITE RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( civiliteDetails.getCode());
        System.out.println( civiliteDetails.getLibelle());
        System.out.println("=========== DELETE RESSOURCE +===============");
        Civilite updatedCivilite = civiliteService.delete(id, civiliteDetails);
        return new ResponseEntity<>(updatedCivilite, HttpStatus.OK);
    }
}


