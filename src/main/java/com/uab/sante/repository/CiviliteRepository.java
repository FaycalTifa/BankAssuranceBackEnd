package com.uab.sante.repository;

import com.uab.sante.entities.Civilite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiviliteRepository extends JpaRepository<Civilite, Long> {
    List<Civilite> findAllByIsDeletedFalse();
}
