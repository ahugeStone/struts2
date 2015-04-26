
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
![windows-preferences](http://b.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=f1df2017ec24b899da3c793d5e3d6ca8/1e30e924b899a90195ef512118950a7b0308f5cb.jpg?referer=0e2d44ec0f46f21f90236a6333c9&x=.jpg)

配置设置文件路径
![配置设置文件路径](http://d.picphotos.baidu.com/album/s%3D550%3Bq%3D90%3Bc%3Dxiangce%2C100%2C100/sign=b21e7a4a50fbb2fb302b58177f715199/3c6d55fbb2fb431686d2086d25a4462308f7d3dc.jpg?referer=5df07dd43f87e9501b00c75c1db2&x=.jpg)



> 来自我的 [StackEdit](https://stackedit.io/).