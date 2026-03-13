package dev.kourier.rabbitmq

import dev.kourier.model.PriceUpdatedEvent
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class PriceEventPublisher(
    private val rabbitTemplate: RabbitTemplate
) {

    fun publishPriceUpdated(event: PriceUpdatedEvent) {
        rabbitTemplate.convertAndSend(
            RabbitMqConfig.PRICE_EXCHANGE,
            RabbitMqConfig.PRICE_ROUTING_KEY,
            event
        )
    }
}