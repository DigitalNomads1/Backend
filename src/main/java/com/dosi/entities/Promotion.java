package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROMOTION")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Promotion implements Identifiable<PromotionId>{
    @Valid
    @EmbeddedId
    private PromotionId id;

    @MapsId("codeFormation")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODE_FORMATION", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @NotNull(message = "CODE_FORMATION est Requis!")
    @Valid
    private Formation codeFormation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @NotNull(message = "NO_ENSEIGNANT est Requis!")
    @Valid
    private Enseignant noEnseignant;

    @Column(name = "SIGLE_PROMOTION", length = 16)
    private String siglePromotion;

    @Column(name = "NB_MAX_ETUDIANT", nullable = false)
    @NotNull(message = "NB_MAX_ETUDIANT est Requis!")
    private Short nbMaxEtudiant;

    @Column(name = "DATE_REPONSE_LP")
    private LocalDate dateReponseLp;

    @Column(name = "DATE_REPONSE_LALP")
    private LocalDate dateReponseLalp;

    @Column(name = "DATE_RENTREE")
    private LocalDate dateRentree;

    @Column(name = "LIEU_RENTREE", length = 12)
    private String lieuRentree;

    @Column(name = "PROCESSUS_STAGE", length = 5)
    private String processusStage;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @OneToMany(mappedBy = "promotion",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Etudiant> listEtudiant = new ArrayList<>();
    @OneToMany(mappedBy = "promotion",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Candidat> ListCandidats = new ArrayList<>();

}