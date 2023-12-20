package com.example.restfulapi.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class EmailUtil {
     @Autowired
     private JavaMailSender mailSender;

    public void sendNotification(LocalDateTime ngaytao, double nd,double da, String noidung,String email) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Cảnh báo");
        mimeMessageHelper.setText(
                """
                                      
                        <table>
                                                                          <tr>
                                                                           
                                                                              <th>Thời gian</th>
                                                                              <th>Nhiệt độ</th>
                                                                              <th>Độ ẩm</th>
                                                                              <th>Nội dung</th>
                                                                          </tr>
                                                                          <tr>
                                                                             
                                                                              <td>%s</td>
                                                                              <td>%.2f</td>
                                                                             <td>%.2f</td>
                                                                              <td>%s</td>
                                                                          </tr>
                                                                      </table>
                                       """.formatted(ngaytao.toString(),nd,da,noidung), true);
        mailSender.send(mimeMessage);
    }
}
