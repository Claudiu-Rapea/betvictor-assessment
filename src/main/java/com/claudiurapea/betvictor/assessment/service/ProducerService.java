package com.claudiurapea.betvictor.assessment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private static final Logger log = LoggerFactory.getLogger(ProducerService.class);

    private final JmsTemplate jmsTemplate;
    private final String outboundQueue;

    public ProducerService(JmsTemplate jmsTemplate, @Value("${queue.outbound}") String outboundQueue) {
        this.jmsTemplate = jmsTemplate;
        this.outboundQueue = outboundQueue;
    }

    public void send(String message) {
        log.info("Send message with payload {}", message);
        jmsTemplate.convertAndSend(outboundQueue, message);
    }

}
