# crack
#该Demo 讲解反编译的过程,主要的目的是让大家在编写软件时,尽量避免一些低级的错误让软件被有心者破解
#准备(工欲善其事,必先利其器)
  一、工具
    1、工具说明:工具存放在tools文件夹下,这里仅提供Mac版,其他版本可以根据网址下载
       1.1、ApkTool
       1.1.1、下载地址:https://ibotpeaches.github.io/Apktool/
       1.1.2、集成:appt、apktool、apktool.jar，这三个文件拷贝到/usr/local/bin，升级的时候只需替换jar包
       1.1.3、功能:
              1.1.2.1、获取apk资源文件、AndroidManifest.xml文件、smali文件
                       apktool d xxx/xxx/crack.apk(生成文件在命令当前位置)
              1.1.2.2、 重新打包
                        apktool b xxx/xxx/crack -o new_crack.apk
       1.1.4、可能存在问题:
               Exception in thread "main" brut.androlib.AndrolibException: Could not decode arsc file
                                      	at brut.androlib.res.decoder.ARSCDecoder.decode(ARSCDecoder.java:56)
                                      	at brut.androlib.res.AndrolibResources.getResPackagesFromApk(AndrolibResources.java:491)
                                      	at brut.androlib.res.AndrolibResources.loadMainPkg(AndrolibResources.java:74)
                                      	at brut.androlib.res.AndrolibResources.getResTable(AndrolibResources.java:66)
                                      	at brut.androlib.Androlib.getResTable(Androlib.java:50)
                                      	at brut.androlib.ApkDecoder.getResTable(ApkDecoder.java:189)
                                      	at brut.androlib.ApkDecoder.decode(ApkDecoder.java:114)
                                      	at brut.apktool.Main.cmdDecode(Main.java:146)
                                      	at brut.apktool.Main.main(Main.java:77)
                                      	解决方案:https://ibotpeaches.github.io/Apktool/ 下载最新jar包替换即可。

       1.2、DexToJar
       1.2.1、下载地址:https://sourceforge.net/projects/dex2jar/
       1.2.2、功能:dex转jar
       1.2.3、使用
              mac: 将d2j-dex2jar.sh 托人命令行 将反编译apk拖入命令行
               /Users/xxx/Desktop/xxx/xxx/d2j-dex2jar.sh /Users/xxx/Desktop/xxx/crack.apk
       1.3、JD-GUI
       1.3.1、下载地址:http://jd.benow.ca/
       1.3.2、功能: 读取jar文件
       1.4、界面工具
       下载地址:https://github.com/Jermic/Android-Crack-Tool
    2、反编译思路
       2.1、dexToJar 获取jar包
       2.2、使用JD-GUI分析代码,定位位置
       2.3、修改变量的值,或方法返回后重置返回值
       2.4、重新打包,签名,安装
    3、如何加大破解难度
      3.1、加固
      3.2、混淆
      3.3、尽量别在代码中直接写中文,这样相当于完全暴露意图,尽量放在String.xml文件中。
      3.4、在.so文件中验证签名


#第一个demo:
     1、功能:打印工具类中的调试日志
     2、影响:日志完全暴露。
     3、建议:敏感性的日志尽量使用完就删除,一旦工具类被修改,日志也会完全暴露
     4、具体复现
        4.1、使用DexToJar 获取jar包
        4.2、使用JD-GUI打开分析,定位关键代码位置。这里是通过Log.d定位关键类和关键方法(代码复制可以使用JD-GUI的 save all sources将代码放入android studio 中分析)
        4.3、修改工具类,在判断之前,修改判断对象的值(可参考assets/DemoOneAndroidManifest.xml)
             4.3.1、# move-result v0  65行注释返回调用
             4.3.2、 const/4 v0, 0x1
        4.4、打包:使用 apktool b 文件夹路基 -o new.apk
        4.5、签名(签名可使用工具中的)

