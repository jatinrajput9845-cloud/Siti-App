package in.sp.main.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import in.sp.main.entity.CustomerService;
import in.sp.main.repository.CustomerServiceRepository;

@Service
public class CustomerServiceManagement {

    private final CustomerServiceRepository customerServiceRepository;

    public CustomerServiceManagement(
            CustomerServiceRepository customerServiceRepository
    ) {

        this.customerServiceRepository =
                customerServiceRepository;
    }

    // REMINDER LOGIC
    public void sendExpiryReminders() {

        LocalDate today = LocalDate.now();

        LocalDate next5Days =
                today.plusDays(5);

        List<CustomerService> services =
                customerServiceRepository
                        .findByEndDateBetweenAndStatus(
                                today,
                                next5Days,
                                "ACTIVE"
                        );

        for (CustomerService service : services) {

            long daysLeft =
                    ChronoUnit.DAYS.between(
                            today,
                            service.getEndDate()
                    );

            String message;

            if (daysLeft == 0) {

                message =
                        "Your connection expires today!";

            } else if (daysLeft == 1) {

                message =
                        "Your connection expires in 1 day!";

            } else {

                message =
                        "Your connection expires in "
                                + daysLeft +
                                " days!";
            }

            System.out.println(
                    "Sending reminder to: "
                            + service.getCustomer().getMobile()
            );

            System.out.println(message);
        }
    }
}