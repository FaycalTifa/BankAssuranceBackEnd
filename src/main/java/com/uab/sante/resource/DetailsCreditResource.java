package com.uab.sante.resource;

import com.uab.sante.entities.DetailsCredit;
import com.uab.sante.service.DetailsCreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/detailsCredit")
public class DetailsCreditResource {
    @Autowired
    private DetailsCreditService detailsCreditService;
    private static final Logger logger = LoggerFactory.getLogger(DetailsCreditResource.class);


    /**
     *
     * @param detailsCredit
     * @return
     */
    @PostMapping
    public ResponseEntity<DetailsCredit> createProduct(@RequestBody DetailsCredit detailsCredit) {
        logger.info("+++++++++++++ ajout detailsCredit en cours dans le service ++++++++++++");
        if (detailsCredit != null) {
            detailsCredit = detailsCreditService.save(detailsCredit);
            logger.info("========== save DetailsCredit Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(detailsCredit);
        } else {
            logger.warn("Requête invalide : detailsCredit est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<DetailsCredit>> findAll() {
        List<DetailsCredit> detailsCreditList = detailsCreditService.findAll();
        logger.info("+++++++++++++ list detailsCredit en cours dans le service ++++++++++++");
        return ResponseEntity.ok(detailsCreditList);
    }

    /**
     *
     * @param id
     * @param detailsCreditDetails
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<DetailsCredit> updateDetailsCredit(@PathVariable Long id, @RequestBody DetailsCredit detailsCreditDetails) {
        logger.info("+++++++++++++ UPDATE DETAILSCREDIT RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( detailsCreditDetails.getMontantCreditAssurer());
        System.out.println( detailsCreditDetails.getMontantCreditDecouvert());
        System.out.println(detailsCreditDetails.getNombreDeRemboursement());
        System.out.println(detailsCreditDetails.getMontantDesTermes());
        System.out.println(detailsCreditDetails.getNumeroCompteClient());
        System.out.println(detailsCreditDetails.getDureeTotaleCredit());
        System.out.println(detailsCreditDetails.getDiffererAmortissement());
        System.out.println(detailsCreditDetails.getDatePremierRemboursementTerme());
        System.out.println(detailsCreditDetails.getDateEffet());
        System.out.println(detailsCreditDetails.getDateEcheance());
        System.out.println("=========== RESSOURCE +===============");
        DetailsCredit updatedDetailsCredit = detailsCreditService.update(id, detailsCreditDetails);
        return new ResponseEntity<>(updatedDetailsCredit, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param detailsCreditDetails
     * @return
     */
    @PutMapping("/deleteDetailsCredit/{id}")
    public ResponseEntity<DetailsCredit> delete(@PathVariable Long id, @RequestBody DetailsCredit detailsCreditDetails) {
        logger.info("+++++++++++++ DELETE DETAILSCREDIT RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( detailsCreditDetails.getMontantCreditAssurer());
        System.out.println( detailsCreditDetails.getMontantCreditDecouvert());
        System.out.println(detailsCreditDetails.getNombreDeRemboursement());
        System.out.println(detailsCreditDetails.getMontantDesTermes());
        System.out.println(detailsCreditDetails.getNumeroCompteClient());
        System.out.println(detailsCreditDetails.getDureeTotaleCredit());
        System.out.println(detailsCreditDetails.getDiffererAmortissement());
        System.out.println(detailsCreditDetails.getDatePremierRemboursementTerme());
        System.out.println(detailsCreditDetails.getDateEffet());
        System.out.println(detailsCreditDetails.getDateEcheance());
        System.out.println("=========== DELETE RESSOURCE +===============");

        DetailsCredit updatedDetailsCredit = detailsCreditService.delete(id, detailsCreditDetails);
        return new ResponseEntity<>(updatedDetailsCredit, HttpStatus.OK);
    }





}

