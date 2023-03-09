package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RUBRIQUE_EVALUATION")
public class RubriqueEvaluation implements Identifiable<Integer>{
    @Id
    @Column(name = "ID_RUBRIQUE_EVALUATION", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_EVALUATION", nullable = false)
    @JsonIgnoreProperties("listeRubriques")
    private Evaluation idEvaluation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_RUBRIQUE")
    private Rubrique idRubrique;

    @Column(name = "ORDRE", nullable = false)
    private Short ordre;

    @Column(name = "DESIGNATION", length = 64)
    private String designation;

}