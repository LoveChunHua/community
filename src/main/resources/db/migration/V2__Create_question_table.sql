create table question
(
	id int auto_increment,
	title VARCHAR(50),
	description TEXT,
	tag VARCHAR(36),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator int,
	view_count int default 0,
	comment_count int default 0,
	like_count int default 0,
	constraint question_pk
		primary key (id)
);