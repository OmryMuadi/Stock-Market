package model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.UUID

//@Document(collection = "transactions")
//data class Transaction(
//    @Id
//    var id: UUID,
//    val type: TransactionType,
//    val userEmail: String,
//    val symbol: String,
//    val quantity: Int,
//    val currentPrice: Float,
//    val timeStamp: LocalDateTime)

data class Transaction(
    var id: UUID,
    val type: TransactionType,
    val userEmail: String,
    val symbol: String,
    val quantity: Int,
    val currentPrice: Float,
    val timeStamp: LocalDateTime)