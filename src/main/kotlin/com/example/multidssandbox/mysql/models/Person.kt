package com.example.multidssandbox.mysql.models

import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Person(
    @Id
    @Column(length = 36)
    var id: String = UUID.randomUUID().toString(),
    @Column(nullable = false)
    val name: String,
    @Column
    val age: Int,
    @Column
    val lastUpdated: Instant = Instant.now(),
)
