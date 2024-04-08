package com.uab.sante.repository;

import com.uab.sante.entities.TypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeContratRepository extends JpaRepository<TypeContrat, Long> {
    List<TypeContrat> findAllByIsDeletedFalse();
}
