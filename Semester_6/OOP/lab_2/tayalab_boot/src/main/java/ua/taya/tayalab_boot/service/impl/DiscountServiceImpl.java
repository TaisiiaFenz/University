package ua.taya.tayalab_boot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.taya.tayalab_boot.entity.Discount;
import ua.taya.tayalab_boot.repository.DiscountRepository;
import ua.taya.tayalab_boot.service.DiscountService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    final DiscountRepository discountRepository;

    @Override
    public List<Discount> getAll() {
        return discountRepository.findAll();
    }
}
