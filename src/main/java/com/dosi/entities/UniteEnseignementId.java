package com.dosi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.Hibernate;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniteEnseignementId implements Serializable {
    private static final long serialVersionUID = 8208650618508750641L;
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    @NotBlank(message = "codeFormation est Requis!")
    private String codeFormation;

    @Column(name = "CODE_UE", nullable = false, length = 8)
    @NotBlank(message = "codeUe est Requis!")
    private String codeUe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UniteEnseignementId entity = (UniteEnseignementId) o;
        return Objects.equals(this.codeUe, entity.codeUe) &&
                Objects.equals(this.codeFormation, entity.codeFormation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeUe, codeFormation);
    }

}