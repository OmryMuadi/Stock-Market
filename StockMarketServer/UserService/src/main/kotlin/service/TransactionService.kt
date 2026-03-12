package service

import model.Transaction
import model.TransactionType
import org.springframework.stereotype.Service
import repository.TransactionRepository
import java.time.LocalDateTime
import java.util.LinkedList
import java.util.UUID
import kotlin.math.PI

//@Service
//class TransactionService(private val transactionRepository: TransactionRepository) {
//
//    fun saveTransaction(type: TransactionType, userEmail: String, symbol: String, quantity: Int, currentPrice: Float): Transaction {
//        val transaction: Transaction = Transaction(UUID.randomUUID(), type, userEmail,symbol,quantity, currentPrice, LocalDateTime.now())
//        return transactionRepository.save<Transaction>(transaction)
//    }
//
//    fun getTransactionsOfUser(userEmail: String): List<Transaction> {
//        return transactionRepository.findByUserEmail(userEmail)
//    }
//}

class TransactionService {

    val transactions: MutableMap<UUID, Transaction> = emptyMap<UUID, Transaction>() as MutableMap<UUID, Transaction>

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