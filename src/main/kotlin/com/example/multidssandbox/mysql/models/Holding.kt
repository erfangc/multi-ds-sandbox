package com.example.multidssandbox.mysql.models

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(HoldingId::class)
class Holding(
    @Id
    @Column(length = 32)
    val accountId: String,
    @Id
    @Column(length = 32)
    val assetId: String,
    var quantity: Int? = null,
    var holdingDate: LocalDate = LocalDate.now(),
)
