package br.com.conexao.saude.infrastructure.scheduling;

import br.com.conexao.saude.application.facade.DeliveryNotificationFacade;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationScheduler {

    private final DeliveryNotificationFacade notificationDeliveryFacade;

    @Scheduled(cron = "0 0 10 * * ?")
    public void generateDailyNotification() {
        log.warn("Test Executing daily: Start - {}", System.currentTimeMillis());
        notificationDeliveryFacade.deliveryNotification(NotificationFrequency.DAILY);
        log.warn("Test Executing daily: Finish - {}", System.currentTimeMillis());
    }

    @Scheduled(cron = "0 0 13 ? * MON")
    public void generateWeeklyNotification() {
        log.warn("Test Executing weekly: Start - {}", System.currentTimeMillis());
        notificationDeliveryFacade.deliveryNotification(NotificationFrequency.WEEKLY);
        log.warn("Test Executing weekly: Finish - {}", System.currentTimeMillis());
    }

}