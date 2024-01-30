package com.uab.sante.service;

import com.uab.sante.entities.Agence;
import com.uab.sante.entities.Gestionnaire;
import com.uab.sante.repository.AgenceRepository;
import com.uab.sante.repository.BanqueRepository;
import com.uab.sante.repository.GestionnaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionnaireService {
    @Autowired
    private GestionnaireRepository gestionnaireRepository;
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(GestionnaireService.class);


    public Gestionnaire saveGs(Gestionnaire gestionnaire){
        logger.info("ajout gestionnaire en cours dans le service");

        gestionnaire = gestionnaireRepository.save(gestionnaire);
        logger.info("========== save agence  Service ===============" + gestionnaire);
        return gestionnaire;
    }

    public Gestionnaire save(Gestionnaire gestionnaire){
        logger.info("ajout Gestionnaire en cours dans le service");
        gestionnaire = gestionnaireRepository.save(gestionnaire);
        logger.info("========== save Gestionnaire  Service ===============" + gestionnaire);
        return gestionnaire;
    }

    public List<Gestionnaire> findAll(){
        List<Gestionnaire> gestionnaires = gestionnaireRepository.findAll();
        logger.info("========== List gestionnaire en cours dans le service traiter ===============\" + agence");
        System.out.println("=====================    gestionnaireList   ========================");
        System.out.println(gestionnaires);
        System.out.println("=====================    gestionnaireList   ========================");
        return gestionnaires;
    }


    public Gestionnaire update(Long id, Gestionnaire gestionnaire) {

        Gestionnaire exitingGestionnaire = gestionnaireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La  gestionnaire avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la agence existante avec les détails de la nouvelle agence
        exitingGestionnaire.setLibelle(gestionnaire.getLibelle()); // et ainsi de suite pour les autres propriétés...
        exitingGestionnaire.setAgence(gestionnaire.getAgence()); // par exemple, si 'nom' est un champ de l'entité Banque

        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return gestionnaireRepository.save(exitingGestionnaire);
    }

    public Gestionnaire delete(Long id, Gestionnaire gestionnaire) {

        Gestionnaire exixtingGestionnaire = gestionnaireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La gestionnaire avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle banque
        exixtingGestionnaire.setLibelle(exixtingGestionnaire.getLibelle()); // par exemple, si 'nom' est un champ de l'entité Banque
        exixtingGestionnaire.setAgence(exixtingGestionnaire.getAgence()); // et ainsi de suite pour les autres propriétés...
        exixtingGestionnaire.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return gestionnaireRepository.save(exixtingGestionnaire);
    }

    }
