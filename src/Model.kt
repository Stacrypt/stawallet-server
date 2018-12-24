package com.stawallet

data class Coin(
    val code: String = ""
)

data class Wallet(
    val owner: String,
    val name: String,
    val type: String,
    val network: String
)

data class Account(
    val type: Int = 44,
    val coin: String,
    val account: Int,
)

data class Address(
    val index,
    val change
)