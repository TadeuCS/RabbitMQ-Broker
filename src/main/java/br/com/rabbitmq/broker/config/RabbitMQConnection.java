package br.com.rabbitmq.broker.config;

import br.com.rabbitmq.broker.utils.RabbitMQConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.*;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {

    private final AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue getQueue(String name){
        return new Queue(name, true, false, false);
    }

    private DirectExchange getExchange(){
        return new DirectExchange(RabbitMQConstants.EXCHANGE_NAME);
    }

    private Binding getBinding(Queue queue, Exchange exchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void init(){
        Queue queue = this.getQueue(RabbitMQConstants.QUEUE_SEND);

        DirectExchange exchange = this.getExchange();

        Binding binding = this.getBinding(queue, exchange);

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareExchange(exchange);
        this.amqpAdmin.declareBinding(binding);
    }
}
