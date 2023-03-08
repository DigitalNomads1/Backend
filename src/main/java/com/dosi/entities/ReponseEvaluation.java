package com.dosi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "REPONSE_EVALUATION")
public class ReponseEvaluation implements Identifiable<Integer>{
    @Id
    @Column(name = "ID_REPONSE_EVALUATION", nullable = false)
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

}