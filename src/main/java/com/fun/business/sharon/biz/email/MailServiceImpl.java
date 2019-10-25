package com.fun.business.sharon.biz.email;

import com.fun.business.sharon.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @Package: com.fun.business.sharon.biz.email
 * @ClassName: MailServiceImpl
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/7/1 17:10
 * @UpdateDate: 2019/7/1 17:10
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendSimpleMail(String to, String subject, String content) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); // 邮件发送者
        message.setTo(to); // 邮件接受者
        message.setSubject(subject); // 主题
        message.setText(content); // 内容
        log.info("发送给 " + to + " 的邮件，内容为：" + content);
        try {
            mailSender.send(message);
            return "发送成功";
        } catch (MailException e) {
            log.error(e.getMessage(), e);
            return "发送失败";
        }
    }

    @Override
    public String sendAttachmentsMail(String to, String subject, String content, List<String> filePaths) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        // 添加附件
        if (ObjectUtil.isNotEmpty(filePaths)) {
            FileSystemResource file = null;
            String fileName = null;
            for (String path : filePaths) {
                file = new FileSystemResource(new File(path));
                fileName = file.getFilename();
                helper.addAttachment(fileName, file);
            }
        }
        // 设置邮件头的发送信息
        message.setSentDate(new Date());
        message.saveChanges();
        try {
            mailSender.send(message);
            return "发送成功！";
        } catch (MailException e) {
            return "发送失败！";
        }
    }


}
