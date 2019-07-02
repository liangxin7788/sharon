package com.fun.business.sharon.biz.email;

import org.springframework.mail.MailException;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Package: com.fun.business.sharon.biz.email
 * @ClassName: MailService
 * @Description: java类作用描述
 * @Author: liangxin
 * @CreateDate: 2019/7/1 17:11
 * @UpdateDate: 2019/7/1 17:11
 */
public interface MailService {

    /**
     * 纯文本邮件发送
     * @param to
     *          接收者
     * @param subject
     *          邮件主题
     * @param content
     *          邮件内容
     * @return
     * @throws MailException
     */
    String sendSimpleMail(String to, String subject, String content) throws MailException;

    /**
     *
     * @param to
     *          接收者
     * @param subject
     *          邮件主题
     * @param content
     *          邮件内容
     * @param filePaths
     *          文件位置
     * @throws MessagingException
     */
    String sendAttachmentsMail(String to, String subject, String content, List<String> filePaths) throws MessagingException;


}
