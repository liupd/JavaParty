# 概述

jsoup 是一款 Java 的 HTML 解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于JQuery的操作方法来取出和操作数据。

jsoup工作的流程主要如下：

1. 从一个URL，文件或字符串中解析HTML，并加载为一个 `Document` 对象。
2. 使用DOM或CSS选择器来取出数据；
3. 可操作HTML元素、属性、文本。

jsoup是基于MIT协议发布的，可放心使用于商业项目。



# 加载

## 从HTML字符串加载一个文档

使用静态`Jsoup.parse(String html)` 方法或 `Jsoup.parse(String html, String baseUri)`示例代码：

```java
String html = "<html><head><title>First parse</title></head>"
  + "<body><p>Parsed HTML into a doc.</p></body></html>";
Document doc = Jsoup.parse(html);
```

**说明**

`parse(String html, String baseUri)` 这方法能够将输入的HTML解析为一个新的文档 (Document），参数 baseUri 是用来将相对 URL 转成绝对URL，并指定从哪个网站获取文档。如这个方法不适用，你可以使用 `parse(String html)` 方法来解析成HTML字符串如上面的示例。

只要解析的不是空字符串，就能返回一个结构合理的文档，其中包含(至少) 一个head和一个body元素。

一旦拥有了一个Document，你就可以使用Document中适当的方法或它父类 `Element`和`Node`中的方法来取得相关数据。



## 从URL加载一个文档

使用 `Jsoup.connect(String url)`方法

```java
Document doc = Jsoup.connect("http://example.com/").get();
```

**说明**

`connect(String url)` 方法创建一个新的 `Connection`, 和 `get()` 取得和解析一个HTML文件。如果从该URL获取HTML时发生错误，便会抛出 IOException，应适当处理。

`Connection` 接口还提供一个方法链来解决特殊请求，具体如下：

```
Document doc = Jsoup.connect("http://example.com")
  .data("query", "Java")
  .userAgent("Mozilla")
  .cookie("auth", "token")
  .timeout(3000)
  .post();
```



## 从一个文件加载一个文档

可以使用静态 `Jsoup.parse(File in, String charsetName, String baseUri)` 方法

```
File input = new File("/tmp/input.html");
Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
```

**说明**

`parse(File in, String charsetName, String baseUri)` 这个方法用来加载和解析一个HTML文件。如在加载文件的时候发生错误，将抛出IOException，应作适当处理。

`baseUri` 参数用于解决文件中URLs是相对路径的问题。如果不需要可以传入一个空的字符串。

另外还有一个方法`parse(File in, String charsetName)` ，它使用文件的路径做为 `baseUri`。 这个方法适用于如果被解析文件位于网站的本地文件系统，且相关链接也指向该文件系统。



# 解析



# 数据修改



```
   jsoup
    ├── examples #样例，包括一个将html转为纯文本和一个抽取所有链接地址的例子。    
    ├── helper #一些工具类，包括读取数据、处理连接以及字符串转换的工具
    ├── nodes #DOM节点定义
    ├── parser #解析html并转换为DOM树
    ├── safety #安全相关，包括白名单及html过滤
    └── select #选择器，支持CSS Selector以及NodeVisitor格式的遍历
```



使用静态`Jsoup.parse(String html)` 方法或 `Jsoup.parse(String html, String baseUri)`可以解析一个文件或一个网站的HTML字符串。

```java
String html = "<html><head><title>First parse</title></head>"
  + "<body><p>Parsed HTML into a doc.</p></body></html>";
Document doc = Jsoup.parse(html);
```



# 参考

[jsoup github托管代码](https://github.com/jhy/jsoup)

[jsoup Cookbook](https://jsoup.org/cookbook/)

[jsoup Cookbook(中文版)](http://www.open-open.com/jsoup/)

[不错的jsoup学习笔记](https://github.com/code4craft/jsoup-learning)