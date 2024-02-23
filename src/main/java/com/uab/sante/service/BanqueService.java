package com.uab.sante.service;

import com.uab.sante.entities.Banque;
import com.uab.sante.entities.PeriodiciteRemboursement;
import com.uab.sante.repository.BanqueRepository;
import com.uab.sante.repository.PeriodiciteRemboursementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BanqueService {
    @Autowired
    private BanqueRepository banqueRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(BanqueService.class);


    public Banque save(Banque banque){
        logger.info("ajout banque en cours dans le service");

        banque = banqueRepository.save(banque);
        logger.info("========== save banque  Service ===============" + banque);
        return banque;
    }

    public List<Banque> findAll(){
        List<Banque> banques = banqueRepository.findAllByIsDeletedFalse();
        logger.info("========== List banque en cours dans le service traiter ===============\" + banque");
        return banques;
    }

    public Optional<Banque> findById(Long id) {
        return banqueRepository.findById(id);
    }


    public Banque update(Long id, Banque banqueDetails) {

        Banque exitingBanque = banqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La  banque avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle banque
        exitingBanque.setCode(banqueDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        exitingBanque.setLibelle(banqueDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...

        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return banqueRepository.save(exitingBanque);
    }

    public Banque delete(Long id, Banque banqueDetails) {

        Banque exixtingBanque = banqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La ba,que avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle banque
        exixtingBanque.setCode(banqueDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        exixtingBanque.setLibelle(banqueDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...
        exixtingBanque.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la periodiciteRemboursement mise à jour dans la base de données
        return banqueRepository.save(exixtingBanque);
    }




}
