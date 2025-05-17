package com.rhenan.pedidos.api.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {


    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.dlx.name}")
    private String exchangeDlxName;

    @Value("${rabbitmq.queue.dlq.name}")
    private String queueDlqName;

    @Bean
    public FanoutExchange pedidosExchange() { //cria a exchange
        return new FanoutExchange(exchangeName);
    }

    @Bean
    public FanoutExchange pedidosDlxExchange() { //cria a exchange
        return new FanoutExchange(exchangeDlxName);
    }

    @Bean
    public Queue processadorQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", exchangeDlxName);
        return new Queue(queueName, true, false, false, arguments);
    }

    @Bean
    public Queue processadorDlqQueue() {
        return new Queue(queueDlqName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(processadorQueue()).to(pedidosExchange());
    }

    @Bean
    public Binding bindingDlq() {
        return BindingBuilder.bind(processadorDlqQueue()).to(pedidosDlxExchange());
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter(); //converter o objeto em json
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
