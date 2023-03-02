package com.dosi.services;

import com.dosi.entities.Promotion;
import com.dosi.repositories.PromotionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class PromotionServiceTest {

    @Mock
    private PromotionRepository promotionRepository;

    @InjectMocks
    private PromotionService promotionService;

    private Promotion promotion1;
    private Promotion promotion2;
    private List<Promotion> promotions;

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.initMocks(this);
    }



}
