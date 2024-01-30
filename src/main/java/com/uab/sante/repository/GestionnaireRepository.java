package com.uab.sante.repository;

import com.uab.sante.entities.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Long> {
    List<Gestionnaire> findAllByIsDeletedFalse();
}
