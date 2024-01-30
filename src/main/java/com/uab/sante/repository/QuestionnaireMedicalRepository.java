package com.uab.sante.repository;

import com.uab.sante.entities.QuestionnaireMedical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionnaireMedicalRepository extends JpaRepository<QuestionnaireMedical, Long> {
    List<QuestionnaireMedical> findAllByIsDeletedFalse();
}
