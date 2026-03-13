package dev.kourier

import dev.kourier.rabbitmq.PriceEventPublisher
import dev.kourier.service.PriceHistoryService
import dev.kourier.service.PriceSimulationService
import dev.kourier.service.StockService
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MarketServiceApplication
fun main(args: Array<String>) {
    runApplication<MarketServiceApplication>(*args)

}