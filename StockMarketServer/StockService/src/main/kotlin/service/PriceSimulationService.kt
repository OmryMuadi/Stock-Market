package dev.kourier.service

import dev.kourier.model.PriceHistory
import dev.kourier.model.PricePoint
import dev.kourier.model.PriceUpdatedEvent
import dev.kourier.model.Stock
import dev.kourier.rabbitmq.PriceEventPublisher
import dev.kourier.repository.PriceHistoryRepository
import dev.kourier.repository.StockRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.math.round
import kotlin.random.Random

//@Service
//class PriceSimulationService(
//    private val stockService: StockService,
//    private val stockRepository: StockRepository,
//    private val priceHistoryService: PriceHistoryService,
//    private val priceHistoryRepository: PriceHistoryRepository) {
//    val OPEN_TIME: LocalTime = LocalTime.of(9, 0)
//    val CLOSE_TIME: LocalTime = LocalTime.of(17, 0)
//    val MIN_RANDOM_PERCENT = -0.02
//    val MAX_RANDOM_PERCENT = 0.02
//    fun roundTo2(value: Double): Float {
//        return (round(value * 100) / 100).toFloat()
//    }
//
//    fun isMarketOpen(): Boolean{
//        val now = LocalTime.now()
//        if (OPEN_TIME.isBefore(now) && now.isBefore(CLOSE_TIME)){
//            return true
//        }
//        return false
//    }
//        @Scheduled(fixedRate = 5000)
//    fun Simulation(){
//            if (!isMarketOpen()){
//                return
//            }
//            val stocks = stockService.getAllStocks()
//            for (stock in stocks){
//                val oldPrice = stock.currentPrice
//                val randomPercent = MIN_RANDOM_PERCENT + Random.nextFloat() * (MAX_RANDOM_PERCENT - MIN_RANDOM_PERCENT)
//                var newPrice = oldPrice * (1 + randomPercent)
//                if (newPrice < 1){
//                    newPrice = 1.0
//                }
//                stock.currentPrice = roundTo2(newPrice)
//                stock.lastUpdatedAt = LocalDateTime.now()
//
//                stockRepository.save<Stock>(stock)
//                val pricePoint = PricePoint(stock.lastUpdatedAt, stock.currentPrice)
//                priceHistoryService.getPriceHistoryOfStock(stock.symbol).add(pricePoint)
//                priceHistoryRepository.save<PriceHistory>(priceHistoryService.getEntityByStock(stock.symbol)!!)
//
//                // websockets are not used yet
//
//            }
//    }
//}

class PriceSimulationService(
    private val stockService: StockService,
    private val priceHistoryService: PriceHistoryService,
    private val priceEventPublisher: PriceEventPublisher) {
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
    @Scheduled(fixedRate = 5000)
    fun Simulation(){
        if (!isMarketOpen()){
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

