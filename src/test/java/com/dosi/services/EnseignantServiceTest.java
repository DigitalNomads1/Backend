package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.EnseignantRepository;
import com.dosi.repositories.FormationRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class EnseignantServiceTest {

    @Mock
    private EnseignantRepository enseignantRepository;

    @Mock
    private FormationRepository formationRepository;

    @Mock
    private UniteEnseignementRepository uniteEnseignementRepository;

    private EnseignantService enseignantService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        enseignantService = new EnseignantService(enseignantRepository);
    }

    @Test
    public void testFindAll() {
        List<Enseignant> enseignants = new ArrayList<>();
        enseignants.add( Enseignant.builder()
                        .id(1L)
                        .prenom("John")
                        .nom("Doe")
                        .build());
        enseignants.add(
                Enseignant.builder()
                        .id(2L)
                        .prenom("Jane")
                        .nom("Doe")
                        .build());
        when(enseignantRepository.findAll()).thenReturn(enseignants);

        List<Enseignant> result = enseignantService.findAll();

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getPrenom());
        assertEquals("Doe", result.get(0).getNom());
        assertEquals("Jane", result.get(1).getPrenom());
        assertEquals("Doe", result.get(1).getNom());
    }

    @Test
    public void testCreateEnseignant() {
        Enseignant enseignant = new Enseignant();
        enseignant.setNom("Dupont");
        enseignant.setPrenom("Jean");
        enseignant.setEmailUbo("jean.dupont@univ-brest.fr");
        enseignant.setType("MCF");
        enseignant.setSexe("H");
        enseignant.setAdresse("1 Rue de l'Université");
        enseignant.setCodePostal("29200");
        enseignant.setVille("Brest");
        enseignant.setPays("FR");
        enseignant.setMobile("06.00.00.01.00");

        when(enseignantRepository.save(any(Enseignant.class))).thenReturn(enseignant);
        Enseignant savedEnseignant = enseignantService.create(enseignant);

        assertEquals("Dupont", savedEnseignant.getNom());
        assertEquals("Jean", savedEnseignant.getPrenom());
        assertEquals("jean.dupont@univ-brest.fr", savedEnseignant.getEmailUbo());
        assertEquals("MCF", savedEnseignant.getType());
        assertEquals("H", savedEnseignant.getSexe());
        assertEquals("1 Rue de l'Université", savedEnseignant.getAdresse());
        assertEquals("29200", savedEnseignant.getCodePostal());
        assertEquals("Brest", savedEnseignant.getVille());
        assertEquals("FR", savedEnseignant.getPays());
        assertEquals("06.00.00.01.00", savedEnseignant.getMobile());
    }

    @Test
    public void testCreateEnseignantWithExistingId() {
        Enseignant enseignant = new Enseignant();
        enseignant.setId(1L);

        when(enseignantRepository.existsById(1L)).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> {
            enseignantService.create(enseignant);
        });
    }

    @Test
    public void testCreateEnseignantWithExistingEmail() {
        Enseignant enseignant = new Enseignant();
        enseignant.setEmailUbo("jean.dupont@univ-brest.fr");
        List<Enseignant> enseignants = new ArrayList<Enseignant>();
        enseignants.add(enseignant);

        when(enseignantRepository.findByEmailUbo("jean.dupont@univ-brest.fr")).thenReturn(enseignants);

        assertThrows(EntityExistsException.class, () -> {
            enseignantService.create(enseignant);
        });
    }

    @Test
    public void testRead() {
        Enseignant enseignant = Enseignant.builder()
                .id(1L)
                .prenom("John")
                .nom("Doe")
                .build();
        when(enseignantRepository.findById(1L)).thenReturn(Optional.of(enseignant));

        Enseignant result = enseignantService.read(1L);

        assertEquals(1L, result.getId());
        assertEquals("John", result.getPrenom());
        assertEquals("Doe", result.getNom());
        verify(enseignantRepository, times(1)).findById(1L);
    }

    @Test
    void testReadWithNonExistingId() {
        // Given
        Long nonExistingId = 999L;

        when(enseignantRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(ResponseStatusException.class, () -> {
            enseignantService.read(nonExistingId);
        });
    }

    @Test
    public void testUpdate() {
        // given
        Enseignant enseignant = Enseignant.builder()
                .id(1L)
                .prenom("John")
                .nom("Doe")
                .build();
        when(enseignantRepository.save(enseignant)).thenReturn(enseignant);

        // when
        Enseignant result = enseignantService.update(enseignant);

        // then
        assertEquals(1L, result.getId());
        assertEquals("John", result.getPrenom());
        assertEquals("Doe", result.getNom());
        verify(enseignantRepository, times(1)).save(enseignant);
    }

    @Test
    public void testUpdateEnseignantUniqueConstraintViolation() {
        Enseignant existingEnseignant = new Enseignant();
        existingEnseignant.setId(1L);
        existingEnseignant.setEmailUbo("john.doe@univ-brest.fr");

        Enseignant updatedEnseignant = new Enseignant();
        updatedEnseignant.setId(2L);
        updatedEnseignant.setEmailUbo("kim.kepper@univ-brest.fr");

        when(enseignantRepository.findById(2L)).thenReturn(Optional.of(updatedEnseignant));
        doThrow(ApplicationException.class).when(enseignantRepository).save(updatedEnseignant);

        assertThrows(ApplicationException.class, () -> {
            enseignantService.update(updatedEnseignant);
        });
    }

    @Test
    public void testDelete() {
        // given
        long enseignantId = 1L;
        when(enseignantRepository.findById(enseignantId)).thenReturn(Optional.of(     Enseignant.builder()
                .id(1L)
                .prenom("John")
                .nom("Doe")
                .build()));

        // when
        enseignantService.delete(enseignantId);

        // then
        verify(enseignantRepository, times(1)).findById(enseignantId);
        verify(enseignantRepository, times(1)).deleteById(enseignantId);
    }

    @Test
    public void testDeleteWithException() {
        // Given
        Long id = 1000L;
        when(enseignantRepository.findById(id)).thenReturn(Optional.of(new Enseignant()));
        doThrow(ApplicationException.class).when(enseignantRepository).deleteById(id);

        // Then
        assertThrows(ApplicationException.class, () -> {
            enseignantService.delete(id);
        });
    }

    @Test
    public void testDeleteNotFound() {
        // Given
        Long id = 1L;
        when(enseignantRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResponseStatusException.class, () -> {
            enseignantService.delete(id);
        });
    }

    @Test
    public void testFindByEmailUbo() {
        Enseignant enseignant = new Enseignant();
        enseignant.setEmailUbo("jean.dupont@univ-brest.fr");
        List<Enseignant> enseignants = new ArrayList<Enseignant>();
        enseignants.add(enseignant);

        when(enseignantRepository.findByEmailUbo("jean.dupont@univ-brest.fr")).thenReturn(enseignants);

        List<Enseignant> foundEnseignants = enseignantRepository.findByEmailUbo("jean.dupont@univ-brest.fr");

        assertEquals("jean.dupont@univ-brest.fr", foundEnseignants.get(0).getEmailUbo());
    }

    @Test
    void testGetUE() {
        // Setup
        Long enseignantId = 1L;
        Enseignant enseignant = new Enseignant();
        enseignant.setId(enseignantId);
        List<UniteEnseignement> ues = new ArrayList<>();
        UniteEnseignement ue1 = new UniteEnseignement();
        ue1.setId(new UniteEnseignementId());
        ues.add(ue1);
        UniteEnseignement ue2 = new UniteEnseignement();
        ue2.setId(new UniteEnseignementId());
        ues.add(ue2);
        enseignant.setListUE(ues);

        when(enseignantRepository.findById(enseignantId)).thenReturn(Optional.of(enseignant));

        // Exercise
        List<UniteEnseignement> result = enseignantService.getUE(enseignantId);

        // Verify
        verify(enseignantRepository, times(1)).findById(enseignantId);
        assertEquals(ues, result);
    }

    @Test
    void testGetEC() {
        // Setup
        Long enseignantId = 1L;
        Enseignant enseignant = new Enseignant();
        enseignant.setId(enseignantId);
        List<ElementConstitutif> ecs = new ArrayList<>();
        ElementConstitutif ec1 = new ElementConstitutif();
        ecs.add(ec1);
        ElementConstitutif ec2 = new ElementConstitutif();
        ecs.add(ec2);
        enseignant.setListEC(ecs);

        when(enseignantRepository.findById(enseignantId)).thenReturn(Optional.of(enseignant));

        // Exercise
        List<ElementConstitutif> result = enseignantService.getEC(enseignantId);

        // Verify
        verify(enseignantRepository, times(1)).findById(enseignantId);
        assertEquals(ecs, result);
    }

    @Test
    void testGetPromotions() {
        // Setup
        Long enseignantId = 1L;
        Enseignant enseignant = new Enseignant();
        enseignant.setId(enseignantId);
        List<Promotion> promotions = new ArrayList<>();
        Promotion promotion1 = new Promotion();
        promotions.add(promotion1);
        Promotion promotion2 = new Promotion();
        promotions.add(promotion2);
        enseignant.setListPromotion(promotions);

        when(enseignantRepository.findById(enseignantId)).thenReturn(Optional.of(enseignant));

        // Exercise
        List<Promotion> result = enseignantService.getPromotions(enseignantId);

        // Verify
        verify(enseignantRepository, times(1)).findById(enseignantId);
        assertEquals(promotions, result);
    }

}