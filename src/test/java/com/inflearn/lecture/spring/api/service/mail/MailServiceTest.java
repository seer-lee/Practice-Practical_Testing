package com.inflearn.lecture.spring.api.service.mail;

import com.inflearn.lecture.spring.client.mail.MailSendClient;
import com.inflearn.lecture.spring.domain.history.mail.MailSendHistory;
import com.inflearn.lecture.spring.domain.history.mail.MailSendHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {
    @Spy
    private MailSendClient mailSendClient;
    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        doReturn(true).
                when(mailSendClient)
                .sendEmail(
                        anyString(),
                        anyString(),
                        anyString(),
                        anyString());

        // when
        boolean result = mailService.sendMail("", "", "", "");
        // then
        assertTrue(result);
        verify(mailSendHistoryRepository, times(1))
                .save(any(MailSendHistory.class));
    }
}