package com.uab.sante.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import com.itextpdf.text.pdf.PdfWriter;
import com.uab.sante.entities.Souscription;
import com.uab.sante.repository.SouscriptionRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;



@Service
public class ReportService {


    private JasperReport jasperReport;
    @Autowired
    private SouscriptionRepository souscriptionRepository;
    public void generate() {
        try {
            // Charger le fichier JasperReport (.jasper)
            File reportFile = new File("C:\\Users\\billa\\Documents\\PROJETS\\BANK ASSURANCE\\BankAssuranceBackEnd\\src\\main\\java\\com\\uab\\sante\\etat");
            if (!reportFile.exists()) {
                // Compiler le fichier JRXML en JasperReport (.jasper)
                JasperCompileManager.compileReportToFile("src/main/java/com/uab/sante/etat/biaReport.jrxml",
                        "src/main/java/com/uab/sante/etat/biaReport.jaspe");
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


            document.add(new Paragraph( souscription.getPersonne().getCivilite().getLibelle() + "  " + souscription.getPersonne().getNom() + "   " + souscription.getPersonne().getPrenom()     ));
            document.add(new Paragraph("Né le   : " + souscription.getPersonne().getDateDeNaissance() + "  à  " + souscription.getPersonne().getLieuDeNaissance()));
            document.add(new Paragraph("Profession actuelle : " + souscription.getPersonne().getProfessionActuelle() + "                   Employeur : " + souscription.getPersonne().getEmployeur()));
            document.add(new Paragraph("Pièce d'identité/Passeport  : " + souscription.getPersonne().getNumeroPiecePasseport() + "     Fait le  : " + souscription.getPersonne().getDateEtablissement() + "      à : " + souscription.getPersonne().getLieuEtablissement()));
            document.add(new Paragraph("Taille : " + souscription.getPersonne().getTaille() + "          Poids : " + souscription.getPersonne().getPoids() + "    Tension : " + souscription.getPersonne().getTension()  ));
            document.add(new Paragraph("Adresse Postale  : " + souscription.getPersonne().getAdressePostale() + "      Telephone : " + souscription.getPersonne().getTelephone() + "       Email : " + souscription.getPersonne().getEmail()));
            document.add(new Paragraph("Adresse Secours : " + souscription.getPersonne().getAdresseSecours() + "      Telephone Secours : " + souscription.getPersonne().getTelephoneSecours() + "                     Email Secours : " + souscription.getPersonne().getEmailSecours()));

            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- " ));

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

            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------  " ));

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

            document.add(new Paragraph("4. Avez-vous fait récemment le test de l'hépatite B et ou SIDA ? " ));
            document.add(new Paragraph("    Réponse : " + souscription.getQuestionnaireMedical().getQuestion4() + "      Detail : " + souscription.getQuestionnaireMedical().getDetail4()));

            document.add(new Paragraph("5. Etes-vous titulaire d'une pension d'invalidité ? "));
            document.add(new Paragraph("    Réponse : " + souscription.getQuestionnaireMedical().getQuestion5() + "      Detail : " + souscription.getQuestionnaireMedical().getDetail5()));

            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------  " ));
            Chunk chunk3 = new Chunk("INFORMATIONS PERSONNELLES LIEES A LA GARANTIE PERTE EMPLOI                   ", font);
            chunk3.setBackground(BaseColor.CYAN); // Couleur de fond bleu clair
            Paragraph paragraph3 = new Paragraph(chunk3);
            document.add(paragraph3);

            document.add(new Paragraph("Employeur : " + souscription.getInformationEmploi().getEmployeur() + "                                                 Profession actuelle : " + souscription.getInformationEmploi().getProfessionActuelle()));
            document.add(new Paragraph("Date d'embauche  : " + souscription.getInformationEmploi().getDateEmbauche() + "                                     Numero CNSS : " + souscription.getInformationEmploi().getNumeroCNSS()));
            document.add(new Paragraph("Tel Employeur : " + souscription.getInformationEmploi().getTelEmployeur() + "                                     Numero RCCM et IFU : " + souscription.getInformationEmploi().getNumeroRCCMIFU()));
            document.add(new Paragraph("Adresse Employeur : " + souscription.getInformationEmploi().getAdresseEmployeur() ));

            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------  " ));
            Chunk chunk4 = new Chunk("RESERVE A LA BANQUE OU AU MANDATAIRE UAB VIE                        ", font);
            chunk4.setBackground(BaseColor.CYAN); // Couleur de fond bleu clair
            Paragraph paragraph4 = new Paragraph(chunk3);
            document.add(paragraph4);
            document.add(new Paragraph("Capital assuré : " + souscription.getMandataire().getCapitalAssurer() + "                                                  Prime Découvert : " + souscription.getMandataire().getPrimeDecouvert()));
            document.add(new Paragraph("Prime Perte d'emploi : " + souscription.getMandataire().getPrimeGarantiePerteEmploi() + "                                       Prime Amortissable : " + souscription.getMandataire().getPrimeSimple() ));
            document.add(new Paragraph("Prime Différé : " + souscription.getMandataire().getPrimeDiffere() + "                                           Prime Totale : " + souscription.getMandataire().getPrimeTotale()));
            document.add(new Paragraph("Numero de compte UAB VIE : " + souscription.getMandataire().getNumeroDeCompteUABVie()));
            document.add(new Paragraph("                                                                                                                                                                                                                                                                                                                                        "));

            // Ajoutez d'autres informations de la souscription selon vos besoins

            document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------  " ));
            document.add(new Paragraph("                                                                                                                                                                                                                                                                                                                                        "));

            document.add(new Paragraph("                                                           Signature "));
            document.add(new Paragraph("                                                                                                                                                                                                                                                                                                                                        "));

            document.add(new Paragraph("Ahérent                                            bénéficiere                                     Assureur/Mandataire"));

            document.add(new Paragraph("                                                                                                                                               *" +
                    "                                                                                                                                                                                 " +
                    "                                                                                                                                              " +
                    "                                                                                                                                                                  " +
                    "        "));

            document.add(new Paragraph("                                                           Fait le : " + LocalDate.now() ));


            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] generatePdfReport() throws JRException, FileNotFoundException {
        String fileName = "src/main/java/com/uab/sante/etat/biaReport.jrxml"; // Remplacez par le chemin de votre fichier JRXML
        String outFile = "C:\\Users\\billa\\Documents\\DB\\fichier.pdf"; // Chemin où le PDF généré sera enregistré

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("title", "Hi, I am Title");

        // Compilation du rapport JRXML en fichier JasperReport (.jasper)
        JasperCompileManager.compileReportToFile(fileName);

        // Chargement du rapport compilé
        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(fileName.replace(".jrxml", ".jasper"));

        // Remplissage du rapport avec des données
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, new JREmptyDataSource());

        // Export du rapport au format PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        // Retourner le tableau d'octets représentant le fichier PDF généré
        return outputStream.toByteArray();
    }

    public static void main(String[] args) {
        try {
            byte[] pdfBytes = generatePdfReport();
            // Vous pouvez faire ce que vous voulez avec le tableau d'octets représentant le fichier PDF, par exemple, l'écrire dans un fichier
            // FileOutputStream fos = new FileOutputStream("chemin/vers/le/fichier.pdf");
            // fos.write(pdfBytes);
            // fos.close();
            System.out.println("Report Generated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public byte[] generatePdfReport2(Long souscriptionId) throws JRException {
        try {
            // Charger le fichier JasperReport (.jasper)
            String reportPath =  "src/main/java/com/uab/sante/etat/biaTest.jasper";
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportPath);

            // Récupérer les informations de la souscription à partir de son identifiant
            Souscription souscription = souscriptionRepository.findById(souscriptionId)
                    .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

            // Préparer les paramètres pour le rapport
            // Ici, vous pouvez passer les objets directement, ou extraire les données nécessaires de la souscription
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nom", souscription.getPersonne().getNom());
            parameters.put("prenon", souscription.getPersonne().getPrenom());
            parameters.put("questionnaireMedical", souscription.getQuestionnaireMedical());
            parameters.put("Mandataire", souscription.getMandataire());
            parameters.put("InformationEmploi", souscription.getInformationEmploi());
            // Ajoutez d'autres paramètres selon vos besoins

            // Remplir le rapport avec les données de la souscription
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            // Exporter le rapport en PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JRException("Erreur lors de la génération du rapport PDF: " + e.getMessage());
        }
    }


}
