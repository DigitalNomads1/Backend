package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.ElementConstitutifRepository;
import com.dosi.repositories.FormationRepository;
import com.dosi.repositories.UniteEnseignementRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FormationServiceTest {




    @Mock
    private FormationRepository formationRepository;

    private FormationService formationService;

    @Mock
    private UniteEnseignementRepository uniteEnseignementRepository;

    private UEService ueService;

    @Mock
    private ElementConstitutifRepository ecRepository;

    private ECService ecService;



    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        formationService = new FormationService(formationRepository);
        ueService = new UEService(uniteEnseignementRepository);
        ecService = new ECService(ecRepository);


    }


    @Test
    public void testFindAll() {
        List<Formation> formations = new ArrayList<>();
        formations.add( Formation.builder()
                .id("dosi1")
                .diplome("dd")
                .n0Annee(1)
                .build());
        formations.add(Formation.builder()
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
    public void testCreateFormation() {
        Formation formation = new Formation();
        formation.setId("dosi1");
        formation.setDiplome("dd");
        formation.setN0Annee(1);
        formation.setNomFormation("dosi1");
        formation.setDoubleDiplome("O");
        formation.setDebutAccreditation(LocalDate.parse("2000-05-04"));
        formation.setFinAccreditation(LocalDate.parse("2000-09-22"));


        when(formationRepository.save(any(Formation.class))).thenReturn(formation);
        Formation savedFormation = formationService.create(formation);

        assertEquals("dosi1", savedFormation.getId());
        assertEquals("dd", savedFormation.getDiplome());
        assertEquals(1, savedFormation.getN0Annee());
        assertEquals("dosi1", savedFormation.getNomFormation());
        assertEquals("O", savedFormation.getDoubleDiplome());

    }

    @Test
    public void testCreateFormationWithExistingId() {
        Formation formation = new Formation();
        formation.setId("f");

        when(formationRepository.existsById("f")).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> {
            formationService.create(formation);
        });
    }

    @Test
    public void testRead() {


        Formation formation  = Formation.builder()
                .id("dosi1")
                .diplome("dd")
                .n0Annee(1)
                .build();
        when(formationRepository.findById("dosi1")).thenReturn(Optional.of(formation));

        Formation result = formationService.read("dosi1");

        assertEquals("dosi1", result.getId());
        assertEquals(1, result.getN0Annee());
        assertEquals("dd", result.getDiplome());


        verify(formationRepository, times(1)).findById("dosi1");
    }
    @Test
    void testReadWithNonExistingId() {
        // Given
        String nonExistingId = "dosi";

        when(formationRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResponseStatusException.class, () -> {
            formationService.read(nonExistingId);
        });
    }

    @Test
    public void testUpdate() {
        // given
        Formation formation  = Formation.builder()
                .id("dosi")
                .diplome("dd")
                .n0Annee(1)
                .build();
        when(formationRepository.save(formation)).thenReturn(formation);

        // when
        Formation result = formationService.update(formation);

        // then
        assertEquals("dosi", result.getId());
        assertEquals("dd", result.getDiplome());
        assertEquals(1, result.getN0Annee());
        verify(formationRepository, times(1)).save(formation);
    }

    @Test
    public void testUpdateFormationUniqueConstraintViolation() {
        //violation de la contrainte id unique
        Formation existingFormation = new Formation();
        existingFormation.setId("dosi1");

        Formation updatedFormation = new Formation();
        updatedFormation.setId("dosi2");

        when(formationRepository.findById("dosi2")).thenReturn(Optional.of(updatedFormation));
        doThrow(ApplicationException.class).when(formationRepository).save(updatedFormation);

        assertThrows(ApplicationException.class, () -> {
            formationService.update(updatedFormation);
        });
    }

    @Test
    public void testDelete() {
        // given
        String formationId = "dosi";
        when(formationRepository.findById(formationId)).thenReturn(Optional.of(Formation.builder()
                .id("dosi")
                .diplome("dd")
                .n0Annee(1)
                .build()));


        // when
        formationService.delete(formationId);

        // then
        verify(formationRepository, times(1)).findById(formationId);
        verify(formationRepository, times(1)).deleteById(formationId);
    }

    @Test
    public void testDeleteWithException() {
        // Given
        String formationId = "dosi";
        when(formationRepository.findById(formationId)).thenReturn(Optional.of(new Formation()));
        doThrow(ApplicationException.class).when(formationRepository).deleteById(formationId);

        // Then
        assertThrows(ApplicationException.class, () -> {
            formationService.delete(formationId);
        });
    }

    @Test
    public void testDeleteNotFound() {
        // Given
        String formationId = "dosi";
        when(formationRepository.findById(formationId)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResponseStatusException.class, () -> {
            formationService.delete(formationId);
        });
    }



    @Test
    public void findUEList(){

        List<UniteEnseignement> uniteEnseignementList = new ArrayList<>();

        Formation formation = Formation.builder()
                .id("dosi")
                .diplome("dd")
                .n0Annee(1)
                .build();

        Enseignant enseignant = Enseignant.builder()
                .id(1L)
                .nom("nom")
                .prenom("prenom")
                .build();

        UniteEnseignementId ueId = new UniteEnseignementId().builder()
                .codeFormation("dosi")
                .codeUe("codeUe")
                .build();

        UniteEnseignementId ueId2 = new UniteEnseignementId().builder()
                .codeFormation("dosi")
                .codeUe("codeUe2")
                .build();

        uniteEnseignementList.add(UniteEnseignement.builder()
                .id(ueId)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .description("description1")
                .build());

        uniteEnseignementList.add(UniteEnseignement.builder()
                .id(ueId2)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .description("description2")
                .build());

        when(uniteEnseignementRepository.findAll()).thenReturn(uniteEnseignementList);
        List<UniteEnseignement> result = ueService.findAll();

        assertEquals(2, result.size());
        assertEquals("codeUe", result.get(0).getId().getCodeUe());
        assertEquals("codeUe2", result.get(1).getId().getCodeUe());
        assertEquals("description1", result.get(0).getDescription());
        assertEquals("description2", result.get(1).getDescription());

    }

    @Test
    public void findECList(){
        List<ElementConstitutif> elementConstitutifList = new ArrayList<>();
        List<UniteEnseignement> uniteEnseignementList = new ArrayList<>();

        Formation formation = Formation.builder()
                .id("dosi")
                .diplome("dd")
                .n0Annee(1)
                .build();

        Enseignant enseignant = Enseignant.builder()
                .id(1L)
                .nom("nom")
                .prenom("prenom")
                .build();

        UniteEnseignementId ueId = new UniteEnseignementId().builder()
                .codeFormation("dosi")
                .codeUe("codeUe")
                .build();

        UniteEnseignementId ueId2 = new UniteEnseignementId().builder()
                .codeFormation("dosi")
                .codeUe("codeUe2")
                .build();

        uniteEnseignementList.add(UniteEnseignement.builder()
                .id(ueId)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .description("description1")
                .build());

        uniteEnseignementList.add(UniteEnseignement.builder()
                .id(ueId2)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .description("description2")
                .build());

        ElementConstitutifId ecId = new ElementConstitutifId().builder()
                .codeFormation("dosi")
                .codeUe("codeUe")
                .codeEc("codeEc")
                .build();

        ElementConstitutifId ecId2 = new ElementConstitutifId().builder()
                .codeFormation("dosi")
                .codeUe("codeUe2")
                .codeEc("codeEc2")
                .build();



      elementConstitutifList.add(ElementConstitutif.builder()
                .id(ecId)
                .codeUE(uniteEnseignementList.get(0))
                .no_enseignant(enseignant)
                .description("description1")
                .build());

        elementConstitutifList.add(ElementConstitutif.builder()
                .id(ecId2)
                .codeUE(uniteEnseignementList.get(1))
                .no_enseignant(enseignant)
                .description("description2")
                .build());

        when(uniteEnseignementRepository.findAll()).thenReturn(uniteEnseignementList);


        when(ecRepository.findAll()).thenReturn(elementConstitutifList);
        List<ElementConstitutif> result = ecService.findAll();

        assertEquals(2, result.size());
        assertEquals("codeEc", result.get(0).getId().getCodeEc());
        assertEquals("codeEc2", result.get(1).getId().getCodeEc());
        assertEquals("description1", result.get(0).getDescription());
        assertEquals("description2", result.get(1).getDescription());


    }

}