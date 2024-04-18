package com.uab.sante.service;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.uab.sante.entities.Souscription;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import sun.font.FontFamily;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static java.awt.Font.createFont;


@Service
public class ReportService {




    private JasperReport jasperReport;
/*
    public byte[] generatePdfReport(Souscription souscription) {
        try {
            // Charger le fichier Jasper depuis le chemin d'accès
            InputStream jasperStream = getClass().getResourceAsStream("chemin/vers/votre/fichier.jasper");

            // Compiler le fichier Jasper en un objet JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            // Remplir le modèle de rapport avec les données de la souscription
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("souscription", souscription); // Ajoutez la souscription comme paramètre au rapport
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println(souscription);
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);

            // Exporter le rapport rempli au format PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            pdfExporter.exportReport();

            return outputStream.toByteArray();
        } catch (Exception e) {
            // Gérer les exceptions
            e.printStackTrace();
            return null;
        }
    }
*/



    // Déclaration de la famille de police
    /*private static final Font.FontFamily fontFamily = Font.FontFamily.TIMES_ROMAN;*/



    public byte[] generatePdfReport(Souscription souscription) {
        try {
            // Remplir le modèle de rapport avec les données de la souscription
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("souscription", souscription); // Ajouter la souscription comme paramètre au rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(loadJasperFile(), parameters);

            // Exporter le rapport rempli au format PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            return outputStream.toByteArray();
        } catch (Exception e) {
            // Gérer les exceptions
            e.printStackTrace();
            return null;
        }
    }

    private JasperReport loadJasperFile() throws JRException {
        // Charger le fichier Jasper depuis le chemin d'accès
        InputStream jasperStream = getClass().getResourceAsStream("/path/to/your/report.jasper");
        return (JasperReport) JRLoader.loadObject(jasperStream);
    }


