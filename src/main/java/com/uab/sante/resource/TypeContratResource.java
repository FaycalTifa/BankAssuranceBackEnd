package com.uab.sante.resource;

import com.uab.sante.entities.TypeContrat;
import com.uab.sante.service.TypeContratService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/typeContrat")
public class TypeContratResource {
    @Autowired
    private TypeContratService typeContratService;
    private static final Logger logger = LoggerFactory.getLogger(TypeContratResource.class);


    /**
     *
     * @param typeContrat
     * @return
     */
    @PostMapping
    public ResponseEntity<TypeContrat> createProduct(@RequestBody TypeContrat typeContrat) {
        logger.info("+++++++++++++ ajout typeContrrat en cours dans le service ++++++++++++");
        if (typeContrat != null) {
            typeContrat = typeContratService.save(typeContrat);
            logger.info("========== save TypeContrat Resource réussi [Code: {}] ===============", HttpStatus.CREATED.value());
            return ResponseEntity.status(HttpStatus.CREATED).body(typeContrat);
        } else {
            logger.warn("Requête invalide : typeContrat est null [Code: {}]", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TypeContrat>> findAll() {
        List<TypeContrat> typeContratList = typeContratService.findAll();
        logger.info("+++++++++++++ list typeContrat en cours dans le service ++++++++++++");
        return ResponseEntity.ok(typeContratList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeContrat> updateTypeContrat(@PathVariable Long id, @RequestBody TypeContrat typeContratDetails) {
        logger.info("+++++++++++++ UPDATE TYPECONTRAT RESSOURCE++++++++++++");
        System.out.println("=========== RESSOURCE +===============");
        System.out.println(id);
        System.out.println( typeContratDetails.getCode());
        System.out.println( typeContratDetails.getLibelle());
        System.out.println("=========== RESSOURCE +===============");
        TypeContrat updatedTypeContrat = typeContratService.update(id, typeContratDetails);
        return new ResponseEntity<>(updatedTypeContrat, HttpStatus.OK);
    }

    @PutMapping("/deleteTypeContrat/{id}")
    public ResponseEntity<TypeContrat> delete(@PathVariable Long id, @RequestBody TypeContrat typeContratDetails) {
        logger.info("+++++++++++++ DELETE TYPECONTRAT RESSOURCE++++++++++++");
        System.out.println("=========== DELETE RESSOURCE +===============");
        System.out.println(id);
        System.out.println( typeContratDetails.getCode());
        System.out.println( typeContratDetails.getLibelle());
        System.out.println("=========== DELETE RESSOURCE +===============");
        TypeContrat updatedTypeContrat = typeContratService.delete(id, typeContratDetails);
        return new ResponseEntity<>(updatedTypeContrat, HttpStatus.OK);
    }
}
