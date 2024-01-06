package com.uab.sante.resource;

import com.uab.sante.entities.PeriodiciteRemboursement;
import com.uab.sante.service.PeriodiciteRemboursementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("api/periodiciteRemboursement")
public class PeriodiciteRemboursementResource {
    @Autowired
    private PeriodiciteRemboursementService periodiciteRemboursementService;
    private static final Logger logger = LoggerFactory.getLogger(PeriodiciteRemboursementResource.class);


    /**
     *
     * @param periodiciteRemboursement
     * @return
     */
    @PostMapping
    public ResponseEntity<PeriodiciteRemboursement> createProduct(@RequestBody PeriodiciteRemboursement periodiciteRemboursement) {
        logger.info("+++++++++++++ ajout periodiciteRemboursement en cours dans le service ++++++++++++");
        if (periodiciteRemboursement != null) {
            periodiciteRemboursement = periodiciteRemboursementService.save(periodiciteRemboursement);
            logger.info("========== save PeriodiciteRemboursement Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(periodiciteRemboursement);
        } else {
            logger.warn("Requête invalide : banque est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PeriodiciteRemboursement>> findAll() {
        List<PeriodiciteRemboursement> periodiciteRemboursementList = periodiciteRemboursementService.findAll();
        logger.info("+++++++++++++ list periodiciteRemboursement en cours dans le service ++++++++++++");
        return ResponseEntity.ok(periodiciteRemboursementList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodiciteRemboursement> updatePeriodiciteRemboursement(@PathVariable Long id, @RequestBody PeriodiciteRemboursement periodiciteRemboursementDetails) {
        logger.info("+++++++++++++ UPDATE PERIODICITEREMBOURSEMENT RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( periodiciteRemboursementDetails.getCode());
        System.out.println( periodiciteRemboursementDetails.getLibelle());
        System.out.println("=========== RESSOURCE +===============");
        PeriodiciteRemboursement updatedPeriodiciteRemboursement = periodiciteRemboursementService.update(id, periodiciteRemboursementDetails);
        return new ResponseEntity<>(updatedPeriodiciteRemboursement, HttpStatus.OK);
    }

    @PutMapping("/deletePeriodiciteRemboursement/{id}")
    public ResponseEntity<PeriodiciteRemboursement> delete(@PathVariable Long id, @RequestBody PeriodiciteRemboursement periodiciteRemboursementDetails) {
        logger.info("+++++++++++++ DELETE PERIODICITEREMBOURSEMENT RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( periodiciteRemboursementDetails.getCode());
        System.out.println( periodiciteRemboursementDetails.getLibelle());
        System.out.println("=========== DELETE RESSOURCE +===============");
        PeriodiciteRemboursement updatedPeriodiciteRemboursement = periodiciteRemboursementService.delete(id, periodiciteRemboursementDetails);
        return new ResponseEntity<>(updatedPeriodiciteRemboursement, HttpStatus.OK);
    }



}
