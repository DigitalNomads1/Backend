package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "FORMATION")
@ToString
public class Formation implements Identifiable<String>{
    @Id
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    @NotBlank(message = "CODE_FORMATION est Requis!")
    private String id;

    @Column(name = "DIPLOME", nullable = false, length = 3)
    @NotBlank(message = "diplome est Requis!")
    private String diplome;


    @Column(name = "N0_ANNEE", nullable = false)
    @NotNull(message = "n0Annee est Requis!")
    private int n0Annee;

    @Column(name = "NOM_FORMATION", nullable = false, length = 64)
    @NotBlank(message = "nomFormation est Requis!")
    private String nomFormation;

    @Column(name = "DOUBLE_DIPLOME", nullable = false)
    @NotBlank(message = "doubleDiplome est Requis!")
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