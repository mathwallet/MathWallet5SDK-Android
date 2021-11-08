package com.mgx.mathwallet5sample

import android.os.Bundle
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
import com.mgx.mathwallet5lib.bean.action.TransactionData
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
        val simpleWallet = SimpleWallet<TransactionData>(
            chain = chain,
            dapp = dapp,
            action = "transaction",
            //D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH transfer 0.0001 sol to CBNrNPRzhJb6oBWdy3CaN79TKJ41oCWA46A3fN49oFDV
            data = TransactionData(
                "D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH",
                "CBNrNPRzhJb6oBWdy3CaN79TKJ41oCWA46A3fN49oFDV",
                (0.0001 * 10.0.pow(9.0)).toString(),
                ""
            ),
            callback = "customscheme://customhost?response=",
        )
        MathWallet5Manager.requestAction(this, simpleWallet, object : MathWallet5CallBack {
            override fun callback(params: Map<String, Any>, uriString: String) {
                showMessageTv?.text = "params=" + Gson().toJson(params) + "\n \n uri=" + uriString
            }
        })
    }

    fun signMessage(view: View) {
        val simpleWallet = SimpleWallet<SignMessageData>(
            chain = chain,
            dapp = dapp,
            action = "signMessage",
            data = SignMessageData(
                "D37m1SKWnyY4fmhEntD84uZpjejUZkbHQUBEP3X74LuH",
                "hello world",
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