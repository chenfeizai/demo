DROP TABLE IF EXISTS "t_news";
CREATE TABLE t_news (
	id varchar not null,
	address varchar,
	create_time timestamp,
	description varchar,
	news_time timestamp,
	title varchar,
	primary key (id)
);

-- ----------------------------
--  Records of t_news
-- ----------------------------
BEGIN;
INSERT INTO "t_news" VALUES (2018052000000000001, '测试多数据源1', X'323031382d30352d32302030333a33353a3530', '测试多数据源1', X'323031382d30352d32302030333a33353a3530', '测试多数据源1');
COMMIT;

select * from t_news;