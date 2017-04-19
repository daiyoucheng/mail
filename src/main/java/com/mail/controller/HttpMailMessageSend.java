package com.mail.controller;

import com.mail.bean.mailContent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by dai_youcheng on 2017/4/19.
 */
@RequestMapping("/httpMail")
@Controller
@Api
public class HttpMailMessageSend {
    private static Logger logger = LoggerFactory.getLogger(HttpMailMessageSend.class);

    @Autowired
    private Environment ev;

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Http邮件发送", notes = "Http邮件发送")
    public String send(@RequestBody mailContent content) {
        logger.info("开始发送邮件");
        logger.info(content.toString());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;
        try {
            String mailFromPeople = ev.getProperty("spring.mail.from");
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            mimeMessageHelper.setFrom(mailFromPeople);
            mimeMessageHelper.setSubject(content.getSubject());
            mimeMessageHelper.setTo(content.getTo());
            StringBuffer sb = new StringBuffer();
            String rscId = "rscId01";
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:right'>右对齐</p>")
                    .append("<img src=\"cid:").append(rscId).append("\">");
            FileSystemResource file = new FileSystemResource(new File("E:/two/red.jpg"));
            mimeMessageHelper.setText(sb.toString(), true);
            mimeMessageHelper.addInline(rscId, file);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.toString());
            return "F";
        }
        logger.info("邮件发送结束");
        return "S";
    }
}
