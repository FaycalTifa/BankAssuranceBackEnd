package com.uab.sante.resource;


import com.uab.sante.entities.Mandataire;
import com.uab.sante.service.MandataireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/mandataire")
public class MandataireResource {
    @Autowired
    private MandataireService mandataireService;
    private static final Logger logger = LoggerFactory.getLogger(MandataireResource.class);


    /**
     *
     * @param mandataire
     * @return
     */
    @PostMapping
    public ResponseEntity<Mandataire> createProduct(@RequestBody Mandataire mandataire) {
        logger.info("+++++++++++++ ajout mandataire en cours dans le service ++++++++++++");
        if (mandataire != null) {
            mandataire = mandataireService.save(mandataire);
            logger.info("========== save Mandataire Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(mandataire);
        } else {
            logger.warn("Requête invalide : mandataire est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Mandataire>> findAll() {
        List<Mandataire> mandataireList = mandataireService.findAll();
        logger.info("+++++++++++++ list mandataire en cours dans le service ++++++++++++");
        return ResponseEntity.ok(mandataireList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mandataire> updateMandataire(@PathVariable Long id, @RequestBody Mandataire mandataireDetails) {
        logger.info("+++++++++++++ UPDATE MANDATAIRE RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( mandataireDetails.getCapitalAssurer());
        System.out.println( mandataireDetails.getPrimeGarantieDecesOuIAD());
        System.out.println( mandataireDetails.getPrimeGarantiePerteEmploi());
        System.out.println( mandataireDetails.getPrimeTotale());
        System.out.println( mandataireDetails.getNumeroDeCompteUABVie());
        System.out.println("=========== RESSOURCE +===============");
        Mandataire updatedMandataire = mandataireService.update(id, mandataireDetails);
        return new ResponseEntity<>(updatedMandataire, HttpStatus.OK);
    }

    @PutMapping("/deleteMandataire/{id}")
    public ResponseEntity<Mandataire> delete(@PathVariable Long id, @RequestBody Mandataire mandataireDetails) {
        logger.info("+++++++++++++ DELETE MANDATAIRE RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( mandataireDetails.getCapitalAssurer());
        System.out.println( mandataireDetails.getPrimeGarantieDecesOuIAD());
        System.out.println( mandataireDetails.getPrimeGarantiePerteEmploi());
        System.out.println( mandataireDetails.getPrimeTotale());
        System.out.println( mandataireDetails.getNumeroDeCompteUABVie());
        System.out.println("=========== DELETE RESSOURCE +===============");
        Mandataire updatedMandataire = mandataireService.delete(id, mandataireDetails);
        return new ResponseEntity<>(updatedMandataire, HttpStatus.OK);
    }
}
