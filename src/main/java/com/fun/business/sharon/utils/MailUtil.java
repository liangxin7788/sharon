package com.fun.business.sharon.utils;

import com.alibaba.fastjson.JSON;
import com.fun.business.sharon.biz.business.bean.CustomerMassage;
import com.fun.business.sharon.common.EmailRecieverList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
@Slf4j
public class MailUtil {

    @Value("${spring.mail.username}")
    private String fromMail;

    @Value("${spring.mail.password}")
    private String fromPassword;

    @Value("${spring.mail.host}")
    private String host;

    /**
     * 邮件发送
     */
    public boolean send(CustomerMassage sendMailVo, String subject) throws Exception
    {
        log.info(sendMailVo.toString());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // 1. 创建一封邮件
        Properties props = new Properties();
        props.setProperty("mail.smtp.host",host);
        props.setProperty("mail.smtp.auth", "true");

        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(fromMail, fromPassword);
            }
        };
        Session session = Session.getInstance(props,auth);

        // 2.创建一个Message，它相当于是邮件内容
        Message message = new MimeMessage(session);

        // 设置发送者
        message.setFrom(new InternetAddress(fromMail));

        // 设置收件人 抄送给自己 避免网易554错误
        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(fromMail));
        InternetAddress[] inets = new InternetAddress[new EmailRecieverList().getToMail().size()];
        for (int i = 0; i < new EmailRecieverList().getToMail().size(); i++) {
            inets[i] = new InternetAddress(new EmailRecieverList().getToMail().get(i));
        }
//        for (String toM : sendMailVo.getToMail()) {
//            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(toM));
//        }
        message.setRecipients(MimeMessage.RecipientType.TO,inets);

        // 邮件主题
        message.setSubject(subject);
        // 邮件正文
        message.setContent(JSON.toJSONString(sendMailVo), "text/html;charset=utf-8");

        // 设置显示的发件时间
        message.setSentDate(new Date());

        // 保存前面的设置
        message.saveChanges();

        try {
            // 创建 Transport用于将邮件发送
            Transport.send(message);

        }catch (Exception e){
            log.info(JSON.toJSONString(sendMailVo)+" 发送邮件失败 "+e.getMessage());
            return false;
        }
        log.info("邮件发送结束");
        return true;

    }
}
