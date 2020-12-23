/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.21 : Database - brain_storm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`brain_storm` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `brain_storm`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `category_id` int unsigned NOT NULL AUTO_INCREMENT,
  `category_pid` int DEFAULT NULL,
  `category_name` varchar(50) DEFAULT NULL,
  `category_description` varchar(255) DEFAULT NULL,
  `category_order` int unsigned DEFAULT '1',
  `category_icon` varchar(20) DEFAULT NULL,
  `category_status` int unsigned DEFAULT '1',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`category_id`,`category_pid`,`category_name`,`category_description`,`category_order`,`category_icon`,`category_status`) values 
(2,1,'Java基础','',1,'',1),
(3,1,'Core Java','',1,'',1),
(4,1,'多线程并发编程','',1,'',1),
(5,1,'Sockets和IO','',1,'',1),
(6,1,'设计模式和反射','',1,'',1),
(7,1,'JVM','',1,'',1),
(8,1,'JavaWeb','',1,'',1),
(9,1,'Java框架','',1,'',1),
(11,10,'数据结构和算法','',1,'',1),
(12,10,'操作系统','',1,'',1),
(13,10,'数据库','',1,'',1),
(14,10,'计算机网络','',1,'',1),
(16,15,'消息服务','',1,'',1),
(17,15,'缓存服务','',1,'',1),
(18,15,'负载均衡','',1,'',1),
(19,0,'学术猜想','基于现有理论基础上的超前的合理假设',1,'fa fa-coffee',1),
(20,19,'物理学','',1,'',1),
(21,19,'天文学','',1,'',1),
(22,19,'计算机','',1,'',1),
(23,19,'哲学','',1,'',1),
(24,19,'生物学','',1,'',1),
(25,19,'化学','',1,'',1),
(26,19,'数学','',1,'',1),
(27,0,'创新发明','',1,'fa fa-cab',1),
(28,27,'生活实用','',1,'',1),
(29,0,'其他','目前未找到分类',1,'fa fa-cloud',1),
(30,27,'数码科技','',1,'',1);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `comment_id` int unsigned NOT NULL AUTO_INCREMENT,
  `comment_pid` int unsigned DEFAULT '0',
  `comment_pname` varchar(255) DEFAULT NULL,
  `comment_thought_id` int unsigned DEFAULT NULL,
  `comment_author_name` varchar(50) DEFAULT NULL,
  `comment_author_email` varchar(50) DEFAULT NULL,
  `comment_author_url` varchar(50) DEFAULT NULL,
  `comment_content` varchar(1000) DEFAULT NULL,
  `comment_agent` varchar(200) DEFAULT NULL,
  `comment_ip` varchar(50) DEFAULT NULL,
  `comment_create_time` datetime DEFAULT NULL,
  `comment_role` int DEFAULT NULL,
  `comment_status` int unsigned DEFAULT '1',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`comment_id`,`comment_pid`,`comment_pname`,`comment_thought_id`,`comment_author_name`,`comment_author_email`,`comment_author_url`,`comment_content`,`comment_agent`,`comment_ip`,`comment_create_time`,`comment_role`,`comment_status`) values 
(16,0,'',19,'三更力争','1449462080@qq.com','','这里是软件测试',NULL,'0:0:0:0:0:0:0:1','2020-12-18 11:59:14',1,1),
(17,0,'',22,'三更力争','1449462080@qq.com','','这里是软件测试',NULL,'0:0:0:0:0:0:0:1','2020-12-20 13:29:53',1,1),
(18,0,'',19,'23k纯帅','547168254@qq.com','','我是三土',NULL,'0:0:0:0:0:0:0:1','2020-12-21 05:10:28',1,1);

/*Table structure for table `link` */

DROP TABLE IF EXISTS `link`;

