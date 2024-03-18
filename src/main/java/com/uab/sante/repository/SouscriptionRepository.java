package com.uab.sante.repository;

import com.uab.sante.entities.Agence;
import com.uab.sante.entities.Souscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SouscriptionRepository extends JpaRepository<Souscription, Long> {

}
