package com.uab.sante.repository;

import com.uab.sante.entities.DetailsCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsCreditRepository extends JpaRepository<DetailsCredit, Long> {
    List<DetailsCredit> findAllByIsDeletedFalse();
}
