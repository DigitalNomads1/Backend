package com.dosi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "QUESTION_EVALUATION")
public class QuestionEvaluation implements Identifiable<Integer>{
    @Id
    @Column(name = "ID_QUESTION_EVALUATION", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_QUESTION")
    private Question idQuestion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_QUALIFICATIF")
    private Qualificatif idQualificatif;

    @Column(name = "ORDRE", nullable = false)
    private Short ordre;

    @Column(name = "INTITULE", length = 64)
    private String intitule;

}