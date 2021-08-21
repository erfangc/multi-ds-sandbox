package com.example.multidssandbox.mysql.models

import java.time.Instant
import javax.persistence.*

@Entity
class Counter(
    @Id
    val name: String,
    @Column
    val count: Int,
    @Column
    val lastUpdated: Instant = Instant.now(),
)
