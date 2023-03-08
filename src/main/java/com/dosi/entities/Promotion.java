package com.dosi.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.dosi.utils.Constants.DATE_PATTERN;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROMOTION")
public class Promotion implements Identifiable<PromotionId>{
    @Valid
    @EmbeddedId
    private PromotionId id;

    @MapsId("codeFormation")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CODE_FORMATION", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    @NotNull(message = "Code formation est Requis!")
    private Formation codeFormation;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private Enseignant noEnseignant;

    @Column(name = "SIGLE_PROMOTION", length = 16)
    @NotNull(message = "Le Sigle promotion est Requis!")
    private String siglePromotion;

    @Column(name = "NB_MAX_ETUDIANT", nullable = false)
    @NotNull(message = "NB_MAX_ETUDIANT est Requis!")
    private Short nbMaxEtudiant;

    @Column(name = "DATE_REPONSE_LP")
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDate dateReponseLp;

    @Column(name = "DATE_REPONSE_LALP")
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDate dateReponseLalp;

    @Column(name = "DATE_RENTREE")
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDate dateRentree;

    @Column(name = "LIEU_RENTREE", length = 12)
    private String lieuRentree;

    @Column(name = "PROCESSUS_STAGE", length = 5)
    private String processusStage;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @OneToMany(mappedBy = "promotion",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("promotion")
    private List<Etudiant> listEtudiants = new ArrayList<>();


    @OneToMany(mappedBy = "promotion",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("promotion")
    private List<Candidat> listCandidats = new ArrayList<>();

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", codeFormation=" + codeFormation +
                ", noEnseignant=" + noEnseignant +
                ", siglePromotion='" + siglePromotion +
                ", nbMaxEtudiant=" + nbMaxEtudiant +
                ", dateReponseLp=" + dateReponseLp +
                ", dateReponseLalp=" + dateReponseLalp +
                '}';
    }
}