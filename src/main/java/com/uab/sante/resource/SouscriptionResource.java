package com.uab.sante.resource;

import com.uab.sante.entities.Souscription;
import com.uab.sante.service.SouscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/souscription")
public class SouscriptionResource {

    @Autowired
    private  SouscriptionService souscriptionService;


    @PostMapping
    public ResponseEntity<Souscription> enregistrerSouscription(@RequestBody Souscription souscription) {
        System.out.println("================================== SOUSCRIPTION RESSOURCE =====================================");
        System.out.println(souscription);
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
}
