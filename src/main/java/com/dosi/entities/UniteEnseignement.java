package com.dosi.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "UNITE_ENSEIGNEMENT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UniteEnseignement implements Identifiable<UniteEnseignementId>{
    @NotNull(message = "codeFormation est Requis!")
    @Valid
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

    @Column(name = "DESIGNATION", nullable = false, length = 64)
    @NotBlank(message = "designation est Requis!")
    private String designation;

    @Column(name = "SEMESTRE", nullable = false, length = 3)
    @NotBlank(message = "semestre est Requis!")
    private String semestre;

    @Column(name = "DESCRIPTION", length = 256)
    private String description;

    @Column(name = "NBH_CM")
    private Long nbhCm;

    @Column(name = "NBH_TD")
    private Short nbhTd;

    @Column(name = "NBH_TP")
    private Short nbhTp;

}