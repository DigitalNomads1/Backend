package com.dosi.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ENSEIGNANT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Enseignant {
    @Id
    @Column(name = "NO_ENSEIGNANT", nullable = false)
    private Long id;

    @Column(name = "TYPE", nullable = false, length = 5)
    private String type;

    @Column(name = "SEXE", nullable = false, length = 1)
    private String sexe;

    @Column(name = "NOM", nullable = false, length = 50)
    private String nom;

    @Column(name = "PRENOM", nullable = false, length = 50)
    private String prenom;

    @Column(name = "ADRESSE", nullable = false)
    private String adresse;

    @Column(name = "CODE_POSTAL", nullable = false, length = 10)
    private String codePostal;

    @Column(name = "VILLE", nullable = false)
    private String ville;

    @Column(name = "PAYS", nullable = false, length = 5)
    private String pays;

    @Column(name = "MOBILE", nullable = false, length = 20)
    private String mobile;

    @Column(name = "TELEPHONE", length = 20)
    private String telephone;

    @Column(name = "EMAIL_UBO", nullable = false)
    private String emailUbo;

    @Column(name = "EMAIL_PERSO")
    private String emailPerso;

    @OneToMany(mappedBy = "noEnseignant", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JsonBackReference(value="noEnseignant")
//    @JsonIgnore
    private List<UniteEnseignement> listUE;

}