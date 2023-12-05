package br.com.rabbitmq.broker.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.com.rabbitmq.broker.dtos.NfseDto;
import br.com.rabbitmq.broker.services.NotaService;
import br.com.rabbitmq.broker.services.RabbitMQService;
import br.com.rabbitmq.broker.utils.RabbitMQConstants;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RabbitMqConsumer {

    private final NotaService notaService;

    private final RabbitMQService rabbitMQService;

    @RabbitListener(queues = RabbitMQConstants.QUEUE_SEND)
    public void consumer(String message) throws Exception {
        try {
            System.out.println(message);
            NfseDto nfseDto = notaService.sendToExternalAPI(message);
            rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_SAVE, nfseDto);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
