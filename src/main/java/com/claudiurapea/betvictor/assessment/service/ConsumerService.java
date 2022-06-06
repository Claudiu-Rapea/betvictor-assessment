package com.claudiurapea.betvictor.assessment.service;

import com.claudiurapea.betvictor.assessment.dal.entity.MessageEntity;
import com.claudiurapea.betvictor.assessment.dal.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

    private final MessageRepository repository;

    public ConsumerService(MessageRepository repository) {
        this.repository = repository;
    }

    @JmsListener(destination = "${queue.inbound}")
    public void processMessage(Message<String> message) {
        String payload = message.getPayload();
        log.info("Received message with payload {}", payload);
        persistMessage(payload);
    }

    private void persistMessage(String message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContent(message);
        log.info("Save message to the database");
        repository.save(messageEntity);
    }

}
