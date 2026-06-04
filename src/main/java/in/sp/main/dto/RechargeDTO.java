package in.sp.main.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class RechargeDTO {

    private Long customerId;

    private Long serviceId;

    private Double amount;

    private String paymentMode;

    private LocalDate validityTill;

    private Long rechargedByEmployeeId;

}