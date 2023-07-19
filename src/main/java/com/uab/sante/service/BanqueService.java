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


        public Banque deleted(Long banqueId) {
            Banque banque = banqueRepository.findById(banqueId).orElse(null);
            if (banque != null) {
                logger.info("========== Deleted Banque  Service traiter ===============" + banqueId);
                banque.setDeleted(true);
                return banqueRepository.save(banque);
            }
            return null;
        }

    public Banque updateBanque(Banque banque) {
        logger.info("Modification de la banque en cours dans le service");
        Optional<Banque> optionalBanque = banqueRepository.findById(banque.getId());
        if (optionalBanque.isPresent()) {
            Banque existingBanque = optionalBanque.get();
            banque = banqueRepository.save(existingBanque);
            logger.info("Banque modifiée avec succès: " + banque);
            return banque;
        } else {
            throw new IllegalArgumentException("Banque not found with ID: " + banque.getId());
        }
    }


}
