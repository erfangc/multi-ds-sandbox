package com.example.multidssandbox.mysql

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class PersonServiceTest {

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Autowired
    private lateinit var counterRepository: CounterRepository

    @Test
    internal fun testPerson() {
        val svc = PersonService(
            personRepository, counterRepository
        )
        try {
            svc.createPerson()
        } catch (ex: Exception) {

        }

        val person = personRepository.getById(0)
        assertEquals("John", person.name)
    }
}