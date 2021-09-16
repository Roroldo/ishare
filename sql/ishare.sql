/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001
 Date: 01/09/2021 14:58:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tab_category
-- ----------------------------
DROP TABLE IF EXISTS `tab_category`;
CREATE TABLE `tab_category`  (
  `cid` int(11) NOT NULL,
  `cname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_category
-- ----------------------------
INSERT INTO `tab_category` VALUES (1, '计算机基础');
INSERT INTO `tab_category` VALUES (2, '编程语言');
INSERT INTO `tab_category` VALUES (3, '就业体系');

-- ----------------------------
-- Table structure for tab_comment
-- ----------------------------
DROP TABLE IF EXISTS `tab_comment`;
CREATE TABLE `tab_comment`  (
  `commentId` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sendDateStr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `count` int(10) NULL DEFAULT 0,
  `uid` int(11) NULL DEFAULT NULL,
  `answerCommentId` int(10) NULL DEFAULT 0,
  PRIMARY KEY (`commentId`) USING BTREE,
  INDEX `c_uid`(`uid`) USING BTREE,
  CONSTRAINT `c_uid` FOREIGN KEY (`uid`) REFERENCES `tab_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = sjis COLLATE = sjis_japanese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_comment
-- ----------------------------
INSERT INTO `tab_comment` VALUES (1, 'aaaaaaaaaaaaa', '2021年10月12日 13时11分11秒', 8, 1, 0);
INSERT INTO `tab_comment` VALUES (3, 'cccccccccc', '2021年9月12日 13时11分11秒', 7, 7, 0);
INSERT INTO `tab_comment` VALUES (5, 'fffffffffffffff', '2021年10月12日 13时11分11秒', 0, 2, 1);
INSERT INTO `tab_comment` VALUES (6, '你好，留言板', '2021年10月12日 13时11分11秒', 0, 7, 1);
INSERT INTO `tab_comment` VALUES (16, '你好', 'dddddd', 3, 2, 0);
INSERT INTO `tab_comment` VALUES (20, '你好 世界树', '2021年08月23日 23时42分24秒', 9, 7, 0);
INSERT INTO `tab_comment` VALUES (21, '今天是个好日子', '2021年08月23日 23时51分08秒', 23, 10, 0);
INSERT INTO `tab_comment` VALUES (28, 'dddddddddd', '2021年08月25日 16时12分08秒', 0, 10, 3);
INSERT INTO `tab_comment` VALUES (29, '联系 huang', '2021年08月25日 16时17分45秒', 0, 10, 20);
INSERT INTO `tab_comment` VALUES (30, '好好学前端', '2021年08月25日 16时18分18秒', 0, 10, 1);
INSERT INTO `tab_comment` VALUES (31, '你好', '2021年08月25日 16时19分55秒', 0, 10, 21);

-- ----------------------------
-- Table structure for tab_course
-- ----------------------------
DROP TABLE IF EXISTS `tab_course`;
CREATE TABLE `tab_course`  (
  `csId` int(11) NOT NULL AUTO_INCREMENT,
  `csName` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `csAuthor` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `csIntroduce` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `csImg` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT 0,
  `uri` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cid` int(11) NULL DEFAULT NULL,
  `uid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`csId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_course
-- ----------------------------
INSERT INTO `tab_course` VALUES (1, 'Java工程师体系课', 'Java教研室', 'Java工程师2020版，本课程全新升级，从0基础到高薪就业，不需脱产学习，冲击互联网高薪岗位无论你是未就业的学生还是想转行的在职人员，不需要基础，只要你有梦想，想高薪！', 'course_imgs/tixi1.png', 1, 'https://www.imooc.com/', '1234', 3, 1);
INSERT INTO `tab_course` VALUES (2, '前端工程师体系课', '前端教研室', '前端工程师2020版不管你之前学什么、做什么，入行前端，都是明智的选择！', 'course_imgs/tixi2.png', 0, 'https://www.imooc.com/', '1234', 3, 1);
INSERT INTO `tab_course` VALUES (3, 'Python工程师体系课', 'Python教研室', 'Python工程师2020版，Facebook曾声称“只招全栈工程师”！全栈用人需求猛增，市面人才紧缺。0基础进击Python全栈开发，诱人薪资在前方！', 'course_imgs/tixi3.png', 1, 'https://www.imooc.com/', '1234', 3, 1);
INSERT INTO `tab_course` VALUES (4, '大数据工程师体系课', '大数据教研室', '以就业为目标的应用型大数据开发体系课，带你从入门直达中级工程师岗位要求具备Java及数据库基础即可学习，为你架好大数据工程师的进阶阶梯。Java/Scala双语言教学，电商、直播、中台多个热点商用项目实战。', 'course_imgs/tixi4.png', 1, 'https://www.imooc.com/', '1234', 3, 1);
INSERT INTO `tab_course` VALUES (5, 'Java架构师', 'Java教研室', '国内外一线大厂技术大咖与慕课网组成专家团队12个月磨一剑，千万级电商项目从0到1到100全过程，涵盖Java程序员不同成长阶段的问题及优选解决方案。', 'course_imgs/zhichang1.png', 2, 'https://www.imooc.com/', '1234', 3, 2);
INSERT INTO `tab_course` VALUES (6, 'Java全栈工程师', 'Java教研室', '七月亲授，Web领域市场呼声最高的主流技术栈逐一精讲。技术与业务深度融合，电商项目前后端开发的教科书级别案例，赋能Java从业者拥有更强的职场适应力和工作竞争力。', 'course_imgs/zhichang2.png', 0, 'https://www.imooc.com/', '1234', 3, 2);
INSERT INTO `tab_course` VALUES (7, '前端进阶课', '前端教研室', '升级Vue3.0正式版，高级前端工程师成长路径，带你告别迷茫一套真正的思维+技术能力提升课程。不做技术的简单堆砌，7个月实现能力跨越式进阶。', 'course_imgs/zhichang3.png', 0, 'https://www.imooc.com/', '1234', 3, 7);
INSERT INTO `tab_course` VALUES (8, '数据分析课', '大数据教研室', '为运营、产品、市场打造的”专业“课程，为程序员转型产品经理打造的“破圈”课程', 'course_imgs/zhichang4.png', 0, 'https://www.imooc.com/', '1234', 3, 7);
INSERT INTO `tab_course` VALUES (9, '图论算法精讲', 'bobo', '玩转算法系列--图论精讲（Java版），图论算法是所有计算机专业的同学必学的基础知识；也是在算法，数据结构，离散数学等领域的重要内容；是面试，升职，计算机专业考研，考博的必考内容。在这个课程中，老师将用其独到的问题讲解方式，庖丁解牛，深入浅出，让大家在这个课程中，真正地玩转图论算法。', 'course_imgs/suanfa1.png', 2, 'https://www.imooc.com/', '55ty', 1, 7);
INSERT INTO `tab_course` VALUES (10, '算法与数据结构之综合提升', 'bobo', '算法与数据结构-综合提升 C++版。任何时候学习算法都不晚，而且越早越好，这么多年，你听说过技术过时，什么时候听说过算法过时，不仅没有过时，因为机器学习、大数据的要求，算法变得越来越重要了。', 'course_imgs/suanfa2.png', 1, 'https://www.imooc.com/', '3455', 1, 7);
INSERT INTO `tab_course` VALUES (11, '玩转算法面试之Leetcode真题分门别类讲解', 'bobo', 'java语言讲解。学完这门课程，对于面试中遇到的大多数算法问题，你都会迎刃而解，但课程绝不止于面试，同样适合即将参加各类算法竞赛的同学，重要的是提升你的算法思维，这将是贯穿你编程生涯的核心内功！', 'course_imgs/suanfa3.png', 0, 'https://www.imooc.com/', '3545', 1, 7);
INSERT INTO `tab_course` VALUES (12, '大学计算机必修课', '求老仙', '编译原理，操作系统，图形学被称为程序员的三大浪漫，不仅因为它们是大学计算机系的必修内容，更因为它们在回答计算机领域三个基本问题：程序如何被编译成机器指令然后被执行（编译原理）、多个程序如何共享资源（操作系统）、人和机器如何交互（图形学）。掌握这些知识不仅是为了应付大学考试，更是为了能让你在未来的“大型化开发”、“底层开发”中更从容，让你能更好的应对面试、开发、造轮子等实际问题。', 'course_imgs/jichu1.png', 1, 'https://www.imooc.com/', '3354', 1, 13);
INSERT INTO `tab_course` VALUES (13, '编程必备基础', 'Mary', 'python语言讲解。计算机组成原理+操作系统+计算机网络。关于计算机基础的课程很多，内容繁杂，但无论是相关书籍还是大学课程，都有点脱离工作，有鉴于此，讲师结合自己多年工作经验，总结出了这套更适合程序员的计算机基础知识课程，带你更快的补足编程必备基础知识。', 'course_imgs/jichu2.png', 0, 'https://www.imooc.com/', '3333', 1, 13);
INSERT INTO `tab_course` VALUES (14, '大话HTTP协议', '风落几番', '编程必备基础。HTTP如此重要，但很多人对它的认识基本停留在表面，无论从实际工作还是认知的角度，系统学习HTTP都非常必要，这是很多人刚需，但不论是学校还是市面上的教程很少有既系统全面又贴近实际工作的，这是我们制作课程的出发点也是目的，让你以更贴近实际工作状态彻底弄懂HTTP。', 'course_imgs/jichu3.png', 0, 'https://www.imooc.com/', '3215', 1, 13);
INSERT INTO `tab_course` VALUES (15, '2020 重学C++ ', 'quickzhao', '如果你想了解很多编程语言的思想源泉，想要一窥大型企业级开发工程的思路，想开发别人做不了的高性能程序，那C++就是你的不二之选。课程将从C++的发展史讲起，从知识与思想层面 从0带你构建C++知识框架，并会分享讲师亲历的大型项目实践思路，为你打下坚实的基础。', 'course_imgs/c++1.png', 2, 'https://www.imooc.com/', 'fgh3', 2, 7);
INSERT INTO `tab_course` VALUES (16, 'Java通用型支付+电商平台双系统实战', '廖师兄', 'Java通用型支付。本课程将手把手带你实战双系统开发：全模块电商平台、通用型支付系统，打通SpringBoot2.x、MyBatis、MQ等技术栈知识壁垒，此外还略带小伙伴喜欢的硬核黑科技：内网穿透。相信每一个力争上游的你都值得拥有~', 'course_imgs/java1.png', 1, 'https://www.imooc.com/', '56ht', 2, 7);
INSERT INTO `tab_course` VALUES (17, '玩转Java并发工具', '悟空', '本课程深度解密JUC库，对Java并发常见的工具类进行从使用到原理的详解，包括CAS+AQS+ThreadLocal+ConcurrentHashMap+线程池+各种锁+并发综合实战项目等。课程对于面试和实际工作都非常有帮助，还能通过实战项目，把学到的知识真正内化，助你用最快的速度，得到最大的提升。', 'course_imgs/java2.png', 1, 'https://www.imooc.com/', '67jy', 2, 7);
INSERT INTO `tab_course` VALUES (18, 'Java网络编程 ', 'Stannum', '网络层编程，是每一个开发者都要面对的技术。课程为解决大家学习网络层知识的难题，以创新性的“对比式学习”搭建网络编程课程，课程主线清晰（网络层基础铺垫-->java网络编程前置技术讲解-->阻塞式编程BIO-->非阻塞式编程NIO-->异步编程AIO-->综合实战）适合每一位需要理解网络编程的同学们学习。以“项目驱动”为导向的学习，与企业刚需灵魂契合。', 'course_imgs/java3.png', 0, 'https://www.imooc.com/', 'y78i', 2, 7);
INSERT INTO `tab_course` VALUES (19, 'Python3实用编程技巧进阶', '程序员硕', '本课程中每堂课都先从实际问题出发，然后分析问题，多种解决方案解决问题，最后给出最优的解决方案与手段； 通过本课程的学习，可以快速提升你的Python编程能力，摆脱只会Python语法的无力感；轻松掌握解决问题的高级手段,让你成为真正的编程高手。', 'course_imgs/python1.png', 0, 'https://www.imooc.com/', '1234', 2, 7);
INSERT INTO `tab_course` VALUES (20, 'Python3高级核心技术97讲', 'bobby', '这门课程是初中级Python开发人员向高级进阶的必学课程 许多Pythoner喜欢追求新的框架，但却不重视Python本身基础知识的学习， 他们不知道的是，语言本身的进阶优先于框架，大公司更注重语言本身的功底。万丈高楼平地起，学透了Python高级基础知识再学习其它框架，才会事半功倍，才会更好的理解和使用这些框架 。', 'course_imgs/python2.png', 0, 'https://www.imooc.com/', '2233', 2, 7);
INSERT INTO `tab_course` VALUES (21, 'Python爬虫工程师从入门到进阶', 'bobby', '大数据时代，python爬虫工程师人才猛增，本课程专为爬虫工程师打造，课程有四个阶段，爬虫0基础入门->项目实战->爬虫难点突破->scrapy框架快速抓取，带你系统学习。课程精选多个实战项目，从易到难，层层深入。不同项目解决不同的抓取问题，带你从容抓取主流网站，进阶部分针对性讲解数据抓取的难点和面试考点，让你牢牢掌握爬虫工程师硬核技能。', 'course_imgs/python3.png', 0, 'https://www.imooc.com/', '3333', 2, 1);
INSERT INTO `tab_course` VALUES (22, 'Go+Python双语言混合开发', 'bobby', 'bobby老师潜心力作，为1年以上Web开发者打造，Go+Python混合开发微服务框架，让你成为具备双语言后端能力的开发者，倍增你的职场竞争力，为你提供一条与竞争者不同的差异化上升道路。', 'course_imgs/go1.png', 1, 'https://www.imooc.com/', '2344', 2, 2);
INSERT INTO `tab_course` VALUES (23, 'Google资深工程师深度讲解Go语言', 'ccmouse', 'Go作为专门为并发和大数据设计的语言，在编程界占据越来越重要的地位！不论是c/c++，php，java，重构首选语言就是Go~本次课程特邀谷歌资深工程师，将Go语言使用经验总结归纳，从Go语言基本语法到函数式编程、并发编程，最后构建分布式爬虫系统，步步深入，带你快速掌握Go语言！', 'course_imgs/go2.png', 0, 'https://www.imooc.com/', '1234', 2, 2);
INSERT INTO `tab_course` VALUES (24, 'Go实战仿百度云盘', 'xiaomo', '本课程将通过Golang来实现一个支持断点续传和秒传的分布式云存储服务系统。课程中老师将手把手带你从快速构建“云存储”原型系统，到分块上传，到搭建访问阿里云，最后进行系统的微服务化，让你快速掌握架构传输性能和稳定性的优化过程，秒变云时代中第一代“云程序员”。', 'course_imgs/go3.png', 1, 'https://www.imooc.com/', '1234', 2, 7);
INSERT INTO `tab_course` VALUES (25, '高级Redis应用进阶课 一站式Redis解决方案', 'InCowboy', 'Redis不仅功能强大，而且经久不衰，是工作和面试都绕不开的必备技能。本课程以一个实战项目为主线，整合Redis各种问题场景，不断改造项目，以问带学。学完本课后，面对Redis相关问题，你将能够快速进行排查与修复，提升团队中的话语权。', 'course_imgs/redis.png', 0, 'https://www.imooc.com/', '1234', 1, 1);
INSERT INTO `tab_course` VALUES (31, 'Spring Cloud分布式微服务实战', '风间影月', '这是一门培养应对复杂业务的综合技术能力的实战课程，本课采用前后端分离开发模式，严格遵守企业级架构和规范，带你开发门户平台＋媒体中心+运营中心三大业务的企业级自媒体平台。让你全面掌握主流后端技术栈：Spring Cloud+MongoDB+Redis+RabbitMQ等，同时获得微服务、分布式、项目和微架构综合实战经验。', 'course_imgs/springcloud.png', 0, 'https://www.imooc.com/', 'duj9', 2, 1);
INSERT INTO `tab_course` VALUES (32, 'MySQL 8.0详解与实战', 'sqlern', '本课程遵循“用户体验至上”的原则，不敢说设计精良，尽善尽美，却也竭尽全力去锻造良好的学习体验。包含三方面，（1）设计之初：遵循“有教无类，教学相长”的原则；（2）学习路线：采用循序渐进的教学模式，带你从了解到熟悉，到掌握，到精通，最后到深谙；（3）学习内容：课程遵循前沿性，以当前最新企业主流的MySQL8.0版本为内容。以“实用”为课程最高宗旨，带你搞定工作中SQL优化 痛点问题，最终的目的是：助力个人能力的快速提升，最终服务工作所需，助你占据不可替代的一席之地', 'upload/b6c620ac-7c71-48aa-9e4d-aa30ddf4a859.png', 1, 'https://coding.imooc.com/class/332.html#Anchor', 'aaaa', 1, 10);
INSERT INTO `tab_course` VALUES (33, '全面系统Python3.8入门+进阶', '王老师', '无论是大数据、人工智能还是机器学习，Python都是最热门的首选语言 ，这次课程，就将带你从基础入门Python3，掌握Python3.x 版本语法，并结合讲师实际工作经验讲解Python使用技巧以及数据结构等相关知识，并为你精心配套了练习题目及实战案例。', 'upload/default.png', 0, 'https://coding.imooc.com/class/chapter/136.html#Anchor', 'ahdjkd', 2, 1);

-- ----------------------------
-- Table structure for tab_favorite
-- ----------------------------
DROP TABLE IF EXISTS `tab_favorite`;
CREATE TABLE `tab_favorite`  (
  `uid` int(11) NULL DEFAULT NULL,
  `csId` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_favorite
-- ----------------------------
INSERT INTO `tab_favorite` VALUES (1, 1);
INSERT INTO `tab_favorite` VALUES (1, 3);
INSERT INTO `tab_favorite` VALUES (1, 4);
INSERT INTO `tab_favorite` VALUES (1, 5);
INSERT INTO `tab_favorite` VALUES (1, 9);
INSERT INTO `tab_favorite` VALUES (10, 9);
INSERT INTO `tab_favorite` VALUES (1, 10);
INSERT INTO `tab_favorite` VALUES (1, 12);
INSERT INTO `tab_favorite` VALUES (1, 15);
INSERT INTO `tab_favorite` VALUES (10, 17);
INSERT INTO `tab_favorite` VALUES (1, 24);
INSERT INTO `tab_favorite` VALUES (10, 22);
INSERT INTO `tab_favorite` VALUES (10, 5);
INSERT INTO `tab_favorite` VALUES (10, 15);
INSERT INTO `tab_favorite` VALUES (10, 16);

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `email` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `code` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tab_user
-- ----------------------------
INSERT INTO `tab_user` VALUES (1, 'root', 'root', '123456@qq.com', 'Y', NULL);
INSERT INTO `tab_user` VALUES (2, 'jolly', '123', NULL, NULL, NULL);
INSERT INTO `tab_user` VALUES (3, 'joicy', '123', NULL, NULL, NULL);
INSERT INTO `tab_user` VALUES (7, 'chenming', '123456', 'mt807301586@163.com', 'Y', '44d5af56462b4095b3981d427d216180');
INSERT INTO `tab_user` VALUES (10, 'huang', '1234', 'huang@qq.com', 'Y', 'e89fff1920304811b7d137f208bb2598');
INSERT INTO `tab_user` VALUES (11, 'datou', 'datou', 'datou@qq.com', 'N', '3aaf8bce6eaa44508c136f6a70a5e19f');
INSERT INTO `tab_user` VALUES (13, 'roroldo', '123456', 'mt807301586@163.com', 'Y', '1a1c6f4b6e164a65a651b2dc176d0173');
INSERT INTO `tab_user` VALUES (16, 'huangyijun', '1234', 'mt807301586@163.com', 'Y', '06e833f0777b4fa7884d2b934ea848ba');
INSERT INTO `tab_user` VALUES (17, 'dddddddddd', 'dddd', 'mt807301586@163.com', 'Y', '02fdfadc32b24327a18789b718916c64');

SET FOREIGN_KEY_CHECKS = 1;
