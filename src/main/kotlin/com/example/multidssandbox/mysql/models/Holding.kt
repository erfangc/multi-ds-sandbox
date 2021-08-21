package com.example.multidssandbox.mysql.models

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(HoldingPrimaryKey::class)
class Holding(
    @Id
    @Column(length = 32)
    val accountId: String,
    @Id
    @Column(length = 32)
    val assetId: String,
    var quantity: Int? = null,
)

class HoldingPrimaryKey : Serializable {

    lateinit var accountId: String

    lateinit var  assetId: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HoldingPrimaryKey

        if (accountId != other.accountId) return false
        if (assetId != other.assetId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accountId.hashCode()
        result = 31 * result + assetId.hashCode()
        return result
    }
}