package com.uab.sante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatistiqueDTO {

    private String libelle;
    private Long nombre;
}
