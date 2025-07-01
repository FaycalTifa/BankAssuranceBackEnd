package com.uab.sante.repository;

import com.uab.sante.dto.StatistiqueDTO;
import com.uab.sante.entities.Agence;
import com.uab.sante.entities.Souscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SouscriptionRepository extends JpaRepository<Souscription, Long> {

    List<Souscription> findAllByIsCuperieurIsFalse();
    List<Souscription> findAllByIsCuperieurIsTrue();

    @Query("SELECT s.gestionnaire.libelle, COUNT(s) FROM Souscription s GROUP BY s.gestionnaire.libelle")
    List<Object[]> countByGestionnaire();

    @Query("SELECT s.gestionnaire.agence.libelle, COUNT(s) FROM Souscription s GROUP BY s.gestionnaire.agence.libelle")
    List<Object[]> countByAgence();

    @Query("SELECT s.gestionnaire.agence.banque.libelle, COUNT(s) FROM Souscription s GROUP BY s.gestionnaire.agence.banque.libelle")
    List<Object[]> countByBanque();

    @Query("SELECT new com.uab.sante.dto.StatistiqueDTO(CAST(s.dateDuJour AS string), COUNT(s)) " +
            "FROM Souscription s WHERE s.isDeleted = false " +
            "GROUP BY s.dateDuJour ORDER BY s.dateDuJour")
    List<StatistiqueDTO> getSouscriptionsParDate();

    @Query("SELECT new com.uab.sante.dto.StatistiqueDTO(FUNCTION('to_char', s.dateDuJour, 'YYYY-MM-DD'), COUNT(s)) " +
            "FROM Souscription s " +
            "WHERE s.dateDuJour BETWEEN :start AND :end " +
            "GROUP BY FUNCTION('to_char', s.dateDuJour, 'YYYY-MM-DD') " +
            "ORDER BY FUNCTION('to_char', s.dateDuJour, 'YYYY-MM-DD')")
    List<StatistiqueDTO> findStatistiquesParDateFiltre(@Param("start") LocalDate start,
                                                       @Param("end") LocalDate end);

    @Query("SELECT DISTINCT s.gestionnaire.agence.banque.libelle, s.gestionnaire.agence.banque.id FROM Souscription s")
    List<Object[]> listeBanques();

    @Query("SELECT new com.uab.sante.dto.StatistiqueDTO(s.gestionnaire.agence.libelle, COUNT(s)) " +
            "FROM Souscription s WHERE s.gestionnaire.agence.banque.id = :idBanque GROUP BY s.gestionnaire.agence.libelle")
    List<StatistiqueDTO> countSouscriptionsParUneBanque(@Param("idBanque") Long idBanque);

    List<Souscription> findByDateDuJourBetween(LocalDate start, LocalDate end);


}
