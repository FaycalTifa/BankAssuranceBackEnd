package com.uab.sante.service;


import com.uab.sante.entities.Personne;
import com.uab.sante.repository.PersonneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(PersonneService.class);


    public Personne save(Personne personne){
        logger.info("ajout personne en cours dans le service");
        personne = personneRepository.save(personne);
        logger.info("========== save personne  Service ===============" + personne);
        return personne;
    }

    public List<Personne> findAll(){
        List<Personne> personnes = personneRepository.findAllByIsDeletedFalse();
        logger.info("========== List personne en cours dans le service traiter ===============\" + personne");
        return personnes;
    }


    public Personne update(Long id, Personne personneDetails) {

        Personne exitingPersonne = personneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La  personne avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la banque existante avec les détails de la nouvelle personne
        exitingPersonne.setNom(personneDetails.getNom()); // par exemple, si 'nom' est un champ de l'entité Personne
        exitingPersonne.setPrenom(personneDetails.getPrenom()); // et ainsi de suite pour les autres propriétés...
        exitingPersonne.setNomDeJeuneFille(personneDetails.getNomDeJeuneFille()); // par exemple, si 'nom' est un champ de l'entité Personne
        exitingPersonne.setDateDeNaissance(personneDetails.getDateDeNaissance()); // et ainsi de suite pour les autres propriétés...
        exitingPersonne.setLieuDeNaissance(personneDetails.getLieuDeNaissance()); // par exemple, si 'nom' est un champ de l'entité Personne
        exitingPersonne.setTaille(personneDetails.getTaille()); // et ainsi de suite pour les autres propriétés...
        exitingPersonne.setPoids(personneDetails.getPoids());
        exitingPersonne.setTension(personneDetails.getTension());
        exitingPersonne.setProfessionActuelle(personneDetails.getProfessionActuelle());
        exitingPersonne.setEmployeur(personneDetails.getEmployeur());
        exitingPersonne.setNumeroPiecePasseport(personneDetails.getNumeroPiecePasseport());
        exitingPersonne.setDateEtablissement(personneDetails.getDateEtablissement());
        exitingPersonne.setLieuEtablissement(personneDetails.getLieuEtablissement());
        exitingPersonne.setAdressePostale(personneDetails.getAdressePostale());
        exitingPersonne.setTelephone(personneDetails.getTelephone());
        exitingPersonne.setEmail(personneDetails.getEmail());
        exitingPersonne.setTelephoneSecours(personneDetails.getTelephoneSecours());
        exitingPersonne.setEmailSecours(personneDetails.getEmailSecours());
        exitingPersonne.setAdresseSecours(personneDetails.getAdresseSecours());


        // Enregistrez la personne mise à jour dans la base de données
        return personneRepository.save(exitingPersonne);
    }

    public Personne delete(Long id, Personne personneDetails) {

       Personne exixtingPersonne = personneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La personne avec l'ID fourni n'existe pas."));
        // Mettez à jour les propriétés de la personne existante avec les détails de la nouvelle personne

        exixtingPersonne.setNom(personneDetails.getNom()); // par exemple, si 'nom' est un champ de l'entité Personne
        exixtingPersonne.setPrenom(personneDetails.getPrenom()); // et ainsi de suite pour les autres propriétés...
        exixtingPersonne.setNomDeJeuneFille(personneDetails.getNomDeJeuneFille()); // par exemple, si 'nom' est un champ de l'entité Personne
        exixtingPersonne.setDateDeNaissance(personneDetails.getDateDeNaissance()); // et ainsi de suite pour les autres propriétés...
        exixtingPersonne.setLieuDeNaissance(personneDetails.getLieuDeNaissance()); // par exemple, si 'nom' est un champ de l'entité Personne
        exixtingPersonne.setTaille(personneDetails.getTaille()); // et ainsi de suite pour les autres propriétés...
        exixtingPersonne.setPoids(personneDetails.getTaille());
        exixtingPersonne.setTension(personneDetails.getTaille());
        exixtingPersonne.setProfessionActuelle(personneDetails.getProfessionActuelle());
        exixtingPersonne.setEmployeur(personneDetails.getEmployeur());
        exixtingPersonne.setNumeroPiecePasseport(personneDetails.getNumeroPiecePasseport());
        exixtingPersonne.setDateEtablissement(personneDetails.getDateEtablissement());
        exixtingPersonne.setLieuEtablissement(personneDetails.getLieuEtablissement());
        exixtingPersonne.setAdressePostale(personneDetails.getAdressePostale());
        exixtingPersonne.setTelephone(personneDetails.getTelephone());
        exixtingPersonne.setEmail(personneDetails.getEmail());
        exixtingPersonne.setTelephoneSecours(personneDetails.getTelephoneSecours());
        exixtingPersonne.setEmailSecours(personneDetails.getEmailSecours());
        exixtingPersonne.setAdresseSecours(personneDetails.getAdresseSecours());
        exixtingPersonne.setDeleted(true); // et ainsi de suite pour les autres propriétés...
        // Enregistrez la personne mise à jour dans la base de données
        return personneRepository.save(exixtingPersonne);
    }




}
