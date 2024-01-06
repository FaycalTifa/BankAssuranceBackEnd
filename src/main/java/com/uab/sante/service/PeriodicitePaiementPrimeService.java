package com.uab.sante.service;

import com.uab.sante.entities.PeriodicitePaiementPrime;
import com.uab.sante.entities.PeriodiciteRemboursement;
import com.uab.sante.repository.PeriodicitePaiementPrimeRepository;
import com.uab.sante.repository.PeriodiciteRemboursementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodicitePaiementPrimeService {
    @Autowired
    private PeriodicitePaiementPrimeRepository periodicitePaiementPrimeRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(PeriodicitePaiementPrimeRepository.class);


    public PeriodicitePaiementPrime save(PeriodicitePaiementPrime periodicitePaiementPrime){
        logger.info("ajout periodicitePaiementPrime en cours dans le service");
        periodicitePaiementPrime = periodicitePaiementPrimeRepository.save(periodicitePaiementPrime);
        logger.info("========== save periodicitePaiementPrime  Service ===============" + periodicitePaiementPrime);
        return periodicitePaiementPrime;
    }

    public List<PeriodicitePaiementPrime> findAll(){
        List<PeriodicitePaiementPrime> periodicitePaiementPrimes = periodicitePaiementPrimeRepository.findAllByIsDeletedFalse();
        logger.info("========== List periodicitePaiementPrime en cours dans le service traiter ===============\" + periodicitePaiementPrimes");
        return periodicitePaiementPrimes;
    }


    public PeriodicitePaiementPrime update(Long id, PeriodicitePaiementPrime periodicitePaiementPrimeDDetails) {

        PeriodicitePaiementPrime existingPeriodicitePaiementPrime = periodicitePaiementPrimeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La  periodicitePaiementPrime avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la periodicitePaieme,tPrimes existante avec les détails de la nouvelle periodicitePaiementPrime
        existingPeriodicitePaiementPrime.setCode(periodicitePaiementPrimeDDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité Banque
        existingPeriodicitePaiementPrime.setLibelle(periodicitePaiementPrimeDDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...

        // Enregistrez la periodicitePaiementPrime mise à jour dans la base de données
        return periodicitePaiementPrimeRepository.save(existingPeriodicitePaiementPrime);
    }

    public PeriodicitePaiementPrime delete(Long id, PeriodicitePaiementPrime periodicitePaiementPrimeDetails) {

        PeriodicitePaiementPrime existingPeriodicitePaiementprime = periodicitePaiementPrimeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La periodicitePaiementPrime avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la periodicitePaiementPrime existante avec les détails de la nouvelle periodicitePaiementPrime
        existingPeriodicitePaiementprime.setCode(periodicitePaiementPrimeDetails.getCode()); // par exemple, si 'nom' est un champ de l'entité PerioicitePaiementPrime
        existingPeriodicitePaiementprime.setLibelle(periodicitePaiementPrimeDetails.getLibelle()); // et ainsi de suite pour les autres propriétés...
        existingPeriodicitePaiementprime.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la periodicitePaiementPrime mise à jour dans la base de données
        return periodicitePaiementPrimeRepository.save(existingPeriodicitePaiementprime);
    }




}

