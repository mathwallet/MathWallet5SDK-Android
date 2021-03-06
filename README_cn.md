
# MathWallet5SDK-Android
#### 基于SimpleWallet协议的Android SDK
#### 目前支持 EVM 系,Solana的公链


# 如何安装 ？
#### 1.复制 Sample 中 libs 下的 mathwallet5lib-release.aar 到你的 app libs 下
#### 2.在app的build.gradle中添加
```
repositories {
    flatDir {
        dirs 'libs'
    }
}
```  
#### 3.在app的build.gradle中添加依赖
 ```
    implementation(name: 'mathwallet5lib-release', ext: 'aar')
```
#### 4.添加依赖库 implementation 'com.google.code.gson:gson:2.8.5'，如果已添加则无需重复添加
#### 5.复制下面的代码到你的AndroidManifest.xml
```xml
        <uses-permission android:name="android.permission.QUERY_ALL_PACKAGES"/> // 兼容Android 11 需要添加此权限！！！！

        <activity android:name="com.mgx.mathwallet5lib.RouterActivity" >
            <intent-filter>
                <data
                    android:host="customhost"
                    android:scheme="customscheme" />
                <data />

                <category android:name="android.intent.category.DEFAULT" />
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.BROWSABLE" />

            </intent-filter>
        </activity>
```
设置自定义的host以及scheme

# 如何使用 ？
#### 1.登录
```java
//EVM 
   val simpleWallet = SimpleWallet<LoginData>(
            chain = Chain(
                "EVM",                          //chaintype
                "56"                            //chainid 
            ),
            dapp = Dapp(
                "SimpleWallet Sample",          //app title
                null                            //app logo
            ),
            action = "login",                   //action
            data = LoginData(),
            callback = "customscheme://customhost?response=",       //回调，scheme和host务必和RouterActivity在xml中设置的相同
        )
        
//Solana
   val simpleWallet = SimpleWallet<LoginData>(
            chain = Chain(
                "SOLANA",                          //chaintype
                "5eykt4UsFv8P8NJdTREpY1vzqKqZKvdpKuc147dw2N9d"                            //chainid 
            ),
            dapp = Dapp(
                "SimpleWallet Sample",          //app title
                null                            //app logo
            ),
            action = "login",                   //action
            data = LoginData(),
            callback = "customscheme://customhost?response=",       //回调，scheme和host务必和RouterActivity在xml中设置的相同
        )
        
```

#### 2.Transaction
```java
//EVM
  val simpleWallet = SimpleWallet<EvmTransactionData>(
            chain = Chain(
                "EVM",          //chaintype
                "56"            //chainid
            ),
            dapp = Dapp(
                "SimpleWallet Sample",          //app title
                null                            //app logo
            ),
            action = "transaction",             //action

            //以下是 0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32 transfer 0.0001 bnb to 0xf5bA48D7EFF5e89A90bf76Bb276AF6FD22A6233B
            data = EvmTransactionData(
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",
                "0xf5bA48D7EFF5e89A90bf76Bb276AF6FD22A6233B",
                (0.0001 * 10.0.pow(18.0)).toString(),
                ""
            ),
            callback = "customscheme://customhost?response=",       ///回调，scheme和host务必和RouterActivity在xml中设置的相同
        )
        
//SOLANA
 val simpleWallet = SimpleWallet<SolTransactionData>(
            chain = Chain(
                "SOLANA",                          //chaintype
                "5eykt4UsFv8P8NJdTREpY1vzqKqZKvdpKuc147dw2N9d"                            //chainid 
            ),
            dapp = Dapp(
                "SimpleWallet Sample",          //app title
                null                            //app logo
            ),
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
                     * SPLTokenTransfer with createNewAccountAddress
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
                     * SplTokenTransfer without CreateNewAccount
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
```  

#### 3.消息签名
```java
//EVM
 val simpleWallet = SimpleWallet<SignMessageData>(
            chain = Chain(
                "EVM",              //chaintype
                "56"                //chainid
            ),
            dapp = Dapp(
                "SimpleWallet Sample",           //app title
                null                              //app logo
            ),  
            action = "signMessage",                 //action
            data = SignMessageData(
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",       //地址
                "hello world",                                      //签名的内容
            ),
            callback = "customscheme://customhost?response=",            ///回调，scheme和host务必和RouterActivity在xml中设置的相同
        )
        
//SOLANA
 val simpleWallet = SimpleWallet<SignMessageData>(
            chain = Chain(
                "SOLANA",                          //chaintype
                "5eykt4UsFv8P8NJdTREpY1vzqKqZKvdpKuc147dw2N9d"                            //chainid 
            ),
            dapp = Dapp(
                "SimpleWallet Sample",          //app title
                null                            //app logo
            ),
            action = "signMessage",                 //action
            data = SignMessageData(
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",       //地址
                "hello world",                                      //签名的内容
            ),
            callback = "customscheme://customhost?response=",            ///回调，scheme和host务必和RouterActivity在xml中设置的相同
        )
        

```  

#### 4.Dapp跳转
```java
//EVM
    val simpleWallet = SimpleWallet<OpenUrlData>(
            chain = Chain(
                "EVM",          //chaintype
                "56"             //chainid
            ),
            dapp = Dapp(
                "SimpleWallet Sample",           //地址
                null                              //app logo
            ),
            action = "openURL",                  //action
            data = OpenUrlData(
                "https://pancakeswap.finance/",     //跳转的dapp地址
            ),
            callback = "customscheme://customhost?response=",       ///回调，scheme和host务必和RouterActivity在xml中设置的相同
        )
        
//SOLANA
val simpleWallet = SimpleWallet<OpenUrlData>(
            chain =  Chain(
                "SOLANA",                          //chaintype
                "5eykt4UsFv8P8NJdTREpY1vzqKqZKvdpKuc147dw2N9d"                            //chainid 
            ),
            dapp = Dapp(
                "SimpleWallet Sample",          //app title
                null                            //app logo
            ),
            action = "openURL",
            data = OpenUrlData(
                "https://raydium.io/swap/",
            ),
            callback = "customscheme://customhost?response=",
        )
```  
