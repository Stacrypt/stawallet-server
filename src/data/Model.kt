package com.perfect.sirma.data

import com.perfect.sirma.data.AccountTable.default
import com.perfect.sirma.data.AccountTable.primaryKey
import com.perfect.sirma.data.AccountTable.references
import org.jetbrains.exposed.sql.*


//val ALL_MODELS = arrayOf()

object WalletTable : Table("wallets") {
    val id = varchar("id", 100).primaryKey()
}

object HdNodeTable : Table("hd_nodes") {
    val id = varchar("id", 20).primaryKey(0)

    val walletId = (varchar("wallet_id", 100) references WalletTable.id).primaryKey(1)

    // Exp: m/44'/0'/0'
    val path = integer("purpose").primaryKey(1).default(44)

//    val purpose = integer("purpose").primaryKey(1).default(44)
//    val coinType = integer("coin_type").primaryKey(2)
//    val account_index = integer("account_index").primaryKey(3)

    val xpub = binary("xprv", 128)
    val xprv = binary("xprv", 64).nullable()
}

object AddressTable : Table("addresses") {

    val walletId = (varchar("wallet_id", 100) references HdNodeTable.walletId).primaryKey(0)
    val hdNodeId = (varchar("hd_node_id", 100) references HdNodeTable.id).primaryKey(1)

    val sequenceIndex = integer("sequence_index").primaryKey(2)
    val changeIndex = integer("change_index").primaryKey(3).nullable()

    val archived = bool("archived").default(false)

}

//class MarketEntity(id: EntityID<String>) : Entity<String>(id) {
//    companion object : EntityClass<String, MarketEntity>(MarketTable)
//}
//
//object TikTable : LongIdTable("tiks") {
//}