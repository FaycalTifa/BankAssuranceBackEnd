package com.uab.sante.resource;

import com.uab.sante.entities.Souscription;
import com.uab.sante.service.ReportService;
import com.uab.sante.service.SouscriptionService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

  /*  @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generatePdfReport(@PathVariable("id") Long id) {
        Souscription souscription = souscriptionService.getSouscriptionById(id);
        byte[] pdfBytes = reportService.generatePdfReport2(souscription);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "souscription.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }*/


    @GetMapping("/{souscriptionId}")
    public ResponseEntity<byte[]> getReport(@PathVariable Long souscriptionId) throws JRException {
        byte[] reportBytes = reportService.generateReport(souscriptionId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(reportBytes);
    }



}
