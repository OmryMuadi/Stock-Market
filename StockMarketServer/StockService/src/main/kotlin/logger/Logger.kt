package dev.kourier.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Logger {
    private val logger: Logger = LoggerFactory.getLogger("Stock Service")
    fun log(message: String){
        logger.info(message)
    }
}