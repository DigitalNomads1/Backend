package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ELEMENT_CONSTITUTIF")
public class ElementConstitutif implements Identifiable<ElementConstitutifId>{
    @NotNull(message = "id est Requis!")
    @Valid
    @EmbeddedId
    private ElementConstitutifId id;

    @Column(name = "DESIGNATION", length = 64)
    private String designation;

    @Column(name = "DESCRIPTION", length = 240)
    private String description;

    @Column(name = "NBH_CM")
    private Short nbhCm;

    @Column(name = "NBH_TD")
    private Short nbhTd;

    @Column(name = "NBH_TP")
    private Short nbhTp;

    @MapsId("codeUE")
    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE", nullable = false)
    @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @NotNull(message = "codeUE est Requis!")
    private UniteEnseignement codeUE;

    @NotNull(message = "noEnseignant est Requis!")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Enseignant no_enseignant;

}