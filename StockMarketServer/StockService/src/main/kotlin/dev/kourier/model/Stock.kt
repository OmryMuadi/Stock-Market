package dev.kourier.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

//@Document(collection = "stocks")
//data class Stock(
//    @Id
//    val symbol: String,
//    val name: String,
//    var currentPrice: Float,
//    var lastUpdatedAt: LocalDateTime)


data class Stock(
    val symbol: String,
    val name: String,
    var currentPrice: Float,
    var lastUpdatedAt: LocalDateTime)