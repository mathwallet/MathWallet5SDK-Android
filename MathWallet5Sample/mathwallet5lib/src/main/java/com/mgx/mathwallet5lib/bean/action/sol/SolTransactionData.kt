package com.mgx.mathwallet5lib.bean.action.sol

data class SolTransactionData(
    var recentBlockhash: String?,
    var instructions: List<SolInstruction>,
)