package com.uab.sante.resource;

import com.uab.sante.dto.StatistiqueDTO;
import com.uab.sante.repository.SouscriptionRepository;
import com.uab.sante.service.SouscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/dashboard")
public class DashboardResource {


    @Autowired
    private SouscriptionRepository souscriptionRepository;

    @Autowired
    private SouscriptionService souscriptionService;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(SouscriptionResource.class);


    @GetMapping("/gestionnaires")
    public List<StatistiqueDTO> getParGestionnaire() {
        return souscriptionRepository.countByGestionnaire().stream()
                .map(obj -> new StatistiqueDTO((String) obj[0], (Long) obj[1]))
                .collect(Collectors.toList());
    }

    @GetMapping("/agences")
    public List<StatistiqueDTO> getParAgence() {
        return souscriptionRepository.countByAgence().stream()
                .map(obj -> new StatistiqueDTO((String) obj[0], (Long) obj[1]))
                .collect(Collectors.toList());
    }

    @GetMapping("/banques")
    public List<StatistiqueDTO> getParBanque() {
        return souscriptionRepository.countByBanque().stream()
                .map(obj -> new StatistiqueDTO((String) obj[0], (Long) obj[1]))
                .collect(Collectors.toList());
    }

    @GetMapping("/souscriptions-par-date")
    public ResponseEntity<List<StatistiqueDTO>> getSouscriptionsParDate() {
        List<StatistiqueDTO> stats = souscriptionRepository.getSouscriptionsParDate();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/souscriptions-par-date-filtre")
    public ResponseEntity<List<StatistiqueDTO>> getSouscriptionsParDateFiltre(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        List<StatistiqueDTO> statistiques = souscriptionService.statistiquesParDateFiltre(start, end);
        return ResponseEntity.ok(statistiques);
    }

    @GetMapping("/liste-banques")
    public List<StatistiqueDTO> getListeBanques() {
        return souscriptionRepository.listeBanques()
                .stream()
                .map(obj -> new StatistiqueDTO((String) obj[0], (Long) obj[1])) // ou juste new DTO(obj[0], null) si tu n'as pas besoin du total
                .collect(Collectors.toList());
    }

    @GetMapping("/banqueFiltre/{id}")
    public List<StatistiqueDTO> getSouscriptionsParBanque(@PathVariable Long id) {
        return souscriptionService.statistiquesPourUneBanque(id);
    }


}
