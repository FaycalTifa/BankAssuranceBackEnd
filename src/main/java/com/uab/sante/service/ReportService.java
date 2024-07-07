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

import java.io.*;
import java.time.LocalDate;
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

    public static byte[] generatePdfReport2(Souscription souscription) {
        LocalDate dateDuJour = LocalDate.now();
        String imagePath = "src/main/java/com/uab/sante/img/logo.jpg";
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();


// Ajouter une image au PDF
            Image image = Image.getInstance(imagePath); // Chemin vers l'image
            image.scaleToFit(100, 100); // Redimensionne l'image à une taille maximale de 300x300 pixels
            document.add(image);

// Créez un nouveau paragraphe avec le texte en gras

            Font font = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC, BaseColor.BLACK);
            Chunk chunk = new Chunk("RENSEIGNEMENT ADHERENT                                                                                             ", font);
            chunk.setBackground(BaseColor.CYAN); // Couleur de fond bleu clair
            Paragraph paragraph = new Paragraph(chunk);
            document.add(paragraph);


            document.add(new Paragraph(souscription.getPersonne().getCivilite().getLibelle() + "  " + souscription.getPersonne().getNom() + "   " + souscription.getPersonne().getPrenom()));
            document.add(new Paragraph("Né le   : " + souscription.getPersonne().getDateDeNaissance() + "  à  " + souscription.getPersonne().getLieuDeNaissance()));
            document.add(new Paragraph("Profession actuelle : " + souscription.getPersonne().getProfessionActuelle() + "                   Employeur : " + souscription.getPersonne().getEmployeur()));
            document.add(new Paragraph("Pièce d'identité/Passeport  : " + souscription.getPersonne().getNumeroPiecePasseport() + "     Fait le  : " + souscription.getPersonne().getDateEtablissement() + "      à : " + souscription.getPersonne().getLieuEtablissement()));
            document.add(new Paragraph("Taille : " + souscription.getPersonne().getTaille() + "          Poids : " + souscription.getPersonne().getPoids() + "    Tension : " + souscription.getPersonne().getTension()));
            document.add(new Paragraph("Adresse Postale  : " + souscription.getPersonne().getAdressePostale() + "      Telephone : " + souscription.getPersonne().getTelephone() + "       Email : " + souscription.getPersonne().getEmail()));
            document.add(new Paragraph("Adresse Secours : " + souscription.getPersonne().getAdresseSecours() + "      Telephone Secours : " + souscription.getPersonne().getTelephoneSecours() + "                     Email Secours : " + souscription.getPersonne().getEmailSecours()));

            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));

            Chunk chunk1 = new Chunk("CARACTERISTIQUE DU CREDIT                                                                                             ", font);
            chunk1.setBackground(BaseColor.CYAN); // Couleur de fond bleu clair
            Paragraph paragraph1 = new Paragraph(chunk1);
            document.add(paragraph1);
            document.add(new Paragraph("Montant du Crédit à Assurer : " + souscription.getDetailsCredit().getMontantCreditAssurer() + "                     Durée Totale du Credit : " + souscription.getDetailsCredit().getDureeTotaleCreditMois()));
            document.add(new Paragraph("Montant du Découvert à Assurer : " + souscription.getDetailsCredit().getMontantCreditDecouvert() + "                            Différer d'amortissement : " + souscription.getDetailsCredit().getDiffererAmortissement()));
            document.add(new Paragraph("Date 1er Remboursement terme  : " + souscription.getDetailsCredit().getDatePremierRemboursementTerme() + "           Nombre de remboursement : " + souscription.getDetailsCredit().getNombreDeRemboursement()));
            document.add(new Paragraph("Periodicité Remboursement : " + souscription.getDetailsCredit().getPeriodiciteRemboursement().getLibelle() + "                        Date Effet : " + souscription.getDetailsCredit().getDateEffet()));
            document.add(new Paragraph("Montant des termes : " + souscription.getDetailsCredit().getMontantDesTermes() + "                                  Numero de compte du client : " + souscription.getDetailsCredit().getNumeroCompteClient()));
            document.add(new Paragraph("Date d'échéance : " + souscription.getDetailsCredit().getDateEcheance()));

            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------  "));

            Chunk chunk2 = new Chunk("QUESTIONNAIRE MEDICAL                                                                                             ", font);
            chunk2.setBackground(BaseColor.CYAN); // Couleur de fond bleu clair
            Paragraph paragraph2 = new Paragraph(chunk1);
            document.add(paragraph2);
            document.add(new Paragraph("1. Avez-vous des antécédents médicaux et ou churirgicaux ?"));
            document.add(new Paragraph("    Réponse : " + souscription.getQuestionnaireMedical().getQuestion1() + "      Detail : " + souscription.getQuestionnaireMedical().getDetail1()));

            document.add(new Paragraph("2. Etes vous porteur d'une maladie chronique nécessitant un traitement ?"));
            document.add(new Paragraph("    Réponse : " + souscription.getQuestionnaireMedical().getQuestion2() + "      Detail : " + souscription.getQuestionnaireMedical().getDetail2()));

            document.add(new Paragraph("3. Suivez-vous un traitement médical ?"));
            document.add(new Paragraph("    Réponse : " + souscription.getQuestionnaireMedical().getQuestion3() + "      Detail : " + souscription.getQuestionnaireMedical().getDetail3()));

            document.add(new Paragraph("4. Avez-vous fait récemment le test de l'hépatite B et ou SIDA ? "));
            document.add(new Paragraph("    Réponse : " + souscription.getQuestionnaireMedical().getQuestion4() + "      Detail : " + souscription.getQuestionnaireMedical().getDetail4()));

            document.add(new Paragraph("5. Etes-vous titulaire d'une pension d'invalidité ? "));
            document.add(new Paragraph("    Réponse : " + souscription.getQuestionnaireMedical().getQuestion5() + "      Detail : " + souscription.getQuestionnaireMedical().getDetail5()));

            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------  "));
            Chunk chunk3 = new Chunk("INFORMATIONS PERSONNELLES LIEES A LA GARANTIE PERTE EMPLOI                   ", font);
            chunk3.setBackground(BaseColor.CYAN); // Couleur de fond bleu clair
            Paragraph paragraph3 = new Paragraph(chunk3);
            document.add(paragraph3);

            document.add(new Paragraph("Employeur : " + souscription.getInformationEmploi().getEmployeur() + "                                                 Profession actuelle : " + souscription.getInformationEmploi().getProfessionActuelle()));
            document.add(new Paragraph("Date d'embauche  : " + souscription.getInformationEmploi().getDateEmbauche() + "                                     Numero CNSS : " + souscription.getInformationEmploi().getNumeroCNSS()));
            document.add(new Paragraph("Tel Employeur : " + souscription.getInformationEmploi().getTelEmployeur() + "                                     Numero RCCM et IFU : " + souscription.getInformationEmploi().getNumeroRCCMIFU()));
            document.add(new Paragraph("Adresse Employeur : " + souscription.getInformationEmploi().getAdresseEmployeur()));

            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------  "));
            Chunk chunk4 = new Chunk("RESERVE A LA BANQUE OU AU MANDATAIRE UAB VIE                        ", font);
            chunk4.setBackground(BaseColor.CYAN); // Couleur de fond bleu clair
            Paragraph paragraph4 = new Paragraph(chunk3);
            document.add(paragraph4);
            document.add(new Paragraph("Capital assuré : " + souscription.getMandataire().getCapitalAssurer() + "                                                  Prime Découvert : " + souscription.getMandataire().getPrimeDecouvert()));
            document.add(new Paragraph("Prime Perte d'emploi : " + souscription.getMandataire().getPrimeGarantiePerteEmploi() + "                                       Prime Amortissable : " + souscription.getMandataire().getPrimeSimple()));
            document.add(new Paragraph("Prime Différé : " + souscription.getMandataire().getPrimeDiffere() + "                                           Prime Totale : " + souscription.getMandataire().getPrimeTotale()));
            document.add(new Paragraph("Numero de compte UAB VIE : " + souscription.getMandataire().getNumeroDeCompteUABVie()));
            document.add(new Paragraph("                                                                                                                                                                                                                                                                                                                                        "));

            // Ajoutez d'autres informations de la souscription selon vos besoins

            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------  "));
            document.add(new Paragraph("                                                                                                                                                                                                                                                                                                                                        "));

            document.add(new Paragraph("                                                           Signature "));
            document.add(new Paragraph("                                                                                                                                                                                                                                                                                                                                        "));

            document.add(new Paragraph("Ahérent                                            bénéficiere                                     Assureur/Mandataire"));

            document.add(new Paragraph("                                                                                                                                               *" + "                                                                                                                                                                                 " + "                                                                                                                                              " + "                                                                                                                                                                  " + "        "));

            document.add(new Paragraph("                                                           Fait le : " + LocalDate.now()));


            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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


    public byte[] generateReport1(Long souscriptionId) throws JRException {
        Souscription souscription = souscriptionRepository.findById(souscriptionId).orElseThrow(() -> new IllegalArgumentException("Invalid souscription ID"));
        Personne personne = souscription.getPersonne();


        // Log the person details to ensure they are being retrieved correctly
        if (personne == null) {
            logger.error("Personne is null for Souscription ID: " + souscriptionId);
        } else {
            logger.info("Personne details: " + personne.toString());
        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(personne));

        InputStream reportStream = getClass().getResourceAsStream("etat/BIA_Letter.jrxml");
        if (reportStream == null) {
            throw new JRException("Report template not found");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nom", "Détails de la Personne");
        parameters.put("prenom", "Détails de la Personne");
        parameters.put("nomDeJeuneFille", "Détails de la Personne");
        parameters.put("dateDeNaissance", "Détails de la Personne");
        parameters.put("lieuDeNaissance", "Détails de la Personne");
        parameters.put("professionActuelle", "Détails de la Personne");
        parameters.put("employeur", "Détails de la Personne");
        parameters.put("numeroPiecePasseport", "Détails de la Personne");
        parameters.put("telephone", "Détails de la Personne");
        parameters.put("lieuEtablissement", "Détails de la Personne");
        parameters.put("numeroPiecePasseport", "Détails de la Personne");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }


    public byte[] generateReport(Long souscriptionId) throws JRException {
        Souscription souscription = souscriptionRepository.findById(souscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid souscription ID"));
        Personne personne = souscription.getPersonne();
        DetailsCredit detailsCredit = souscription.getDetailsCredit();
        Mandataire mandataire = souscription.getMandataire();
        QuestionnaireMedical questionnaireMedical = souscription.getQuestionnaireMedical();
        InformationEmploi informationEmploi = souscription.getInformationEmploi();

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

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("nom", personne.getNom());
        dataMap.put("civilite", personne.getCivilite().getLibelle());
        dataMap.put("prenom", personne.getPrenom());
        dataMap.put("dateDeNaissance", personne.getDateDeNaissance());
        dataMap.put("lieuDeNaissance", personne.getLieuDeNaissance());
        dataMap.put("professionActuelle", personne.getProfessionActuelle());
        dataMap.put("numeroPiecePasseport", personne.getNumeroPiecePasseport());
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
        dataMap.put("numeroRCCMIFU", informationEmploi.getNumeroRCCMIFU());

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
