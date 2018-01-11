

# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(512) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容描述',
  `summary` text,
  `icon` varchar(128) DEFAULT NULL COMMENT '图标',
  `post_count` int(11) unsigned DEFAULT '0' COMMENT '该分类的内容数量',
  `order_number` int(11) DEFAULT NULL COMMENT '排序编码',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父级分类的ID',
  `meta_keywords` varchar(256) DEFAULT NULL COMMENT 'SEO关键字',
  `meta_description` varchar(256) DEFAULT NULL COMMENT 'SEO描述内容',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`id`, `name`, `content`, `summary`, `icon`, `post_count`, `order_number`, `parent_id`, `meta_keywords`, `meta_description`, `created`, `modified`)
VALUES
	('0c275ece74fe4c1fbcaa9d3c7438f1f3','提问',NULL,NULL,NULL,0,1,NULL,NULL,NULL,'2018-01-05 14:50:48',NULL),
	('7796fa78031a4b3988d6bc5a629041f3','分享',NULL,NULL,NULL,0,2,NULL,NULL,NULL,'2018-01-05 14:50:55',NULL),
	('ae361130722f47cfba93019ba27177ba','讨论',NULL,NULL,NULL,0,3,NULL,NULL,NULL,'2018-01-05 14:50:49',NULL),
	('bb21e00b41174683bd4e5ec2baa88032','公告',NULL,NULL,NULL,0,4,NULL,NULL,NULL,'2018-01-05 14:50:58',NULL);

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `content` longtext COMMENT '评论的内容',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '回复的评论ID',
  `post_id` varchar(32) DEFAULT NULL COMMENT '评论的内容ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '评论的用户ID',
  `user_ip` varchar(64) DEFAULT NULL COMMENT '评论的IP地址',
  `user_agent` text COMMENT '提交评论的浏览器信息',
  `vote_up` int(11) unsigned DEFAULT '0' COMMENT '“顶”的数量',
  `vote_down` int(11) unsigned DEFAULT '0' COMMENT '“踩”的数量',
  `level` tinyint(2) unsigned DEFAULT '0' COMMENT '置顶等级',
  `status` tinyint(2) DEFAULT NULL COMMENT '评论的状态',
  `created` datetime DEFAULT NULL COMMENT '评论的时间',
  `modified` datetime DEFAULT NULL COMMENT '评论的更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;