    public static <BaseColor> byte[] generatePdfReport2(Souscription souscription) {
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


            // Création de la police avec la couleur verte
//            Font greenFont = createFont(12, Font.BOLD);




            // Ajouter les informations de la souscription au PDF


            document.add(new Paragraph("RENSEIGNEMENT ADHERENT" ));

            document.add(new Paragraph("Civilité : " + souscription.getPersonne().getCivilite()));

            document.add(new Paragraph("Nom : " + souscription.getPersonne().getNom() + "                                                     Prenom : " + souscription.getPersonne().getPrenom()));

            document.add(new Paragraph("Nom De Jeune Fille : " + souscription.getPersonne().getNomDeJeuneFille() + "                                   Lieu de Naissance : " + souscription.getPersonne().getLieuDeNaissance()));

            document.add(new Paragraph("Date de Naissance : " + souscription.getPersonne().getDateDeNaissance()));

            document.add(new Paragraph("Taille : " + souscription.getPersonne().getTaille() + "                                  Poids : " + souscription.getPersonne().getPoids() +    "                            Tension : " + souscription.getPersonne().getTension()));


            document.add(new Paragraph("Profession actuelle : " + souscription.getPersonne().getProfessionActuelle() + "                                Employeur : " + souscription.getPersonne().getEmployeur()));

            document.add(new Paragraph("Pièce d'identité/Passeport  : " + souscription.getPersonne().getNumeroPiecePasseport() + "      Date d'établissement : " + souscription.getPersonne().getDateEtablissement() + "                     Lieu d'établissement : " + souscription.getPersonne().getLieuEtablissement()));

            document.add(new Paragraph("Adresse Postale  : " + souscription.getPersonne().getAdressePostale() + "      Telephone : " + souscription.getPersonne().getTelephone() + "                     Email : " + souscription.getPersonne().getEmail()));

            document.add(new Paragraph("Adresse Secours : " + souscription.getPersonne().getAdresseSecours() + "      Telephone Secours : " + souscription.getPersonne().getTelephoneSecours() + "                     Email Secours : " + souscription.getPersonne().getEmailSecours()));
            document.add(new Paragraph("Email : " + souscription.getPersonne().getNom()));


            document.add(new Paragraph("CARACTERISTIQUE DU CREDIT" ));
            document.add(new Paragraph("Montant du Crédit à Assurer : " + souscription.getDetailsCredit().getMontantCreditAssurer() + "                                                     Durée Totale du Credit : " + souscription.getDetailsCredit().getDureeTotaleCreditMois()));
            document.add(new Paragraph("Montant du Découvert à Assurer : " + souscription.getDetailsCredit().getMontantCreditDecouvert() + "                                                Différer d'amortissement : " + souscription.getDetailsCredit().getDiffererAmortissement()));
                                                                                                                                document.add(new Paragraph("Date 1er Remboursement terme  : " + souscription.getDetailsCredit().getDatePremierRemboursementTerme()));
            document.add(new Paragraph("Nombre de remboursement : " + souscription.getDetailsCredit().getNombreDeRemboursement()));
            document.add(new Paragraph("Periodicité Remboursement : " + souscription.getDetailsCredit().getPeriodiciteRemboursement()));
                                                                                                                                document.add(new Paragraph("Date Effet : " + souscription.getDetailsCredit().getDateEffet()));
            document.add(new Paragraph("Montant des termes : " + souscription.getDetailsCredit().getMontantDesTermes()));
            document.add(new Paragraph("Numero de compte du client : " + souscription.getDetailsCredit().getNumeroCompteClient() + "                                                     Date d'échéance : " + souscription.getDetailsCredit().getDateEcheance()));


            document.add(new Paragraph("QUESTIONNAIRE MEDICAL" ));
            document.add(new Paragraph("1. Avez-vous des antécédents médicaux et ou churirgicaux? : " + souscription.getQuestionnaireMedical().getQuestion1()));
            document.add(new Paragraph("2. Etes vous porteur d'une maladie chronique nécessitant un traitement? : " + souscription.getQuestionnaireMedical().getQuestion2()));
            document.add(new Paragraph("3. Suivez-vous un traitement médical? : " + souscription.getQuestionnaireMedical().getQuestion3()));
            document.add(new Paragraph("4. Avez-vous fait récemment le test de l'hépatite B et ou SIDA? : " + souscription.getQuestionnaireMedical().getQuestion4()));
            document.add(new Paragraph("5. Etes-vous titulaire d'une pension d'invalidité? : " + souscription.getQuestionnaireMedical().getQuestion5()));

            document.add(new Paragraph("INFORMATIONS PERSONNELLES LIEES A LA GARANTIE PERTE EMPLOI" ));
            document.add(new Paragraph("Employeur : " + souscription.getInformationEmploi().getEmployeur() + "                                                     Profession actuelle : " + souscription.getInformationEmploi().getProfessionActuelle()));
            document.add(new Paragraph("Date d'embauche  : " + souscription.getInformationEmploi().getDateEmbauche() + "                                           Numero CNSS : " + souscription.getInformationEmploi().getNumeroCNSS()));
            document.add(new Paragraph("Adresse Employeur : " + souscription.getInformationEmploi().getAdresseEmployeur() + "      Tel Employeur : " + souscription.getInformationEmploi().getTelEmployeur() + "                     Numero RCCM et IFU : " + souscription.getInformationEmploi().getNumeroRCCMIFU()));


            document.add(new Paragraph("RESERVE A LA BANQUE OU AU MANDATAIRE UAB VIE" ));
            document.add(new Paragraph("Capital assuré : " + souscription.getMandataire().getCapitalAssurer()));
            document.add(new Paragraph("Prime Garantie décès ou IAD : " + souscription.getMandataire().getPrimeGarantieDecesOuIAD()));
            document.add(new Paragraph("Prime Garantie Perte d'emploi: " + souscription.getMandataire().getPrimeGarantiePerteEmploi()));
            document.add(new Paragraph("Prime Totale : " + souscription.getMandataire().getPrimeTotale()));
            document.add(new Paragraph("Numero de compte UAB VIE : " + souscription.getMandataire().getNumeroDeCompteUABVie()));


            // Ajoutez d'autres informations de la souscription selon vos besoins


            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
