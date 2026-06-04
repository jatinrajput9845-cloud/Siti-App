package in.sp.main.dto;

import java.time.LocalDate;

public class RechargeDTO {

    private Long customerId;

    private Long serviceId;

    private Double amount;

    private String paymentMode;

    private LocalDate validityTill;

    private Long rechargedByEmployeeId;

    public RechargeDTO() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LocalDate getValidityTill() {
        return validityTill;
    }

    public void setValidityTill(LocalDate validityTill) {
        this.validityTill = validityTill;
    }

    public Long getRechargedByEmployeeId() {
        return rechargedByEmployeeId;
    }

    public void setRechargedByEmployeeId(Long rechargedByEmployeeId) {
        this.rechargedByEmployeeId = rechargedByEmployeeId;
    }
}