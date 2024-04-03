package com.uab.sante.service;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.uab.sante.entities.Souscription;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


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


    public static byte[] generatePdfReport2(Souscription souscription) {
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

            // Ajouter les informations de la souscription au PDF
            document.add(new Paragraph("Taux " + souscription.getMandataire().getTauxAmortissement() +" de la souscription :"));
            document.add(new Paragraph("Nom : " + souscription.getMandataire().getPrimeTotale()));
            document.add(new Paragraph("Email : " + souscription.getPersonne().getNom()));
            // Ajoutez d'autres informations de la souscription selon vos besoins


            document.close();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
