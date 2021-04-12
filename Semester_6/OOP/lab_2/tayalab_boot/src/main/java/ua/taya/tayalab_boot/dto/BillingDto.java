package ua.taya.tayalab_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingDto {
    private Long reservationId;
    private Long discountId;
}
