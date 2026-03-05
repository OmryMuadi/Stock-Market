package model

import javax.print.attribute.standard.DateTimeAtCreation

data class Portfolio(val portfolioId: Int,
                     val userId: Int,
                     var cashBalance: Float,
                     var holdings: MutableList<Holding>,
                     val createdAt: DateTimeAtCreation)