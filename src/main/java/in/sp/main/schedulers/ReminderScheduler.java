package in.sp.main.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import in.sp.main.service.CustomerServiceManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A scheduler to send email.
 */
//@Slf4j
@Component
public class ReminderScheduler {
    private static final Logger log =
            LoggerFactory.getLogger(ReminderScheduler.class);
    private final CustomerServiceManagement
            customerServiceManagement;

    public ReminderScheduler(
            CustomerServiceManagement customerServiceManagement
    ) {

        this.customerServiceManagement =
                customerServiceManagement;
    }

    /**
     * To send a ramiander emails to send to customers.
     */
    @Scheduled(cron = "0 15 14 * * ?")
    public void runDailyReminder() {
    log.info("Starting daily remainders schedulers");
        customerServiceManagement
                .sendExpiryReminders();
        log.info("Completed Schedule Process");
    }

}