package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.repositories.ElementConstitutifRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class ECServiceTest {

    @Mock
    private ElementConstitutifRepository elementConstitutifRepository;

    private ECService ecService;


    @BeforeEach
    public  void init(){
        MockitoAnnotations.openMocks(this);
        ecService = new ECService(elementConstitutifRepository);
    }

    @Test
    public void testFindAll(){
        List<ElementConstitutif> elementConstitutifList = new ArrayList<>();

        Formation formation = Formation.builder()
                .id("FORMATION_TEST")
                .diplome("TEST")
                .n0Annee(1)
                .build();

        Enseignant enseignant = Enseignant.builder()
                .id(1L)
                .prenom("John")
                .nom("Doe")
                .build();


        UniteEnseignementId uniteEnseignementId = UniteEnseignementId.builder()
                .codeFormation("FORMATION_TEST")
                .codeUe("UE_TEST")
                .build();


        UniteEnseignement uniteEnseignement = UniteEnseignement.builder()
                .id(uniteEnseignementId)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .designation("designation test 1")
                .semestre("1")
                .description("description test 1")
                .nbhCm(20L)
                .nbhTd((short)20)
                .nbhTp((short)2)
                .build();

        ElementConstitutifId elementConstitutifId = ElementConstitutifId.builder()
                .codeFormation("FORMATION_TEST")
                .codeUe("UE_TEST")
                .codeEc("EC_TEST")
                .build();

        ElementConstitutifId elementConstitutifId2 = ElementConstitutifId.builder()
                .codeFormation("FORMATION_TEST")
                .codeUe("UE_TEST")
                .codeEc("EC_TEST2")
                .build();

        elementConstitutifList.add(ElementConstitutif.builder()
                .id(elementConstitutifId)
                .codeUE(uniteEnseignement)
                .no_enseignant(enseignant)
                .designation("designation test 1")
                .description("description test 1")
                .nbhCm((short)20)
                .nbhTd((short)20)
                .nbhTp((short)2)
                .build());

        elementConstitutifList.add(ElementConstitutif.builder()
                .id(elementConstitutifId2)
                .codeUE(uniteEnseignement)
                .no_enseignant(enseignant)
                .designation("designation test 2")
                .description("description test 2")
                .nbhCm((short)20)
                .nbhTd((short)20)
                .nbhTp((short)2)
                .build());

        when(elementConstitutifRepository.findAll()).thenReturn(elementConstitutifList);

        List<ElementConstitutif> result = ecService.findAll();

        assertEquals(2,result.size());
        assertEquals("EC_TEST",result.get(0).getId().getCodeEc());
        assertEquals("description test 1",result.get(0).getDescription());
        assertEquals("designation test 1", result.get(0).getDesignation());

        assertEquals("EC_TEST2",result.get(1).getId().getCodeEc());
        assertEquals("description test 2",result.get(1).getDescription());
        assertEquals("designation test 2", result.get(1).getDesignation());
    }

    @Test
    public void testFindById(){
        Formation formation = Formation.builder()
                .id("FORMATION_TEST")
                .diplome("TEST")
                .n0Annee(1)
                .build();

        Enseignant enseignant = Enseignant.builder()
                .id(1L)
                .prenom("John")
                .nom("Doe")
                .build();}


}