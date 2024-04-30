package com.uab.sante.resource;

import com.uab.sante.entities.Souscription;
import com.uab.sante.service.ReportService;
import com.uab.sante.service.SouscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("api/report")
public class ReportResource {


@Autowired
    private  ReportService reportService;
    @Autowired
    private SouscriptionService souscriptionService;

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generatePdfReport(@PathVariable("id") Long id) {
        Souscription souscription = souscriptionService.getSouscriptionById(id);
        byte[] pdfBytes = reportService.generatePdfReport2(souscription);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "souscription.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdfReport() {
        try {
            byte[] pdfBytes = reportService.generatePdfReport();

            // Retourner le fichier PDF sous forme de réponse HTTP
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(pdfBytes.length)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping(value = "/generate/{souscriptionId}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdfReportOk(@PathVariable Long souscriptionId) {
        try {
            byte[] pdfBytes = reportService.generatePdfReport2(souscriptionId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "souscription.pdf");
            headers.setContentLength(pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
