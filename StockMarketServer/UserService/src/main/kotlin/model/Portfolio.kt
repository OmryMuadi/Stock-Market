package model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.print.attribute.standard.DateTimeAtCreation

@Document(collection = "portfolios")
data class Portfolio(
    @Id
    val portfolioId: Int,
    val userId: Int,
    var cashBalance: Float,
    var holdings: MutableList<Holding>)