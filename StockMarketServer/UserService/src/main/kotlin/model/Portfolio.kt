package model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID
import javax.print.attribute.standard.DateTimeAtCreation

//@Document(collection = "portfolios")
//data class Portfolio(
//    @Id
//    val portfolioId: Int,
//    val userEmail: String,
//    var cashBalance: Float,
//    var holdings: MutableList<Holding>)

data class Portfolio(
    val portfolioId: UUID,
    val userEmail: String,
    var cashBalance: Float,
    var holdings: MutableList<Holding>)