package dev.kourier.rabbitmq

import dev.kourier.model.PriceUpdatedEvent
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Component

@Component
class PriceEventPublisher(
    private val amqpTemplate: AmqpTemplate
) {

    fun publishPriceUpdated(event: PriceUpdatedEvent) {
        amqpTemplate.convertAndSend(
            RabbitMqConfig.PRICE_EXCHANGE,
            RabbitMqConfig.PRICE_ROUTING_KEY,
            event
        )
    }
}