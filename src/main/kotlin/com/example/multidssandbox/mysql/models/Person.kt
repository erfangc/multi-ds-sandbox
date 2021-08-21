package com.example.multidssandbox.mysql.models

import java.time.Instant
import javax.persistence.*

@Entity
class Person(
    @Id
    val id: Int,
    @Column(nullable = false)
    val name: String,
    @Column
    val age: Int,
    @Column
    val lastUpdated: Instant = Instant.now(),
)
