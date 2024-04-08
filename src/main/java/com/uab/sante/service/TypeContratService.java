package com.uab.sante.service;

import com.uab.sante.entities.TypeContrat;
import com.uab.sante.repository.TypeContratRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeContratService {
    @Autowired
    private TypeContratRepository typeContratRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(TypeContratService.class);


    public TypeContrat save(TypeContrat typeContrat){
        logger.info("ajout typeContrat en cours dans le service");
        typeContrat = typeContratRepository.save(typeContrat);
        logger.info("========== save TypeContrat  Service ===============" + typeContrat);
        return typeContrat;
    }

    public List<TypeContrat> findAll(){
        List<TypeContrat> typeContrats = typeContratRepository.findAllByIsDeletedFalse();
        logger.info("========== List TypeContrat  Service traiter ===============" + typeContrats);
        return typeContrats;
    }


    public TypeContrat update(Long id, TypeContrat typeContratDetails) {

        TypeContrat existingTypeContrat = typeContratRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Le typeContrat avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la civilite existante avec les détails de la nouvelle civilite
        existingTypeContrat.setCode(typeContratDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Civilite
        existingTypeContrat.setLibelle(typeContratDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...

        // Enregistrez la civilite mise à jour dans la base de données
        return typeContratRepository.save(existingTypeContrat);
    }

    public TypeContrat delete(Long id, TypeContrat typeContratDetails) {

        TypeContrat existingTypeContrat = typeContratRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La typeContrat avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la civilite existante avec les détails de la nouvelle civilite
        existingTypeContrat.setCode(typeContratDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        existingTypeContrat.setLibelle(typeContratDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...
        existingTypeContrat.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la civilite mise à jour dans la base de données
        return typeContratRepository.save(existingTypeContrat);
    }




}
