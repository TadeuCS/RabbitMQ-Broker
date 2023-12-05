package br.com.rabbitmq.broker.exceptions;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class ExceptionHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        Message message = ((ListenerExecutionFailedException) t).getFailedMessage();

        String queueName = message.getMessageProperties().getConsumerQueue();
        System.out.println(queueName);
        String content = new String(message.getBody());
        System.out.println(content);

        System.out.println(t.getCause().getMessage());

        throw new AmqpRejectAndDontRequeueException("Não deve retornar a fila", t);
    }

}
