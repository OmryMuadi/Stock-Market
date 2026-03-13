package rabbitmq

import dev.kourier.model.PriceUpdatedEvent
import model.LatestPrice
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import repository.LatestPriceService

@Component
class PriceUpdateListener(
    private val latestPriceService: LatestPriceService
) {

    @RabbitListener(queues = [RabbitMqConfig.USER_PRICE_QUEUE])
    fun handlePriceUpdated(event: PriceUpdatedEvent) {
        val latestPrice = LatestPrice(
            symbol = event.symbol,
            price = event.price,
            updatedAt = event.timestamp
        )

        latestPriceService.save(latestPrice)
    }
}