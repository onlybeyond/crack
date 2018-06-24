# crack
一、介绍
-------
 反编译与防反编译的0到1系列第一篇。该篇目的是让读者了解反编译的基本过程，可以完全没有基础，也不用了解smali语法。文章里面demo提供所有
需要使用的apk以及相应的修改代码。只需傻瓜式的模仿就能够学会。</br>

 反编译与防反编译的0到1系列主要用于增加反编译技术栈，避免一些低级的错误让软件被有心者破解。因此后续还会有smali语法的介绍和smali的修改。
在.so文件中添加签名验证。

二、工具
-------
  * 1、工具说明:工具存放在tools文件夹下,这里仅提供Mac版,其他版本可以根据网址下载
  - 1.1、ApkTool
    + 1.1.1、下载地址:https://ibotpeaches.github.io/Apktool/
    + 1.1.2、集成:appt、apktool、apktool.jar，这三个文件拷贝到/usr/local/bin，升级的时候只需替换jar包
    + 1.1.3、功能:
      - 1.1.2.1、获取apk资源文件、AndroidManifest.xml文件、smali文件
                 apktool d xxx/xxx/crack.apk(生成文件在命令当前位置)
      - 1.1.2.2、 重新打包
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
     + 1.2.1、下载地址:https://sourceforge.net/projects/dex2jar/
     + 1.2.2、功能:dex转jar
     + 1.2.3、使用
             mac: 将d2j-dex2jar.sh 托人命令行 将反编译apk拖入命令行<br>
              /Users/xxx/Desktop/xxx/xxx/d2j-dex2jar.sh /Users/xxx/Desktop/xxx/crack.apk<br>
  - 1.3、JD-GUId<br>
    + 1.3.1、下载地址:http://jd.benow.ca/
    + 1.3.2、功能: 读取jar文件
  - 1.4、界面工具
    - 1.4.1、下载地址:https://github.com/Jermic/Android-Crack-Tool
 * 2、反编译思路
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
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one3.png)
     
     这里没有使用各大app,只是简单写了一个demo,控制台只会输出一行日志。<br>
     有两原因：<br>
     原因一：复杂的App不利于初学者上手。<br>
     原因二：破解别人的软件始终不是太好。<br>
     这里简单的写了一个日志工具类，很可能和大家使用的不一样，但大多数日志都有一个开关逻辑。用这个开关去控制所有日志是否打印。<br>
   - 4.2、使用DexToJar 获取jar包(参照工具中获取jar的方法)，然后分析
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one8.png)
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one10.png)        
          这里的中文可以说为定位文件起来不小作用，我们只用去找a类中的a方法<br>       
   - 4.3、使用ApkTool反编译apk(参照工具中的方法)，然后修改
        ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one6.png)
          Log.d 能很轻松的定位修改位置。
          <pre><code>if-eqz v0, :cond_0  #这句话的含义如果v0＝＝0执行cond_0分支，执行cond_0分支就方法就结束了所以把v0值修改即可</code></pre>
        ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one9.png)
          “＃“ 代表注释返回值赋值给v0 注释掉，重新给v0赋值        
   - 4.4、重新打包（参考工具）
   - 4.5、签名（签名工具在assets中，key也准备好了，密钥就在项目里，自己找吧）
   - 4.6、安装打印
          ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one7.png)
           如果日志是这样的，那恭喜你，你成功了。
   - 4.7、其他<br>
    ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_one11.png)<br>
     也可以直接反编译demo_one_apk1进行修改。demo_two_crack为修改后的apk.


 第二个demo:插入一个开始页
 -------
 * 1.影响:app 可能被插入广告
 * 2.建议：进行签名校验
 * 3.步骤:
   - 3.1、生成demo_two.apk和demo_two_2.apk
           ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_two8.png)<br>
         运行得到demo_two.apk,将assets的demo_two文件已经写好Activity和布局文件，添加到相应位置运行得到demo_two_2.apk
         也可以直接使用文件夹中两个apk
   - 3.2反编译demo_two.apk<br>
             命令：apktool d demo_two.apk(文件真实路径)
   - 3.3、反编译demo_two_2.apk<br>
                命令：apktool d demo_two_2.apk(文件真实路径)
   - 3.4合并smali文件<br>
            ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_two9.png)
        将demo_two_2.apk反编译smali文件夹中的a.smali（TimerTask的匿名内部类产品）和InsertActivity.smali拷贝到demo_two.apk反编译的文件中。
   - 3.5、修改AndroidManifest.xml文件<br>
              ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_two4.png)
          去掉默认的启动标志，添加新的启动Activity
   - 3.6、修改资源id<br>
               ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_two10.png)
         布局文件id可能在public.xml(res/values中)也有可能在R$layout.smali文件中（smail文件夹下）有可能是不同编译器版本造成的，<br>
         不在本篇讨论范围。layout id 最大的是0x7f04002e，在它的基础上加1即可（注意采用的是16进制）
               ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_two5.png)
         过程中有可能遇到上面的错误，上面原因很明显id重复，说明新加的id不对。
   - 3.7、修改smail文件的id<br>
               ![配置图](https://github.com/onlybeyond/crack/blob/master/app/assets/picture/demo_two11.png)
         找到setContentView()可以快速定位，将id替换成步骤3.6中添加的id
   - 3.8、重新打包<br>
          命令：apktool b  demo_two(文件真实路径) －o demo_two_new.apk
   - 3.9、签名安装<br>
          如果先显示“这是一个注入的页面”恭喜你成功了。如果没见到这个页面可以反编译demo_two_crack.apk查看一下原因   
          
 文章到此就结束了，如果对你有帮助，可以star一下，让更多人知道，让更多人受益。文章中存在问题的地方，或者有待改进的地方可以issues中提出。
         




