package com.uab.sante.service;

import com.uab.sante.entities.Banque;
import com.uab.sante.repository.BanqueRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

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
        logger.info("========== save Banque  Service ===============" + banque);
        return banque;
    }

    public List<Banque> findAll(){
        List<Banque> banques = banqueRepository.findAllByIsDeletedFalse();
        logger.info("========== List Banque  Service traiter ===============" + banques);
        return banques;
    }


    public Banque update(Long id, Banque banqueDetails) {

        Banque existingBanque = banqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La banque avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle banque
        existingBanque.setCode(banqueDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        existingBanque.setLibelle(banqueDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...

        // Enregistrez la banque mise à jour dans la base de données
        return banqueRepository.save(existingBanque);
    }

   public Banque delete(Long id, Banque banqueDetails) {

        Banque existingBanque = banqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La banque avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle banque
        existingBanque.setCode(banqueDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        existingBanque.setLibelle(banqueDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...
        existingBanque.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la banque mise à jour dans la base de données
        return banqueRepository.save(existingBanque);
    }




}
