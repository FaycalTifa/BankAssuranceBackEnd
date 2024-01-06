package com.uab.sante.repository;

import com.uab.sante.entities.PeriodicitePaiementPrime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeriodicitePaiementPrimeRepository extends JpaRepository<PeriodicitePaiementPrime, Long> {
  List<PeriodicitePaiementPrime> findAllByIsDeletedFalse();
}
