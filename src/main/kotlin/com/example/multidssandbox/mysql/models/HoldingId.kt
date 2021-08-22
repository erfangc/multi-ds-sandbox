package com.example.multidssandbox.mysql.models

import java.io.Serializable

class HoldingId : Serializable {

    lateinit var accountId: String

    lateinit var assetId: String

    constructor(accountId: String, assetId: String) {
        this.accountId = accountId
        this.assetId = assetId
    }

    constructor()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HoldingId

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