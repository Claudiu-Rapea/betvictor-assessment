package com.claudiurapea.betvictor.assessment.web;

import com.claudiurapea.betvictor.assessment.service.ProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/message")
public class MessagingController {

    private final ProducerService service;

    public MessagingController(ProducerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> send(@RequestBody String message) {
        service.send(message);
        return ResponseEntity.ok("Message sent!");
    }

}
