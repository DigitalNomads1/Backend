package com.dosi.services;

import com.dosi.entities.Candidat;
import com.dosi.entities.Enseignant;
import com.dosi.entities.Etudiant;
import com.dosi.entities.Promotion;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.CandidatRepository;
import com.dosi.services.CandidatService;
import com.dosi.services.EnseignantService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;





public class CandidatServiceTest {

    @Mock
    private CandidatRepository candidatRepository;

    @Mock
    private Promotion promotion;

    private CandidatService candidatService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        candidatService = new CandidatService(candidatRepository);
    }

    @Test
    public void testFindAll() {
        List<Candidat> candidats = new ArrayList<>();
        candidats.add( Candidat.builder()
                .id("1")
                .nom("Nom")
                .prenom("Prenom")
                .sexe("H")
                .dateNaissance(LocalDate.parse("2000-10-08"))
                .lieuNaissance("Lieu")
                .nationalite("Nationalité")
                .email("email@test.com")
                .adresse("Adresse")
                .ville("Ville")
                .paysOrigine("FR")
                .universiteOrigine("UAE")
                .build());

        candidats.add(
                Candidat.builder()
                        .id("1")
                        .nom("Nom")
                        .prenom("Prenom")
                        .sexe("H")
                        .dateNaissance(LocalDate.parse("2000-10-08"))
                        .lieuNaissance("Lieu")
                        .nationalite("Nationalité")
                        .email("email@test.com")
                        .adresse("Adresse")
                        .ville("Ville")
                        .paysOrigine("FR")
                        .universiteOrigine("UAE")
                        .build());

        when(candidatRepository.findAll()).thenReturn(candidats);

        List<Candidat> result = candidatService.findAll();

        assertEquals(2, result.size());
        assertEquals("Prenom", result.get(0).getPrenom());
        assertEquals("Nom", result.get(0).getNom());

        assertEquals("Prenom", result.get(1).getPrenom());
        assertEquals("Nom", result.get(1).getNom());
    }

    @Test
    public void testCreateCandidats() {
        Candidat candidat = new Candidat();
        candidat.setNom("ABDOUH");
        candidat.setPrenom("Manal");
        candidat.setSexe("F");
        candidat.setDateNaissance(LocalDate.parse("2000-08-19"));
        candidat.setLieuNaissance("Tétouan");
        candidat.setNationalite("Marocaine");
        candidat.setEmail("manalabdouh1@gmail.com");
        candidat.setAdresse("1 Rue Tolbiac");
        candidat.setPaysOrigine("MR");
        candidat.setUniversiteOrigine("UAE");

        when(candidatRepository.save(any(Candidat.class))).thenReturn(candidat);
        Candidat result = candidatService.create(candidat);

        assertEquals(candidat, result);
        verify(candidatRepository, times(1)).save(any(Candidat.class));

    }


    @Test
    public void testCreateCandidatWithExistingId() {
        Candidat candidat = new Candidat();
        candidat.setId("1");

        when(candidatRepository.existsById("1")).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> {
            candidatService.create(candidat);
        });
    }

    @Test
    public void testCreateCandidatWithExistingEmail() {
        Candidat candidat = new Candidat();
        candidat.setEmail("dupont@gmail.com");

        when(candidatRepository.findByEmail("dupont@gmail.com")).thenReturn(candidat);

        assertThrows(EntityExistsException.class, () -> {
            candidatService.create(candidat);
        });
    }


    @Test
    public void testRead() {
        Candidat candidat = Candidat.builder()
                .id("1")
                .nom("Nom")
                .prenom("Prenom")
                .sexe("H")
                .dateNaissance(LocalDate.parse("2000-10-08"))
                .lieuNaissance("Lieu")
                .nationalite("Nationalité")
                .email("email@test.com")
                .adresse("Adresse")
                .ville("Ville")
                .paysOrigine("FR")
                .universiteOrigine("UAE")
                .build();
        when(candidatRepository.findById("1")).thenReturn(Optional.of(candidat));

        Candidat result = candidatService.read("1");

        assertEquals("1", result.getId());
        assertEquals("Prenom", result.getPrenom());
        assertEquals("Nom", result.getNom());
        verify(candidatRepository, times(1)).findById("1");
    }

    @Test
    void testReadWithNonExistingId() {
        String nonExistingId = "999L";

        when(candidatRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            candidatService.read(nonExistingId);
        });
    }

    @Test
    public void testUpdate() {
        // given
        Candidat candidat = Candidat.builder()
                .id("1L")
                .prenom("Anas")
                .nom("Salhi")
                .build();
        when(candidatRepository.save(candidat)).thenReturn(candidat);

        // when
        Candidat result = candidatService.update(candidat);

        // then
        assertEquals("1L", result.getId());
        assertEquals("Anas", result.getPrenom());
        assertEquals("Salhi", result.getNom());
        verify(candidatRepository, times(1)).save(candidat);
    }

    @Test
    public void testUpdateCandidatWithExistingEmail() {
        Candidat candidat = new Candidat();
        candidat.setEmail("dupont@gmail.com");

        when(candidatRepository.findByEmail("dupont@gmail.com")).thenReturn(candidat);

        assertThrows(EntityExistsException.class, () -> {
            candidatService.update(candidat);
        });
    }

    @Test
    public void testDelete() {
        // given
        String candidatId = "1";
        when(candidatRepository.findById(candidatId)).thenReturn(Optional.of(Candidat.builder()
                .id("1")
                .prenom("John")
                .nom("Doe")
                .build()));

        // when
        candidatService.delete(candidatId);

        // then
        verify(candidatRepository, times(1)).findById(candidatId);
        verify(candidatRepository, times(1)).deleteById(candidatId);
    }

    @Test
    public void testDeleteWithException() {
        // Given
        String id = "1000L";
        when(candidatRepository.findById(id)).thenReturn(Optional.of(new Candidat()));
        doThrow(ApplicationException.class).when(candidatRepository).deleteById(id);

        // Then
        assertThrows(ApplicationException.class, () -> {
            candidatService.delete(id);
        });
    }

    @Test
    public void testDeleteNotFound() {
        // Given
        String id = "1L";
        when(candidatRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResponseStatusException.class, () -> {
            candidatService.delete(id);
        });
    }



}
