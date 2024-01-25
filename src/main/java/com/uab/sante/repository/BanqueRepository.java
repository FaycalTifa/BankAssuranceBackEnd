package com.uab.sante.repository;

import com.uab.sante.entities.Banque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BanqueRepository extends JpaRepository<Banque, Long> {

    List<Banque> findAllByIsDeletedFalse();
}
