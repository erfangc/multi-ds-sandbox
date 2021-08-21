package com.example.multidssandbox.mysql.models

import java.time.Instant
import javax.persistence.*

@Entity
class Counter(
    @Id
    @Column(length = 32)
    val name: String,
    @Column
    var count: Int = 0,
    @Column
    var lastUpdated: Instant = Instant.now(),
)
