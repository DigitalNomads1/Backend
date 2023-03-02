package com.dosi.services;

import com.dosi.entities.Etudiant;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.EtudiantRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        when(etudiantRepository.findAll()).thenReturn(etudiants);

        List<Etudiant> result = etudiantService.findAll();

        assertEquals(etudiants, result);
        verify(etudiantRepository, times(1)).findAll();
    }

    @Test
    public void testCreateEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant1);

        Etudiant result = etudiantService.create(etudiant1);

        assertEquals(etudiant1, result);
        verify(etudiantRepository, times(1)).save(any(Etudiant.class));
    }

    @Test
    public void testCreateEtudiantWithExistingId() {
        Etudiant etudiant = new Etudiant();
        etudiant.setId("1");

        when(etudiantRepository.existsById("1")).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> {
            etudiantService.create(etudiant);
        });

    }

    @Test
    public void testCreateEtudianttWithExistingUBOEmail() {
        Etudiant etudiant = new Etudiant();
        etudiant.setEmailUbo("jean.dupont@univ-brest.fr");

        when(etudiantRepository.findByEmailUbo("jean.dupont@univ-brest.fr").get()).thenReturn(etudiant);

        assertThrows(EntityExistsException.class, () -> {
            etudiantService.create(etudiant);
        });
    }
    @Test
    public void testCreateEtudianttWithExistingPersonalEmail() {
        Etudiant etudiant = new Etudiant();
        etudiant.setEmail("jean.dupont@gmail.fr");

        when(etudiantRepository.findByEmail("jean.dupont@gmail.fr").get()).thenReturn(etudiant);

        assertThrows(EntityExistsException.class, () -> {
            etudiantService.create(etudiant);
        });
    }

    @Test
    public void testRead() {
        when(etudiantRepository.findById(any(String.class))).thenReturn(Optional.of(etudiant1));

        Etudiant result = etudiantService.read("1");

        assertEquals(etudiant1, result);
        verify(etudiantRepository, times(1)).findById(any(String.class));
    }

    @Test
    void testReadWithNonExistingId() {
        String nonExistingId = "999";

        when(etudiantRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {
            etudiantService.read(nonExistingId);
        });
    }

    @Test
    public void testUpdate() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant1);

        Etudiant result = etudiantService.update(etudiant1);

        assertEquals(etudiant1, result);
        verify(etudiantRepository, times(1)).save(any(Etudiant.class));
    }

    @Test
    public void testDelete() {
        when(etudiantRepository.findById(any(String.class))).thenReturn(Optional.of(etudiant1));

        etudiantService.delete("1");

        verify(etudiantRepository, times(1)).deleteById(any(String.class));

    }

    @Test
    public void testDeleteWithException() {
        // Given
        String id = "9999";
        when(etudiantRepository.findById(id)).thenReturn(Optional.of(new Etudiant()));
        doThrow(ApplicationException.class).when(etudiantRepository).deleteById(id);

        // Then
        assertThrows(ApplicationException.class, () -> {
            etudiantService.delete(id);
        });
    }

    @Test
    public void testDeleteNotFound() {
        // Given
        String id = "1";
        when(etudiantRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        assertThrows(ResponseStatusException.class, () -> {
            etudiantService.delete(id);
        });
    }

    @Test
    public void testFindByEmailUbo() {
        Etudiant etudiant = new Etudiant();
        etudiant.setEmailUbo("jean.dupont@univ-brest.fr");

        when(etudiantRepository.findByEmailUbo("jean.dupont@univ-brest.fr").get()).thenReturn(etudiant);

        Etudiant foundEtudiant = etudiantService.findByEmailUbo("jean.dupont@univ-brest.fr");

        assertEquals("jean.dupont@univ-brest.fr", foundEtudiant.getEmailUbo());
    }



}
