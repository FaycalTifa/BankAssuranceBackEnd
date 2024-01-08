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

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Banque>> findAll() {
        List<Banque> banqueList = banqueService.findAll();
        logger.info("+++++++++++++ list banque en cours dans le service ++++++++++++");
         return ResponseEntity.ok(banqueList);
    }

    /**
     *
     * @param id
     * @param banqueDetails
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Banque> updateBanque(@PathVariable Long id, @RequestBody Banque banqueDetails) {
        logger.info("+++++++++++++ UPDATE BANQUE RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( banqueDetails.getCode());
        System.out.println( banqueDetails.getLibelle());
        System.out.println("=========== RESSOURCE +===============");
        Banque updatedBanque = banqueService.update(id, banqueDetails);
        return new ResponseEntity<>(updatedBanque, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param banqueDetails
     * @return
     */
 @PutMapping("/deleteBanque/{id}")
    public ResponseEntity<Banque> delete(@PathVariable Long id, @RequestBody Banque banqueDetails) {
        logger.info("+++++++++++++ DELETE BANQUE RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( banqueDetails.getCode());
        System.out.println( banqueDetails.getLibelle());
        System.out.println("=========== DELETE RESSOURCE +===============");
        Banque updatedBanque = banqueService.delete(id, banqueDetails);
        return new ResponseEntity<>(updatedBanque, HttpStatus.OK);
    }





}
