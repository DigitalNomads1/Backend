package com.dosi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "FORMATION")
public class Formation {
    @Id
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    private String id;

    @Column(name = "DIPLOME", nullable = false, length = 3)
    private String diplome;

    @Column(name = "N0_ANNEE", nullable = false)
    private int n0Annee;

    @Column(name = "NOM_FORMATION", nullable = false, length = 64)
    private String nomFormation;

    @Column(name = "DOUBLE_DIPLOME", nullable = false)
    private String doubleDiplome;

    @Column(name = "DEBUT_ACCREDITATION")
    private LocalDate debutAccreditation;

    @Column(name = "FIN_ACCREDITATION")
    private LocalDate finAccreditation;

}