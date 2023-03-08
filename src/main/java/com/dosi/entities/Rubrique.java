package com.dosi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "RUBRIQUE")
public class Rubrique {
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

}