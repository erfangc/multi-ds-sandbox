package com.example.multidssandbox.mssql.models

import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Account(
    @Id
    @Column(length = 36)
    val accountId: String = UUID.randomUUID().toString(),
    var name: String? = null,
    var type: Int = 0,
    var lastUpdated: Instant = Instant.now(),
)