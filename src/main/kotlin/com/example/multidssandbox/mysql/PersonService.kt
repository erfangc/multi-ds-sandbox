package com.example.multidssandbox.mysql

import com.example.multidssandbox.mysql.models.Counter
import com.example.multidssandbox.mysql.models.Person
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val counterRepository: CounterRepository,
) {
    @Transactional
    fun createPerson() {
        personRepository.save(Person(id = 0, name = "John", age = 12))
        val counter = counterRepository.findByIdOrNull("people")
        if (counter == null) {
            counterRepository.save(Counter(name = "people", count = 1))
        }
    }
}