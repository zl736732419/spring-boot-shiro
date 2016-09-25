#权限信息
INSERT INTO t_permission VALUES ('1', 1, '用户管理', '0', '0/', 'user:view', 'menu', 'user/list');
INSERT INTO t_permission VALUES ('2', 1, '用户添加', '1', '0/1', 'user:add', 'button', 'user/add');
INSERT INTO t_permission VALUES ('3', 1, '用户删除', '1', '0/1', 'user:delete', 'button', 'user/delete');

#角色信息
INSERT INTO t_role VALUES ('1', 1, '管理员', 'admin');
INSERT INTO t_role VALUES ('2', 1, 'VIP会员', 'vip');
INSERT INTO t_role VALUES ('3', 1, '普通角色', 'user');

#角色权限关联
INSERT INTO t_role_permission VALUES ('1', '1');
INSERT INTO t_role_permission VALUES ('1', '2');
INSERT INTO t_role_permission VALUES ('3', '1');
INSERT INTO t_role_permission VALUES ('3', '2');

#用户信息
INSERT INTO t_user(id, NAME, username, PASSWORD, salt, state) VALUES ('1', '管理员', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '0');
INSERT INTO t_user(id, NAME, username, PASSWORD, salt, state) VALUES ('2', '普通用户', 'user', 'dfd516b9c98116a361018b4deb942325', 'helloworld', '0');


#角色用户
INSERT INTO t_user_role(user_id, role_id) VALUES ('1', '1');
INSERT INTO t_user_role(user_id, role_id) VALUES ('1', '2');
INSERT INTO t_user_role(user_id, role_id) VALUES ('2', '3');

