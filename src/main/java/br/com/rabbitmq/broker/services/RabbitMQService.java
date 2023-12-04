package br.com.rabbitmq.broker.services;

import br.com.rabbitmq.broker.utils.LocalDateSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String queueName, Object message) throws Exception{
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(message);
            System.out.println(json);
            this.rabbitTemplate.convertAndSend(queueName, json);
        }catch (Exception e){
            throw new IllegalArgumentException("Erro ao formatar o JSON da mensagem!", e);
        }
    }

}
