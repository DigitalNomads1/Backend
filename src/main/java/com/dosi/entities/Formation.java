package com.dosi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;

import static com.dosi.utils.Constants.*;


@Getter
@Setter
@Entity
@Table(name = "FORMATION")
public class Formation implements Identifiable<String>{
    @Id
    @Column(name = "CODE_FORMATION", nullable = false, length = 8)
    @NotBlank(message = "CODE_FORMATION est Requis!")
    private String id;

    @Column(name = "DIPLOME", nullable = false, length = 3)
    @NotBlank(message = "diplome est Requis!")
    @Pattern(regexp = "^(L|M|D|O|N)$", message = "diplome doit être 'L', 'M', 'D', 'O' ou 'N'")
    private String diplome;


    @Column(name = "N0_ANNEE", nullable = false)
    @NotNull(message = "n0Annee est Requis!")
    @DecimalMin(value = "1", message = "N0_ANNEE doit être entre 1 et 3")
    @DecimalMax(value = "3", message = "N0_ANNEE doit être entre 1 et 3")
    private int n0Annee;

    @Column(name = "NOM_FORMATION", nullable = false, length = 64)
    @NotBlank(message = "nomFormation est Requis!")
    private String nomFormation;

    @Column(name = "DOUBLE_DIPLOME", nullable = false)
    @NotBlank(message = "doubleDiplome est Requis!")
    @Pattern(regexp = "[ON]", message = "doubleDiplome doit être 'O' ou 'N'")
    private String doubleDiplome;

    @Column(name = "DEBUT_ACCREDITATION")
    @NotNull(message = "debutAccreditation est Requis! Le format doit être "+ DATE_PATTERN +".")
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDate debutAccreditation;

    @Column(name = "FIN_ACCREDITATION")
    @NotNull(message = "finAccreditation est Requis! Le format doit être " + DATE_PATTERN +".")
    @DateTimeFormat(pattern = DATE_PATTERN)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDate finAccreditation;

    @OneToMany(mappedBy = "codeFormation", fetch = FetchType.EAGER)
    @JsonBackReference
    private List<UniteEnseignement> listUE;

    @AssertTrue(message = "La date de début d'accréditation doit être antérieure à la date de fin d'accréditation")
    public boolean isDebutAccreditationBeforeFinAccreditation() {
        if (debutAccreditation == null || finAccreditation == null) {
            return true; // skip validation if either date is null
        }
        return debutAccreditation.isBefore(finAccreditation);
    }

    @Override
    public String toString() {
        return "Formation{" +
                "id='" + id + '\'' +
                ", diplome='" + diplome + '\'' +
                ", n0Annee=" + n0Annee +
                ", nomFormation='" + nomFormation + '\'' +
                ", doubleDiplome='" + doubleDiplome + '\'' +
                ", debutAccreditation=" + debutAccreditation +
                ", finAccreditation=" + finAccreditation +
                '}';
    }
}