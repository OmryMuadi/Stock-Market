package controller.MarketDataAPI.response

data class StockResponse(
    val symbol: String,
    val name: String,
    val currentPrice: Float
)
