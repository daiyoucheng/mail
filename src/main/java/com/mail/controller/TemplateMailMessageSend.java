package com.mail.controller;

import com.mail.bean.mailContent;
import io.swagger.annotations.Api;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * Created by dai_youcheng on 2017/4/19.
 */
@Api
@Controller
@RequestMapping("/template")
public class TemplateMailMessageSend {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment ev;

    //spring版本过高会导致不支持Velocity
    @Autowired
    private VelocityEngine velocityEngine;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public String send(@RequestBody mailContent content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            mimeMessageHelper.setTo(content.getTo());
            mimeMessageHelper.setFrom(ev.getProperty("spring.mail.from"));
            mimeMessage.setSubject(content.getSubject());
            Map<String, Object> map = new HashedMap();
            map.put("username", "DYC");
            //*VelocityEngine velocityEngine = new VelocityEngine();*//*
            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template.vm", "UTF-8", map);
            mimeMessageHelper.setText(text, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "S";

    }

}