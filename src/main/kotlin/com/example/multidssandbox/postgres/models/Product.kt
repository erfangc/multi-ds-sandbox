package com.example.multidssandbox.postgres.models

import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Product(
    @Id
    val id: String = UUID.randomUUID().toString(),
    @Column(nullable = false)
    var name: String,
    @Column(nullable = true)
    var description: String? = null,
    @Column
    var lastUpdated: Instant = Instant.now(),
)
