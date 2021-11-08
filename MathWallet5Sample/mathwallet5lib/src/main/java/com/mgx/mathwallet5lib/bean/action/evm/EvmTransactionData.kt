package com.mgx.mathwallet5lib.bean.action.evm

data class EvmTransactionData(
    var from: String,
    var to: String,
    var value: String,
    var data: String?
)