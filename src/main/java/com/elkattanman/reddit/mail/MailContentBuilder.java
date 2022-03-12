package com.elkattanman.reddit.mail;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {

    public static final String MESSAGE = "message";
    public static final String MAIL_TEMPLATE = "mailTemplate";
    private final TemplateEngine templateEngine;

    public String build(String message) {
        Context context = new Context();
        context.setVariable(MESSAGE, message);
        return templateEngine.process(MAIL_TEMPLATE, context);
    }
}