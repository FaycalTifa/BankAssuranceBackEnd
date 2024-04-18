package com.uab.sante.resource;

import com.uab.sante.entities.Agence;
import com.uab.sante.entities.Souscription;
import com.uab.sante.service.SouscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("api/souscription")
public class SouscriptionResource {

    @Autowired
    private  SouscriptionService souscriptionService;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(SouscriptionResource.class);


    @PostMapping
    public ResponseEntity<Souscription> enregistrerSouscription(@RequestBody Souscription souscription) {
        System.out.println("================================== SOUSCRIPTION RESSOURCE =====================================");
        System.out.println(souscription.getDetailsCredit());
        System.out.println("=================================== SOUSCRIPTION RESSOURCE ====================================");

        try {
            Souscription souscriptionEnregistree = souscriptionService.save(souscription);
            System.out.println("================================== SOUSCRIPTION RESSOURCE souscriptionEnregistree =====================================");
            System.out.println(souscriptionEnregistree);
            System.out.println("=================================== SOUSCRIPTION RESSOURCE souscriptionEnregistree ====================================");

            return new ResponseEntity<>(souscriptionEnregistree, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{souscriptionId}")
    public Souscription updateSouscription(@PathVariable Long souscriptionId, @RequestBody Souscription updatedSouscription)  {
        System.out.println("===================== updateSouscription Ressource ========================");
        System.out.println(souscriptionId);
        System.out.println(updatedSouscription.getIsCuperieur());
        System.out.println(updatedSouscription);
        System.out.println("===================== updateSouscription Ressource ========================");
        return souscriptionService.updateSouscription(updatedSouscription.getId(), updatedSouscription);
    }

    @GetMapping("/findAllByIsSuperieurFalse")
    public ResponseEntity<List<Souscription>> findAllByIsSuperieurFalse() {
        List<Souscription> souscriptionList = souscriptionService.findAllByIsSuperieurFalse();
        logger.info("+++++++++++++ list Agence en cours dans le Ressource ++++++++++++" + souscriptionList);
        return ResponseEntity.ok(souscriptionList);
    }

    @GetMapping("/findAllByIsSuperieurTrue")
    public ResponseEntity<List<Souscription>> findAllByIsSuperieurTrue() {
        List<Souscription> souscriptionList = souscriptionService.findAllByIsSuperieurTrue();
        logger.info("+++++++++++++ list findAllByIsSuperieurTrue en cours dans le Ressource ++++++++++++"+ souscriptionList);
                return ResponseEntity.ok(souscriptionList);
    }
}
