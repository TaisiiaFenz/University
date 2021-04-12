package ua.taya.tayalab_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.taya.tayalab_boot.dto.BillingDto;
import ua.taya.tayalab_boot.entity.Billing;
import ua.taya.tayalab_boot.repository.DiscountRepository;
import ua.taya.tayalab_boot.repository.ReservationRepository;

@Mapper(componentModel = "spring", uses = {ReservationRepository.class, DiscountRepository.class})
public interface BillingMapper {
    @Mappings({
            @Mapping(target = "reservation", source = "dto.reservationId"),
            @Mapping(target = "discount", source = "dto.discountId")
    })
    Billing billingFromDto(BillingDto dto);
}