CREATE TABLE `link` (
  `link_id` int unsigned NOT NULL AUTO_INCREMENT,
  `link_url` varchar(255) DEFAULT NULL,
  `link_name` varchar(255) DEFAULT NULL,
  `link_image` varchar(255) DEFAULT NULL,
  `link_description` varchar(255) DEFAULT NULL,
  `link_owner_nickname` varchar(40) DEFAULT NULL,
  `link_owner_contact` varchar(255) DEFAULT NULL,
  `link_update_time` datetime DEFAULT NULL,
  `link_create_time` datetime DEFAULT NULL,
  `link_order` int unsigned DEFAULT '1',
  `link_status` int unsigned DEFAULT '1',
  PRIMARY KEY (`link_id`),
  UNIQUE KEY `link_name` (`link_name`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `link` */

insert  into `link`(`link_id`,`link_url`,`link_name`,`link_image`,`link_description`,`link_owner_nickname`,`link_owner_contact`,`link_update_time`,`link_create_time`,`link_order`,`link_status`) values 
(6,'https://www.baidu.com','测试',NULL,'测试',NULL,'测试','2017-10-07 16:51:49','2017-10-07 16:51:49',1,0);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `menu_level` int DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT NULL,
  `menu_order` int DEFAULT NULL,
  `menu_status` int DEFAULT '1',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_name` (`menu_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`menu_id`,`menu_name`,`menu_url`,`menu_level`,`menu_icon`,`menu_order`,`menu_status`) values 
(4,'文章归档','/thoughtFile',1,'fa-list-alt fa',2,1);

/*Table structure for table `options` */

DROP TABLE IF EXISTS `options`;

CREATE TABLE `options` (
  `option_id` int NOT NULL,
  `option_site_title` varchar(255) DEFAULT NULL,
  `option_site_descrption` varchar(255) DEFAULT NULL,
  `option_meta_descrption` varchar(255) DEFAULT NULL,
  `option_meta_keyword` varchar(255) DEFAULT NULL,
  `option_aboutsite_avatar` varchar(255) DEFAULT NULL,
  `option_aboutsite_title` varchar(255) DEFAULT NULL,
  `option_aboutsite_content` varchar(255) DEFAULT NULL,
  `option_aboutsite_wechat` varchar(255) DEFAULT NULL,
  `option_aboutsite_qq` varchar(255) DEFAULT NULL,
  `option_aboutsite_github` varchar(255) DEFAULT NULL,
  `option_aboutsite_weibo` varchar(255) DEFAULT NULL,
  `option_tongji` varchar(255) DEFAULT NULL,
  `option_status` int DEFAULT '1',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `options` */

insert  into `options`(`option_id`,`option_site_title`,`option_site_descrption`,`option_meta_descrption`,`option_meta_keyword`,`option_aboutsite_avatar`,`option_aboutsite_title`,`option_aboutsite_content`,`option_aboutsite_wechat`,`option_aboutsite_qq`,`option_aboutsite_github`,`option_aboutsite_weibo`,`option_tongji`,`option_status`) values 
(1,'头脑风暴','BrainStorm','Explore the World and Discover the Mystery','头脑风暴,BrainStorm','/uploads/2020/12/touxiang.jpg','头脑风暴','希望是隐藏在群山后的星星，探索是人生道路上倔执的旅人。','/uploads/2020/12/weixin.jpg','1449462080','Soceremite','18851898628',NULL,1);

/*Table structure for table `page` */

DROP TABLE IF EXISTS `page`;

CREATE TABLE `page` (
  `page_id` int unsigned NOT NULL AUTO_INCREMENT,
  `page_key` varchar(50) DEFAULT NULL,
  `page_title` varchar(50) DEFAULT NULL,
  `page_content` mediumtext,
  `page_create_time` datetime DEFAULT NULL,
  `page_update_time` datetime DEFAULT NULL,
  `page_view_count` int unsigned DEFAULT '0',
  `page_comment_count` int unsigned DEFAULT '0',
  `page_status` int unsigned DEFAULT '1',
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `page_key` (`page_key`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `page` */

insert  into `page`(`page_id`,`page_key`,`page_title`,`page_content`,`page_create_time`,`page_update_time`,`page_view_count`,`page_comment_count`,`page_status`) values 
(1,'map','站点导航',NULL,NULL,NULL,0,0,2),
(2,'thoughtFile','文章归档',NULL,NULL,NULL,0,0,2);

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `tag_id` int unsigned NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) DEFAULT NULL,
  `tag_description` varchar(255) DEFAULT NULL,
  `tag_status` int unsigned DEFAULT '1',
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `tag_name` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

insert  into `tag`(`tag_id`,`tag_name`,`tag_description`,`tag_status`) values 
(23,'计算机','',1),
(24,'物理','',1),
(25,'化学','',1),
(26,'生物','',1),
(27,'哲学','',1),
(28,'天文学','',1),
(29,'数学','',1);

/*Table structure for table `thought` */

DROP TABLE IF EXISTS `thought`;

CREATE TABLE `thought` (
  `thought_id` int unsigned NOT NULL AUTO_INCREMENT,
  `thought_user_id` int unsigned DEFAULT '1',
  `thought_title` varchar(255) DEFAULT NULL,
  `thought_content` mediumtext,
  `thought_parent_category_id` int DEFAULT NULL,
  `thought_child_category_id` int DEFAULT NULL,
  `thought_tag_ids` varchar(50) DEFAULT NULL,
  `thought_view_count` int DEFAULT '0',
  `thought_comment_count` int DEFAULT '0',
  `thought_like_count` int DEFAULT '0',
  `thought_post_time` datetime DEFAULT NULL,
  `thought_update_time` datetime DEFAULT NULL,
  `thought_is_comment` int unsigned DEFAULT NULL,
  `thought_status` int unsigned DEFAULT '1',
  `thought_order` int unsigned DEFAULT NULL,
  PRIMARY KEY (`thought_id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `thought` */

insert  into `thought`(`thought_id`,`thought_user_id`,`thought_title`,`thought_content`,`thought_parent_category_id`,`thought_child_category_id`,`thought_tag_ids`,`thought_view_count`,`thought_comment_count`,`thought_like_count`,`thought_post_time`,`thought_update_time`,`thought_is_comment`,`thought_status`,`thought_order`) values 
(19,1,'软件工程原理','<div class=\"para\" label-module=\"para\">软件工程是一门研究用工程化方法构建和维护有效的、实用的和高质量的软件的学科。它涉及<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%A8%8B%E5%BA%8F%E8%AE%BE%E8%AE%A1%E8%AF%AD%E8%A8%80/2317999\" data-lemmaid=\"2317999\">程序设计语言</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E5%BA%93/103728\" data-lemmaid=\"103728\">数据库</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91%E5%B7%A5%E5%85%B7/4605523\" data-lemmaid=\"4605523\">软件开发工具</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%B3%BB%E7%BB%9F%E5%B9%B3%E5%8F%B0/3766067\" data-lemmaid=\"3766067\">系统平台</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%A0%87%E5%87%86/219665\" data-lemmaid=\"219665\">标准</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E8%AE%BE%E8%AE%A1%E6%A8%A1%E5%BC%8F/1212549\" data-lemmaid=\"1212549\">设计模式</a>等方面。</div><p>在现代社会中，软件应用于多个方面。典型的软件有<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%94%B5%E5%AD%90%E9%82%AE%E4%BB%B6/111106\" data-lemmaid=\"111106\">电子邮件</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%B5%8C%E5%85%A5%E5%BC%8F%E7%B3%BB%E7%BB%9F/186978\" data-lemmaid=\"186978\">嵌入式系统</a>、人机界面、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%8A%9E%E5%85%AC\">办公</a>套件、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F/192\" data-lemmaid=\"192\">操作系统</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%BC%96%E8%AF%91%E5%99%A8/8853067\" data-lemmaid=\"8853067\">编译器</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E5%BA%93/103728\" data-lemmaid=\"103728\">数据库</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%B8%B8%E6%88%8F/33581\" data-lemmaid=\"33581\">游戏</a>等。同时，各个行业几乎都有计算机软件的应用，如工业、农业、银行、航空、政府部门等。这些应用促进了经济和社会的发展，也提高了工作效率和生活效率 。</p><div class=\"para-title level-3\" label-module=\"para-title\"><h3 class=\"title-text\">定义</h3></div><div class=\"para\" label-module=\"para\"><div class=\"lemma-picture text-pic layout-right\"><span class=\"description\">软件工程</span></div>软件工程一直以来都缺乏一个统一的定义，很多学者、组织机构都分别给出了自己认可的定义：<span class=\"sup--normal\" data-sup=\"1\" data-ctrmap=\":1,\">&nbsp;[1]</span><a class=\"sup-anchor\" name=\"ref_[1]_7112047\">&nbsp;</a><a name=\"ref_1\"></a><a name=\"ref_1\"></a></div><div class=\"para\" label-module=\"para\">BarryBoehm：运用现代科学技术知识来设计并构造<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E8%AE%A1%E7%AE%97%E6%9C%BA\">计算机</a>程序及为开发、运行和维护这些程序所必需的相关文件资料。</div><div class=\"para\" label-module=\"para\"><a target=\"_blank\" href=\"https://baike.baidu.com/item/IEEE\">IEEE</a>：在软件工程术语<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%B1%87%E7%BC%96\">汇编</a>中的定义：软件工程是：1.将系统化的、严格约束的、可量化的方法应用于软件的开发、运行和维护，即将工程化应用于软件；2.在1中所述方法的研究</div><div class=\"para\" label-module=\"para\">FritzBauer：在NATO会议上给出的定义：建立并使用完善的工程化原则，以较经济的手段获得能在实际机器上有效运行的可靠软件的一系列方法。</div><div class=\"para\" label-module=\"para\">《计算机科学技术百科全书》：软件工程是应用<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6\">计算机科学</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%95%B0%E5%AD%A6\">数学</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E9%80%BB%E8%BE%91%E5%AD%A6\">逻辑学</a>及<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%AE%A1%E7%90%86%E7%A7%91%E5%AD%A6\">管理科学</a>等原理，开发软件的工程。软件工程借鉴传统工程的原则、方法，以提高质量、降低成本和改进算法。其中，<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E8%AE%A1%E7%AE%97%E6%9C%BA%E7%A7%91%E5%AD%A6\">计算机科学</a>、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%95%B0%E5%AD%A6\">数学</a>用于构建模型与<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%AE%97%E6%B3%95\">算法</a>，<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%B7%A5%E7%A8%8B%E7%A7%91%E5%AD%A6\">工程科学</a>用于制定规范、设计范型(paradigm)、评估成本及确定权衡，管理科学用于计划、资源、质量、成本等管理。</div><div class=\"para\" label-module=\"para\">比较认可的一种定义认为：软件工程是研究和应用如何以系统性的、规范化的、可定量的过程化方法去开发和维护软件，以及如何把经过时间考验而证明正确的管理技术和当前能够得到的最好的技术方法结合起来。</div><div class=\"para\" label-module=\"para\"><span><a target=\"_blank\" href=\"https://baike.baidu.com/item/ISO%209000\">ISO 9000</a></span>对软件工程过程的定义是：软件工程过程是输入转化为输出的一组彼此相关的资源和活动。<span class=\"sup--normal\" data-sup=\"2\" data-ctrmap=\":2,\">&nbsp;[2]</span><a class=\"sup-anchor\" name=\"ref_[2]_7112047\">&nbsp;</a><a name=\"ref_2\"></a><a name=\"ref_2\"></a><a name=\"ref_2\"></a><a name=\"ref_2\"></a><a name=\"ref_2\"></a><a name=\"ref_2\"></a></div><div class=\"para\" label-module=\"para\">其它定义：1．运行时，能够提供所要求功能和性能的<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%8C%87%E4%BB%A4\">指令</a>或计算机程序集合。2．程序能够满意地处理信息的<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84/1450\" data-lemmaid=\"1450\">数据结构</a>。3．描述程序功能需求以及程序如何操作和使用所要求的文档。以开发语言作为描述语言，可以认为：软件=<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E7%A8%8B%E5%BA%8F/13831935\" data-lemmaid=\"13831935\">程序</a>+<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%95%B0%E6%8D%AE\">数据</a>+<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%96%87%E6%A1%A3\">文档</a>。<span class=\"sup--normal\" data-sup=\"2\" data-ctrmap=\":2,\">&nbsp;[2]</span></div>',19,22,'23',4,2,1,'2020-12-18 11:58:46','2020-12-18 12:16:45',1,1,1),
(20,1,'软件可靠性测试','软件测试',19,22,'23',0,0,0,'2020-12-18 12:25:08','2020-12-18 12:25:08',1,1,1),
(22,1,'软件工程原理','软件工程',19,22,'23',5,1,1,'2020-12-18 12:26:15','2020-12-18 12:26:15',1,1,1),
(23,1,'我是王孜垚','<p>我是王孜垚<img src=\"http://localhost:8080/plugin/layui/images/face/1.gif\" alt=\"[嘻嘻]\"><img src=\"http://localhost:8080/plugin/layui/images/face/1.gif\" alt=\"[嘻嘻]\"><img src=\"http://localhost:8080/plugin/layui/images/face/1.gif\" alt=\"[嘻嘻]\"></p>',19,21,'28',6,0,1,'2020-12-21 05:10:03','2020-12-21 07:56:41',1,1,1),
(24,1,'头脑风暴法','<p>头脑风暴法出自“头脑风暴”一词。所谓头脑风暴(Brain — Storming), 最早是精神病理学上的用语，</p><p>指精神病患者的精神错乱状态而言的。而现在则成为无限制的自由联想和讨论的代名词，其目的在千产生</p><p>新观念或激发创新设想。</p><p>头脑风暴法是由美国创造学家A• F• 奥斯本千1939 年首次提出、1953 年正式发表的一种激发性思维的</p><p>方法。此法经各国创造学研究者的实践和发展，至今已经形成了一个发明技法群，如奥斯本智力激励法、</p><p>默写式智力激励法、卡片式智力激励法等等。</p><p>在群体决策中，由于群体成员心理相互作用影响，易屈于权威或大多数人意见，形成所谓的“ 群体思</p><p>组”。群体思维削弱了群体的批判精神和创造力，损害了决策的质晕。为了保证群体决策的创造性，提高</p><p>决策质量，管理上发展了一系列改善群体决策的方法，头脑风暴法是较为典型的一个。</p><p>头脑风暴法有可分为直接头脑风暴法（通常简称为头脑风暴法）和质疑头脑风暴法（也称反头脑风</p><p>暴达）。前者是在专家群体决策尽可能激发创造性，产生尽可能多的设想的方法，后者则是对前者提出的</p><p>设想、方案逐一质疑，分析其现实可行性的方法。</p><p>采用头脑风暴法组织群体决策时，要集中有关专家召开专题会议，主持者以明确的方式向所有参与者</p><p>阐明问题，说明会议的规则，尽力创造在融洽轻松的会议气氛。一般不发表意见，以免影响会议的自由气</p><p>氛。由专家们“自由“提出尽可能多的方案。</p>',29,NULL,NULL,7,0,1,'2020-12-21 08:25:24','2020-12-21 08:25:24',1,1,1);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL DEFAULT '',
  `user_pass` varchar(255) NOT NULL DEFAULT '',
  `user_nickname` varchar(255) NOT NULL DEFAULT '',
  `user_email` varchar(100) DEFAULT '',
  `user_url` varchar(100) DEFAULT '',
  `user_avatar` varchar(255) DEFAULT NULL,
  `user_last_login_ip` varchar(255) DEFAULT NULL,
  `user_register_time` datetime DEFAULT NULL,
  `user_last_login_time` datetime DEFAULT NULL,
  `user_status` int unsigned DEFAULT '1',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_email` (`user_email`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_pass`,`user_nickname`,`user_email`,`user_url`,`user_avatar`,`user_last_login_ip`,`user_register_time`,`user_last_login_time`,`user_status`) values 
(1,'liuyadong','123456','三更力争','1449462080@qq.com','','/uploads/2020\\12/Cache_-4f6576895d5a9f87.png','0:0:0:0:0:0:0:1','2020-12-09 13:50:56','2020-12-22 13:57:46',1),
(3,'wangziyao','wangziyao','23k纯帅','547168254@qq.com','','/uploads/2020\\12/20201220222021(1).jpg','0:0:0:0:0:0:0:1','2020-12-20 14:18:38','2020-12-21 05:07:47',1),
(4,'caipeiyan','caipeiyan','Max D','1550820064@qq.com','','/uploads/2020\\12/B226A95A77B155507F4EFCE73BA5F243.jpg',NULL,'2020-12-21 04:58:08',NULL,1),
(6,'nuaa','nuaa','南航','nuaa@nuaa.edu.cn','','/uploads/2020\\12/南航(3).jpg','0:0:0:0:0:0:0:1','2020-12-21 05:03:58','2020-12-22 11:28:49',1),
(12,'wangrong','wangrong','荣哥','wangrong@nuaa.edu.cn','','/uploads/2020\\12/微信图片_20201222200357.jpg',NULL,'2020-12-22 11:19:07',NULL,1),
(15,'zhangsan','zhangsan','新用户','zhangsan@nuaa.edu.cn','','/uploads/2020/12/touxiang.jpg',NULL,'2020-12-22 11:28:02',NULL,1),
(18,'lisi','lisi','新用户','lisi','','/uploads/2020/12/touxiang.jpg','0:0:0:0:0:0:0:1','2020-12-22 12:38:20','2020-12-22 12:38:36',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
