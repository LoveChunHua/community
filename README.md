## 问答社区

## 资料
[Spring文档](https://spring.io/guides) \
[Spring Web文档](https://spring.io/guides/gs/serving-web-content/) \
[es社区](https://elasticsearch.cn/explore) \
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys) \
[bootstrap 文档](https://v3.bootcss.com/getting-started/#download)\
[Github OAuth Document](https://developer.github.com/apps/building-github-apps/creating-a-github-app/)\
[菜鸟教程](https://www.runoob.com/)\
[okhttp网站](https://square.github.io/okhttp/)\
[maven仓库](https://mvnrepository.com/artifact/com.alibaba)\
[H2数据库网站](http://www.h2database.com/html/main.html)\
[bootstrap CSS布局](https://v3.bootcss.com/css/#grid)\
[markdownEdit](https://pandao.github.io/editor.md/)\
[云服务](https://github.com/ucloud/ufile-sdk-java)\
[图标](https://www.iconfont.cn/)\

## 工具
[Git](https://git-scm.com/download) \
[Visual Paradigm](https://www.visual-paradigm.com)\
[Flyway](https://flywaydb.org/getstarted/)\
[lombok](https://projectlombok.org/setup/maven)\
[liveReload](http://livereload.com/extensions/)\

## 脚本
```sql
create table user
(
	id int auto_increment,
	account_id VARCHAR(100),
	name VARCHAR(50),
	token char(36),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	constraint user_pk
		primary key (id)
);
```
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
```
遇到页面问题的时候点击页面的检查，再点击左上角的按钮，然后将鼠标悬浮再要检查的元素上面就可以看到元素有什么语句
遇到的问题：
    用了持久化工具如mybatis这样的；
    然后按照实体来进行插入？
    1.mysql字段默认的含义：在插入时不指定该字段的值；
    2.以mybatis举例，如果是插入实体，那么为空的字段就会插入空；
    3.如果不想mybatis等持久化工具插入空，可以尝试insertSelective方式进行，这样为空字段会被剔除
```