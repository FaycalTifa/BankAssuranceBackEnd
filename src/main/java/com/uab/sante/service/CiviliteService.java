package com.uab.sante.service;

import com.uab.sante.entities.Civilite;
import com.uab.sante.repository.CiviliteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiviliteService {
    @Autowired
    private CiviliteRepository civiliteRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(CiviliteService.class);


    public Civilite save(Civilite civilite){
        logger.info("ajout civilite en cours dans le service");
        civilite = civiliteRepository.save(civilite);
        logger.info("========== save Civilite  Service ===============" + civilite);
        return civilite;
    }

    public List<Civilite> findAll(){
        List<Civilite> civilites = civiliteRepository.findAllByIsDeletedFalse();
        logger.info("========== List Civilite  Service traiter ===============" + civilites);
        return civilites;
    }


    public Civilite update(Long id, Civilite civiliteDetails) {

        Civilite existingCivilite = civiliteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La civilite avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la civilite existante avec les détails de la nouvelle civilite
        existingCivilite.setCode(civiliteDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Civilite
        existingCivilite.setLibelle(civiliteDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...

        // Enregistrez la civilite mise à jour dans la base de données
        return civiliteRepository.save(existingCivilite);
    }

    public Civilite delete(Long id, Civilite civiliteDetails) {

        Civilite existingCivilite = civiliteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La civilite avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la civilite existante avec les détails de la nouvelle civilite
        existingCivilite.setCode(civiliteDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        existingCivilite.setLibelle(civiliteDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...
        existingCivilite.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la civilite mise à jour dans la base de données
        return civiliteRepository.save(existingCivilite);
    }




}
