package com.mgx.mathwallet5lib.bean.action.sol

data class SolKey(
    var pubkey: String,
    var isSigner: Boolean,
    var isWritable: Boolean
)