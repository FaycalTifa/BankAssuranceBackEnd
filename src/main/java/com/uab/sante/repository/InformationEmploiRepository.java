package com.uab.sante.repository;

import com.uab.sante.entities.InformationEmploi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InformationEmploiRepository extends JpaRepository<InformationEmploi, Long> {
    List<InformationEmploi> findAllByIsDeletedFalse();
}
