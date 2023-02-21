package com.dosi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Table(name = "ENSEIGNANT_JN")
public class EnseignantJn {
    @Column(name = "JN_OPERATION", nullable = false, length = 3)
    private String jnOperation;

    @Column(name = "JN_ORACLE_USER", nullable = false, length = 30)
    private String jnOracleUser;

    @Column(name = "JN_DATETIME", nullable = false)
    private LocalDate jnDatetime;

    @Column(name = "JN_NOTES", length = 240)
    private String jnNotes;

    @Column(name = "JN_APPLN", length = 35)
    private String jnAppln;

    @Column(name = "JN_SESSION")
    private Long jnSession;

    @Column(name = "NO_ENSEIGNANT", nullable = false)
    private Short noEnseignant;

    @Column(name = "\"TYPE\"", length = 5)
    private String type;

    @Column(name = "SEXE", length = 1)
    private String sexe;

    @Column(name = "NOM", length = 50)
    private String nom;

    @Column(name = "PRENOM", length = 50)
    private String prenom;

    @Column(name = "ADRESSE")
    private String adresse;

    @Column(name = "CODE_POSTAL", length = 10)
    private String codePostal;

    @Column(name = "VILLE")
    private String ville;

    @Column(name = "PAYS", length = 5)
    private String pays;

    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Column(name = "TELEPHONE", length = 20)
    private String telephone;

    @Column(name = "EMAIL_UBO")
    private String emailUbo;

    @Column(name = "EMAIL_PERSO")
    private String emailPerso;

}