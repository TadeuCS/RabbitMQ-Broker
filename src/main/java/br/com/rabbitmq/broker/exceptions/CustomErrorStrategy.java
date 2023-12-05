package br.com.rabbitmq.broker.exceptions;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;

public class CustomErrorStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {

    @Override
    public boolean isFatal(Throwable t) {
        Message message = ((ListenerExecutionFailedException) t).getFailedMessage();
        System.out.println(message.getBody());
        return t.getCause() instanceof IllegalArgumentException;
    }

}
