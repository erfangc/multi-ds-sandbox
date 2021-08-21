package com.example.multidssandbox.postgres.models

import java.time.Instant
import javax.persistence.*

@Entity
class Product(
    @Id
    val id: Int,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = true)
    val description: String? = null,
    @Column
    val lastUpdated: Instant = Instant.now(),
)
