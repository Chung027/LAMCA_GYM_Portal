package com.example.lamcagym.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${contact.recipient}")
    private String recipient;
    @Value("${spring.mail.username}")
    private String sender;

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
   @PostMapping
   public ResponseEntity<?> sendContactEmail(@RequestBody Map<String, String> form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setReplyTo(form.get("email"));
        message.setSubject("Contact Form Submission from " + form.get("name"));
        message.setText("Name: " + form.get("name") + "\nEmail: " + form.get("email") + "\nMessage: " + form.get("message"));
        try {
            mailSender.send(message);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error sending email: ", e);
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    } 
}
