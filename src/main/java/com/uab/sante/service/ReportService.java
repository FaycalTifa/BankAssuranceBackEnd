package com.uab.sante.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import com.itextpdf.text.pdf.PdfWriter;
import com.uab.sante.entities.*;
import com.uab.sante.repository.SouscriptionRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
public class ReportService {


    private JasperReport jasperReport;
    @Autowired
    private SouscriptionRepository souscriptionRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public void generate() {
        try {
            // Charger le fichier JasperReport (.jasper)
            File reportFile = new File("C:\\Users\\billa\\Documents\\PROJETS\\BANK ASSURANCE\\BankAssuranceBackEnd\\src\\main\\java\\com\\uab\\sante\\etat");
            if (!reportFile.exists()) {
                // Compiler le fichier JRXML en JasperReport (.jasper)
                JasperCompileManager.compileReportToFile("src/main/java/com/uab/sante/etat/biaReport.jrxml", "src/main/java/com/uab/sante/etat/biaReport.jaspe");
            }

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile);

            // Paramètres facultatifs pour le rapport
            Map<String, Object> parameters = new HashMap<>();
            // Ajouter des paramètres si nécessaire
            // parameters.put("paramètre", valeur);

            // Compiler le rapport (peut être omis si vous avez déjà le fichier .jasper)
            // JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Afficher le rapport dans une visionneuse
            JasperPrint jasperPrint = new JasperPrint(); // Remplacez-le par votre objet JasperPrint

            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public Map<String, Object> getSouscriptionDetails(Long souscriptionId) {
        Souscription souscription = souscriptionRepository.findById(souscriptionId).orElse(null);
        if (souscription == null) {
            // Gérer le cas où la souscription n'est pas trouvée
            return null;
        }

        Map<String, Object> details = new HashMap<>();
        details.put("montantCreditAssurer", souscription.getDetailsCredit().getMontantCreditAssurer());
        details.put("question2", souscription.getQuestionnaireMedical().getQuestion2());

        return details;
    }





    public byte[] generateReport(Long souscriptionId) throws JRException {
        Souscription souscription = souscriptionRepository.findById(souscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid souscription ID"));
        Personne personne = souscription.getPersonne();
        DetailsCredit detailsCredit = souscription.getDetailsCredit();
        Mandataire mandataire = souscription.getMandataire();
        QuestionnaireMedical questionnaireMedical = souscription.getQuestionnaireMedical();
        InformationEmploi informationEmploi = souscription.getInformationEmploi();
        Gestionnaire gestionnaire = souscription.getGestionnaire();
        Agence agence = souscription.getGestionnaire().getAgence();
        Banque banque = souscription.getGestionnaire().getAgence().getBanque();

        // Log the person details to ensure they are being retrieved correctly
        if (personne == null) {
            logger.error("Personne is null for Souscription ID: " + souscriptionId);
            throw new IllegalArgumentException("Personne is null for the given Souscription ID");
        } else {
            logger.info("Personne details: " + personne.toString());
        }

        // Log the credit details to ensure they are being retrieved correctly
        if (detailsCredit == null) {
            logger.error("DetailsCredit is null for Souscription ID: " + souscriptionId);
            throw new IllegalArgumentException("DetailsCredit is null for the given Souscription ID");
        } else {
            logger.info("DetailsCredit details: " + detailsCredit.toString());
        }

        // Log the mandataire to ensure they are being retrieved correctly
        if (mandataire == null) {
            logger.error("Mandataire is null for Souscription ID: " + souscriptionId);
            throw new IllegalArgumentException("Mandataire is null for the given Souscription ID");
        } else {
            logger.info("Mandataire details: " + mandataire.toString());
        }

        // Log the questionnaireMedical to ensure they are being retrieved correctly
        if (questionnaireMedical == null) {
            logger.error("QuestionnaireMedical is null for Souscription ID: " + souscriptionId);
            throw new IllegalArgumentException("QuestionnaireMedical is null for the given Souscription ID");
        } else {
            logger.info("QuestionnaireMedical details: " + questionnaireMedical.toString());
        }

        // Log the informationEmploi to ensure they are being retrieved correctly
        if (informationEmploi == null) {
            logger.error("InformationEmploi is null for Souscription ID: " + souscriptionId);
            throw new IllegalArgumentException("InformationEmploi is null for the given Souscription ID");
        } else {
            logger.info("InformationEmploi details: " + informationEmploi.toString());
        }
 // Log the informationEmploi to ensure they are being retrieved correctly
        if (banque == null) {
            logger.error("banque is null for Souscription ID: " + souscriptionId);
            throw new IllegalArgumentException("banque is null for the given Souscription ID");
        } else {
            logger.info("banque details: " + banque.toString());
        }

 // Log the informationEmploi to ensure they are being retrieved correctly
        if (agence == null) {
            logger.error("agence is null for Souscription ID: " + souscriptionId);
            throw new IllegalArgumentException("agence is null for the given Souscription ID");
        } else {
            logger.info("agence details: " + agence.toString());
        }
// Log the informationEmploi to ensure they are being retrieved correctly
        if (gestionnaire == null) {
            logger.error("gestionnaire is null for Souscription ID: " + souscriptionId);
            throw new IllegalArgumentException("gestionnaire is null for the given Souscription ID");
        } else {
            logger.info("gestionnaire details: " + gestionnaire.toString());
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("nom", personne.getNom());
        dataMap.put("civilite", personne.getCivilite().getLibelle());
        dataMap.put("prenom", personne.getPrenom());
        dataMap.put("dateDeNaissance", personne.getDateDeNaissance());
        dataMap.put("lieuDeNaissance", personne.getLieuDeNaissance());
        dataMap.put("professionActuelle", personne.getProfessionActuelle());
        dataMap.put("numeroPiecePasseport", personne.getNumeroPiecePasseport());
        dataMap.put("gestionnaire",gestionnaire.getLibelle());
        dataMap.put("agence", agence.getLibelle());
        dataMap.put("banque",banque.getLibelle());
        dataMap.put("telephone", personne.getTelephone());
        dataMap.put("email", personne.getEmail());
        dataMap.put("telephoneSecours", personne.getTelephoneSecours());
        dataMap.put("employeur", personne.getEmployeur());
        dataMap.put("dateEtablissement", personne.getDateEtablissement());
        dataMap.put("montantCreditAssurer", detailsCredit.getMontantCreditAssurer());
        dataMap.put("datePremierRemboursementTerme", detailsCredit.getDatePremierRemboursementTerme());
        dataMap.put("nombreDeRemboursement", detailsCredit.getNombreDeRemboursement());
        dataMap.put("differerAmortissement", detailsCredit.getDiffererAmortissement());
        dataMap.put("montantDesTermes", detailsCredit.getMontantDesTermes());
        dataMap.put("numeroCompteClient", detailsCredit.getNumeroCompteClient());
        dataMap.put("dureeTotaleCreditMois", detailsCredit.getDureeTotaleCreditMois());
        dataMap.put("dateEffet", detailsCredit.getDateEffet());
        dataMap.put("dateEcheance", detailsCredit.getDateEcheance());
        dataMap.put("primeTotale", mandataire.getPrimeTotale());
        dataMap.put("primeSimple", mandataire.getPrimeSimple());
        dataMap.put("primeGarantiePerteEmploi", mandataire.getPrimeGarantiePerteEmploi());
        dataMap.put("primeDiffere", mandataire.getPrimeDiffere());
        dataMap.put("primeDecouvert", mandataire.getPrimeDecouvert());
        dataMap.put("numeroDeCompteUABVie", mandataire.getNumeroDeCompteUABVie());
        dataMap.put("libelle", detailsCredit.getPeriodiciteRemboursement().getLibelle());
        dataMap.put("dateEmbauche", informationEmploi.getDateEmbauche());
        dataMap.put("adresseEmployeur", informationEmploi.getAdresseEmployeur());
        dataMap.put("telEmployeur", informationEmploi.getTelEmployeur());
        dataMap.put("numeroCNSS", informationEmploi.getNumeroCNSS());
        dataMap.put("numeroRCCMIFU", informationEmploi.getNumeroRCCMIFU());
        dataMap.put("banqueLibele", banque.getLibelle());
        if (souscription.getSignature() != null && !souscription.getSignature().isEmpty()) {
            try {
                // Décoder la signature depuis Base64
                byte[] signatureBytes = Base64.getDecoder().decode(souscription.getSignature());

                // Convertir les bytes en BufferedImage
                ByteArrayInputStream bis = new ByteArrayInputStream(signatureBytes);
                BufferedImage signatureImage = ImageIO.read(bis);

                // Ajouter l'image à la map
                dataMap.put("signatureImage", signatureImage); // Ajouter l'image en BufferedImage
            } catch (IOException e) {
                logger.error("Erreur lors de la lecture de la signature en image", e);
                // Tu peux décider de relancer une exception ou de continuer sans signature
            }
        }


        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(dataMap));

        // Log to verify the path and availability of the template
        logger.info("Loading report template from classpath: etat/uabBia.jrxml");
        InputStream reportStream = getClass().getClassLoader().getResourceAsStream("etat/uabBia.jrxml");
        if (reportStream == null) {
            logger.error("Report template not found in classpath: etat/uabBia.jrxml");
            throw new JRException("Report template not found in classpath: etat/uabBia.jrxml");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", "Détails de la Personne et du Crédit");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);

    }



}
