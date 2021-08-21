package com.example.multidssandbox.mysql

import com.example.multidssandbox.mysql.models.Counter
import com.example.multidssandbox.mysql.models.Person
import com.example.multidssandbox.mysql.repositories.CounterRepository
import com.example.multidssandbox.mysql.repositories.PersonRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val counterRepository: CounterRepository,
) {
    @Transactional
    fun createPerson(): Person {
        val person = personRepository.save(Person(name = "John", age = 12))
        val counter = counterRepository.findByIdOrNull("people")
        if (counter == null) {
            counterRepository.save(Counter(name = "people", count = 1))
        } else {
            counterRepository.save(counter.apply { count += 1 })
        }
        return person
    }
}