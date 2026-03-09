package controller.MarketDataAPI

import controller.MarketDataAPI.response.StockResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient


object MarketDataClient{

    val webClient: WebClient = WebClient.builder()
        .baseUrl("http://localhost:4200")
        .build()

    fun getStockBySymbol(symbol: String): StockResponse? {
            return webClient
                .get()
                .uri("/stock/{symbol}", symbol)
                .retrieve()
                .bodyToMono(StockResponse::class.java)
                .block()
    }
}