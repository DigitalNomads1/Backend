package com.dosi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "EVALUATION")
public class Evaluation {
    @Id
    @Column(name = "ID_EVALUATION", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION"),
            @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE")
    })
    private UniteEnseignement uniteEnseignement;

    @Column(name = "NO_EVALUATION", nullable = false)
    private Short noEvaluation;

    @Column(name = "DESIGNATION", nullable = false, length = 16)
    private String designation;

    @Column(name = "ETAT", nullable = false, length = 3)
    private String etat;

    @Column(name = "PERIODE", length = 64)
    private String periode;

    @Column(name = "DEBUT_REPONSE", nullable = false)
    private LocalDate debutReponse;

    @Column(name = "FIN_REPONSE", nullable = false)
    private LocalDate finReponse;

}