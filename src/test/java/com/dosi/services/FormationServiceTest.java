package com.dosi.services;

import com.dosi.entities.Enseignant;
import com.dosi.entities.Formation;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.FormationRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FormationServiceTest {




    @Mock
    private FormationRepository formationRepository;

    private FormationService formationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        formationService = new FormationService(formationRepository);
    }

    @Test
    public void testFindAll() {
        List<Formation> formations = new ArrayList<>();
        formations.add( Formation.builder()
                .id("dosi1")
                .diplome("dd")
                .n0Annee(1)
                .build());
        formations.add(
        Formation.builder()
                .id("dosi2")
                .diplome("ff")
                .n0Annee(2)
                .build());
        when(formationRepository.findAll()).thenReturn(formations);

        List<Formation> result = formationRepository.findAll();

        assertEquals(2, result.size());
        assertEquals("dd", result.get(0).getDiplome());
        assertEquals(1, result.get(0).getN0Annee());
        assertEquals("ff", result.get(1).getDiplome());
        assertEquals(2, result.get(1).getN0Annee());
    }

    @Test
    void findUEList() {

    }

    @Test
    void findECList() {
    }
}