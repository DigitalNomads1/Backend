package com.dosi.services;

import com.dosi.entities.*;
import com.dosi.exceptions.ApplicationException;
import com.dosi.repositories.PromotionRepository;
import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class PromotionServiceTest {

    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private PromotionService promotionService;

    private Promotion promotion1;
    private Promotion promotion2;
    private List<Promotion> promotions;

    @BeforeEach
    public void SetUp() {
        MockitoAnnotations.initMocks(this);
        Formation formation1 = Formation.builder()
                .id("DOSI")
                .diplome("MASTER")
                .n0Annee(1)
                .build();

        Formation formation2 = Formation.builder()
                .id("M1TIIL")
                .diplome("MASTER")
                .n0Annee(1)
                .build();

        promotion1 = Promotion.builder()
                .id(new PromotionId("2014 - 2015", "DOSI"))
                .siglePromotion("DOSI")
                .codeFormation(formation1)
                .lieuRentree("TEST")
                .build();

        promotion2 = Promotion.builder()
                .id(new PromotionId("2015 - 2016", "M1TIIL"))
                .siglePromotion("M1TIIL")
                .codeFormation(formation2)
                .lieuRentree("TEST")
                .build();

        promotions = Arrays.asList(promotion1, promotion2);

    }

    @Test
    public void testFindAll() {
        when(promotionRepository.findAll()).thenReturn(promotions);

        List<Promotion> result = promotionService.findAll();

        assertEquals(promotions, result);
        verify(promotionRepository, times(1)).findAll();
    }

    @Test
    public void testCreatePromotion() {
        when(promotionRepository.save(any(Promotion.class))).thenReturn(promotion1);

        Promotion result = promotionService.create(promotion1);

        assertEquals(promotion1, result);
        verify(promotionRepository, times(1)).save(any(Promotion.class));
    }

    @Test
    public void testCreatePromotionWithExistingId() {
        Promotion promotion = Promotion.builder()
                .id(new PromotionId("2014 - 2015", "DOSI"))
                .build();

        when(promotionRepository.existsById(new PromotionId("2014 - 2015", "DOSI"))).thenReturn(true);

        assertThrows(EntityExistsException.class, () -> {
            promotionService.create(promotion);
        });
    }

    @Test
    public void testRead() {
        when(promotionRepository.findById(any(PromotionId.class))).thenReturn(Optional.of(promotion1));

        Promotion result = promotionService.read(new PromotionId("2014 - 2015", "DOSI"));

        assertEquals(promotion1, result);
        verify(promotionRepository, times(1)).findById(any(PromotionId.class));
    }

    @Test
    void testReadWithNonExistingId() {
        PromotionId nonExistingId = new PromotionId("2014 - 2015", "DOSI");

        when(promotionRepository.findById(nonExistingId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {
            promotionService.read(nonExistingId);
        });
    }

    @Test
    public void testUpdate() {
        when(promotionRepository.save(any(Promotion.class))).thenReturn(promotion1);

        Promotion result = promotionService.update(promotion1);

        assertEquals(promotion1, result);
        verify(promotionRepository, times(1)).save(any(Promotion.class));
    }

    /*
    @Test
    public void testDelete() {
        PromotionId promotionId = new PromotionId("2014 - 2015", "DOSI");
        when(promotionRepository.findById(any(PromotionId.class))).thenReturn(Optional.of(promotion1));

        promotionService.deleteByEmbeddedId(promotionId.getAnneeUniversitaire(),promotionId.getCodeFormation());

        verify(promotionRepository, Mockito.times(1)).deleteByEmbeddedId(any(String.class),any(String.class));

    }

    @Test
    public void testDeleteWithException() {

        PromotionId promotionId = new PromotionId("2050 - 2052", "DOSI");
        when(promotionRepository.findById(promotionId)).thenReturn(Optional.of(new Promotion()));
        doThrow(ApplicationException.class).when(promotionRepository).deleteByEmbeddedId(promotionId.getAnneeUniversitaire(),promotionId.getCodeFormation());

        // Then
        assertThrows(ApplicationException.class, () -> {
            promotionService.deleteByEmbeddedId(promotionId.getAnneeUniversitaire(),promotionId.getCodeFormation());
        });
    }
*/
}
