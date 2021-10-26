
# MathWallet5SDK-Android
#### 基于SimpleWallet协议的Android SDK
#### 目前支持 EVM 系的公链


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
```

#### 2.转账
```java
  val simpleWallet = SimpleWallet<TransactionData>(
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
            data = TransactionData(
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",
                "0xf5bA48D7EFF5e89A90bf76Bb276AF6FD22A6233B",
                (0.0001 * 10.0.pow(18.0)).toString(),
                ""
            ),
            callback = "customscheme://customhost?response=",       ///回调，scheme和host务必和RouterActivity在xml中设置的相同
        )
```  

#### 3.消息签名
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
                "0x306Bb8081C7dD356eA951795Ce4072e6e4bFdC32",       //地址
                "hello world",                                      //签名的内容
            ),
            callback = "customscheme://customhost?response=",            ///回调，scheme和host务必和RouterActivity在xml中设置的相同
        )
```  

#### 4.Dapp跳转
```java
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
```  
