package com.claudiurapea.betvictor.assessment;

import com.claudiurapea.betvictor.assessment.dal.entity.MessageEntity;
import com.claudiurapea.betvictor.assessment.dal.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ApplicationTests {

    private static final String DUMMY_MESSAGE = "Dummy message";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MessageRepository repository;

    @Test
    void persistMessageToTheDatabaseWhenMessageIsReceivedOnInboundQueue() {
        // given
        // when
        String inboundQueue = "dummy.inbound.queue";
        jmsTemplate.convertAndSend(inboundQueue, DUMMY_MESSAGE);

        // then
        List<MessageEntity> persistedMessages = repository.findAll();
        assertThat(persistedMessages).hasSize(1);
        assertThat(persistedMessages.get(0).getId()).isNotNull();
        assertThat(persistedMessages.get(0).getContent()).isEqualTo(DUMMY_MESSAGE);
        assertThat(persistedMessages.get(0).getCreatedDateTime()).isBefore(LocalDateTime.now());
    }

    @Test
    void sendMessageOnOutboundQueue() throws Exception {
        // given
        String outboundQueue = "dummy.outbound.queue";

        // when
        ResultActions response = mockMvc.perform(post("/v1/message")
                .content(DUMMY_MESSAGE));

        // then
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Message sent!"));

        jmsTemplate.setReceiveTimeout(200);
        assertThat(jmsTemplate.receiveAndConvert(outboundQueue)).isEqualTo(DUMMY_MESSAGE);
    }

}
