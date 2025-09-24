package br.com.conexao.saude.infrastructure.scheduling;

import br.com.conexao.saude.application.facade.DeliveryNotificationFacade;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NotificationSchedulerTest {

    @Mock
    private DeliveryNotificationFacade notificationDeliveryFacade;

    @InjectMocks
    private NotificationScheduler notificationScheduler;

    @Test
    @DisplayName("should call delivery notification with daily frequency")
    void generateDailyNotification_ShouldCallFacadeWithDailyFrequency() {
        notificationScheduler.generateDailyNotification();
        verify(notificationDeliveryFacade).deliveryNotification(NotificationFrequency.DAILY);
    }

    @Test
    @DisplayName("should call delivery notification with weekly frequency")
    void generateWeeklyNotification_ShouldCallFacadeWithWeeklyFrequency() {
        notificationScheduler.generateWeeklyNotification();
        verify(notificationDeliveryFacade).deliveryNotification(NotificationFrequency.WEEKLY);
    }
}