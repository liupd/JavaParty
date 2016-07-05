# tent
tent是一个Java Web开发框架，集成了当前一些主流的技术。

## 技术树
### 框架
***`Spring`***
使用Spring来作为JavaBean容器。代码中应用到依赖注入和AOP。

***`SpringMVC`***
使用SpringMVC来作为MVC框架。

***`Mybatis`***
使用轻量级ORM框架Mybatis。并在代码中使用`Mybatis Generater`来生成DAO、DTO以及映射关系xml文件。
尽量减少对sql的依赖。

### 项目管理
***`Maven`***
使用Maven管理项目构建、jar包依赖

### 工具
***`log4j+slf4j`***
使用log4j+slf4j来记录日志

***`velocity`***
使用velocity来作为模板引擎

***`fastjson`***
使用alibaba.fastjson来作为json处理工具

## 开发环境
***`JDK版本`***
JDK1.8

***`编码格式(Encoding)`***
UTF-8

***`服务器`***
Tomcat
