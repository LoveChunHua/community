create table user
(
	id int auto_increment,
	name VARCHAR(50),
	account_id VARCHAR(50),
	token VARCHAR(50),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	avatar_url VARCHAR(100)
);
