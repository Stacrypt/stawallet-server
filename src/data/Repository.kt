package com.perfect.sirma.data

import org.jetbrains.exposed.sql.transactions.transaction

object Repository {
    fun getAllMarkets(): ArrayList<MarketEntity> {
        val out = ArrayList<MarketEntity>()
        transaction {
            out.addAll(MarketEntity.all())
        }
        return out
    }
}