package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REPONSE_EVALUATION")
public class ReponseEvaluation implements Identifiable<Integer>{
    @Id
    @Column(name = "ID_REPONSE_EVALUATION", nullable = false)
    @SequenceGenerator(name="rpe_seq", sequenceName = "rpe_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="rpe_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_EVALUATION", nullable = false)
    private Evaluation idEvaluation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NO_ETUDIANT")
    private Etudiant noEtudiant;

    @Column(name = "COMMENTAIRE", length = 512)
    private String commentaire;

    @Column(name = "NOM", length = 32)
    private String nom;

    @Column(name = "PRENOM", length = 32)
    private String prenom;

    @OneToMany(mappedBy = "idReponseEvaluation",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("idReponseEvaluation")
    private List<ReponseQuestion> reponseQuestionList;

    @Override
    public String toString() {
        return "ReponseEvaluation{" +
                "id=" + id +
                ", idEvaluation=" + idEvaluation +
                ", noEtudiant=" + noEtudiant +
                ", commentaire='" + commentaire + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", reponseQuestionList=" + reponseQuestionList +
                '}';
    }
}