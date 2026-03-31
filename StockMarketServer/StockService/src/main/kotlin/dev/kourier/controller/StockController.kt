package dev.kourier.controller

import dev.kourier.model.Stock
import dev.kourier.service.StockService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stocks")
class StockController(
    private val stockService: StockService
) {

    @GetMapping
    fun getAllStocks(): ResponseEntity<List<Stock>> {
        return ResponseEntity.ok(stockService.getAllStocks())
    }

    @GetMapping("/{symbol}")
    fun getStockBySymbol(@PathVariable symbol: String): ResponseEntity<Stock> {
        val stock = stockService.getStock(symbol.uppercase())
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(stock)
    }
}