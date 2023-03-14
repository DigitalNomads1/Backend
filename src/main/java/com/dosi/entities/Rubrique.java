package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "RUBRIQUE")
public class Rubrique implements Identifiable<Integer>{
    @Id
    @Column(name = "ID_RUBRIQUE", nullable = false)
    private Integer id;

    @Column(name = "TYPE", nullable = false, length = 10)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NO_ENSEIGNANT")
    private Enseignant noEnseignant;

    @Column(name = "DESIGNATION", nullable = false, length = 32)
    private String designation;

    @Column(name = "ORDRE")
    private Long ordre;

    @OneToMany(mappedBy = "idRubrique",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("idRubrique")
    private List<RubriqueEvaluation> listeRubriques;

    public Rubrique(Integer id) {
        this.id = id;
    }
}