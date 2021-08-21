package com.example.multidssandbox

import com.example.multidssandbox.mysql.HoldingRepository
import com.example.multidssandbox.mysql.models.Holding
import com.example.multidssandbox.postgres.ProductRepository
import com.example.multidssandbox.postgres.models.Product
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MultiDsSandboxApplication {
    @Bean
    fun commandLineRunner(
        holdingRepository: HoldingRepository
    ): CommandLineRunner {
        return CommandLineRunner {
            val holding = holdingRepository.save(
                Holding(accountId = "ACC1", assetId = "ASSET1", quantity = 100)
            )
            holdingRepository.save(holding.apply { quantity = quantity?.plus(150) })
        }
    }
}

fun main(args: Array<String>) {
    runApplication<MultiDsSandboxApplication>(*args)
}
