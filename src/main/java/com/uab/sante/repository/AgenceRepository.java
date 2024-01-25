package com.uab.sante.repository;

import com.uab.sante.entities.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgenceRepository extends JpaRepository<Agence, Long> {

    List<Agence> findAllByIsDeletedFalse();
}
