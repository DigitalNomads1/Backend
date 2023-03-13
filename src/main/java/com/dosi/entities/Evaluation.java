package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "EVALUATION")
public class Evaluation implements Identifiable<Integer>{
    @Id
    @Column(name = "ID_EVALUATION", nullable = false)
    @SequenceGenerator(name="eve_seq", sequenceName = "eve_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="eve_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "NO_ENSEIGNANT", nullable = false)
    private Enseignant noEnseignant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "CODE_FORMATION", referencedColumnName = "CODE_FORMATION"),
            @JoinColumn(name = "CODE_UE", referencedColumnName = "CODE_UE")
    })
    @JsonIgnoreProperties({"noEnseignant", "codeFormation", "noEnseignant", "evaluationList"})
    private UniteEnseignement uniteEnseignement;

    @Column(name = "ANNEE_UNIVERSITAIRE", nullable = false, length = 10)
    @NotBlank(message = "ANNEE_UNIVERSITAIRE est Requis!")
    private String anneeUniversitaire;

    @Column(name = "NO_EVALUATION", nullable = false)
    private Short noEvaluation;

    @Column(name = "DESIGNATION", nullable = false, length = 16)
    @Size(max = 16, message = "La désignation doit contenir au maximum {max} caractères")
    private String designation;

    @Column(name = "ETAT", nullable = false, length = 3)
    @Size(max = 3, message = "L'état doit contenir au maximum {max} caractères")
    private String etat;

    @Column(name = "PERIODE", length = 64)
    @Size(max = 64, message = "La période doit contenir au maximum {max} caractères")
    private String periode;

    @Column(name = "DEBUT_REPONSE", nullable = false)
    private LocalDate debutReponse;

    @Column(name = "FIN_REPONSE", nullable = false)
    private LocalDate finReponse;

    @OneToMany(mappedBy = "idEvaluation", fetch = FetchType.EAGER)
//    @JsonIgnoreProperties("idEvaluation")
    private List<RubriqueEvaluation> listeRubriques;

    @ManyToOne
    @JoinColumn(name = "ANNEE_UNIVERSITAIRE", nullable = false, insertable=false, updatable=false)
    @JoinColumn(name = "CODE_FORMATION", nullable = false, insertable=false, updatable=false)
    @JsonIgnoreProperties("listEvaluations")
    private Promotion promotion;

    @Transient
    private double moyenne;
/*
    @OneToMany(mappedBy = "idEvaluation",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("idEvaluation")
    private List<ReponseEvaluation> reponses;*/

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", noEnseignant=" + noEnseignant +
                ", uniteEnseignement=" + uniteEnseignement +
                ", anneeUniversitaire='" + anneeUniversitaire + '\'' +
                ", noEvaluation=" + noEvaluation +
                ", designation='" + designation + '\'' +
                ", etat='" + etat + '\'' +
                ", periode='" + periode + '\'' +
                ", debutReponse=" + debutReponse +
                ", finReponse=" + finReponse +
                ", promotion=" + promotion +
                ", moyenne=" + moyenne +
                '}';
    }
}