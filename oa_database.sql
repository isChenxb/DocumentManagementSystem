# Host: localhost  (Version: 5.6.27)
# Date: 2017-05-31 11:19:32
# Generator: MySQL-Front 5.3  (Build 4.214)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "approve_info"
#

DROP TABLE IF EXISTS `approve_info`;
CREATE TABLE `approve_info` (
  `approve_id` varchar(255) NOT NULL,
  `approve_date` datetime DEFAULT NULL,
  `approve_message` varchar(255) DEFAULT NULL,
  `approve_result` bit(1) NOT NULL,
  `approve_user` varchar(255) NOT NULL,
  `doc_id` varchar(255) NOT NULL,
  PRIMARY KEY (`approve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "approve_info"
#

INSERT INTO `approve_info` VALUES ('201704280001','2017-05-08 19:35:00','dd',b'1','admin','201704280001'),('201704280002','2017-05-08 20:24:00','支持',b'1','user2','201704280001'),('201704280003','2017-05-08 19:56:00','尽快办理',b'1','admin','201704280002'),('201704280004','2017-05-11 14:17:00','审批同意',b'0','user4','201704280002'),('201705020001','2017-05-08 20:01:00','无',b'0','admin','201705020001'),('201705020002',NULL,NULL,b'0','user2','201705020001'),('201705020003','2017-05-08 21:06:00','同意办理',b'0','admin','201705020002'),('201705020004',NULL,NULL,b'0','user4','201705020002'),('201705040001','2017-05-08 21:07:00','无',b'0','admin','201705040001'),('201705040002',NULL,NULL,b'0','user2','201705040001'),('201705080001','2017-05-08 21:10:00','同意办理',b'1','admin','201705080001'),('201705080002','2017-05-11 14:35:00','审批同意',b'1','user4','201705080001'),('201705080003','2017-05-11 15:13:00','审批同意，尽快办理',b'1','admin','201705080002'),('201705080004','2017-05-11 15:14:00','无',b'1','user4','201705080002'),('201705110001','2017-05-19 10:07:00','无',b'1','admin','201705110001'),('201705110002','2017-05-19 10:08:00','无',b'1','user2','201705110001'),('201705230001',NULL,NULL,b'0','admin','201705230001'),('201705230002',NULL,NULL,b'0','user4','201705230001'),('201705230003','2017-05-23 15:47:00','无',b'1','admin','201705230002'),('201705230004','2017-05-23 15:48:00','无',b'1','user1','201705230002'),('201705230005','2017-05-23 15:48:00','不同意',b'0','user2','201705230002'),('201705230006','2017-05-23 15:47:00','无',b'1','admin','201705230003'),('201705230007','2017-05-23 15:48:00','无',b'1','user1','201705230003'),('201705230008','2017-05-23 15:49:00','尽快办理',b'1','user2','201705230003'),('201705230009',NULL,NULL,b'0','admin','201705230004'),('201705230010',NULL,NULL,b'0','user1','201705230004'),('201705230011',NULL,NULL,b'0','user2','201705230004'),('201705240001','2017-05-24 21:32:00','无',b'1','admin','201705240001'),('201705240002','2017-05-24 21:33:00','wu ',b'1','user4','201705240001'),('201705240003',NULL,NULL,b'0','admin','201705240002'),('201705240004',NULL,NULL,b'0','user2','201705240002'),('201705250001',NULL,NULL,b'0','admin','201705250001'),('201705250002',NULL,NULL,b'0','user2','201705250001'),('201705250003',NULL,NULL,b'0','user2','201705250002'),('201705250004',NULL,NULL,b'0','user4','201705250002'),('201705250005',NULL,NULL,b'0','user4','201705250003'),('201705250006',NULL,NULL,b'0','user2','201705250003');

#
# Structure for table "dep_info"
#

DROP TABLE IF EXISTS `dep_info`;
CREATE TABLE `dep_info` (
  `dep_id` varchar(255) NOT NULL,
  `dep_des` varchar(255) DEFAULT NULL,
  `dep_name` varchar(255) NOT NULL,
  `incharge` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "dep_info"
#

INSERT INTO `dep_info` VALUES ('001',NULL,'网络部','admin'),('002',NULL,'校办公室','user2'),('003',NULL,'宣传部','user4');

#
# Structure for table "doc_info"
#

DROP TABLE IF EXISTS `doc_info`;
CREATE TABLE `doc_info` (
  `doc_id` varchar(255) NOT NULL,
  `approve_log` varchar(255) NOT NULL,
  `approve_status` bit(1) NOT NULL,
  `dep_id` varchar(255) NOT NULL,
  `doc_des` varchar(255) NOT NULL,
  `doc_name` varchar(255) NOT NULL,
  `doc_type` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `from_user` varchar(255) NOT NULL,
  `inprocess` varchar(255) NOT NULL,
  `log_time` datetime NOT NULL,
  `log_user` varchar(255) NOT NULL,
  `others` varchar(255) DEFAULT NULL,
  `process_log` varchar(255) NOT NULL,
  `process_status` bit(1) NOT NULL,
  `start_time` datetime NOT NULL,
  `target_dep` varchar(255) NOT NULL,
  `target_user` varchar(255) NOT NULL,
  `emergency` varchar(255) NOT NULL DEFAULT '',
  `status` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "doc_info"
#

INSERT INTO `doc_info` VALUES ('201704280001','201704280001,201704280002',b'1','001','无','公文测试1','内部公文','实验指导.doc','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201704280001实验指导.doc','陈序彪','user1','2017-04-28 17:27:00','admin','无','201704280001',b'1','2017-04-28 17:27:00','002','user1','普通','已承办'),('201704280002','201704280003,201704280004',b'0','001','无','测试2','内部公文','2017届毕业生生源核对填写说明.docx','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\2017042800022017届毕业生生源核对填写说明.docx','陈序彪','null','2017-04-28 17:32:00','admin','无','201704280002',b'0','2017-04-28 17:32:00','003','user3','紧急','审核失败'),('201705020001','201705020001,201705020002',b'0','001','无','公文测试3','内部公文','20170501-陈序彪-A03学习小结.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\20170502000120170501-陈序彪-A03学习小结.pdf','陈序彪','null','2017-05-02 20:22:00','admin','无','201705020001',b'0','2017-05-02 20:22:00','002','user1','紧急','审核失败'),('201705020002','201705020003,201705020004',b'0','001','无','测试4','内部公文','20170501-陈序彪-A03学习小结.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\20170502000220170501-陈序彪-A03学习小结.pdf','陈序彪','null','2017-05-02 20:23:00','admin','无','201705020002',b'0','2017-05-02 20:23:00','003','user3','紧急','审核失败'),('201705040001','201705040001,201705040002',b'0','001','无','公文测试5','内部公文','20170501-陈序彪-A03学习小结.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\20170504000120170501-陈序彪-A03学习小结.pdf','陈序彪','null','2017-05-04 16:47:00','admin','无','201705040001',b'0','2017-05-04 16:47:00','002','user1','普通','审核失败'),('201705080001','201705080001,201705080002',b'1','001','无','测试6','内部公文','正文.doc','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705080001正文.doc','陈序彪','null','2017-05-08 16:00:00','admin','无','201705080001',b'0','2017-05-08 16:00:00','003','user3','普通','办理退回'),('201705080002','201705080003,201705080004',b'1','001','无','公文测试8','内部公文','完成情况.docx','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705080002完成情况.docx','陈序彪','user2','2017-05-08 20:27:00','admin','无','201705080002,201705110002,201705110003',b'1','2017-05-08 20:27:00','003','user4','普通','已承办'),('201705110001','201705110001,201705110002',b'1','001','无','公文测试9','内部公文','完成情况.docx','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705110001完成情况.docx','陈序彪','user2','2017-05-11 15:12:00','admin','无','201705110001',b'0','2017-05-11 15:12:00','002','user2','紧急','待办理'),('201705230001','201705230001,201705230002',b'0','001','无','公文测试11','内部公文','任务书.doc','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705230001任务书.doc','陈序彪','admin','2017-05-23 15:16:00','admin','无','201705230001',b'0','2017-05-23 15:16:00','003','user4','紧急','待审核'),('201705230002','201705230003,201705230004,201705230005',b'0','003','无','公文测试12','内部公文','开题报告.docx','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705230002开题报告.docx','陈序彪','null','2017-05-23 15:35:00','user3','无','201705230002',b'0','2017-05-23 15:35:00','001','admin','紧急','审核失败'),('201705230003','201705230006,201705230007,201705230008',b'1','003','无','公文测试13','内部公文','成绩评定记录.doc','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705230003成绩评定记录.doc','陈序彪','user2','2017-05-23 15:39:00','user3','无','201705230003',b'1','2017-05-23 15:39:00','002','user2','紧急','已承办'),('201705230004','201705230009,201705230010,201705230011',b'0','003','无','公文测试14','内部公文','任务书.doc','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705230004任务书.doc','陈序彪','admin','2017-05-23 15:50:00','user3','无','201705230004',b'0','2017-05-23 15:50:00','002','user2','普通','待审核'),('201705240001','201705240001,201705240002',b'1','001','无','公文测试000','内部公文','欢迎使用马克飞象.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705240001欢迎使用马克飞象.pdf','陈序彪','user3','2017-05-24 21:30:00','admin','无','201705240001,201705240002',b'1','2017-05-24 21:30:00','003','user4','普通','已承办'),('201705240002','201705240003,201705240004',b'0','001','无','公文测试0000','内部公文','A03学习小结.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705240002A03学习小结.pdf','00','admin','2017-05-24 21:59:00','admin','无','201705240003',b'0','2017-05-24 21:59:00','002','user1','普通','待审核'),('201705250001','201705250001,201705250002',b'0','001','无','测试002','内部公文','A03学习小结.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705250001A03学习小结.pdf','陈序彪','admin','2017-05-25 13:39:00','admin','无','201705250001',b'0','2017-05-25 13:39:00','002','user1','紧急','待审核'),('201705250002','201705250003,201705250004',b'0','002','无','公文测试002','内部公文','欢迎使用马克飞象.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705250002欢迎使用马克飞象.pdf','cxb','user2','2017-05-25 14:44:00','user1','无','201705250002',b'0','2017-05-25 14:44:00','003','user3','紧急','待审核'),('201705250003','201705250005,201705250006',b'0','003','无','公文测试003','内部公文','A03学习小结.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\201705250003A03学习小结.pdf','陈序彪','user4','2017-05-25 14:51:00','user5','无','201705250003',b'0','2017-05-25 14:51:00','002','user1','紧急','待审核');

#
# Structure for table "outerapprove_info"
#

DROP TABLE IF EXISTS `outerapprove_info`;
CREATE TABLE `outerapprove_info` (
  `approve_id` varchar(255) NOT NULL,
  `approve_date` datetime DEFAULT NULL,
  `approve_message` varchar(255) DEFAULT NULL,
  `approve_result` bit(1) NOT NULL,
  `approve_user` varchar(255) NOT NULL,
  `doc_id` varchar(255) NOT NULL,
  PRIMARY KEY (`approve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "outerapprove_info"
#

INSERT INTO `outerapprove_info` VALUES ('201705230001',NULL,NULL,b'0','admin','【教】2017第01号'),('201705230002',NULL,NULL,b'0','user4','【教】2017第01号');

#
# Structure for table "outerdoc_info"
#

DROP TABLE IF EXISTS `outerdoc_info`;
CREATE TABLE `outerdoc_info` (
  `doc_id` varchar(255) NOT NULL,
  `approve_log` varchar(255) NOT NULL,
  `approve_status` bit(1) NOT NULL,
  `doc_des` varchar(255) NOT NULL,
  `doc_name` varchar(255) NOT NULL,
  `doc_type` varchar(255) NOT NULL,
  `emergency` varchar(255) NOT NULL,
  `file_name` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `from_dep` varchar(255) NOT NULL,
  `from_user` varchar(255) NOT NULL,
  `inprocess` varchar(255) NOT NULL,
  `log_time` datetime NOT NULL,
  `log_user` varchar(255) NOT NULL,
  `others` varchar(255) DEFAULT NULL,
  `process_log` varchar(255) NOT NULL,
  `process_status` bit(1) NOT NULL,
  `start_time` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `target_dep` varchar(255) NOT NULL,
  `target_user` varchar(255) NOT NULL,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "outerdoc_info"
#

INSERT INTO `outerdoc_info` VALUES ('【教】2017第01号','201705230001,201705230002',b'0','无','外部测试1','内部公文','普通','A03学习小结.pdf','E:\\workspace-JAVAEE\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\OA\\uploadFiles\\【教】2017第01号A03学习小结.pdf','教育局','陈序彪','admin','2017-05-23 10:41:00','admin','无','201705230001',b'0','2017-05-23 10:41:00','待审核','003','user4');

#
# Structure for table "outerprocess_info"
#

DROP TABLE IF EXISTS `outerprocess_info`;
CREATE TABLE `outerprocess_info` (
  `process_id` varchar(255) NOT NULL,
  `doc_id` varchar(255) NOT NULL,
  `entrust_user` varchar(255) DEFAULT NULL,
  `process_date` datetime DEFAULT NULL,
  `process_message` varchar(255) DEFAULT NULL,
  `process_result` bit(1) NOT NULL,
  `process_type` varchar(255) DEFAULT NULL,
  `process_user` varchar(255) NOT NULL,
  PRIMARY KEY (`process_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "outerprocess_info"
#

INSERT INTO `outerprocess_info` VALUES ('201705230001','【教】2017第01号',NULL,NULL,NULL,b'0',NULL,'user4');

#
# Structure for table "process"
#

DROP TABLE IF EXISTS `process`;
CREATE TABLE `process` (
  `process_id` varchar(255) NOT NULL,
  `doc_id` varchar(255) NOT NULL,
  `process_date` datetime DEFAULT NULL,
  `process_message` varchar(255) DEFAULT NULL,
  `process_result` bit(1) NOT NULL,
  `process_type` varchar(255) DEFAULT NULL,
  `process_user` varchar(255) NOT NULL,
  `entrust_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`process_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "process"
#

INSERT INTO `process` VALUES ('201704280001','201704280001','2017-05-11 13:08:00','同意办理',b'1','公文承办','user1',NULL),('201704280002','201704280002',NULL,NULL,b'0',NULL,'user3',NULL),('201705020001','201705020001',NULL,NULL,b'0',NULL,'user1',NULL),('201705020002','201705020002',NULL,NULL,b'0',NULL,'user3',NULL),('201705040001','201705040001',NULL,NULL,b'0',NULL,'user1',NULL),('201705080001','201705080001','2017-05-11 14:37:00','暂时不能提供办理',b'0','公文退办','user3',NULL),('201705080002','201705080002','2017-05-11 16:50:00','小陈负责',b'1','公文委办','user4','user3'),('201705110001','201705110001',NULL,NULL,b'0',NULL,'user2',NULL),('201705110002','201705080002','2017-05-11 16:55:00','无',b'1','公文委办','user3','user2'),('201705110003','201705080002','2017-05-11 16:59:00','尽快办理',b'1','公文承办','user2',NULL),('201705230001','201705230001',NULL,NULL,b'0',NULL,'user4',NULL),('201705230002','201705230002',NULL,NULL,b'0',NULL,'admin',NULL),('201705230003','201705230003','2017-05-23 15:50:00','尽快办理',b'1','公文承办','user2',NULL),('201705230004','201705230004',NULL,NULL,b'0',NULL,'user2',NULL),('201705240001','201705240001','2017-05-24 21:34:00','wu',b'1','公文委办','user4','user3'),('201705240002','201705240001','2017-05-24 21:35:00','尽快办理',b'1','公文承办','user3',NULL),('201705240003','201705240002',NULL,NULL,b'0',NULL,'user1',NULL),('201705250001','201705250001',NULL,NULL,b'0',NULL,'user1',NULL),('201705250002','201705250002',NULL,NULL,b'0',NULL,'user3',NULL),('201705250003','201705250003',NULL,NULL,b'0',NULL,'user1',NULL);

#
# Structure for table "user_info"
#

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `username` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `user_dep` varchar(255) NOT NULL,
  `user_identity` varchar(255) NOT NULL,
  `user_sex` varchar(255) DEFAULT NULL,
  `user_permission` int(11) NOT NULL,
  `approve_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_info"
#

INSERT INTO `user_info` VALUES ('admin',NULL,NULL,NULL,'系统管理员','123',NULL,'001','负责人',NULL,4,NULL),('null',NULL,NULL,NULL,'null','000',NULL,'000','null',NULL,0,NULL),('user1',NULL,NULL,NULL,'小张','123',NULL,'002','公文管理员',NULL,3,NULL),('user2',NULL,NULL,NULL,'老张','123',NULL,'002','负责人',NULL,4,NULL),('user3',NULL,NULL,NULL,'小陈','123',NULL,'003','普通职员',NULL,2,'admin,user1,user2'),('user4',NULL,NULL,NULL,'张三','123',NULL,'003','负责人',NULL,4,NULL),('user5',NULL,NULL,NULL,'李四','123',NULL,'003','无',NULL,4,NULL);
