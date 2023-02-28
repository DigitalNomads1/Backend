package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.dosi.utils.Constants.DATE_PATTERN;

@Getter
@Setter
@Entity
@ToString
@Table(name = "CANDIDAT")
public class Candidat  implements  Identifiable<String>{
    @Id
    @Column(name = "NO_CANDIDAT", nullable = false, length = 50)
    @NotBlank(message = "NO Candidat est Requis!")
    private String id;

    @Column(name = "NOM", nullable = false, length = 50)
    @NotBlank(message = "Nom est requis!")
    private String nom;

    @Column(name = "PRENOM", nullable = false, length = 50)
    @NotBlank(message = "Prenom est requis!")
    private String prenom;

    @Column(name = "SEXE", nullable = false, length = 1)
    @NotBlank(message = "Sexe est eequis!")
    @Pattern(regexp = "^(H|F)$", message = "Sexe doit être 'H' ou 'F'")
    private String sexe;

    @Column(name = "DATE_NAISSANCE", nullable = false)
    @NotNull(message = "Date de naissance est requise! Le format doit être " + DATE_PATTERN +".")
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDate dateNaissance;

    @Column(name = "LIEU_NAISSANCE", nullable = false)
    @NotBlank(message = "Lieu de naissance est Requis!")
    private String lieuNaissance;

    @Column(name = "NATIONALITE", nullable = false, length = 50)
    @NotBlank(message = "Nationalité est Requise!")
    private String nationalite;

    @Column(name = "TELEPHONE", length = 20)
    private String telephone;

    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Column(name = "EMAIL", nullable = false)
    @NotBlank(message = "Email est requis!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Veuillez bien respecter le format du mail : nomprenom@email.xy ")
    private String email;

    @Column(name = "ADRESSE", nullable = false)
    @NotBlank(message = "Adresse est Requise!")
    private String adresse;

    @Column(name = "CODE_POSTAL", length = 10)
    private String codePostal;

    @Column(name = "VILLE", nullable = false)
    @NotBlank(message = "Ville est Requise!")
    private String ville;

    @Column(name = "PAYS_ORIGINE", nullable = false, length = 5)
    @NotBlank(message = "Pays d'origine est Requise!")
    @Pattern(regexp = "^(FR|MA|AL|TU|BF)$", message = "Pays doit être 'FR', 'MA', 'AL', 'TU' ou 'BF'")
    private String paysOrigine;

    @Column(name = "UNIVERSITE_ORIGINE", nullable = false, length = 6)
    @NotBlank(message = "Université d'origine est Requise!")
    @Pattern(regexp = "^(UAE|UCD|UCAM|UH2M|UIT|UH1|UIZ|UM5A|USMBA)$", message = "Universite d'Origine doit être 'FR', 'MA', 'AL', 'TU' ou 'BF'")
    private String universiteOrigine;

    @Column(name = "LISTE_SELECTION", length = 6)
    private String listeSelection;

    @Column(name = "SELECTION_NO_ORDRE")
    private Long selectionNoOrdre;

    @Column(name = "CONFIRMATION_CANDIDAT")
    private char confirmationCandidat;

    @Column(name = "DATE_REPONSE_CANDIDAT")
    private LocalDate dateReponseCandidat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ANNEE_UNIVERSITAIRE", nullable = false)
    @JoinColumn(name = "CODE_FORMATION", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonIgnore
    @NotBlank(message = "la promotion du candidat est Requise!")
    private Promotion promotion;
}