drop table if exists t_user;
create table t_user (
  user_id  varchar(50),
  username varchar(50),
  password varchar(50)
);

insert into t_user values ('1', 'admin', 'admin');
insert into t_user values ('2', 'yizhiwazi', '123456');