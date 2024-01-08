package com.uab.sante.service;

import com.uab.sante.entities.PeriodiciteRemboursement;
import com.uab.sante.repository.PeriodiciteRemboursementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodiciteRemboursementService {
    @Autowired
    private PeriodiciteRemboursementRepository periodiciteRemboursementRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(BanqueService.class);


    public PeriodiciteRemboursement save(PeriodiciteRemboursement periodiciteRemboursement){
        logger.info("ajout periodiciteRemboursement en cours dans le service");
        periodiciteRemboursement = periodiciteRemboursementRepository.save(periodiciteRemboursement);
        logger.info("========== save periodiciteRemboursement  Service ===============" + periodiciteRemboursement);
        return periodiciteRemboursement;
    }

    public List<PeriodiciteRemboursement> findAll(){
        List<PeriodiciteRemboursement> periodiciteRemboursements = periodiciteRemboursementRepository.findAllByIsDeletedFalse();
        logger.info("========== List periodiciteRemboursement en cours dans le service traiter ===============\" + periodiciteRemboursements");
        return periodiciteRemboursements;
    }


    public PeriodiciteRemboursement update(Long id, PeriodiciteRemboursement periodiciteRemboursementDetails) {

        PeriodiciteRemboursement existingPeriodiciteRemboursement = periodiciteRemboursementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La  periodiciteRemboursement avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la periodiciteRemboursement existante avec les détails de la nouvelle periodiciteRemboursement
        existingPeriodiciteRemboursement.setCode(periodiciteRemboursementDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        existingPeriodiciteRemboursement.setLibelle(periodiciteRemboursementDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...

        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return periodiciteRemboursementRepository.save(existingPeriodiciteRemboursement);
    }

    public PeriodiciteRemboursement delete(Long id, PeriodiciteRemboursement periodiciteRemboursementDetails) {

        PeriodiciteRemboursement existingPeriodiciteRemboursement = periodiciteRemboursementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La periodiciteRemboursement avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la periodiciteRemboursement existante avec les détails de la nouvelle periodiciteRemboursement
        existingPeriodiciteRemboursement.setCode(periodiciteRemboursementDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        existingPeriodiciteRemboursement.setLibelle(periodiciteRemboursementDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...
        existingPeriodiciteRemboursement.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return periodiciteRemboursementRepository.save(existingPeriodiciteRemboursement);
    }




}
