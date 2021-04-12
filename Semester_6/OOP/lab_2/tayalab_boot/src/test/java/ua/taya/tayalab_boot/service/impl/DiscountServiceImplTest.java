package ua.taya.tayalab_boot.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ua.taya.tayalab_boot.entity.Discount;
import ua.taya.tayalab_boot.entity.DiscountType;
import ua.taya.tayalab_boot.repository.DiscountRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceImplTest {
    @Mock
    private DiscountRepository discountRepository;
    @InjectMocks
    private DiscountServiceImpl discountService;
    private Discount discount;

    @Before
    public void setUp() throws Exception {
        discount = Discount.builder()
                .discountPercentage(10.0f)
                .discountType(DiscountType.HOT_TOUR)
                .build();
    }

    @Test
    public void getAll() {
        List<Discount> expectedDiscounts = Collections.singletonList(discount);
        Mockito.when(discountRepository.findAll()).thenReturn(expectedDiscounts);
        List<Discount> actualDiscounts = discountService.getAll();
        assertEquals(expectedDiscounts, actualDiscounts);
    }
}