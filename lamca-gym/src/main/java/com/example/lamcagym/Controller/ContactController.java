package com.example.lamcagym.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

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

   @PostMapping("/")
   public ResponseEntity<String> sendContactEmail(@RequestBody Map<String, String> form) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setReplyTo(form.get("email"));
        message.setSubject("Contact Form Submission from " + form.get("name"));
        message.setText("Name: " + form.get("name") + "\nEmail: " + form.get("email") + "\nMessage: " + form.get("message"));
        try {
            mailSender.send(message);
            return ResponseEntity.ok(Map.of("status", "success"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("status", "error", "message", e.getMessage()));
        }
    } 
}
