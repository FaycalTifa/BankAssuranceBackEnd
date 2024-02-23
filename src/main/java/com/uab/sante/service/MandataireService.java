package com.uab.sante.service;

import com.uab.sante.entities.DetailsCredit;
import com.uab.sante.entities.Mandataire;
import com.uab.sante.repository.DetailsCreditRepository;
import com.uab.sante.repository.MandataireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MandataireService {
    @Autowired
    private MandataireRepository mandataireRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(MandataireService.class);


    public Mandataire save(Mandataire mandataire){
        logger.info("ajout mandataire en cours dans le service");
        mandataire = mandataireRepository.save(mandataire);
        logger.info("========== save mandataire  Service ===============" + mandataire);
        return mandataire;
    }

    public List<Mandataire> findAll(){
        List<Mandataire> mandataires = mandataireRepository.findAllByIsDeletedFalse();
        logger.info("========== List mandataire en cours dans le service traiter ===============\" + mandataire");
        return mandataires;
    }


    public Mandataire update(Long id, Mandataire mandataireDetails) {

        Mandataire exitingMandataire = mandataireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Le mandataire avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés du mandataire existante avec les détails de la nouvelle personne
        exitingMandataire.setCapitalAssurer(mandataireDetails.getCapitalAssurer()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exitingMandataire.setPrimeGarantieDecesOuIAD(mandataireDetails.getPrimeGarantieDecesOuIAD()); // et ainsi de suite pour les autres propriétés...
        exitingMandataire.setPrimeGarantiePerteEmploi(mandataireDetails.getPrimeGarantiePerteEmploi()); // et ainsi de suite pour les autres propriétés...
        exitingMandataire.setPrimeTotale(mandataireDetails.getPrimeTotale()); // et ainsi de suite pour les autres propriétés...
        exitingMandataire.setNumeroDeCompteUABVie(mandataireDetails.getNumeroDeCompteUABVie()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        // Enregistrez le mandataire mise à jour dans la base de données
        return mandataireRepository.save(exitingMandataire);
    }

    public Mandataire delete(Long id, Mandataire mandataireDetails) {

        Mandataire exixtingMandataire = mandataireRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Le mandataire avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la detailsCredit existante avec les détails du nouveau DetailsCredit

        exixtingMandataire.setCapitalAssurer(mandataireDetails.getCapitalAssurer()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exixtingMandataire.setPrimeGarantieDecesOuIAD(mandataireDetails.getPrimeGarantieDecesOuIAD());// et ainsi de suite pour les autres propriétés...
        exixtingMandataire.setPrimeGarantiePerteEmploi(mandataireDetails.getPrimeGarantiePerteEmploi()); // et ainsi de suite pour les autres propriétés...
        exixtingMandataire.setPrimeTotale(mandataireDetails.getPrimeTotale()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exixtingMandataire.setNumeroDeCompteUABVie(mandataireDetails.getNumeroDeCompteUABVie()); // et ainsi de suite pour les autres propriétés...
        exixtingMandataire.setDeleted(true); // et ainsi de suite pour les autres propriétés...

        return mandataireRepository.save(exixtingMandataire);
    }




}

