# Java-Crawl
Java程序中使用 Jsoup 爬虫( 简单示例 )

### 方式
+ nutch
+ WebMagic
+ jsoup
+ ....

##### 简单的步骤
~~~ JavaScript
function crawl( url ){
	//先判断此 url 是否 爬询过
	//如果 此 url 在数据库 已经存在 则跳过
	
	//fetch( url ).then().....
	//分析页面内容
	//拿取关键字、标题.......
	//将有用的信息保存到数据库
	
	//获取当前页面中关联的所有 url 
	//将当前 url 放入 数组中 。
	
	//将 数组中的url 再进行上述操作
}
~~~
