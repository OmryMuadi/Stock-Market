package dev.kourier.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "stocks")
data class Stock(
    @Id
    val symbol: String,
    val name: String,
    val currentPrice: Float)