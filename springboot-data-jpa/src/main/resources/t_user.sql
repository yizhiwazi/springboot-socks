
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` varchar(50) ,
  `username` varchar(50) ,
  `PASSWORD` varchar(50) 
) ;

INSERT INTO `t_user` VALUES ('1', 'admin', 'admin');
INSERT INTO `t_user` VALUES ('2', 'hikari', 'buzhidao');