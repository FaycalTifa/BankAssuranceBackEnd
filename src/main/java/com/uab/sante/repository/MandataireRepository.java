package com.uab.sante.repository;

import com.uab.sante.entities.Mandataire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MandataireRepository extends JpaRepository<Mandataire, Long> {
    List<Mandataire> findAllByIsDeletedFalse();
}
