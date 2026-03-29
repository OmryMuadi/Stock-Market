package dev.kourier.service

import dev.kourier.model.Transaction
import dev.kourier.model.TransactionType
import org.springframework.stereotype.Service
//import repository.TransactionRepository
import java.time.LocalDateTime
import java.util.LinkedList
import java.util.UUID
import kotlin.math.PI


@Service
class TransactionService {

    val transactions: MutableMap<UUID, Transaction> = mutableMapOf()
    fun saveTransaction(type: TransactionType, userEmail: String, symbol: String, quantity: Int, currentPrice: Float) {
        val transaction: Transaction = Transaction(UUID.randomUUID(), type, userEmail,symbol,quantity, currentPrice, LocalDateTime.now())
        transactions.put(UUID.randomUUID(), transaction)
    }

    fun getTransactionsOfUser(userEmail: String): List<Transaction> {
        val userTransactions = LinkedList<Transaction>()
        for((key, value) in transactions){
            if (value.userEmail == userEmail) {
                userTransactions.add(value)
            }
        }
        return userTransactions
    }
}