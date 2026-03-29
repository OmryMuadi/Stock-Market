package dev.kourier

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [
    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration::class
])
@EnableRabbit
class UserServiceApplication

fun main(args: Array<String>) {
    runApplication<UserServiceApplication>(*args)
}
