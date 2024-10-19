package baxtiyor.hotel.hotelmanagment.component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailCodeSender {
    private final JavaMailSender javaMailSender;
    @Async
    public void sendMessage(Integer code, String recipient) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("baxtisadulloyev@gmail.com");
            helper.setTo(recipient);
            helper.setSubject("Auth Code");
            helper.setText("Your verification code is :" + code, true);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
