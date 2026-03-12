package service

import model.Transaction
import model.TransactionType
import org.springframework.stereotype.Service
import repository.TransactionRepository
import java.time.LocalDateTime
import java.util.UUID

@Service
class TransactionService(private val transactionRepository: TransactionRepository) {

    fun saveTransaction(type: TransactionType, userEmail: String, symbol: String, quantity: Int, currentPrice: Float): Transaction {
        val transaction: Transaction = Transaction(UUID.randomUUID(), type, userEmail,symbol,quantity, currentPrice, LocalDateTime.now())
        return transactionRepository.save<Transaction>(transaction)
    }

    fun getTransactionsOfUser(userEmail: String): List<Transaction> {
        return transactionRepository.findByUserEmail(userEmail)
    }
}