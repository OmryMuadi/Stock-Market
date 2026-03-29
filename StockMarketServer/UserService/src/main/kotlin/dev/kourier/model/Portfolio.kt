package dev.kourier.model

import java.util.*

data class Portfolio(
    val portfolioId: UUID,
    val userEmail: String,
    var cashBalance: Float,
    var holdings: MutableList<Holding>)