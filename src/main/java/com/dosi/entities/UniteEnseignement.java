package com.dosi.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "UNITE_ENSEIGNEMENT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UniteEnseignement implements Identifiable<UniteEnseignementId>{
    @Valid
    @NotNull(message = "codeFormation est Requis!")
    @EmbeddedId
    private UniteEnseignementId id;

    @MapsId("codeFormation")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODE_FORMATION", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @NotNull(message = "codeFormation est Requis!")
    private Formation codeFormation;

    @NotNull(message = "noEnseignant est Requis!")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Enseignant noEnseignant;

    @Column(name = "DESIGNATION", nullable = false, length = 256)
    @NotBlank(message = "designation est Requis!")
    private String designation;

    @Column(name = "SEMESTRE", nullable = false, length = 3)
    @NotBlank(message = "semestre est Requis!")
    @Pattern(regexp = "^(1|2|3|4|5|6|7|9|10)$", message = "Semestre doit être de '1' à '10'")
    private String semestre;

    @Column(name = "DESCRIPTION", length = 256)
    private String description;

    @Column(name = "NBH_CM")
    @DecimalMin(value = "1", message = "Nb d'heures de cours magistraux CM dispensées dans l'UE est entre 1 et 200")
    @DecimalMax(value = "200", message = "Nb d'heures de cours magistraux CM dispensées dans l'UE est entre 1 et 200")
    private Long nbhCm;

    @Column(name = "NBH_TD")
    @DecimalMin(value = "1", message = "Nb d'heures des TD dispensées dans l'UE est entre 1 et 200")
    @DecimalMax(value = "100", message = "Nb d'heures des TD dispensées dans l'UE est entre 1 et 200")
    private Short nbhTd;

    @Column(name = "NBH_TP")
    @DecimalMin(value = "1", message = "Nb d'heures des TP dispensées dans l'UE est entre 1 et 200")
    @DecimalMax(value = "100", message = "Nb d'heures des TP dispensées dans l'UE est entre 1 et 200")
    private Short nbhTp;

    @Override
    public String toString() {
        return "UniteEnseignement{" +
                "id=" + id +
                ", codeFormation=" + codeFormation +
                ", designation='" + designation + '\'' +
                ", semestre='" + semestre + '\'' +
                ", description='" + description + '\'' +
                ", nbhCm=" + nbhCm +
                ", nbhTd=" + nbhTd +
                ", nbhTp=" + nbhTp +
                '}';
    }
}