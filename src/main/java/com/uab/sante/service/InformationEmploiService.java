package com.uab.sante.service;

import com.uab.sante.entities.InformationEmploi;
import com.uab.sante.repository.InformationEmploiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationEmploiService {
    @Autowired
    private InformationEmploiRepository informationEmploiRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(InformationEmploiService.class);

    public InformationEmploi save(InformationEmploi informationEmploi){
        logger.info("ajout informationEmploi en cours dans le service");
        informationEmploi = informationEmploiRepository.save(informationEmploi);
        logger.info("========== save informationEmploi  Service ===============" + informationEmploi);
        return informationEmploi;
    }

    public List<InformationEmploi> findAll(){
        List<InformationEmploi> informationEmplois = informationEmploiRepository.findAllByIsDeletedFalse();
        logger.info("========== List informationEmploi en cours dans le service traiter ===============\" + informationEmploi");
        return informationEmplois;
    }
    public InformationEmploi update(Long id, InformationEmploi informationEmploiDetails) {

        InformationEmploi exitingInformationEmploi = informationEmploiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("InformationEmploi avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle information
        exitingInformationEmploi.setEmployeur(informationEmploiDetails.getEmployeur()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exitingInformationEmploi.setDateEmbauche(informationEmploiDetails.getDateEmbauche()); // et ainsi de suite pour les autres propriétés...
        exitingInformationEmploi.setAdresseEmployeur(informationEmploiDetails.getAdresseEmployeur()); // et ainsi de suite pour les autres propriétés...
        exitingInformationEmploi.setProfessionActuelle(informationEmploiDetails.getProfessionActuelle()); // et ainsi de suite pour les autres propriétés...
        exitingInformationEmploi.setTypeDeContrat(informationEmploiDetails.getTypeDeContrat()); // par exemple, si 'montantCreditAssurer' est un champ de l'entité DetailsCredit
        exitingInformationEmploi.setTelEmployeur(informationEmploiDetails.getTelEmployeur()); // et ainsi de suite pour les autres propriétés...
        exitingInformationEmploi.setNumeroCNSS(informationEmploiDetails.getNumeroCNSS());
        exitingInformationEmploi.setNumeroRCCMIFU(informationEmploiDetails.getNumeroRCCMIFU());

        // Enregistrez la detailsCredit mise à jour dans la base de données
        return informationEmploiRepository.save(exitingInformationEmploi);
    }
    public InformationEmploi delete(Long id, InformationEmploi informationEmploiDetails) {

        InformationEmploi exixtingInformationEmploi = informationEmploiRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Infotmmation Emploi avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés des informations emploi existante avec les détails de la nouvelle personne

        exixtingInformationEmploi.setEmployeur(informationEmploiDetails.getEmployeur()); // par exemple, si 'nom' est un champ de l'entité Personne
        exixtingInformationEmploi.setDateEmbauche(informationEmploiDetails.getDateEmbauche()); // et ainsi de suite pour les autres propriétés...
        exixtingInformationEmploi.setAdresseEmployeur(informationEmploiDetails.getAdresseEmployeur()); // par exemple, si 'nom' est un champ de l'entité Personne
        exixtingInformationEmploi.setProfessionActuelle(informationEmploiDetails.getProfessionActuelle()); // et ainsi de suite pour les autres propriétés...
        exixtingInformationEmploi.setTypeDeContrat(informationEmploiDetails.getTypeDeContrat()); // par exemple, si 'nom' est un champ de l'entité Personne
        exixtingInformationEmploi.setTelEmployeur(informationEmploiDetails.getTelEmployeur()); // et ainsi de suite pour les autres propriétés...
        exixtingInformationEmploi.setNumeroCNSS(informationEmploiDetails.getNumeroCNSS());
        exixtingInformationEmploi.setNumeroRCCMIFU(informationEmploiDetails.getNumeroRCCMIFU());
        exixtingInformationEmploi.setIsDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la personne mise à jour dans la base de données
        return informationEmploiRepository.save(exixtingInformationEmploi);
    }




}



