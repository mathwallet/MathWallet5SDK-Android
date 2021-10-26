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

class EvmActivity : AppCompatActivity() {

    var showMessageTv: AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evm)
        showMessageTv = findViewById<AppCompatTextView>(R.id.show_message_tv);
    }

    fun login(view: View) {
        val simpleWallet = SimpleWallet<LoginData>(
            chain = Chain(
                "EVM",
                "56"            //bsc
            ),
            dapp = Dapp(
                "SimpleWallet Sample",
                null
            ),
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
            chain = Chain(
                "EVM",
                "56"            //bsc
            ),
            dapp = Dapp(
                "SimpleWallet Sample",
                null
            ),
            action = "transaction",
            //0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32 transfer 0.0001 bnb to 0xf5bA48D7EFF5e89A90bf76Bb276AF6FD22A6233B
            data = TransactionData(
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",
                "0xf5bA48D7EFF5e89A90bf76Bb276AF6FD22A6233B",
                (0.0001 * 10.0.pow(18.0)).toString(),
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
            chain = Chain(
                "EVM",
                "56"            //bsc
            ),
            dapp = Dapp(
                "SimpleWallet Sample",
                null
            ),
            action = "signMessage",
            data = SignMessageData(
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",
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
            chain = Chain(
                "EVM",
                "56"            //bsc
            ),
            dapp = Dapp(
                "SimpleWallet Sample",
                null
            ),
            action = "openURL",
            data = OpenUrlData(
                "https://pancakeswap.finance/",
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