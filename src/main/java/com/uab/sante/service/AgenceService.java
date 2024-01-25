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


    public Agence save(Agence agence){
        try {
            logger.info("ajout agence en cours dans le service");
            // Ajoutez une vérification pour s'assurer que la Banque associée à l'Agence est correctement initialisée
            if (agence.getBanque() == null) {
                logger.error("Erreur lors de la sauvegarde de l'agence : Banque non initialisée.");
                throw new IllegalArgumentException("Banque non initialisée");
            }
            // Assurez-vous que la Banque associée existe en base de données
            Banque existingBanque = banqueRepository.findById(agence.getBanque().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Banque introuvable"));
            // Mettez à jour la référence à la Banque associée avec l'instance persistante
            agence.setBanque(existingBanque);
            // Sauvegarde de l'Agence
            agence = agenceRepository.save(agence);
            logger.info("========== save agence  Service ===============" + agence);
            System.out.println("=====================saveAgence========================");
            System.out.println(agence);
            System.out.println("=====================saveAgence========================");

            return agence;
        } catch (Exception e) {
            // Loguez toute exception survenue pendant la sauvegarde
            logger.error("Erreur lors de la sauvegarde de l'agence", e);
            throw e;
        }
        }


    public List<Agence> findAll(){
        List<Agence> banques = agenceRepository.findAllByIsDeletedFalse();
        logger.info("========== List agence en cours dans le service traiter ===============\" + banque");
        System.out.println("=====================agenceList========================");
        System.out.println(banques);
        System.out.println("=====================agenceList========================");
        return banques;
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
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle banque
        exixtingAgence.setLibelle(exixtingAgence.getLibelle()); // par exemple, si 'nom' est un champ de l'entité Banque
        exixtingAgence.setBanque(exixtingAgence.getBanque()); // et ainsi de suite pour les autres propriétés...
        exixtingAgence.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return agenceRepository.save(exixtingAgence);
    }

}
