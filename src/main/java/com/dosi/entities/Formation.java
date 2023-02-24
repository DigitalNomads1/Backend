package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
/*
    @OneToMany(mappedBy = "codeFormation", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<UniteEnseignement> listUE;*/

}