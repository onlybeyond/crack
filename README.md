# crack
一、Demo功能描述
-------
    1.增加反编译技术栈<br>
    2.描述反编译的过程,避免一些低级的错误让软件被有心者破解。<br>

二、工具
-------
  * 1、工具说明:工具存放在tools文件夹下,这里仅提供Mac版,其他版本可以根据网址下载
  - 1.1、ApkTool
    - 1.1.1、下载地址:https://ibotpeaches.github.io/Apktool/
    - 1.1.2、集成:appt、apktool、apktool.jar，这三个文件拷贝到/usr/local/bin，升级的时候只需替换jar包
    - 1.1.3、功能:
      + 1.1.2.1、获取apk资源文件、AndroidManifest.xml文件、smali文件
                 apktool d xxx/xxx/crack.apk(生成文件在命令当前位置)
      + 1.1.2.2、 重新打包
                        apktool b xxx/xxx/crack -o new_crack.apk<br>
    - 1.1.4、可能存在问题:<br>
    
<pre><code> Exception in thread "main" brut.androlib.AndrolibException: Could not decode arsc file
                                      	at brut.androlib.res.decoder.ARSCDecoder.decode(ARSCDecoder.java:56)
                                      	at brut.androlib.res.AndrolibResources.getResPackagesFromApk(AndrolibResources.java:491)
                                      	at brut.androlib.res.AndrolibResources.loadMainPkg(AndrolibResources.java:74)
                                      	at brut.androlib.res.AndrolibResources.getResTable(AndrolibResources.java:66)
                                      	at brut.androlib.Androlib.getResTable(Androlib.java:50)
                                      	at brut.androlib.ApkDecoder.getResTable(ApkDecoder.java:189)
                                      	at brut.androlib.ApkDecoder.decode(ApkDecoder.java:114)
                                      	at brut.apktool.Main.cmdDecode(Main.java:146)
                                      	at brut.apktool.Main.main(Main.java:77) 
                                        </code></pre>
                                                                            
                                        
      解决方案:https://ibotpeaches.github.io/Apktool/ 下载最新jar包替换即可。

  - 1.2、DexToJar<br>
    - 1.2.1、下载地址:https://sourceforge.net/projects/dex2jar/
    - 1.2.2、功能:dex转jar
    - 1.2.3、使用
             mac: 将d2j-dex2jar.sh 托人命令行 将反编译apk拖入命令行
              /Users/xxx/Desktop/xxx/xxx/d2j-dex2jar.sh /Users/xxx/Desktop/xxx/crack.apk
  - 1.3、JD-GUI
    - 1.3.1、下载地址:http://jd.benow.ca/
    - 1.3.2、功能: 读取jar文件
  - 1.4、界面工具
    - 1.4.1、下载地址:https://github.com/Jermic/Android-Crack-Tool
  - 2、反编译思路
    - 2.1、dexToJar 获取jar包
    - 2.2、使用JD-GUI分析代码,定位位置
    - 2.3、修改变量的值,或方法返回后重置返回值
    - 2.4、重新打包,签名,安装
  - 3、如何加大破解难度
    - 3.1、加固
    - 3.2、混淆
    - 3.3、尽量别在代码中直接写中文,这样相当于完全暴露意图,尽量放在String.xml文件中。
    - 3.4、在.so文件中验证签名


三、第一个demo:
-----
 * 1、功能:修改打印工具类，实现输出打印日志
 * 2、影响:日志完全暴露。
 * 3、建议:敏感性的日志尽量使用完就删除,一旦工具类被修改,日志也会完全暴露
 * 4、具体复现<br>
  - 4.1、使用该demo
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one1.png)
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one2.png)
     
     这里没有使用各大app,只是简单写了一个demo,原因一：此篇文章主要是通过反编译去提高App被反编译的难度，原因二：破解别人的软件始终不是太好。
     这里简单的写了一个日志工具类，很可能和大家使用的不一样，但大多数日志都有一个开关逻辑。用这个开关去控制所有日志是否打印。
  - 4.2、使用DexToJar 获取jar包(参照工具中获取jar的方法)，然后分析
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one8.png)
          

          这里的中文可以说为定位文件起来不小作用，我们只用去找a类中的a方法
  - 4.3使用ApkTool反编译apk(参照工具中的方法)，然后修改
        ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one6.png)

          然后Log.d 能很轻松的定位修改位置。
          <pre><code>if-eqz v0, :cond_0  #这句话的含义如果v0＝＝0执行cond_0分支，执行cond_0分支就方法就结束了所以把v0值修改即可</code></pre>
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one9.png)
          “＃“ 代表注释返回值赋值给v0 注释掉，重新给v0赋值
  - 4.4、重新打包（参考工具）
  - 4.5、签名（签名工具在assets中，key也准备好了，密钥就在项目里，自己找吧）
  - 4.6、安装打印
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one7.png)
           如果日志是这样的，那恭喜你，你成功了。


  

  * 4.1、使用DexToJar 获取jar包(参照工具中获取jar的方法)
  * 4.2、使用JD-GUI打开分析,定位关键代码位置。这里是通过Log.d定位关键类和关键方法(代码复制可以使用JD-GUI的 save all sources将代码放入android studio 中分析)
        4.3、修改工具类,在判断之前,修改判断对象的值(可参考assets/DemoOneAndroidManifest.xml)
             4.3.1、# move-result v0  65行注释返回调用
             4.3.2、 const/4 v0, 0x1
        4.4、打包:使用 apktool b 文件夹路基 -o new.apk
        4.5、签名(签名可使用工具中的)

 第二个demo:插入一个开始页
       1.步骤:
          1.1、新建一个apk包名一样的项目,创建插入启动页(这里使用的是InsertActivity)
          1.2、反编译旧apk得到旧apk文件夹
          1.3、反编译新apk,将布局文件和smali文件放入旧apk的layout
       2.影响:app 可能被插入广告
       3.进行签名校验



