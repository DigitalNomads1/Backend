package com.dosi;

import com.dosi.entities.Enseignant;
import com.dosi.entities.Etudiant;
import com.dosi.repositories.EtudiantRepository;
import com.dosi.services.EnseignantService;
import com.dosi.services.EtudiantService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EtudiantServiceTest {
    @Mock
    private EtudiantRepository etudiantRepository;
    @InjectMocks
    private EtudiantService etudiantService;

    private Etudiant etudiant1;
    private Etudiant etudiant2;
    private List<Etudiant> etudiants;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        etudiant1 = new Etudiant();
        etudiant1.setId("1");
        etudiant1.setNom("Dupont");
        etudiant1.setPrenom("Jean");
        etudiant1.setEmail("jean.dupont@gmail.fr");
        etudiant1.setEmailUbo("jean.dupont@univ-brest.fr");
        etudiant1.setSexe("H");
        etudiant1.setAdresse("1 Rue de l'Université");
        etudiant1.setCodePostal("29200");
        etudiant1.setVille("Brest");
        etudiant1.setNationalite("FR");
        etudiant1.setMobile("06.00.00.01.00");

        etudiant2 = new Etudiant();
        etudiant2.setId("2");
        etudiant2.setNom("Dupont");
        etudiant2.setPrenom("Jean");
        etudiant2.setEmail("jean.dupont@gmail.fr");
        etudiant2.setEmailUbo("jean.dupont@univ-brest.fr");
        etudiant2.setSexe("H");
        etudiant2.setAdresse("1 Rue de l'Université");
        etudiant2.setCodePostal("29200");
        etudiant2.setVille("Brest");
        etudiant2.setNationalite("FR");
        etudiant2.setMobile("06.00.00.01.00");
        etudiants = Arrays.asList(etudiant1, etudiant2);

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
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant1);

        Etudiant result = etudiantService.create(etudiant1);

        assertEquals(etudiant1, result);
        verify(etudiantRepository, times(1)).save(any(Etudiant.class));
    }

}
