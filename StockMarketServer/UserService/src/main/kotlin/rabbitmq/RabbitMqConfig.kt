package rabbitmq

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
        const val USER_PRICE_QUEUE = "user.price.queue"
        const val PRICE_ROUTING_KEY = "price.updated"
    }

    @Bean
    fun priceExchange(): TopicExchange {
        return TopicExchange(PRICE_EXCHANGE)
    }

    @Bean
    fun userPriceQueue(): Queue {
        return Queue(USER_PRICE_QUEUE, true)
    }

    @Bean
    fun userPriceBinding(userPriceQueue: Queue, priceExchange: TopicExchange): Binding {
        return BindingBuilder
            .bind(userPriceQueue)
            .to(priceExchange)
            .with(PRICE_ROUTING_KEY)
    }
}