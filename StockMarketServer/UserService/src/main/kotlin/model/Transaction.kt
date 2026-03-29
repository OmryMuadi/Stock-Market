package model

import java.time.LocalDateTime
import java.util.*

data class Transaction(
    var id: UUID,
    val type: TransactionType,
    val userEmail: String,
    val symbol: String,
    val quantity: Int,
    val currentPrice: Float,
    val timeStamp: LocalDateTime)