package com.example.multidssandbox

import com.example.multidssandbox.mysql.PersonRepository
import com.example.multidssandbox.mysql.models.Person
import com.example.multidssandbox.postgres.ProductRepository
import com.example.multidssandbox.postgres.models.Product
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.time.Instant

@SpringBootApplication
class MultiDsSandboxApplication {
	@Bean
	fun commandLineRunner(
		productRepository: ProductRepository,
		personRepository: PersonRepository,
	): CommandLineRunner {
		return CommandLineRunner { args ->
			personRepository.save(Person(id = 1, name = "Erfang", lastUpdated = Instant.now(), age = 33))
			productRepository.save(Product(id = 1, name = "Erfang", lastUpdated = Instant.now()))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<MultiDsSandboxApplication>(*args)
}
