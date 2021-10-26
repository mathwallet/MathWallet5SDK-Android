package com.mgx.mathwallet5lib.bean

import java.util.*

open class SimpleWallet<T>(
    val protocol: String = "SimpleWallet",
    val version: String = "2.0",
    var id: String = UUID.randomUUID().toString(),

    var chain: Chain,
    var dapp: Dapp,
    var action: String,
    var data: T,
    var callback: String
)