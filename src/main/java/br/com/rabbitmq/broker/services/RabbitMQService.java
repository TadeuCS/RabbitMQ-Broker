package br.com.rabbitmq.broker.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String queueName, Object message) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(message);
            System.out.println(json);
            this.rabbitTemplate.convertAndSend(queueName, json);
        } catch (Exception e) {
            throw new Exception("Erro ao formatar o JSON da mensagem!", e);
        }
    }

}
