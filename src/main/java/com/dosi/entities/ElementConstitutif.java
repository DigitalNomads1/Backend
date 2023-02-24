package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ELEMENT_CONSTITUTIF")
public class ElementConstitutif {
    @EmbeddedId
    private ElementConstitutifId id;

    @Column(name = "DESIGNATION", nullable = false, length = 64)
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE")
    @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private UniteEnseignement codeUE;

    @MapsId("no_enseignant")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ENSEIGNANT", referencedColumnName = "NO_ENSEIGNANT")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Enseignant no_enseignant;
}