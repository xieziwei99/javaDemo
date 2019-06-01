/**
 * @Description 测试SpringBoot中邮件发送
 * @author xieziwei99
 * @create 2019-05-14
 */
package com.xzw.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/mailing")
public class MailingController {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;

    /**
     * @Description: 用SpringBoot发送简单邮件
     * @Param: []
     * @return: java.lang.String
     * @Author: xieziwei99
     * @Date: 2019/5/15
     */
    @GetMapping("/simple")
    public String simple() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(this.mailProperties.getUsername());
        message.setTo("xieziwei99@gmail.com");
        message.setSubject("简单邮件测试");
        message.setText("这是通过SpringBoot发送的邮件");
        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * @Description: 用SpringBoot发送HTML邮件
     * @throws: MessagingException
     * @return: String
     * @Author: xieziwei99
     * @Date: 2019/5/15
     */
    @GetMapping("/html")
    public String html() throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo("xieziwei99@gmail.com");
        messageHelper.setSubject("HTML内容邮件测试");
        messageHelper.setText("<h1>这是用h1标签显示的标题</h1>", true);
        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * @Description: 发送带附件的邮件
     * @throws: MessagingException
     * @return: java.lang.String
     * @Author: xieziwei99
     * @Date: 2019/5/15
     */
    @GetMapping("/attach")
    public String attach() throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo("xieziwei99@gmail.com");
        messageHelper.setSubject("带附件的邮件测试");
        messageHelper.setText("<h1>这是带有附件的邮件</h1>", true);
        messageHelper.addAttachment("万里花图片", new ClassPathResource("万里花.jpg"));
        this.javaMailSender.send(message);
        return "success";
    }

    /**
     * @Description: 用SpringBoot发送带有内联附件的邮件
     * @return: String
     * @throws  MessagingException
     * @Author: xieziwei99
     * @Date: 2019/5/15
     */
    @GetMapping("/inline_attach")
    public String inlineAttach() throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo("xieziwei99@gmail.com");
        messageHelper.setSubject("带内联附件的邮件测试");
        messageHelper.setText("<h1>这是一封带有内联附件<img src=\"cid:attach1\"/>的邮件</h1>", true);
        messageHelper.addInline("attach1", new ClassPathResource("万里花.jpg"));
        this.javaMailSender.send(message);
        return "success";
    }
}
