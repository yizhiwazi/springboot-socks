USE `SOCKS`;
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` varchar(50) ,
  `USERNAME` varchar(50) ,
  `PASSWORD` varchar(50) ,
	`PHONE_NUM` varchar(15)
) ;

INSERT INTO `t_user` VALUES ('1', 'admin', 'admin','15011791234');
INSERT INTO `t_user` VALUES ('2', 'roots', 'roots','18812342017');