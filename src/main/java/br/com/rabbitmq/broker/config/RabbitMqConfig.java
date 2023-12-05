package br.com.rabbitmq.broker.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.FatalExceptionStrategy;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ErrorHandler;

import br.com.rabbitmq.broker.exceptions.CustomErrorStrategy;

@Configuration
public class RabbitMqConfig {

    @Value("${RABBITMQ_PREFETCH_COUNT:1}")
    private int prefetchCount;

    @Bean
    public RabbitListenerContainerFactory<DirectMessageListenerContainer> rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);

        factory.setPrefetchCount(prefetchCount);
        factory.setGlobalQos(true);

        factory.setErrorHandler(errorHandler());

        // factory.setMessageConverter(converter());
        return factory;
    }

    @Bean
    public FatalExceptionStrategy customErrorStrategy() {
        return new CustomErrorStrategy();
    }

    @Bean
    public ErrorHandler errorHandler() {
        return new ConditionalRejectingErrorHandler(customErrorStrategy());
    }

    // @Bean
    // public MessageConverter converter() {
    // return new Jackson2JsonMessageConverter();
    // }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, AmqpAdmin amqpAdmin) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}