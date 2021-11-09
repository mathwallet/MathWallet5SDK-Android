
# MathWallet5SDK-Android
#### Android SDK based on SimpleWallet protocol
#### Supports public chains : EVM System，Solana
# How to install？
#### 1.Copy mathwallet5lib-release.aar in the libs of Sample to your app libs
#### 2.Add in app's build.gradle:
```
repositories {
      flatDir {
            dirs 'libs'
      }
}
```
#### 3.Add implement in build.gradle of app
```
 implementation(name: 'mathwallet5lib-release', ext: 'aar')
```
#### 4.copy the following code to your AndroidManifest.xml
```xml
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
Set a custom host and scheme
# How to use？
#### 1.Login
```java
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
            callback = "customscheme://customhost?response=",       //callback，scheme and host must be the same as the RouterActivity in xml
        )
```
#### 2.Transfer
```java
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

            //means 0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32 transfer 0.0001 bnb to 0xf5bA48D7EFF5e89A90bf76Bb276AF6FD22A6233B
            data = EvmTransactionData(
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",
                "0xf5bA48D7EFF5e89A90bf76Bb276AF6FD22A6233B",
                (0.0001 * 10.0.pow(18.0)).toString(),
                ""
            ),
            callback = "customscheme://customhost?response=",       ///callback，scheme and host must be the same as the RouterActivity in xml
        )
 });
```

#### 3.sign message
```java
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
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",       //address
                "hello world",                                      //message
            ),
            callback = "customscheme://customhost?response=",            ///callback，scheme and host must be the same as the RouterActivity in xml
        )
```  

#### 5.open DappUrl
```java
         val simpleWallet = SimpleWallet<OpenUrlData>(
            chain = Chain(
                "EVM",          //chaintype
                "56"             //chainid
            ),
            dapp = Dapp(
                "SimpleWallet Sample",           //app title
                null                              //app logo
            ),
            action = "openURL",                  //action
            data = OpenUrlData(
                "https://pancakeswap.finance/",     //dapp url
            ),
            callback = "customscheme://customhost?response=",       ///callback，scheme and host must be the same as the RouterActivity in xml
        )
```  
