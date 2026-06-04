package in.sp.main.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import in.sp.main.service.CustomerServiceManagement;

/**
 * A scheduler to send email.
 */
@Component
public class ReminderScheduler {

    private final CustomerServiceManagement
            customerServiceManagement;

    public ReminderScheduler(
            CustomerServiceManagement customerServiceManagement
    ) {

        this.customerServiceManagement =
                customerServiceManagement;
    }

    @Scheduled(cron = "0 15 14 * * ?")
    public void runDailyReminder() {

        customerServiceManagement
                .sendExpiryReminders();
    }
}