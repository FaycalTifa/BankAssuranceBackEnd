package com.uab.sante.service;

import com.uab.sante.entities.DetailsCredit;
import com.uab.sante.repository.DetailsCreditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailsCreditService {
    @Autowired
    private DetailsCreditRepository detailsCreditRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(DetailsCreditService.class);


    public DetailsCredit save(DetailsCredit detailsCredit){
        logger.info("ajout detailsCredit en cours dans le service");
        detailsCredit = detailsCreditRepository.save(detailsCredit);
        logger.info("========== save detailCredit  Service ===============" + detailsCredit);
        return detailsCredit;
    }

    public List<DetailsCredit> findAll(){
        List<DetailsCredit> detailsCredits = detailsCreditRepository.findAllByIsDeletedFalse();
        logger.info("========== List detailsCredit en cours dans le service traiter ===============\" + detailsCredit");
        return detailsCredits;
    }


    public DetailsCredit update(Long id, DetailsCredit detailsCreditDetails) {

        DetailsCredit exitingDetailsCredit = detailsCreditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Le detailsCredit avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle personne
        exitingDetailsCredit.setMontantCreditAssurer(detailsCreditDetails.getMontantCreditAssurer()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exitingDetailsCredit.setMontantCreditDecouvert(detailsCreditDetails.getMontantCreditDecouvert()); // et ainsi de suite pour les autres propriétés...
        exitingDetailsCredit.setNombreDeRemboursement(detailsCreditDetails.getNombreDeRemboursement()); // et ainsi de suite pour les autres propriétés...
        exitingDetailsCredit.setMontantDesTermes(detailsCreditDetails.getMontantDesTermes()); // et ainsi de suite pour les autres propriétés...
        exitingDetailsCredit.setNumeroCompteClient(detailsCreditDetails.getNumeroCompteClient()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exitingDetailsCredit.setDureeTotaleCredit(detailsCreditDetails.getDureeTotaleCredit()); // et ainsi de suite pour les autres propriétés...
        exitingDetailsCredit.setDiffererAmortissement(detailsCreditDetails.getDiffererAmortissement());
        exitingDetailsCredit.setDatePremierRemboursementTerme(detailsCreditDetails.getDatePremierRemboursementTerme());
        exitingDetailsCredit.setDateEffet(detailsCreditDetails.getDateEffet());
        exitingDetailsCredit.setDateEcheance(detailsCreditDetails.getDateEcheance());
        // Enregistrez la detailsCredit mise à jour dans la base de données
        return detailsCreditRepository.save(exitingDetailsCredit);
    }

    public DetailsCredit delete(Long id, DetailsCredit detailsCreditDetails) {

        DetailsCredit exixtingDetailsCredit = detailsCreditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Le detailsCredit avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la detailsCredit existante avec les détails du nouveau DetailsCredit

        exixtingDetailsCredit.setMontantCreditAssurer(detailsCreditDetails.getMontantCreditAssurer()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exixtingDetailsCredit.setMontantCreditDecouvert(detailsCreditDetails.getMontantCreditDecouvert());// et ainsi de suite pour les autres propriétés...
        exixtingDetailsCredit.setMontantDesTermes(detailsCreditDetails.getMontantDesTermes()); // et ainsi de suite pour les autres propriétés...
        exixtingDetailsCredit.setNumeroCompteClient(detailsCreditDetails.getNumeroCompteClient()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exixtingDetailsCredit.setDureeTotaleCredit(detailsCreditDetails.getDureeTotaleCredit()); // et ainsi de suite pour les autres propriétés...
        exixtingDetailsCredit.setDeleted(true); // et ainsi de suite pour les autres propriétés...

        return detailsCreditRepository.save(exixtingDetailsCredit);
    }




}
