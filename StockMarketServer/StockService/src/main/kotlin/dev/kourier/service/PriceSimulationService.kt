package dev.kourier.service

import dev.kourier.logger.Logger
import dev.kourier.model.PricePoint
import dev.kourier.model.PriceUpdatedEvent
import dev.kourier.rabbitmq.PriceEventPublisher

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.round
import kotlin.random.Random

@Service
class PriceSimulationService(
    private val stockService: StockService,
    private val priceHistoryService: PriceHistoryService,
    private val priceEventPublisher: PriceEventPublisher
) {
    val OPEN_TIME: LocalTime = LocalTime.of(9, 0)
    val CLOSE_TIME: LocalTime = LocalTime.of(17, 0)
    val MIN_RANDOM_PERCENT = -0.02
    val MAX_RANDOM_PERCENT = 0.02
    fun roundTo2(value: Double): Float {
        return (round(value * 100) / 100).toFloat()
    }

    fun isMarketOpen(): Boolean{
        val now = LocalTime.now()
        if (OPEN_TIME.isBefore(now) && now.isBefore(CLOSE_TIME)){
            return true
        }
        return false
    }

    @Scheduled(fixedRate = 3000)
    fun Simulation(){
        if (!isMarketOpen()){
            Logger.log("Market is not opened")
            return
        }
        val stocks = stockService.getAllStocks()
        for (stock in stocks){
            val oldPrice = stock.currentPrice
            val randomPercent = MIN_RANDOM_PERCENT + Random.nextFloat() * (MAX_RANDOM_PERCENT - MIN_RANDOM_PERCENT)
            var newPrice = oldPrice * (1 + randomPercent)
            if (newPrice < 1){
                newPrice = 1.0
            }
            stock.currentPrice = roundTo2(newPrice)
            stock.lastUpdatedAt = LocalDateTime.now()

            val pricePoint = PricePoint(stock.lastUpdatedAt, stock.currentPrice)
            priceHistoryService.getPriceHistoryOfStock(stock.symbol).add(pricePoint)

            val priceEvent = PriceUpdatedEvent(stock.symbol, stock.currentPrice, stock.lastUpdatedAt)
            priceEventPublisher.publishPriceUpdated(priceEvent)
            // websockets are not used yet

        }
    }

}

