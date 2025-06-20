package com.uab.sante.service;

import com.uab.sante.Security.TokenService;
import com.uab.sante.dto.StatistiqueDTO;
import com.uab.sante.entities.*;
import com.uab.sante.repository.*;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.*;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.Properties;


@Service
@Transactional
public class SouscriptionService {


    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private DetailsCreditRepository detailsCreditRepository;
    @Autowired
    private PersonneService personneService;
    @Autowired
    private DetailsCreditService detailsCreditService;
    @Autowired
    private SouscriptionRepository souscriptionRepository;
    @Autowired
    private QuestionnaireMedicalRepository questionnaireMedicalRepository;
    @Autowired
    private MandataireRepository mandataireRepository;
    @Autowired
    private InformationEmploiRepository informationEmploiRepository;
    @Autowired
    private BanqueRepository banqueRepository;
    @Autowired
    private GestionnaireRepository gestionnaireRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(SouscriptionService.class);


    public static final String GREETING = "tif@8899";
    // Déclaration d'une constante de type char
    public static char[] NEW_LINE = {'\n'};
    private String base64;


    @Transactional

    public IDToken getKeycloakToken() {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new IllegalStateException("Aucun utilisateur connecté");
        }

        KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
        return principal.getKeycloakSecurityContext().getIdToken();
    }

    public AccessToken getTokenInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Utilisateur non authentifié !");
        }

        if (authentication.getPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
            return principal.getKeycloakSecurityContext().getToken();
        }

        throw new RuntimeException("Le principal n'est pas une instance de KeycloakPrincipal");
    }

    public Souscription save(Souscription souscription) throws MessagingException {

        logger.info("###########################3 authentication ############################" );

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = (Jwt) authentication.getPrincipal();

            String username = jwt.getClaimAsString("preferred_username"); // parfois un ID chiffré
            String email = jwt.getClaimAsString("email");
            String nom = jwt.getClaimAsString("family_name"); // nom de famille
            String prenom = jwt.getClaimAsString("given_name"); // prénom
            String nomComplet = jwt.getClaimAsString("name"); // parfois "Prénom Nom"

            souscription.setUsername(username);
            souscription.setEmail(email);

            logger.info("###########################3 token.getPreferredUsername() ############################" );
            logger.info("Nom d'utilisateur Keycloak : {}", username);
            logger.info("email Keycloak : {}", email);
            logger.info("###########################3 token.getPreferredUsername() ############################" );
            logger.debug("Contenu complet du JWT : {}", jwt.getClaims());

        System.out.println("###########################3 SOUSCRIPTION SERVICE ############################");
        Personne personne = souscription.getPersonne();
        DetailsCredit detailsCredit = souscription.getDetailsCredit();
        QuestionnaireMedical questionnaireMedical = souscription.getQuestionnaireMedical();
        Mandataire mandataire = souscription.getMandataire();
        InformationEmploi informationEmploi = souscription.getInformationEmploi();
        Gestionnaire gestionnaire = souscription.getGestionnaire();

// Initialisez les identifiants avec des valeurs temporaires
        personne.setId(999L); // Remplacez 1L par une valeur appropriée
        detailsCredit.setId(999L); // Remplacez 2L par une valeur appropriée

        // Initialisez de même les autres identifiants

        // Enregistrer la personne et les détails du crédit
        personne = personneRepository.save(personne);
        detailsCredit = detailsCreditRepository.save(detailsCredit);
        questionnaireMedical = questionnaireMedicalRepository.save(questionnaireMedical);
        mandataire = mandataireRepository.save(mandataire);
        informationEmploi = informationEmploiRepository.save(informationEmploi);
     // banque = banqueRepository.save(banque);
            if (gestionnaire != null && gestionnaire.getId() != null) {
                gestionnaire = gestionnaireRepository.findById(gestionnaire.getId()).orElseThrow(() -> new IllegalArgumentException("Banque non trouvée"));
            } else {
                throw new IllegalArgumentException("Banque ID ne peut pas être null");
            }


        // Mettre à jour la souscription avec les références aux enregistrements nouvellement créés
        souscription.setPersonne(personne);
        souscription.setDetailsCredit(detailsCredit);
        souscription.setQuestionnaireMedical(questionnaireMedical);
        souscription.setMandataire(mandataire);
        souscription.setInformationEmploi(informationEmploi);
        souscription.setGestionnaire(gestionnaire);

            if (souscription.getSignature() != null && souscription.getSignature().startsWith("data:image")) {
                String base64 = souscription.getSignature().split(",")[1];
                souscription.setSignature(base64); // Stocke uniquement la partie utile
                System.out.println("********** save Signature reçue (base64), longueur = " + base64.length());
            }

            System.out.println("********** USERNAME SAVE = " + souscription.getUsername());
        return souscriptionRepository.save(souscription);
    } catch (Exception e) {
            System.out.println("================================== SOUSCRIPTION SERVICE Exception =====================================");
            // Journalisez l'exception pour le débogage
            e.printStackTrace();
            throw new MessagingException("/////////////////////////////////////////////Erreur lors de l'enregistrement de la souscription: " + e.getMessage());
        }
    }

    public Souscription updateSouscription(Long souscriptionId, Souscription nouvelleSouscription) {
        Souscription souscription = souscriptionRepository.findById(souscriptionId)
                .orElseThrow(() -> new RuntimeException("Souscription non trouvée avec l'ID : " + souscriptionId));

        // Update Personne
        updateEntityFields(souscription.getPersonne(), nouvelleSouscription.getPersonne(), personneRepository);

        // Update DetailsCredit
        updateEntityFields(souscription.getDetailsCredit(), nouvelleSouscription.getDetailsCredit(), detailsCreditRepository);

        // Update QuestionnaireMedical
        updateEntityFields(souscription.getQuestionnaireMedical(), nouvelleSouscription.getQuestionnaireMedical(), questionnaireMedicalRepository);
        if (souscription.getSignature() != null && souscription.getSignature().startsWith("data:image")) {
            String base64 = souscription.getSignature().split(",")[1];
            souscription.setSignature(base64); // Stocke uniquement la partie utile
            System.out.println(" ************* update Signature reçue (base64), longueur = " + base64.length());

        }
        // Update Mandataire
        updateEntityFields(souscription.getMandataire(), nouvelleSouscription.getMandataire(), mandataireRepository);

        // Update InformationEmploi
        updateEntityFields(souscription.getInformationEmploi(), nouvelleSouscription.getInformationEmploi(), informationEmploiRepository);
        updateEntityFields(souscription.getGestionnaire(), nouvelleSouscription.getGestionnaire(), gestionnaireRepository);

        // Update getIsCuperieur
        souscription.setIsCuperieur(nouvelleSouscription.getIsCuperieur());

        return souscriptionRepository.save(souscription);
    }

    private <T> void updateEntityFields(T existingEntity, T newEntity, JpaRepository<T, Long> repository) {
        if (existingEntity != null && newEntity != null) {
            Field[] fields = existingEntity.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(newEntity);
                    if (value != null) {
                        field.set(existingEntity, value);
                    }
                } catch (IllegalAccessException e) {
                    // Handle exception
                }
            }
            repository.save(existingEntity);
        }
    }
    public Souscription getSouscriptionById(Long id) {
        return souscriptionRepository.findById(id).orElse(null);
    }

        public List<Souscription> findAllByIsSuperieurFalse(){
        List<Souscription> souscriptions = souscriptionRepository.findAllByIsCuperieurIsFalse();
        logger.info("========== List souscriptions en cours dans le service traiter ===============");
        return souscriptions;
    }

 public List<Souscription> findAllByIsSuperieurTrue(){
        List<Souscription> souscriptions = souscriptionRepository.findAllByIsCuperieurIsTrue();
        logger.info("========== List souscriptions en cours dans le service traiter ===============");
        return souscriptions;
    }

    public List<StatistiqueDTO> statistiquesParDateFiltre(LocalDate start, LocalDate end) {
        return souscriptionRepository.findStatistiquesParDateFiltre(start, end);
    }

    public List<StatistiqueDTO> statistiquesPourUneBanque(Long idBanque) {
        return souscriptionRepository.countSouscriptionsParUneBanque(idBanque);
    }



}
