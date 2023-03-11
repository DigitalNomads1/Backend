package com.dosi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "RUBRIQUE_QUESTION")
public class RubriqueQuestion implements Identifiable<RubriqueQuestionId>{
    @EmbeddedId
    private RubriqueQuestionId id;

    @MapsId("idRubrique")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_RUBRIQUE", nullable = false)
    private Rubrique idRubrique;

    @MapsId("idQuestion")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_QUESTION", nullable = false)
    private Question idQuestion;

    @Column(name = "ORDRE", nullable = false)
    private Long ordre;

    @Override
    public String toString() {
        return "RubriqueQuestion{" +
                "id=" + id +
                ", idRubrique=" + idRubrique +
                ", idQuestion=" + idQuestion +
                ", ordre=" + ordre +
                '}';
    }

}