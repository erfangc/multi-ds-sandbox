package com.example.multidssandbox

import com.example.multidssandbox.mssql.models.Account
import com.example.multidssandbox.mssql.repositories.AccountRepository
import com.example.multidssandbox.mysql.PersonService
import com.example.multidssandbox.mysql.models.Holding
import com.example.multidssandbox.mysql.models.HoldingId
import com.example.multidssandbox.mysql.models.Person
import com.example.multidssandbox.mysql.repositories.HoldingRepository
import com.example.multidssandbox.mysql.repositories.PersonRepository
import com.example.multidssandbox.postgres.models.Product
import com.example.multidssandbox.postgres.repositories.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ApplicationRunner(
    private val accountRepository: AccountRepository,
    private val productRepository: ProductRepository,
    private val holdingRepository: HoldingRepository,
    private val personRepository: PersonRepository,
    private val personService: PersonService,
) : CommandLineRunner {

    private val log = LoggerFactory.getLogger(ApplicationRunner::class.java)

    override fun run(vararg args: String) {
        accountRepository.save(Account(name = "Main Account", type = 3))
        productRepository.save(Product(name = "TV"))
        personRepository.save(Person(name = "John", age = 22))
        holdingRepository.save(Holding(accountId = "ACCOUNT1", assetId = "ASSET1", quantity = 100))
        personService.createPerson()

        val holding = holdingRepository.findByIdOrNull(HoldingId(accountId = "ACCOUNT1", assetId = "ASSET1"))
        log.info("Holding quantity=${holding?.quantity}")
    }

}