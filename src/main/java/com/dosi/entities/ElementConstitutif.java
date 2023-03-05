package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
    @EmbeddedId
    private ElementConstitutifId id;

    @Column(name = "DESIGNATION", nullable = false, length = 64)
    @NotBlank(message = "designation est Requis!")
    private String designation;

    @Column(name = "DESCRIPTION", length = 240)
    private String description;

    @Column(name = "NBH_CM")
    @DecimalMin(value = "1", message = "Nb d'heures de Cours Magistraux (CM) dispensées dans l'EC est entre 1 et 200")
    @DecimalMax(value = "200", message = "Nb d'heures de Cours Magistraux (CM) dispensées dans l'EC est entre 1 et 200")
    private Short nbhCm;

    @Column(name = "NBH_TD")
    @DecimalMin(value = "1", message = "Nb d'heures de Travaux Dirigés (TD) dispensées dans l'EC est entre 1 et 200")
    @DecimalMax(value = "200", message = "Nb d'heures de Travaux Dirigés (TD) dispensées dans l'EC est entre 1 et 200")
    private Short nbhTd;

    @Column(name = "NBH_TP")
    @DecimalMin(value = "1", message = "Nb d'heures de Travaux Pratiques (TP) dispensées dans l'EC est entre 1 et 200")
    @DecimalMax(value = "200", message = "Nb d'heures de Travaux Pratiques (TP) dispensées dans l'EC est entre 1 et 200")
    private Short nbhTp;

    @MapsId("codeUE")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE")
    @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JsonIgnore
    private UniteEnseignement codeUE;

    @NotNull(message = "no_enseignant est Requis!")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT" , nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Enseignant no_enseignant;

}