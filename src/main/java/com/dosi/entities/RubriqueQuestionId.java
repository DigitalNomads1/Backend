package com.dosi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RubriqueQuestionId implements Serializable {
    private static final long serialVersionUID = -3854269259954990806L;
    @Column(name = "ID_RUBRIQUE", nullable = false)
    private Integer idRubrique;

    @Column(name = "ID_QUESTION", nullable = false)
    private Integer idQuestion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RubriqueQuestionId entity = (RubriqueQuestionId) o;
        return Objects.equals(this.idRubrique, entity.idRubrique) &&
                Objects.equals(this.idQuestion, entity.idQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRubrique, idQuestion);
    }

}