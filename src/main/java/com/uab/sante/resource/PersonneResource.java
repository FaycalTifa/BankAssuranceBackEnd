package com.uab.sante.resource;

import com.uab.sante.entities.Banque;
import com.uab.sante.entities.Personne;
import com.uab.sante.service.BanqueService;
import com.uab.sante.service.PersonneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/personne")
public class PersonneResource {

    @Autowired
    private PersonneService personneService;
    private static final Logger logger = LoggerFactory.getLogger(PersonneResource.class);


    /**
     *
     * @param personne
     * @return
     */
    @PostMapping
      public ResponseEntity<Personne> createProduct(@RequestBody Personne personne) {
      logger.info("+++++++++++++ ajout personne en cours dans le service ++++++++++++", personne);
      System.out.println("éééééééééééééééééééééééééééééééééééééééééééééé");
      System.out.println(personne);
      System.out.println("éééééééééééééééééééééééééééééééééééééééééééééé");
        if (personne != null) {
            personne = personneService.save(personne);
            logger.info("========== save Personne Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(personne);
        } else {
            logger.warn("Requête invalide : personne est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Personne>> findAll() {
        List<Personne> personneList = personneService.findAll();
        logger.info("+++++++++++++ list personne en cours dans le service ++++++++++++");
        return ResponseEntity.ok(personneList);
    }

    /**
     *
     * @param id
     * @param personneDetails
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long id, @RequestBody Personne personneDetails) {
        logger.info("+++++++++++++ UPDATE PERSONNE RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( personneDetails.getNom());
        System.out.println( personneDetails.getPrenom());
        System.out.println(personneDetails.getNomDeJeuneFille());
        System.out.println(personneDetails.getDateDeNaissance());
        System.out.println(personneDetails.getLieuDeNaissance());
        System.out.println(personneDetails.getTaille());
        System.out.println(personneDetails.getPoids());
        System.out.println(personneDetails.getTension());
        System.out.println(personneDetails.getProfessionActuelle());
        System.out.println(personneDetails.getEmployeur());
        System.out.println(personneDetails.getNumeroPiecePasseport());
        System.out.println(personneDetails.getDateEtablissement());
        System.out.println(personneDetails.getLieuEtablissement());
        System.out.println(personneDetails.getAdressePostale());
        System.out.println(personneDetails.getTelephone());
        System.out.println(personneDetails.getEmail());
        System.out.println(personneDetails.getTelephoneSecours());
        System.out.println(personneDetails.getEmailSecours());
        System.out.println(personneDetails.getAdresseSecours());
        System.out.println("=========== RESSOURCE +===============");
        Personne updatedPersonne = personneService.update(id, personneDetails);
        return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param personneDetails
     * @return
     */
    @PutMapping("/deletePersonne/{id}")
    public ResponseEntity<Personne> delete(@PathVariable Long id, @RequestBody Personne personneDetails) {
        logger.info("+++++++++++++ DELETE PERSONNE RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( personneDetails.getNom());
        System.out.println( personneDetails.getPrenom());
        System.out.println(personneDetails.getNomDeJeuneFille());
        System.out.println(personneDetails.getDateDeNaissance());
        System.out.println(personneDetails.getLieuDeNaissance());
        System.out.println(personneDetails.getTaille());
        System.out.println(personneDetails.getPoids());
        System.out.println(personneDetails.getTension());
        System.out.println(personneDetails.getProfessionActuelle());
        System.out.println(personneDetails.getEmployeur());
        System.out.println(personneDetails.getNumeroPiecePasseport());
        System.out.println(personneDetails.getDateEtablissement());
        System.out.println(personneDetails.getLieuEtablissement());
        System.out.println(personneDetails.getAdressePostale());
        System.out.println(personneDetails.getTelephone());
        System.out.println(personneDetails.getEmail());
        System.out.println(personneDetails.getTelephoneSecours());
        System.out.println(personneDetails.getEmailSecours());
        System.out.println(personneDetails.getAdresseSecours());

        System.out.println("=========== DELETE RESSOURCE +===============");
        Personne updatedPersonne = personneService.delete(id, personneDetails);
        return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
    }





}
