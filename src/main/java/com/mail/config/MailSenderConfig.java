package com.mail.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by dai_youcheng on 2017/4/18.
 */
@Configuration
public class MailSenderConfig {
    private static Logger logger = LoggerFactory.getLogger(MailSenderConfig.class);
    @Autowired
    private Environment ev;

    @Bean(name = "JavaMailSender")
    public JavaMailSender getSeader() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        String username = ev.getProperty("spring.mail.username");
        String password = ev.getProperty("spring.mail.password");
        String smtpHost = ev.getProperty("spring.mail.host");
        String port = ev.getProperty("spring.mail.port");

        javaMailSender.setHost(smtpHost);
        javaMailSender.setPort(Integer.parseInt(port));
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        logger.info("账户:"+javaMailSender.getUsername()+ "密碼: " + javaMailSender.getPassword() +"端口号:"+javaMailSender.getHost() + "smtpHost:" + javaMailSender.getHost());
        return javaMailSender;
    }

}
