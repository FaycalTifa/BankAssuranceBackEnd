package com.uab.sante.repository;

import com.uab.sante.entities.Personne;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonneRepository extends JpaRepository <Personne, Long> {
  List<Personne> findAllByIsDeletedFalse();
}
