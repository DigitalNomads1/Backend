package com.dosi.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "ENSEIGNANT")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Enseignant  implements  Identifiable<Long>{
    @Id
    @Column(name = "NO_ENSEIGNANT", nullable = false)
    @SequenceGenerator(name="ens_seq", sequenceName = "ens_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ens_seq")
    private Long id;

    @Column(name = "TYPE", nullable = false, length = 5)
    @NotBlank(message = "type est Requis!")
    @Pattern(regexp = "^(MCF|INT|PR|PRAST|PRAG)$", message = "type doit être 'MCF', 'INT', 'PR', 'PRAST' ou 'PRAG'")
    private String type;

    @Column(name = "SEXE", nullable = false, length = 1)
    @NotBlank(message = "sexe est Requis!")
    @Pattern(regexp = "^(H|F)$", message = "sexe doit être 'H' ou 'F'")
    private String sexe;

    @Column(name = "NOM", nullable = false, length = 50)
    @NotBlank(message = "nom est Requis!")
    private String nom;

    @Column(name = "PRENOM", nullable = false, length = 50)
    @NotBlank(message = "prenom est Requis!")
    private String prenom;

    @Column(name = "ADRESSE", nullable = false)
    @NotBlank(message = "Adresse est Requise!")
    private String adresse;

    @Column(name = "CODE_POSTAL", nullable = false, length = 10)
    @NotBlank(message = "code postal est Requis!")
    private String codePostal;

    @Column(name = "VILLE", nullable = false)
    @NotBlank(message = "Ville est requise!")
    private String ville;

    @Column(name = "PAYS", nullable = false, length = 5)
    @NotBlank(message = "pays est Requis!")
    @Pattern(regexp = "^(FR|MA|AL|TU|BF)$", message = "pays doit être 'FR', 'MA', 'AL', 'TU' ou 'BF'")
    private String pays;

    @Column(name = "MOBILE", nullable = false, length = 20)
    @NotBlank(message = "mobile est Requis!")
    private String mobile;

    @Column(name = "TELEPHONE", length = 20)
    private String telephone;

    @Column(name = "EMAIL_UBO", nullable = false, unique = true)
    @NotBlank(message = "email ubo est Requis!")
    @Email(regexp = "[a-z0-9._%+-]+@univ-brest.fr", message="L'email doit être par exemple : nomprenom@univ-brest.fr")
    private String emailUbo;

    @Column(name = "EMAIL_PERSO", unique = true)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String emailPerso;

    @OneToMany(mappedBy = "noEnseignant", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JsonBackReference(value="noEnseignant")
    @JsonIgnore
    private List<UniteEnseignement> listUE;

    @OneToMany(mappedBy = "no_enseignant", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JsonBackReference(value="no_eseignant")
//    @JsonIgnore
    private List<ElementConstitutif> listEC;


    @OneToMany(mappedBy = "noEnseignant",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Promotion> listPromotion = new ArrayList<>();


}