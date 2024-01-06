package com.uab.sante.repository;

import com.uab.sante.entities.PeriodiciteRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PeriodiciteRemboursementRepository extends JpaRepository<PeriodiciteRemboursement, Long> {
    List<PeriodiciteRemboursement> findAllByIsDeletedFalse();
}
