package com.mail.controller;

import com.mail.bean.mailContent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dai_youcheng on 2017/4/18.
 */
@Controller
@RequestMapping("/simpleMail")
@Api
public class SimpleMailMessageSend {
    private static Logger logger = LoggerFactory.getLogger(SimpleMailMessageSend.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ApiOperation(value = "简单邮件发送", notes = "简单邮件发送")
    public String send(@RequestBody mailContent content) {
        String sender = environment.getProperty("spring.mail.from");
        logger.info("发件人：" + sender);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(content.getTo() );
        simpleMailMessage.setSubject(content.getSubject());
        simpleMailMessage.setText(content.getText());
        javaMailSender.send(simpleMailMessage);
        return "";
    }
}
