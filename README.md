
使用eclipse与maven搭建SSI struts+sprint+ibatis
-----
##1 软件
* eclipse使用eclipse-jee-luna-SR2-win32-x86_64
* maven使用apache-maven-3.3.1-bin
* tomcat使用apache-tomcat-8.0.20-windows-x64
* mysql 待定
* java使用jdk-8u45-windows-x64.exe

##2 搭建
下面是开始搭建。
### 2.1 maven配置文件修改

maven配置文件位置 
D:\Program green\apache-maven-3.3.1\conf\settings.xml
默认大部分都是被注释掉的，可根据下面标签名搜索，然后复制一份标签到注释外修改即可，自己添加容易把标签搞错。

#### 修改默认仓库位置
```xml
<localRepository>D:\m2\repository</localRepository>
```
如果不修改，默认位置是用户文件夹，比如windows的为
C:\Users\你的名字\.m2\repository
对于C盘不充裕的，还是换一个吧

#### 添加maven镜像

```xml
<!--国内OSChina提供的镜像-->
	<mirror>
      <id>CN</id>
      <name>OSChina Central</name>                                                                                                                       
      <url>http://maven.oschina.net/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
```

> 来自我的 [StackEdit](https://stackedit.io/).