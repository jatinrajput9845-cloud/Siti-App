package in.sp.main;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import in.sp.main.service.CustomerServiceManagement;

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