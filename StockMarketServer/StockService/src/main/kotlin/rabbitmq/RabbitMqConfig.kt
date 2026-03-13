package dev.kourier.rabbitmq

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {

    companion object {
        const val PRICE_EXCHANGE = "price.exchange"
        const val PRICE_QUEUE = "price.queue"
        const val PRICE_ROUTING_KEY = "price.updated"
    }

    @Bean
    fun priceExchange(): TopicExchange {
        return TopicExchange(PRICE_EXCHANGE)
    }

    @Bean
    fun priceQueue(): Queue {
        return Queue(PRICE_QUEUE, true)
    }

    @Bean
    fun priceBinding(priceQueue: Queue, priceExchange: TopicExchange): Binding {
        return BindingBuilder
            .bind(priceQueue)
            .to(priceExchange)
            .with(PRICE_ROUTING_KEY)
    }
}