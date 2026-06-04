package in.sp.main.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recharges")
public class Recharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private CustomerService service;

    @ManyToOne
    @JoinColumn(name = "recharged_by_employee_id")
    private Employee rechargedByEmployee;

    private Double amount;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "validity_till")
    private LocalDate validityTill;

    public Recharge() {
    }

    @PrePersist
    public void prePersist() {
        transactionDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerService getService() {
        return service;
    }

    public void setService(CustomerService service) {
        this.service = service;
    }

    public Employee getRechargedByEmployee() {
        return rechargedByEmployee;
    }

    public void setRechargedByEmployee(Employee rechargedByEmployee) {
        this.rechargedByEmployee = rechargedByEmployee;
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

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public LocalDate getValidityTill() {
        return validityTill;
    }

    public void setValidityTill(LocalDate validityTill) {
        this.validityTill = validityTill;
    }
}