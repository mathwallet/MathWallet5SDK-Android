package com.mgx.mathwallet5sample

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.google.gson.Gson
import com.mgx.mathwallet5lib.MathWallet5CallBack
import com.mgx.mathwallet5lib.MathWallet5Manager
import com.mgx.mathwallet5lib.bean.Chain
import com.mgx.mathwallet5lib.bean.Dapp
import com.mgx.mathwallet5lib.bean.SimpleWallet
import com.mgx.mathwallet5lib.bean.action.LoginData
import com.mgx.mathwallet5lib.bean.action.OpenUrlData
import com.mgx.mathwallet5lib.bean.action.SignMessageData
import com.mgx.mathwallet5lib.bean.action.sol.SolInstruction
import com.mgx.mathwallet5lib.bean.action.sol.SolKey
import com.mgx.mathwallet5lib.bean.action.sol.SolTransactionData
import com.mgx.mathwallet5lib.util.LogUtil
import kotlin.math.pow

class SolActivity : AppCompatActivity() {

    var showMessageTv: AppCompatTextView? = null

    val chain: Chain = Chain(
        "SOLANA",
        "5eykt4UsFv8P8NJdTREpY1vzqKqZKvdpKuc147dw2N9d"            //sol
    )

    val dapp: Dapp = Dapp(
        "SimpleWallet Sample",
        null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sol)
        showMessageTv = findViewById<AppCompatTextView>(R.id.show_message_tv)
    }

    fun login(view: View) {
        val simpleWallet = SimpleWallet<LoginData>(
            chain = chain,
            dapp = dapp,
            action = "login",
            data = LoginData(),
            callback = "customscheme://customhost?response=",
        )
        MathWallet5Manager.requestAction(this, simpleWallet, object : MathWallet5CallBack {
            override fun callback(params: Map<String, Any>, uriString: String) {
                showMessageTv?.text = "params=" + Gson().toJson(params) + "\n \n uri=" + uriString
            }
        })
    }

    fun pay(view: View) {
        val simpleWallet = SimpleWallet<SolTransactionData>(
            chain = chain,
            dapp = dapp,
            action = "transaction",
            //D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH transfer 0.0001 MATH to H6naeqz7sQj4E3StSBP43CgoZE2JqfMfoUckNK5rua1L
            data = SolTransactionData(
                "",// 可选
                arrayListOf(
                    // TransferSol
//                    SolInstruction(
//                        arrayListOf(
//                            SolKey(
//                                "D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH",
//                                isSigner = true,
//                                isWritable = true
//                            ),
//                            SolKey(
//                                "H6naeqz7sQj4E3StSBP43CgoZE2JqfMfoUckNK5rua1L",
//                                isSigner = false,
//                                isWritable = true
//                            )
//                        ),
//                        programId = "11111111111111111111111111111111",  //Token id
//                        data = "02000000a08601000000000002000000a086010000000000"
//                    )

                    /**
                     * SPLTokenTransfer include createNewAccountAddress
                     */
//                    SolInstruction(
//                        arrayListOf(
//                            // Init
//                            SolKey(
//                                "H6naeqz7sQj4E3StSBP43CgoZE2JqfMfoUckNK5rua1L",
//                                isSigner = false,
//                                isWritable = false
//                            )
//                        ),
//                        programId = "4MNPdKu9wFMvEeZBMt3Eipfs5ovVWTJb31pEXDJAAxX5",  //Token id
//                        data = "0000000000000000000000000000000000000000000000000000000000000000"
//                    ), SolInstruction(
//                        arrayListOf(
//                            // Create Account
//                            SolKey(
//                                "D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH",
//                                isSigner = true,
//                                isWritable = true
//                            ),
//                            SolKey(
//                                "FPNp3cz77JUTSgq8h7SCJaf19FRsYGXfU49KCLEa5VF7",
//                                isSigner = false,
//                                isWritable = true
//                            ), SolKey(
//                                "H6naeqz7sQj4E3StSBP43CgoZE2JqfMfoUckNK5rua1L",
//                                isSigner = false,
//                                isWritable = false
//                            ), SolKey(
//                                "GeDS162t9yGJuLEHPWXXGrb1zwkzinCgRwnT8vHYjKza",
//                                isSigner = false,
//                                isWritable = false
//                            ), SolKey(
//                                "11111111111111111111111111111111",
//                                isSigner = false,
//                                isWritable = false
//                            ), SolKey(
//                                "TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA",
//                                isSigner = false,
//                                isWritable = false
//                            ), SolKey(
//                                "SysvarRent111111111111111111111111111111111",
//                                isSigner = false,
//                                isWritable = false
//                            )
//                        ),
//                        programId = "ATokenGPvbdGVxr1b2hvZbsiqW5xWH25efTNsLJA8knL",  //Token id
//                        data = ""
//                    )
//                    , SolInstruction(
//                        arrayListOf(
//                            // TransferToken
//
//                            SolKey(
//                                "Fs3BgAwGTfQqeUpjWqEdhBWuHqPH7c8tb9pnfEfDncFb",
//                                isSigner = false,
//                                isWritable = true
//                            ), SolKey(
//                                "FPNp3cz77JUTSgq8h7SCJaf19FRsYGXfU49KCLEa5VF7",
//                                isSigner = false,
//                                isWritable = true
//                            ),  SolKey(
//                                "D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH",
//                                isSigner = true,
//                                isWritable = false
//                            )
//                        ),
//                        programId = "TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA",  //Token id
//                        data = "036400000000000000"
//                    )

                    /**
                     * SplTokenTransfer not include CreateNewAccount
                     */
                   SolInstruction(
                        arrayListOf(
                            // TransferSPLToken
                            SolKey(
                                "Fs3BgAwGTfQqeUpjWqEdhBWuHqPH7c8tb9pnfEfDncFb",
                                isSigner = false,
                                isWritable = true
                            ), SolKey(
                                "FPNp3cz77JUTSgq8h7SCJaf19FRsYGXfU49KCLEa5VF7",
                                isSigner = false,
                                isWritable = true
                            ),  SolKey(
                                "D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH",
                                isSigner = true,
                                isWritable = true
                            )
                        ),
                        programId = "TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA",  //Token id
                        data = "036400000000000000"
                    )

                ),
            ),
            callback = "customscheme://customhost?response=",
        )
        MathWallet5Manager.requestAction(this, simpleWallet, object : MathWallet5CallBack {
            override fun callback(params: Map<String, Any>, uriString: String) {
                showMessageTv?.text = "params=" + Gson().toJson(params) + "\n \n uri=" + uriString
            }
        })
    }
    private  val TAG = "SolActivity"
    fun signMessage(view: View) {
        val simpleWallet = SimpleWallet<SignMessageData>(
            chain = chain,
            dapp = dapp,
            action = "signMessage",
            data = SignMessageData(
                "D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH",
//                "546f2061766f6964206469676974616c20646f676e6170706572732c207369676e2062656c6f7720746f2061757468656e74696361746520776974682043727970746f436f726769732e",
            "To avoid digital dognappers, sign below to authenticate with CryptoCorgis."
            ),
            callback = "customscheme://customhost?response=",
        )
        MathWallet5Manager.requestAction(this, simpleWallet, object : MathWallet5CallBack {
            override fun callback(params: Map<String, Any>, uriString: String) {
                showMessageTv?.text = "params=" + Gson().toJson(params) + "\n \n uri=" + uriString
            }
        })
    }

    fun openUrl(view: View) {
        val simpleWallet = SimpleWallet<OpenUrlData>(
            chain = chain,
            dapp = dapp,
            action = "openURL",
            data = OpenUrlData(
                "https://raydium.io/swap/",
            ),
            callback = "customscheme://customhost?response=",
        )
        MathWallet5Manager.requestAction(this, simpleWallet, object : MathWallet5CallBack {
            override fun callback(params: Map<String, Any>, uriString: String) {
                showMessageTv?.text = "params=" + Gson().toJson(params) + "\n \n uri=" + uriString
            }
        })
    }
}