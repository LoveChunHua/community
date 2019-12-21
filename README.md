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
[bootstrap CSS布局](https://v3.bootcss.com/css/#grid)
## 工具
[Git](https://git-scm.com/download) \
[Visual Paradigm](https://www.visual-paradigm.com)
[Flyway](https://flywaydb.org/getstarted/)

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
```