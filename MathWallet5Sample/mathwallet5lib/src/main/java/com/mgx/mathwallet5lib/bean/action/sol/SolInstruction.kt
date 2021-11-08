package com.mgx.mathwallet5lib.bean.action.sol

data class SolInstruction(
    var keys: List<SolKey>,
    var programId: String,
    var data: String
)