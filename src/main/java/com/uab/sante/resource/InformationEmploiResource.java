package com.uab.sante.resource;

import com.uab.sante.entities.InformationEmploi;
import com.uab.sante.service.InformationEmploiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/informationEmploi")
public class InformationEmploiResource {
    @Autowired
    private InformationEmploiService informationEmploiService;
    private static final Logger logger = LoggerFactory.getLogger(InformationEmploiResource.class);


    /**
     *
     * @param informationEmploi
     * @return
     */
    @PostMapping
    public ResponseEntity<InformationEmploi> createProduct(@RequestBody InformationEmploi informationEmploi) {
        logger.info("+++++++++++++ ajout info en cours dans le service ++++++++++++", informationEmploi);
        System.out.println("éééééééééééééééééééééééééééééééééééééééééééééé");
        System.out.println(informationEmploi);
        System.out.println("éééééééééééééééééééééééééééééééééééééééééééééé");
        if (informationEmploi != null) {
            informationEmploi = informationEmploiService.save(informationEmploi);
            logger.info("========== save Info Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(informationEmploi);
        } else {
            logger.warn("Requête invalide : info est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<InformationEmploi>> findAll() {
        List<InformationEmploi> informationEmploiList = informationEmploiService.findAll();
        logger.info("+++++++++++++ list information en cours dans le service ++++++++++++");
        return ResponseEntity.ok(informationEmploiList);
    }
    /**
     *
     * @param id
     * @param informationEmploiDetails
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<InformationEmploi> updateInformationEmploi(@PathVariable Long id, @RequestBody InformationEmploi informationEmploiDetails) {
        logger.info("+++++++++++++ UPDATE INFORMATIONEMPLOI RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( informationEmploiDetails.getEmployeur());
        System.out.println( informationEmploiDetails.getDateEmbauche());
        System.out.println(informationEmploiDetails.getAdresseEmployeur());
        System.out.println(informationEmploiDetails.getProfessionActuelle());
        System.out.println(informationEmploiDetails.getTypeDeContrat());
        System.out.println(informationEmploiDetails.getTelEmployeur());
        System.out.println(informationEmploiDetails.getNumeroCNSS());
        System.out.println(informationEmploiDetails.getNumeroRCCMIFU());
        System.out.println("=========== RESSOURCE +===============");
        InformationEmploi updatedInformationEmploi = informationEmploiService.update(id, informationEmploiDetails);
        return new ResponseEntity<>(updatedInformationEmploi, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param informationEmploiDetails
     * @return
     */
    @PutMapping("/deleteInformationEmploi/{id}")
    public ResponseEntity<InformationEmploi> delete(@PathVariable Long id, @RequestBody InformationEmploi informationEmploiDetails) {
        logger.info("+++++++++++++ DELETE INFORMATIONEMPLOI RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( informationEmploiDetails.getEmployeur());
        System.out.println( informationEmploiDetails.getDateEmbauche());
        System.out.println(informationEmploiDetails.getAdresseEmployeur());
        System.out.println(informationEmploiDetails.getProfessionActuelle());
        System.out.println(informationEmploiDetails.getTypeDeContrat());
        System.out.println(informationEmploiDetails.getTelEmployeur());
        System.out.println(informationEmploiDetails.getNumeroCNSS());
        System.out.println(informationEmploiDetails.getNumeroRCCMIFU());
        System.out.println("=========== DELETE RESSOURCE +===============");
        InformationEmploi updatedInformationEmploi = informationEmploiService.delete(id, informationEmploiDetails);
        return new ResponseEntity<>(updatedInformationEmploi, HttpStatus.OK);
    }





}

