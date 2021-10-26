package com.mgx.mathwallet5lib.bean.action

data class TransactionData(
    var from: String,
    var to: String,
    var value: String,
    var data: String?
)