INSERT INTO `comment` (`id`, `content`, `parent_id`, `post_id`, `user_id`, `user_ip`, `user_agent`, `vote_up`, `vote_down`, `level`, `status`, `created`, `modified`)
VALUES
	('04d498c007844d27a4f0e297fdb2e69d','1111',NULL,'xfc44754ae294f4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 20:52:10',NULL),
	('086094aea7ec425db49ced3671a845c7','1231231',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:53:24',NULL),
	('09ebedf019c64957a22e208d8f08a181','aaa',NULL,'b918f511e0814b94bca667fbcee5ca5e','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 15:12:51',NULL),
	('0eeccf98e7da4c11ab4f12a6b42c1c65','111',NULL,'xf444754ae294f4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:35:42',NULL),
	('11eaa9d636e645189fa9cdfc43f36fd1','xxx',NULL,'b07516377baa4ace84f155153f1db7ca','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 15:15:25',NULL),
	('18b0b0392e4f4099834c4364514cd351','img[/attachment/20180108/fed3da28668b404eae78bb9fcc681120.jpeg]',NULL,'f63d266ee6434a148b8d05196c7a9634','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-08 11:29:16',NULL),
	('29fe48ff58b24609bb1b1e72ed0e3a41','123132131321',NULL,'43cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 19:06:54',NULL),
	('2cd9fcdd8a0e481abf5e32d2288450b0','img[/attachment/20180109/173c4b3d76a346ffaccd560c63355e0a.jpeg]',NULL,'xf444754ae294f4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:33:21',NULL),
	('2e941b4bd1dd40bbb808a423c9f3aa9c','ccc',NULL,'b918f511e0814b94bca667fbcee5ca5e','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 15:12:52',NULL),
	('356e0a6683e94f82a3159d5798f82ac2','@admin aaaa',NULL,'b918f511e0814b94bca667fbcee5ca5e','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:41:09',NULL),
	('35c12e733c6c49aab3cfebcac02bb5e4','face[哈哈]',NULL,'xf444754ae294f4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:34:23',NULL),
	('58ae9814bd8e40999b71c86b14265322','xxx',NULL,'b918f511e0814b94bca667fbcee5ca5e','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 15:00:53',NULL),
	('5bca80d4626e44feb3748eb6413fe997','edd',NULL,'b07516377baa4ace84f155153f1db7c2','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 20:33:06',NULL),
	('5c84c1b649da4cfc8075abae9990a5e1','1321312',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:50:13',NULL),
	('5ca2d1350bde4717a4c93aec85ddf4c9','123213',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:55:55',NULL),
	('622eaa806a44442ea91111afd72e9013','aaa',NULL,'xf444754ae294a4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 21:08:19',NULL),
	('666cac2b65264eafa9d8f327ce63664e','asfasdfasdfasd',NULL,'b07516377baa4ace84f155153f1db7c2','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 20:33:21',NULL),
	('7b3945f11fd2434d9b446bb1491530fd','123123',NULL,'43cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 16:13:51',NULL),
	('80df494b56aa4fdaaca8fb4862ac2dd3','1321312',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:50:27',NULL),
	('8608aec054e846d697af9f595702af05','1111',NULL,'xf444754ae294a4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,9,'2018-01-07 21:08:11','2018-01-07 21:08:15'),
	('8a8eab57c3b5497f99a69bed0e4a8461','123123',NULL,'d3cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,9,'2018-01-07 20:08:38','2018-01-07 20:08:46'),
	('8d9a84290e264ae2897e7921ce27c2fe','sdfasfa',NULL,'13cc8e0acd7848929a35b10637ef0d71','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 20:51:21',NULL),
	('8e8af5ab0d6d4b82aae7cd3a1972fe78','234232423',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:59:12',NULL),
	('9910e6056b334ec7b2d8557badfb5deb','1231231',NULL,'cfc44754ae294f4989ca778dbe6f1e65','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 20:27:24',NULL),
	('99eebcff503e4b9791508711d8597077','ddd',NULL,'771b34717ea84797aa5dc108131429ef','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 15:15:33',NULL),
	('9cd8e8f50c8d4ebfa2714c5c91102398','aaaa',NULL,'b918f511e0814b94bca667fbcee5ca5e','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:39:29',NULL),
	('a36b106b0200457fb6c479a2b95786fe','123123',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:58:51',NULL),
	('aa20a1fe49a84a2b9bec74079ffd9ec9','img[/attachment/20180109/e11b2c02fd35434a8e5e4a3f93b79f6f.jpeg]',NULL,'xf444754ae294f4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:33:38',NULL),
	('ab010255cc824e7486ac3b310fe7cece','1111',NULL,'xf444754ae294f4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:36:28',NULL),
	('b29bfcdba9fc43e08d2d4455cad060f2','aaaa',NULL,'b918f511e0814b94bca667fbcee5ca5e','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 15:32:15',NULL),
	('b4dfe52867b54a51a698c8ac8f456a1a','img[/attachment/20180109/431ed05f1124460a96e4172b8f3865ce.jpeg]',NULL,'771b34717ea84797aa5dc108131429ef','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 09:37:03',NULL),
	('b79d97c985564a68ac140735dc29d861','@admin asdfasdf',NULL,'13cc8e0acd7848929a35b10637ef0d71','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 20:51:31',NULL),
	('bcf28a07fe844b9b8c4c70468b22f1d0','xxxx',NULL,'771b34717ea84797aa5dc108131429ef','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:48:04',NULL),
	('c21e00c71c8d4d75a3e4e2a0fdb4030d','1231231',NULL,'f63d266ee6434a148b8d05196c7a9634','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-08 11:29:05',NULL),
	('cfc1f6905992408aa09bce8babe0b6b3','1231231',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:52:03',NULL),
	('d0a7f865aec8449688745b0a4037a5ac','好呀，很好',NULL,'43cc8e0acd7848929a35b10637ef0d79','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-08 18:02:17',NULL),
	('d1511f4c11bc44b790aa16259d567421','123213',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:54:29',NULL),
	('d2d0d4cb42b84761a54149c5f7fef1b6','1321312',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:49:26',NULL),
	('d559abdfb42d40f7b17c0c22f5c2f96e','2222',NULL,'13cc8e0acd7848929a35b10637ef0d71','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-08 18:05:01',NULL),
	('d978629aac2b4a37bf4517220ded7ecc','1321312',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:50:03',NULL),
	('daebbb08a5294a1783deea80939fddb6','12312312',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,9,'2018-01-07 16:13:27','2018-01-07 20:01:20'),
	('db37a50a4c7a488f84e5c70239e138d6','img[/attachment/20180108/81af7a48453a4c5abb61878f93cd6907.png] [pre] asdfasd [/pre]',NULL,'13cc8e0acd7848929a35b10637ef0d71','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,9,'2018-01-08 09:20:39','2018-01-08 17:59:14'),
	('dd64a3679c6f4455b494a7853028f4f7','xxx',NULL,'b918f511e0814b94bca667fbcee5ca5e','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-09 14:40:25',NULL),
	('e00906a7ffe14ad5ae024e39ef1f2712','ABC啊发顺丰',NULL,'4fc44754ae294f4989ca778dbe6f1e65','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-08 18:05:40',NULL),
	('e3c09b4c7de848b494285318e3a33c93','1231312',NULL,'13cc8e0acd7848929a35b10637ef0d71','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-08 17:59:42',NULL),
	('e94f377a2e5c41e7914ba3a658e0ffa1','1111',NULL,'b07516377baa4ace84f155153f1db7c2','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,9,'2018-01-07 20:33:04','2018-01-07 20:33:15'),
	('f12674ea89b54b059354451fc2efe565','123123',NULL,'13cc8e0acd7848929a35b10637ef0d71','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0,0,'2018-01-07 15:57:04',NULL);

/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table post
# ------------------------------------------------------------

DROP TABLE IF EXISTS `post`;

CREATE TABLE `post` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `title` text COMMENT '标题',
  `content` longtext COMMENT '内容',
  `edit_mode` varchar(32) DEFAULT '0' COMMENT '编辑模式：html可视化，markdown ..',
  `category_id` varchar(32) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `user_ip` varchar(128) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` text COMMENT '发布浏览agent',
  `vote_up` int(11) unsigned DEFAULT '0' COMMENT '支持人数',
  `vote_down` int(11) unsigned DEFAULT '0' COMMENT '反对人数',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '价格',
  `pay_point` int(11) DEFAULT NULL,
  `comment_status` varchar(32) DEFAULT NULL COMMENT '评论状态：可以对帖子的评论功能开启或关闭',
  `comment_count` int(11) unsigned DEFAULT '0' COMMENT '评论总数',
  `comment_time` datetime DEFAULT NULL COMMENT '最后评论时间',
  `view_count` int(11) unsigned DEFAULT '0' COMMENT '访问量',
  `recommend` tinyint(1) DEFAULT NULL COMMENT '是否为精华',
  `level` tinyint(2) DEFAULT '0' COMMENT '置顶等级',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `modified` datetime DEFAULT NULL COMMENT '最后更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;

INSERT INTO `post` (`id`, `title`, `content`, `edit_mode`, `category_id`, `user_id`, `user_ip`, `user_agent`, `vote_up`, `vote_down`, `price`, `pay_point`, `comment_status`, `comment_count`, `comment_time`, `view_count`, `recommend`, `level`, `status`, `created`, `modified`)
VALUES
	('13cc8e0acd7848929a35b10637ef0d71','3334444aaaaa','33333aaaa','html','ae361130722f47cfba93019ba27177ba','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,2,NULL,174,1,3,9,'2018-01-06 15:54:14','2018-01-08 17:59:14'),
	('207516377baa4ace84f155153f1db7c2','11111222','22222','html','ae361130722f47cfba93019ba27177ba','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,1,NULL,0,0,'2018-01-06 15:28:49',NULL),
	('367d266ee6434a148b8d05196c7a9634','1111','2222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,0,NULL,0,0,'2018-01-06 15:26:52',NULL),
	('43cc8e0acd7848929a35b10637ef0d71','333xxx','33333xxxx','html','7796fa78031a4b3988d6bc5a629041f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,36,1,3,0,'2018-01-06 15:54:14','2018-01-08 17:32:20'),
	('43cc8e0acd7848929a35b10637ef0d79','333','33333','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,1,NULL,13,1,0,0,'2018-01-06 15:54:14','2018-01-08 10:46:50'),
	('4fc44754ae294f4989ca778dbe6f1e65','22222','22222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,1,NULL,42,NULL,2,0,'2018-01-06 15:53:41','2018-01-08 10:46:37'),
	('6c7ec250629e42fd9332a6f9ece2a9a8','1212','img[/attachment/20180109/7323651997584517ac58426947c94e31.jpeg]','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,7,NULL,2,0,'2018-01-09 09:38:33','2018-01-09 14:30:49'),
	('771b34717ea84797aa5dc108131429ef','aaaa','img[/attachment/20180109/8d2d4c3e2d66430c8123e024ed4a15c7.jpeg] aaa bbb img[/attachment/20180109/ef81f6f2a125442d8be212eb98bbfe66.jpeg]','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,2,NULL,5,NULL,0,0,'2018-01-09 09:31:01','2018-01-09 09:33:53'),
	('b07516377baa4ace84f155153f1db7c2','11111222','22222','html','ae361130722f47cfba93019ba27177ba','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,4,NULL,11,NULL,0,9,'2018-01-06 15:28:49','2018-01-07 20:33:15'),
	('b07516377baa4ace84f155153f1db7ca','11111222','22222','html','ae361130722f47cfba93019ba27177ba','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,1,NULL,3,NULL,0,0,'2018-01-06 15:28:49',NULL),
	('b918f511e0814b94bca667fbcee5ca5e','11111','aaaaaaa img[/attachment/20180109/d1e2ab3780a34d9b97a3981dad33ca35.jpeg]','html','bb21e00b41174683bd4e5ec2baa88032','e90c553ebbd848519c7606d50eab8b6c','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,6,NULL,16,NULL,0,0,'2018-01-09 14:39:20',NULL),
	('c67d266ee6434a148b8d05196c7a9633','1111','2222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,17,NULL,0,0,'2018-01-06 15:26:52',NULL),
	('c67d266ee6434a148b8d05196c7a9634','1111','2222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,5,1,0,0,'2018-01-06 15:26:52','2018-01-08 09:30:10'),
	('cfc44754ae294f4989ca778dbe6f1e65','22222','22222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,2,NULL,29,NULL,0,9,'2018-01-06 15:53:41','2018-01-07 20:16:27'),
	('cfc44754ae294f4989ca778dbe6f1e66','22222','22222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,8,NULL,0,0,'2018-01-06 15:53:41',NULL),
	('d31c8e0acd7848929a35b10637ef0d71','333','33333','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,7,NULL,0,0,'2018-01-06 15:54:14',NULL),
	('e02516377ba24ace84f155153f1db7c2','11111222','22222','html','ae361130722f47cfba93019ba27177ba','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,0,NULL,0,0,'2018-01-06 15:28:49',NULL),
	('e02516377baa4ace84f155153f1db7c2','11111222','22222','html','ae361130722f47cfba93019ba27177ba','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,0,NULL,0,0,'2018-01-06 15:28:49',NULL),
	('e07516377baa4ace84f155153f1db7c2','11111222','22222','html','ae361130722f47cfba93019ba27177ba','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,1,NULL,0,0,'2018-01-06 15:28:49',NULL),
	('f63d266ee6433a148b8d05196c7a9634','1111','2222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,1,NULL,0,0,'2018-01-06 15:26:52',NULL),
	('f63d266ee6434a148b8d05196c7a9634','1111','2222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,2,NULL,3,NULL,0,0,'2018-01-06 15:26:52',NULL),
	('f67d266ee6434a148b8d05196c7a9634','1111','2222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,0,NULL,2,NULL,2,0,'2018-01-06 15:26:52','2018-01-08 10:00:14'),
	('xf444754ae294f4989ca778dbe6f1e65','22222','22222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,4,NULL,5,NULL,0,0,'2018-01-06 15:53:41',NULL),
	('xfc44754ae294f4989ca778dbe6f1e65','22222','22222','html','0c275ece74fe4c1fbcaa9d3c7438f1f3','70ee8bd502b643378a61de0d5b0000cf','127.0.0.1','Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',0,0,0.00,20,NULL,1,NULL,3,NULL,0,0,'2018-01-06 15:53:41',NULL);

/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `role` varchar(256) NOT NULL DEFAULT 'user' COMMENT '角色',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `email_status` tinyint(2) DEFAULT '0' COMMENT '邮箱状态（是否认证等）',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机电话',
  `mobile_status` tinyint(2) DEFAULT '0' COMMENT '手机状态（是否认证等）',
  `point` int(11) unsigned DEFAULT '0' COMMENT '积分',
  `title` varchar(64) DEFAULT NULL COMMENT '头衔',
  `auth_info` text COMMENT '认证信息',
  `gender` varchar(16) DEFAULT NULL COMMENT '性别',
  `signature` varchar(2048) DEFAULT NULL COMMENT '签名',
  `post_count` int(11) unsigned DEFAULT '0' COMMENT '内容数量',
  `comment_count` int(11) unsigned DEFAULT '0' COMMENT '评论数量',
  `wechat` varchar(32) DEFAULT NULL COMMENT '微信号',
  `weibo` varchar(256) DEFAULT NULL COMMENT '微博',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `company` varchar(256) DEFAULT NULL COMMENT '公司',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `site` varchar(256) DEFAULT NULL COMMENT '个人网址',
  `avatar` varchar(256) DEFAULT NULL COMMENT '头像',
  `status` tinyint(2) DEFAULT '0' COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `create_source` varchar(128) DEFAULT NULL COMMENT '用户来源（可能来之oauth第三方）',
  `modified` datetime DEFAULT NULL COMMENT '最后修改时间',
  `logged` datetime DEFAULT NULL COMMENT '最后的登陆时间',
  `activated` datetime DEFAULT NULL COMMENT '激活时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `nickname`, `password`, `salt`, `role`, `email`, `email_status`, `mobile`, `mobile_status`, `point`, `title`, `auth_info`, `gender`, `signature`, `post_count`, `comment_count`, `wechat`, `weibo`, `birthday`, `company`, `address`, `site`, `avatar`, `status`, `created`, `create_source`, `modified`, `logged`, `activated`)
VALUES
	('1e852b6873a040a993f5b47f00b133dc','11111','115e2c9758c11f276b7d11bbb2576f786918bf2d570132d282134a5c18fa251d','593b62da24740344b','user','111111@11.ss',0,NULL,0,0,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2018-01-05 14:50:48',NULL,NULL,NULL,NULL),
	('70ee8bd502b643378a61de0d5b0000cf','fuhai','84b89dff973e66ed5ed6de054c27e4b6f236fd35d651815f5f6d5917191bfc0c','846e3941d34cdd53','user','fuhai@jboot.io',0,NULL,0,100070,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2018-01-05 15:20:12',NULL,'2018-01-09 16:21:08','2018-01-07 21:53:41',NULL),
	('8ccc537cdfff43a29b75ad66b006bf21','111111','217a87c848648481007bfea2c5cafb817cdb226210e583558c9a5c76e46b2335','fe46a189894f1bb34419b2','user','111111@ss.ss',0,NULL,0,0,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'2018-01-05 14:54:53',NULL,NULL,NULL,NULL),
	('e90c553ebbd848519c7606d50eab8b6c','admin','94c471b4c84e5de425cf68264802fb0a25d6cf50cb69de3e4c245f7a64d57d22','fe5914634c3c1cf3244','admin','admin@jboot.io',0,NULL,0,110,NULL,NULL,NULL,'cc',0,0,NULL,NULL,NULL,NULL,'aaa','bb','/attachment/20180109/a2b00a4354f14677bfc687cafb520d3e.jpeg',0,'2018-01-07 20:43:30',NULL,'2018-01-09 09:21:40','2018-01-08 17:32:15',NULL);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_action
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_action`;

CREATE TABLE `user_action` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `action` varchar(32) DEFAULT NULL COMMENT '动作类型',
  `point` int(11) DEFAULT NULL COMMENT '得分',
  `post_id` varchar(32) DEFAULT NULL COMMENT '关联的帖子ID',
  `comment_id` varchar(32) DEFAULT NULL COMMENT '关联的评论ID',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `user_action` WRITE;
/*!40000 ALTER TABLE `user_action` DISABLE KEYS */;

INSERT INTO `user_action` (`id`, `user_id`, `action`, `point`, `post_id`, `comment_id`, `created`, `modified`)
VALUES
	('08869e3c61e949398a9836f04b7ea3a2','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'b918f511e0814b94bca667fbcee5ca5e','b29bfcdba9fc43e08d2d4455cad060f2','2018-01-09 15:32:15',NULL),
	('29f37cd8232a40189e1861c675f308ef','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'xf444754ae294f4989ca778dbe6f1e65','aa20a1fe49a84a2b9bec74079ffd9ec9','2018-01-09 14:33:38',NULL),
	('35f00dff86d4414f8fb7c8b3909ddb71','70ee8bd502b643378a61de0d5b0000cf','new_post',10,'b07516377baa4ace84f155153f1db7ca',NULL,'2018-01-06 15:28:49',NULL),
	('37508bdadf7f4008a2a0147a463fcf1e','e90c553ebbd848519c7606d50eab8b6c','sign_in',5,NULL,NULL,'2018-01-09 15:22:22',NULL),
	('3f9f0c0070b24f2dbe04e1c22a495d37','e90c553ebbd848519c7606d50eab8b6c','sign_in',5,NULL,NULL,'2018-01-08 17:08:43',NULL),
	('4497c82628734ceea3417cf276868a9d','70ee8bd502b643378a61de0d5b0000cf','finished_post',5,'b07516377baa4ace84f155153f1db7c2',NULL,'2018-01-07 20:33:15',NULL),
	('46254dc1ccec47af82dbaa87e07ca2d8','e90c553ebbd848519c7606d50eab8b6c','new_post',10,'6c7ec250629e42fd9332a6f9ece2a9a8',NULL,'2018-01-09 09:38:33',NULL),
	('471fe17624f34bd68fdf3c87808a69f2','e90c553ebbd848519c7606d50eab8b6c','comment_adopted',5,'13cc8e0acd7848929a35b10637ef0d71','db37a50a4c7a488f84e5c70239e138d6','2018-01-08 17:59:14',NULL),
	('4a98e1a0895e47e9a7cd52d229804220','e90c553ebbd848519c7606d50eab8b6c','sign_in',5,NULL,NULL,'2018-01-08 17:12:02',NULL),
	('5547de737a444250af95787576b7e435','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'xf444754ae294f4989ca778dbe6f1e65','35c12e733c6c49aab3cfebcac02bb5e4','2018-01-09 14:34:23',NULL),
	('5a2191ead7234343bb338ed2014459dd','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'4fc44754ae294f4989ca778dbe6f1e65','e00906a7ffe14ad5ae024e39ef1f2712','2018-01-08 18:05:43',NULL),
	('5c567414995142c9a4cca417f42fb027',NULL,'new_comment',2,'43cc8e0acd7848929a35b10637ef0d79','d0a7f865aec8449688745b0a4037a5ac','2018-01-08 18:02:17',NULL),
	('5d5d6b7e8b7641bbb9c549b5c6028566','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'b918f511e0814b94bca667fbcee5ca5e','dd64a3679c6f4455b494a7853028f4f7','2018-01-09 14:40:25',NULL),
	('7358683483c44a6d84a40ad60bf267e9','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'xf444754ae294f4989ca778dbe6f1e65','0eeccf98e7da4c11ab4f12a6b42c1c65','2018-01-09 14:35:42',NULL),
	('75f2c761bc5b4fe28bc965ad599368e5','e90c553ebbd848519c7606d50eab8b6c','new_post',10,'771b34717ea84797aa5dc108131429ef',NULL,'2018-01-09 09:31:01',NULL),
	('77c1af4b89b64179a812385e4e577d86','70ee8bd502b643378a61de0d5b0000cf','finished_post',5,'13cc8e0acd7848929a35b10637ef0d71',NULL,'2018-01-08 17:59:14',NULL),
	('81a55904a0904af9bd81c72c840905dc','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'b07516377baa4ace84f155153f1db7ca','11eaa9d636e645189fa9cdfc43f36fd1','2018-01-09 15:15:25',NULL),
	('833c4a9546214ae1ad08ac2b96fc68aa','70ee8bd502b643378a61de0d5b0000cf','comment_adopted',5,'d3cc8e0acd7848929a35b10637ef0d71','8a8eab57c3b5497f99a69bed0e4a8461','2018-01-07 20:08:46',NULL),
	('847ee82bb78b4fbd80ec2df34e8f8577',NULL,'new_comment',2,'13cc8e0acd7848929a35b10637ef0d71','d559abdfb42d40f7b17c0c22f5c2f96e','2018-01-08 18:05:05',NULL),
	('86acec2128aa4ef297b4542e481c7d59','e90c553ebbd848519c7606d50eab8b6c','sign_in',5,NULL,NULL,'2018-01-09 14:39:46',NULL),
	('879617afeb2a4094bf1240e7ee8a07fc','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'xf444754ae294f4989ca778dbe6f1e65','ab010255cc824e7486ac3b310fe7cece','2018-01-09 14:36:28',NULL),
	('8853d13b2b914082b175e7ae880f6826','70ee8bd502b643378a61de0d5b0000cf','new_post',10,'43cc8e0acd7848929a35b10637ef0d79',NULL,'2018-01-06 15:54:14',NULL),
	('8f2b72140ac54f158d2967a5786c58a2','e90c553ebbd848519c7606d50eab8b6c','comment_adopted',5,'xf444754ae294a4989ca778dbe6f1e65','8608aec054e846d697af9f595702af05','2018-01-07 21:08:15',NULL),
	('911e917c65dd480798af5d5caad391b2','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'xf444754ae294f4989ca778dbe6f1e65','2cd9fcdd8a0e481abf5e32d2288450b0','2018-01-09 14:33:21',NULL),
	('92235440a64f4c6c9364e06090773c9d','e90c553ebbd848519c7606d50eab8b6c','new_post',10,'b918f511e0814b94bca667fbcee5ca5e',NULL,'2018-01-09 14:39:20',NULL),
	('94d08dc680fc47d4918a502352c3bc85','70ee8bd502b643378a61de0d5b0000cf','comment_adopted',5,'b07516377baa4ace84f155153f1db7c2','e94f377a2e5c41e7914ba3a658e0ffa1','2018-01-07 20:33:15',NULL),
	('9678eda551ef413993b9b7256dac5169','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'b918f511e0814b94bca667fbcee5ca5e','356e0a6683e94f82a3159d5798f82ac2','2018-01-09 14:41:09',NULL),
	('98ed0cf0289547b5bc6a1addb1a916e4','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'6c7ec250629e42fd9332a6f9ece2a9a8','f41bee4f039041c5b457857379e44600','2018-01-09 14:28:20',NULL),
	('9ef64d82e56e41eea8d3c2a3218ea4a3','70ee8bd502b643378a61de0d5b0000cf','new_post',10,'cfc44754ae294f4989ca778dbe6f1e66',NULL,'2018-01-06 15:53:41',NULL),
	('9fb646500be14c05984dc16742f5d3f0','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'b918f511e0814b94bca667fbcee5ca5e','09ebedf019c64957a22e208d8f08a181','2018-01-09 15:12:51',NULL),
	('a5359c1b668a4881b25490d287c72104','70ee8bd502b643378a61de0d5b0000cf','new_post',10,'c67d266ee6434a148b8d05196c7a9633',NULL,'2018-01-06 15:26:52',NULL),
	('b72b29a5f8764d2494ae560bbe123480','e90c553ebbd848519c7606d50eab8b6c','sign_in',5,NULL,NULL,'2018-01-08 17:18:20',NULL),
	('b8750d46d91f4a35b4d2189bc8bbe813','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'771b34717ea84797aa5dc108131429ef','b4dfe52867b54a51a698c8ac8f456a1a','2018-01-09 09:37:03',NULL),
	('b923ce5f4b67416094bded32f35e6457','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'b918f511e0814b94bca667fbcee5ca5e','58ae9814bd8e40999b71c86b14265322','2018-01-09 15:00:53',NULL),
	('b9a2692ad9c146c0a46d72a52ebd2243','70ee8bd502b643378a61de0d5b0000cf','comment_adopted',5,'13cc8e0acd7848929a35b10637ef0d71','daebbb08a5294a1783deea80939fddb6','2018-01-07 20:01:20',NULL),
	('c09eb897af0a4b85a6ea787a5d7aae17','70ee8bd502b643378a61de0d5b0000cf','finished_post',5,'13cc8e0acd7848929a35b10637ef0d71',NULL,'2018-01-07 20:01:20',NULL),
	('c379ac5f045249dc892b6bf5a05c0d3c','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'b918f511e0814b94bca667fbcee5ca5e','9cd8e8f50c8d4ebfa2714c5c91102398','2018-01-09 14:39:29',NULL),
	('c7bc4e5fe9764bae8df23d569c9e79f5','70ee8bd502b643378a61de0d5b0000cf','finished_post',5,'d3cc8e0acd7848929a35b10637ef0d71',NULL,'2018-01-07 20:08:46',NULL),
	('ccd45313b42a459bb4c9adea6a7b9ba8','e90c553ebbd848519c7606d50eab8b6c','sign_in',5,NULL,NULL,'2018-01-08 17:17:58',NULL),
	('d2e34cef5e5c40da816051755433e26a','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'b918f511e0814b94bca667fbcee5ca5e','2e941b4bd1dd40bbb808a423c9f3aa9c','2018-01-09 15:12:52',NULL),
	('e09910b7199b41979d27099891e436b3','70ee8bd502b643378a61de0d5b0000cf','finished_post',5,'xf444754ae294a4989ca778dbe6f1e65',NULL,'2018-01-07 21:08:15',NULL),
	('e67727f5e3ab4aec822a6d77b9af07b7','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'771b34717ea84797aa5dc108131429ef','bcf28a07fe844b9b8c4c70468b22f1d0','2018-01-09 14:48:04',NULL),
	('e9ce92f553b94f0b9ec06d5cbbc8df6b','70ee8bd502b643378a61de0d5b0000cf','comment_adopted',5,'cfc44754ae294f4989ca778dbe6f1e65','a3a83c1bab7f47d9aa49283a24ec66f8','2018-01-07 20:16:27',NULL),
	('ebf53ac720cb4f31812927e035739a18','70ee8bd502b643378a61de0d5b0000cf','finished_post',5,'cfc44754ae294f4989ca778dbe6f1e65',NULL,'2018-01-07 20:16:27',NULL),
	('fe0658dcef4a4cc78f1e509e1dec208d','e90c553ebbd848519c7606d50eab8b6c','new_comment',2,'771b34717ea84797aa5dc108131429ef','99eebcff503e4b9791508711d8597077','2018-01-09 15:15:33',NULL);

/*!40000 ALTER TABLE `user_action` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_collection
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_collection`;

CREATE TABLE `user_collection` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `post_id` varchar(32) DEFAULT NULL COMMENT '帖子ID',
  `post_title` text COMMENT '帖子的标题',
  `post_content` longtext COMMENT '帖子的内容',
  `post_user_id` varchar(32) DEFAULT NULL COMMENT '发布帖子的用户ID',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `user_collection` WRITE;
/*!40000 ALTER TABLE `user_collection` DISABLE KEYS */;

INSERT INTO `user_collection` (`id`, `user_id`, `post_id`, `post_title`, `post_content`, `post_user_id`, `created`, `modified`)
VALUES
	('008afeb5f133444286c59ad3abbd0162','70ee8bd502b643378a61de0d5b0000cf','cfc44754ae294f4989ca778dbe6f1e65','22222','22222','70ee8bd502b643378a61de0d5b0000cf','2018-01-07 20:32:07',NULL),
	('73f85f9e08ce45af9ab9357c2013ca76','70ee8bd502b643378a61de0d5b0000cf','b07516377baa4ace84f155153f1db7c2','11111222','22222','70ee8bd502b643378a61de0d5b0000cf','2018-01-07 20:33:10',NULL),
	('9580f1f4cff042a2918950506f6f40f2','e90c553ebbd848519c7606d50eab8b6c','43cc8e0acd7848929a35b10637ef0d79','333','33333','70ee8bd502b643378a61de0d5b0000cf','2018-01-08 18:14:59',NULL);

/*!40000 ALTER TABLE `user_collection` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_message`;

CREATE TABLE `user_message` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `from_user_id` varchar(32) DEFAULT NULL COMMENT '发送消息的用户ID',
  `to_user_id` varchar(32) DEFAULT NULL COMMENT '接收消息的用户ID',
  `post_id` varchar(32) DEFAULT NULL COMMENT '消息可能关联的帖子',
  `comment_id` varchar(32) DEFAULT NULL COMMENT '消息可能关联的评论',
  `action` varchar(32) DEFAULT NULL COMMENT '消息可能关联的动作',
  `content` text COMMENT '消息内容',
  `type` tinyint(2) DEFAULT NULL COMMENT '消息类型',
  `created` datetime DEFAULT NULL,
  `modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `user_message` WRITE;
/*!40000 ALTER TABLE `user_message` DISABLE KEYS */;

INSERT INTO `user_message` (`id`, `from_user_id`, `to_user_id`, `post_id`, `comment_id`, `action`, `content`, `type`, `created`, `modified`)
VALUES
	('',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	('14cce9a9a24f4256886a1e41e5a45664','e90c553ebbd848519c7606d50eab8b6c','70ee8bd502b643378a61de0d5b0000cf','b07516377baa4ace84f155153f1db7ca','11eaa9d636e645189fa9cdfc43f36fd1',NULL,NULL,1,'2018-01-09 15:15:25',NULL);

/*!40000 ALTER TABLE `user_message` ENABLE KEYS */;
UNLOCK TABLES;


