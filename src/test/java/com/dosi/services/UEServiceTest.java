package com.dosi.services;

import com.dosi.entities.Enseignant;
import com.dosi.entities.Formation;
import com.dosi.entities.UniteEnseignement;
import com.dosi.entities.UniteEnseignementId;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.UniteEnseignementRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UEServiceTest {

    @Mock
    private UniteEnseignementRepository uniteEnseignementRepository;

    private UEService ueService;

    @BeforeEach
    public  void init(){
        MockitoAnnotations.openMocks(this);
        ueService = new UEService(uniteEnseignementRepository);
    }


    @Test
    public void testFindAll(){
        List<UniteEnseignement> uniteEnseignementList = new ArrayList<>();

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

        UniteEnseignementId uniteEnseignementId2 = UniteEnseignementId.builder()
                .codeFormation("FORMATION_TEST")
                .codeUe("UE_TEST2")
                .build();

        uniteEnseignementList.add(UniteEnseignement.builder()
                .id(uniteEnseignementId)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .designation("designation test 1")
                .semestre("1")
                .description("description test 1")
                .nbhCm(20L)
                .nbhTd((short)20)
                .nbhTp((short)2)
                .build());

        uniteEnseignementList.add(UniteEnseignement.builder()
                .id(uniteEnseignementId2)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .designation("designation test 2")
                .semestre("2")
                .description("description test 2")
                .nbhCm(20L)
                .nbhTd((short)20)
                .nbhTp((short)2)
                .build());
        when(uniteEnseignementRepository.findAll()).thenReturn(uniteEnseignementList);

        List<UniteEnseignement> result = ueService.findAll();

        assertEquals(2,result.size());
        assertEquals("description test 1",result.get(0).getDescription());
        assertEquals("designation test 1", result.get(0).getDesignation());
        assertEquals("1", result.get(0).getSemestre());

        assertEquals("description test 2",result.get(1).getDescription());
        assertEquals("designation test 2", result.get(1).getDesignation());
        assertEquals("2", result.get(1).getSemestre());
    }

    @Test
    public  void TestCreateUE(){
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

        UniteEnseignement uniteEnseignement = new UniteEnseignement();
        uniteEnseignement.setId(uniteEnseignementId);
        uniteEnseignement.setNoEnseignant(enseignant);
        uniteEnseignement.setCodeFormation(formation);
        uniteEnseignement.setDesignation("designation test");
        uniteEnseignement.setSemestre("2");
        uniteEnseignement.setDescription("description test");

        when(uniteEnseignementRepository.save(any(UniteEnseignement.class))).thenReturn(uniteEnseignement);

        UniteEnseignement result = ueService.create(uniteEnseignement);

        assertEquals("FORMATION_TEST",result.getId().getCodeFormation());
        assertEquals("UE_TEST",result.getId().getCodeUe());
        assertEquals("designation test",result.getDesignation());
        assertEquals("description test",result.getDescription());
        assertEquals("2",result.getSemestre());
        assertEquals("Doe",result.getNoEnseignant().getNom());
        assertEquals("John",result.getNoEnseignant().getPrenom());
    }

    @Test
    public void testCreateUEWithExistingId(){

        UniteEnseignementId uniteEnseignementId = UniteEnseignementId.builder()
                .codeFormation("M2DOSI")
                .codeUe("ISI")
                .build();

        UniteEnseignement ue = new UniteEnseignement();
        ue.setId(uniteEnseignementId);

        when(uniteEnseignementRepository.existsById(uniteEnseignementId)).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> {
            ueService.create(ue);
        });
    }

    @Test
    public void testRead(){
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

        UniteEnseignement ue = UniteEnseignement.builder()
                .id(uniteEnseignementId)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .designation("designation test")
                .semestre("1")
                .description("description test")
                .nbhCm(20L)
                .nbhTd((short)20)
                .nbhTp((short)2)
                .build();

        when(uniteEnseignementRepository.findById(uniteEnseignementId)).thenReturn(Optional.of(ue));

        UniteEnseignement result = ueService.read(uniteEnseignementId);

        assertEquals("FORMATION_TEST",result.getId().getCodeFormation());
        assertEquals("UE_TEST",result.getId().getCodeUe());
        assertEquals("designation test",result.getDesignation());
        assertEquals("description test",result.getDescription());
        assertEquals("1",result.getSemestre());
        assertEquals("Doe",result.getNoEnseignant().getNom());
        assertEquals("John",result.getNoEnseignant().getPrenom());
    }

    @Test
    public  void testReadWithNonExistingId() {

        UniteEnseignementId uniteEnseignementId = UniteEnseignementId.builder()
                .codeFormation("NonExiste")
                .codeUe("UE_TEST")
                .build();

        when(uniteEnseignementRepository.findById(uniteEnseignementId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            ueService.read(uniteEnseignementId);
        });
    }

    @Test
    public void testUpdate(){
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

        UniteEnseignement ue = UniteEnseignement.builder()
                .id(uniteEnseignementId)
                .codeFormation(formation)
                .noEnseignant(enseignant)
                .designation("designation test")
                .semestre("1")
                .description("description test")
                .nbhCm(20L)
                .nbhTd((short)20)
                .nbhTp((short)2)
                .build();

        when(uniteEnseignementRepository.save(ue)).thenReturn(ue);

        UniteEnseignement result = ueService.update(ue);

        assertEquals("FORMATION_TEST",result.getId().getCodeFormation());
        assertEquals("UE_TEST",result.getId().getCodeUe());
        assertEquals("designation test",result.getDesignation());
        assertEquals("description test",result.getDescription());
        assertEquals("1",result.getSemestre());
        assertEquals("Doe",result.getNoEnseignant().getNom());
        assertEquals("John",result.getNoEnseignant().getPrenom());
        verify(uniteEnseignementRepository, times(1)).save(ue);

    }

    @Test
    public void testUpdateUniqueConstraitnViolation(){
        UniteEnseignement existing = new UniteEnseignement();
        UniteEnseignement updated = new UniteEnseignement();

        UniteEnseignementId uniteEnseignementId = UniteEnseignementId.builder()
                .codeFormation("FORMATION_TEST")
                .codeUe("UE_TEST")
                .build();

        UniteEnseignementId uniteEnseignementId2 = UniteEnseignementId.builder()
                .codeFormation("FORMATION_TEST2")
                .codeUe("UE_TEST2")
                .build();

        existing.setId(uniteEnseignementId);
        updated.setId(uniteEnseignementId2);

        when(uniteEnseignementRepository.findById(uniteEnseignementId2)).thenReturn(Optional.of(updated));
        doThrow(ApplicationException.class).when(uniteEnseignementRepository).save(updated);

        assertThrows(ApplicationException.class, () -> {
            ueService.update(updated);
        });
    }

    @Test
    public void testDelete() {
        UniteEnseignementId uniteEnseignementId = UniteEnseignementId.builder()
                .codeFormation("FORMATION_TEST")
                .codeUe("UE_TEST")
                .build();

        when(uniteEnseignementRepository.findById(uniteEnseignementId))
                .thenReturn(Optional.of(UniteEnseignement.builder()
                        .id(uniteEnseignementId)
                        .build()));
        ueService.delete(uniteEnseignementId);

        verify(uniteEnseignementRepository, times(1)).findById(uniteEnseignementId);
        verify(uniteEnseignementRepository, times(1)).deleteById(uniteEnseignementId);

    }



}