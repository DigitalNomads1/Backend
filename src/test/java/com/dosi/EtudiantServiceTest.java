package com.dosi;

import com.dosi.entities.Enseignant;
import com.dosi.entities.Etudiant;
import com.dosi.repositories.EtudiantRepository;
import com.dosi.services.EnseignantService;
import com.dosi.services.EtudiantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EtudiantServiceTest {
    @Mock
    private EtudiantRepository etudiantRepository;

    private EtudiantService etudiantService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        etudiantService = new EtudiantService(etudiantRepository);
    }

    @Test
    public void testFindAll() {
        List<Etudiant> expectedEtudiants = new ArrayList<>();
        expectedEtudiants.add(Etudiant.builder()
                .id("69")
                .nom("SALHI")
                .prenom("Anas")
                .build());
        expectedEtudiants.add(Etudiant.builder()
                .id("70")
                .nom("ABDOUH")
                .prenom("Manal")
                .build());
        when(etudiantRepository.findAll()).thenReturn(expectedEtudiants);

        List<Etudiant> actualEtudiants = etudiantService.findAll();
        assertEquals(expectedEtudiants, actualEtudiants);
    }

    @Test
    public void testCreateEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("Dupont");
        etudiant.setPrenom("Jean");
        etudiant.setEmailUbo("jean.dupont@univ-brest.fr");
        etudiant.setSexe("H");
        etudiant.setAdresse("1 Rue de l'Université");
        etudiant.setCodePostal("29200");
        etudiant.setVille("Brest");
        etudiant.setNationalite("FR");
        etudiant.setMobile("06.00.00.01.00");

        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);
        Etudiant savedEtudiant = etudiantService.create(etudiant);

        assertEquals(etudiant,savedEtudiant);
    }

}
