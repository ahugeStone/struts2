
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
国内的镜像速度能快一点
```xml
<!--国内OSChina提供的镜像-->
	<mirror>
      <id>CN</id>
      <name>OSChina Central</name>                                                                                                                       
      <url>http://maven.oschina.net/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
```

#### 公共仓库
注意这里起的id是myRep，下面用到了
```xml
<profile>
		<id>myRep</id>
		<repositories>
			<repository>
				  <id>central</id>
				  <name>Central Repository</name>
				  <url>http://repo.maven.apache.org/maven2</url>
				  <!--<layout>default</layout>-->
				  <snapshots>
						<enabled>false</enabled>
				  </snapshots>
			</repository>
			<repository>
				<id>repo-mirror</id>
				<url>http://maven.net.cn/content/groups/public/</url>
				<layout>default</layout>
			</repository>
			
		</repositories>
		<pluginRepositories>    
			<pluginRepository>
				<id>plugin-repo-mirror</id>
				<url>http://maven.net.cn/content/groups/public/</url>
			</pluginRepository>
		</pluginRepositories>
	</profile>
```
#### 生效

使myRep生效
```xml
<activeProfiles>
    <activeProfile>myRep</activeProfile>
  </activeProfiles>
```
### 2.2 eclipse集成maven

eclipse j2ee版本貌似有了maven插件，直接配置即可
配置maven安装路径

windows-preferences
![windows-preferences](http://f.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=d61b7ff667d9f2d3241124ea99d7fb2e/0bd162d9f2d3572cbc30dccb8f13632763d0c3d3.jpg?referer=46176f6b540fd9f9f90061592db1&x=.jpg)

配置设置文件路径
![配置设置文件路径](http://d.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=b21e7a4a50fbb2fb302b58177f715199/3c6d55fbb2fb431686d2086d25a4462308f7d3dc.jpg?referer=5df07dd43f87e9501b00c75c1db2&x=.jpg)

### 2.3 eclipse的maven工程

####新建工程
file-new-maven project

![](http://f.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=c22a9d307a3e6709ba0045fa0bfcee00/c8177f3e6709c93d15636fe09a3df8dcd1005417.jpg?referer=5056b2ea7e899e5121990f24940d&x=.jpg)

![](http://f.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=4401f86cd41373f0f13f6f9a94343ac6/ac4bd11373f08202cfedf1284efbfbedab641bad.jpg?referer=1243f4ac82d6277fb00507086564&x=.jpg)

![](http://g.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=afbb2b196209c93d03f20ef2af0689e1/7e3e6709c93d70cf5d6bcb1afddcd100baa12b17.jpg?referer=80612fae9b510fb3210e42a7950d&x=.jpg)

#### 修改pom.xml增加各种依赖

```xml
		<!-- struts2核心依赖 -->  
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-core</artifactId>
			<version>2.3.4</version>
		</dependency>
		<!-- struts2零配置依赖 
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-convention-plugin</artifactId>
			<version>2.3.4</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>-->  
		<!-- struts2的json依赖 -->
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-json-plugin</artifactId>
			<version>2.3.4</version>
			<type>jar</type>
			<scope>compile</scope>
		</dependency>
		<!-- commons-io 
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-io</artifactId>
			<version>1.3.2</version>
		</dependency>-->
		<dependency>
			<groupId>net.sf.json-lib</groupId>
			<artifactId>json-lib</artifactId>
			<classifier>jdk15</classifier>
			<version>2.4</version>
		</dependency>
```
保存后，如果是第一次执行，后台就会下载各种依赖包到2.1配置的路径下，等待时间视网速而定。
然后右键工程 run as - maven install
#####如果遇到install报错，可以尝试一下办法
> eclipse中使用maven插件的时候，运行run as maven build的时候报错
-Dmaven.multiModuleProjectDirectory system propery is not set. Check $M2_HOME environment variable and mvn script match.
 
> 可以设一个环境变量M2_HOME指向你的maven安装目录
M2_HOME=D:\Apps\apache-maven-3.3.1
然后在Window->Preference->Java->Installed JREs->Edit
在Default VM arguments中设置
-Dmaven.multiModuleProjectDirectory=$M2_HOME
> ![](http://d.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=8bd76a17ec24b899da3c793d5e3d6ca8/1e30e924b899a901efe71b2118950a7b0308f5d3.jpg?referer=74250eec0f46f21f90236a6333b1&x=.jpg)
> 出自:[http://blog.csdn.net/s20082043/article/details/45194721](http://blog.csdn.net/s20082043/article/details/45194721)

#### 将工程部署到tomcat中

新建service，就不截图了，注意先不要选择工程，直接finish，双击新生成的service，修改Deploy path配置路径如下。
![](http://f.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=d61b7ff667d9f2d3241124ea99d7fb2e/0bd162d9f2d3572cbc30dccb8f13632763d0c3d3.jpg?referer=46176f6b540fd9f9f90061592db1&x=.jpg)

如果Deploy path是灰色的不可更改，则删除该service下面的服务，然后clear，再打开。

添加好之后，右键，add and remove 加入刚才的工程。

### 2.4 开始coding

web.xml

```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
   <filter>
        <filter-name>struts2</filter-name>
        <filter-class>
            org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter
        </filter-class>
        <init-param>
            <param-name>filterConfig</param-name>
            <param-value>classpath:./struts.xml</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>struts2</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
    <welcome-file-list>
    	<welcome-file>index.html</welcome-file>
    	<welcome-file>index.jsp</welcome-file>
    </welcome-file-list>
</web-app>

```

/工程名/src/main/resources/struts.xml
struts.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.3//EN"
    "http://struts.apache.org/dtds/struts-2.3.dtd">
<struts>
	<package name="com.amos.web.action" namespace="/" extends="struts-default">
		<action name="HelloWorldAction" class="com.amos.web.action.HelloWorldAction"  method="execute">
			<result name="success" type="stream">
				<param name="contentType">text/html</param>
				<param name="inputName">hello</param>
			</result>
		</action>
	</package>
	<package name="com.hs.json.action" namespace="/json" extends="json-default">
		<action name="getlist" class="com.hs.json.action.HelloWorldJsonAction" method="execute">
			<result  name="success"  type="json">
				<param name="root">JSONResult</param>
				<!-- 指定是否序列化空的属性 
				<param name="excludeNullProperties">true</param>-->
				<!-- 这里指定将序列化JSONResult中的那些属性 
				<param name="includeProperties">userList.*</param>-->
			</result>
		</action>
	</package>
</struts>
```
测试一下，在浏览器输入http://localhost:8180/mySSI/json/getlist

页面返回
```json
{"roomlist":"room","memberlist":"member"}
```
### 2.5 spring
待续
### 2.6 ibatis
待续

> 来自我的 [StackEdit](https://stackedit.io/).
