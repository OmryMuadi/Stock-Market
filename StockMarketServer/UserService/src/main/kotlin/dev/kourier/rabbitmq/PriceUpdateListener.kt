package dev.kourier.rabbitmq

import dev.kourier.model.LatestPrice
import dev.kourier.model.PriceUpdatedEvent
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import dev.kourier.service.LatestPriceService
import java.time.LocalDateTime

@Component
class PriceUpdateListener(
    private val latestPriceService: LatestPriceService
) {

    @RabbitListener(queues = [RabbitMqConfig.PRICE_QUEUE])
    fun handlePriceUpdated(event: PriceUpdatedEvent) {
        val latestPrice = LatestPrice(
            symbol = event.symbol,
            price = event.price,
            timestamp = event.timestamp
        )

        latestPriceService.save(latestPrice)
    }
}