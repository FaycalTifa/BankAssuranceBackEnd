package com.uab.sante.resource;

import com.uab.sante.entities.PeriodicitePaiementPrime;
import com.uab.sante.service.PeriodicitePaiementPrimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/periodicitePaiementPrime")
public class PeriodicitePaiementPrimeResource {
    @Autowired
    private PeriodicitePaiementPrimeService periodicitePaiementPrimeService;
    private static final Logger logger = LoggerFactory.getLogger(PeriodicitePaiementPrime.class);


    /**
     *
     * @param periodicitePaiementPrime
     * @return
     */
    @PostMapping
    public ResponseEntity<PeriodicitePaiementPrime> createProduct(@RequestBody PeriodicitePaiementPrime periodicitePaiementPrime) {
        logger.info("+++++++++++++ ajout periodicitePaiementPaiement en cours dans le service ++++++++++++");
        if (periodicitePaiementPrime != null) {
            periodicitePaiementPrime = periodicitePaiementPrimeService.save(periodicitePaiementPrime);
            logger.info("========== save Banque Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(periodicitePaiementPrime);
        } else {
            logger.warn("Requête invalide : periodicitePaiementPrime est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PeriodicitePaiementPrime>> findAll() {
        List<PeriodicitePaiementPrime> periodicitePaiementPrimeList = periodicitePaiementPrimeService.findAll();
        logger.info("+++++++++++++ list periodicitePaiementPrime en cours dans le service ++++++++++++");
        return ResponseEntity.ok(periodicitePaiementPrimeList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeriodicitePaiementPrime> updatePeriodicitePaiementPrime(@PathVariable Long id, @RequestBody PeriodicitePaiementPrime periodicitePaiementPrimeDetails) {
        logger.info("+++++++++++++ UPDATE PERIODICITEPAIEMENTPRIME RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( periodicitePaiementPrimeDetails.getCode());
        System.out.println( periodicitePaiementPrimeDetails.getLibelle());
        System.out.println("=========== RESSOURCE +===============");
        PeriodicitePaiementPrime updatedPeriodicitePaiementPrime = periodicitePaiementPrimeService.update(id, periodicitePaiementPrimeDetails);
        return new ResponseEntity<>(updatedPeriodicitePaiementPrime, HttpStatus.OK);
    }

    @PutMapping("/deletePeriodicitePaiementPrime/{id}")
    public ResponseEntity<PeriodicitePaiementPrime> delete(@PathVariable Long id, @RequestBody PeriodicitePaiementPrime periodcitePaiementPrimeDetails) {
        logger.info("+++++++++++++ DELETE BANQUE RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( periodcitePaiementPrimeDetails.getCode());
        System.out.println( periodcitePaiementPrimeDetails.getLibelle());
        System.out.println("=========== DELETE RESSOURCE +===============");
        PeriodicitePaiementPrime updatedPeriodicitePaiementPrime = periodicitePaiementPrimeService.delete(id, periodcitePaiementPrimeDetails);
        return new ResponseEntity<>(updatedPeriodicitePaiementPrime, HttpStatus.OK);
    }



}
