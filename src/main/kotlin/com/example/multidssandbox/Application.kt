package com.example.multidssandbox

import com.example.multidssandbox.mssql.AccountRepository
import com.example.multidssandbox.mssql.models.Account
import com.example.multidssandbox.mysql.PersonService
import com.example.multidssandbox.mysql.models.Person
import com.example.multidssandbox.mysql.repositories.PersonRepository
import com.example.multidssandbox.postgres.models.Product
import com.example.multidssandbox.postgres.repositories.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    fun commandLineRunner(
        accountRepository: AccountRepository,
        productRepository: ProductRepository,
        personRepository: PersonRepository,
        personService: PersonService,
    ): CommandLineRunner {
        return CommandLineRunner {
            accountRepository.save(Account(name = "Main Account", type = 3))
            productRepository.save(Product(name = "TV"))
            personRepository.save(Person(name = "John", age = 22))
            personService.createPerson()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
