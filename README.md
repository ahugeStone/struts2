
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
![windows-preferences](https://github.com/ahugeStone/pictures/blob/master/QQ%E6%88%AA%E5%9B%BE20150426205106.png?raw=true)

配置设置文件路径
![配置设置文件路径](https://github.com/ahugeStone/pictures/blob/master/QQ%E6%88%AA%E5%9B%BE20150426205641.png?raw=true)

### 2.3 eclipse的maven工程

####新建工程
file-new-maven project

![](https://github.com/ahugeStone/pictures/blob/master/QQ%E6%88%AA%E5%9B%BE20150426210231.png?raw=true)

![](https://github.com/ahugeStone/pictures/blob/master/QQ%E6%88%AA%E5%9B%BE20150426210252.png?raw=true)

![](https://github.com/ahugeStone/pictures/blob/master/QQ%E6%88%AA%E5%9B%BE20150426210358.png?raw=true)

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
		<!-- javax.servlet -->
		<dependency> 
		   <groupId>javax.servlet</groupId> 
		   <artifactId>servlet-api</artifactId> 
		   <version>2.5</version> 
		   <scope>provided</scope> 
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
> ![](https://github.com/ahugeStone/pictures/blob/master/QQ%E6%88%AA%E5%9B%BE20150426212457.png?raw=true)
> 出自:[http://blog.csdn.net/s20082043/article/details/45194721](http://blog.csdn.net/s20082043/article/details/45194721)

#### 将工程部署到tomcat中

新建service，就不截图了，注意先不要选择工程，直接finish，双击新生成的service，修改Deploy path配置路径如下。
![](https://github.com/ahugeStone/pictures/blob/master/QQ%E6%88%AA%E5%9B%BE20150426212040.png?raw=true)

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
### 2.5 log4j
pom.xml文件中添加log4j的包依赖
```xml
<dependency>
	<groupId>log4j</groupId>
	<artifactId>log4j</artifactId>
	<version>1.2.17</version>
</dependency>
```
在/工程名/src/main/resources/创建log4j.xml文件如下：
```xml
<?xml version="1.0" encoding="UTF-8"?>     
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">     
        
<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/' >     
        
    <appender name="myConsole" class="org.apache.log4j.ConsoleAppender">     
        <layout class="org.apache.log4j.PatternLayout">     
            <param name="ConversionPattern"        
                value="[%d{dd HH:mm:ss,SSS\} %-5p] [%t] %c{2\} - %m%n" />     
        </layout>     
        <!--过滤器设置输出的级别-->     
        <filter class="org.apache.log4j.varia.LevelRangeFilter">     
            <param name="levelMin" value="debug" />     
            <param name="levelMax" value="warn" />     
            <param name="AcceptOnMatch" value="true" />     
        </filter>     
    </appender>     
     
    <appender name="myFile" class="org.apache.log4j.RollingFileAppender">        
        <param name="File" value="D:/workspace/logs/output.log" /><!-- 设置日志输出文件名 -->     
        <!-- 设置是否在重新启动服务时，在原有日志的基础添加新日志 -->     
        <param name="Append" value="true" />     
        <param name="MaxBackupIndex" value="10" />     
        <layout class="org.apache.log4j.PatternLayout">     
            <param name="ConversionPattern" value="%p (%c:%L)- %m%n" />     
        </layout>     
    </appender>     
       
    <appender name="activexAppender" class="org.apache.log4j.DailyRollingFileAppender">     
        <param name="File" value="D:/workspace/logs/activex.log" />       
        <param name="DatePattern" value="'.'yyyy-MM-dd'.log'" />       
        <layout class="org.apache.log4j.PatternLayout">     
         <param name="ConversionPattern"       
            value="[%d{MMdd HH:mm:ss SSS\} %-5p] [%t] %c{3\} - %m%n" />     
        </layout>       
    </appender>     
        
    <!-- 指定logger的设置，additivity指示是否遵循缺省的继承机制-->     
    <logger name="com.runway.bssp.activeXdemo" additivity="false">     
        <priority value ="info"/>       
        <appender-ref ref="activexAppender" />       
    </logger>     
     
    <!-- 根logger的设置-->     
    <root>     
        <priority value ="debug"/>     
        <appender-ref ref="myConsole"/>     
        <appender-ref ref="myFile"/>        
    </root>     
</log4j:configuration>
```
* 输出方式appender一般有5种：
org.apache.log4j.RollingFileAppender(滚动文件，自动记录最新日志)
org.apache.log4j.ConsoleAppender (控制台)
org.apache.log4j.FileAppender (文件)
org.apache.log4j.DailyRollingFileAppender (每天产生一个日志文件)
org.apache.log4j.WriterAppender (将日志信息以流格式发送到任意指定的地方) 
* 日记记录的优先级priority，优先级由高到低分为
OFF ,FATAL ,ERROR ,WARN ,INFO ,DEBUG ,ALL。
Log4j建议只使用FATAL ,ERROR ,WARN ,INFO ,DEBUG这五个级别，当值为"off"时表示没有任何日志信息被输出

### 2.6 spring
待续
### 2.7 ibatis
待续
### 2.8 开发笔记
#### 在Action中获取request并格式化参数
对于如下格式报文

```javascript
{"roomlist":"curLoginUserInfo","memberlist":"{\"room\":\"111\",\"member\":\"222\"}"}
```
获取request对象

```java
HttpServletRequest request = ServletActionContext.getRequest();
```

格式化并获取对象

```java
String reqjsonStr = request.getParameter("json");
JSONObject reqjsonObj = JSONObject.fromObject(reqjsonStr);	
JSONObject reqParamsObj = JSONObject.fromObject(reqjsonObj.getString("params"));
...
String room = reqParamsObj.getString("room");
String member= reqParamsObj.getString("member");
```


> 来自我的 [StackEdit](https://stackedit.io/).