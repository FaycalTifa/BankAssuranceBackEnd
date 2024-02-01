package com.uab.sante.service;

import com.uab.sante.entities.Agence;
import com.uab.sante.entities.Banque;
import com.uab.sante.repository.AgenceRepository;
import com.uab.sante.repository.BanqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AgenceService {

    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private BanqueRepository banqueRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(AgenceService.class);


    public Agence saveAg(Agence agence){
        logger.info("ajout banque en cours dans le service");

        agence = agenceRepository.save(agence);
        logger.info("========== save banque  Service ===============" + agence);
        return agence;
    }

    public Agence save(Agence agence){
        logger.info("ajout Agence en cours dans le service");
        agence = agenceRepository.save(agence);
        logger.info("========== save Agence  Service ===============" + agence);
        return agence;
    }

    public List<Agence> findAll(){
        List<Agence> agences = agenceRepository.findAllByIsDeletedFalse();
        logger.info("========== List agence en cours dans le service traiter ===============\" + banque");
        System.out.println("=====================    agenceList   ========================");
        System.out.println(agences);
        System.out.println("=====================    agenceList   ========================");
        return agences;
    }


    public Agence update(Long id, Agence agence) {

        Agence exitingAgence = agenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La  agence avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle banque
        exitingAgence.setLibelle(agence.getLibelle()); // et ainsi de suite pour les autres propriétés...
        exitingAgence.setBanque(agence.getBanque()); // par exemple, si 'nom' est un champ de l'entité Banque

        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return agenceRepository.save(exitingAgence);
    }

    public Agence delete(Long id, Agence agence) {

        Agence exixtingAgence = agenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La agence avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de agence existante avec les détails de la nouvelle agence

        exixtingAgence.setLibelle(agence.getLibelle()); // par exemple, si 'nom' est un champ de l'entité Banque
        exixtingAgence.setBanque(agence.getBanque()); // et ainsi de suite pour les autres propriétés...
        exixtingAgence.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return agenceRepository.save(exixtingAgence);
    }

}
