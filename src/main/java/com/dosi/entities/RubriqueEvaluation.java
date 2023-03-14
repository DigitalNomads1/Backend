package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RUBRIQUE_EVALUATION")
public class RubriqueEvaluation implements Identifiable<Integer>{
    @Id
    @Column(name = "ID_RUBRIQUE_EVALUATION", nullable = false)
    @SequenceGenerator(name="rev_seq", sequenceName = "rev_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rev_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_EVALUATION", nullable = false)
    @JsonIgnoreProperties("listeRubriques")
    private Evaluation idEvaluation;

//    @MapsId("idRubrique")
    @ManyToOne
    @JoinColumn(name = "ID_RUBRIQUE")
    @JsonIgnoreProperties("rubriqueEvaluationList")
    private Rubrique idRubrique;

    @Column(name = "ORDRE", nullable = false)
    private Short ordre;

    @Column(name = "DESIGNATION", length = 64)
    private String designation;

    @OneToMany(mappedBy = "rubriqueEvaluation")
    @JsonIgnoreProperties("rubriqueEvaluation")
    private List<QuestionEvaluation> questionEvaluationList;

    @Override
    public String toString() {
        return "RubriqueEvaluation{" +
                "id=" + id +
                ", idEvaluation=" + idEvaluation +
                ", idRubrique=" + idRubrique +
                ", ordre=" + ordre +
                ", designation='" + designation + '\'' +
                ", questionEvaluationList=" + questionEvaluationList +
                '}';
    }
}