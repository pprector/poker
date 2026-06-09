-- 门店运营 一级菜单
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, component, component_name, icon, status) VALUES (7000, '门店运营', '', 1, 35, 0, '', '', 'ep:shop', 0);

-- 门店管理 二级菜单
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, component, component_name, icon, status) VALUES (7010, '门店管理', '', 2, 1, 7000, 'poker/club/index', 'PokerClub', 'ep:office-building', 0);

-- 门店管理 CRUD 按钮权限
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7011, '门店查询', 'club:query', 3, 1, 7010, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7012, '门店创建', 'club:create', 3, 2, 7010, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7013, '门店修改', 'club:update', 3, 3, 7010, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7014, '门店删除', 'club:delete', 3, 4, 7010, 0);

-- 经营配置 二级菜单
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, component, component_name, icon, status) VALUES (7020, '经营配置', '', 2, 2, 7000, 'poker/club-biz-config/index', 'PokerClubBizConfig', 'ep:setting', 0);

-- 经营配置 CRUD 按钮权限
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7021, '经营配置查询', 'club-biz-config:query', 3, 1, 7020, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7022, '经营配置创建', 'club-biz-config:create', 3, 2, 7020, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7023, '经营配置修改', 'club-biz-config:update', 3, 3, 7020, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7024, '经营配置删除', 'club-biz-config:delete', 3, 4, 7020, 0);

-- 俱乐部用户 二级菜单
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, component, component_name, icon, status) VALUES (7030, '俱乐部用户', '', 2, 3, 7000, 'poker/club-user/index', 'PokerClubUser', 'ep:user', 0);

-- 俱乐部用户 CRUD 按钮权限
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7031, '俱乐部用户查询', 'club-user:query', 3, 1, 7030, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7032, '俱乐部用户创建', 'club-user:create', 3, 2, 7030, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7033, '俱乐部用户修改', 'club-user:update', 3, 3, 7030, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7034, '俱乐部用户删除', 'club-user:delete', 3, 4, 7030, 0);

-- 赛事活动 二级菜单
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, component, component_name, icon, status) VALUES (7040, '赛事活动', '', 2, 4, 7000, 'poker/event/index', 'PokerEvent', 'ep:trophy', 0);

-- 赛事活动 CRUD 按钮权限
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7041, '赛事活动查询', 'event:query', 3, 1, 7040, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7042, '赛事活动创建', 'event:create', 3, 2, 7040, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7043, '赛事活动修改', 'event:update', 3, 3, 7040, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7044, '赛事活动删除', 'event:delete', 3, 4, 7040, 0);

-- 积分流水 二级菜单
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, component, component_name, icon, status) VALUES (7050, '积分流水', '', 2, 5, 7000, 'poker/points-log/index', 'PokerPointsLog', 'ep:coin', 0);

-- 积分流水 CRUD 按钮权限
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7051, '积分流水查询', 'points-log:query', 3, 1, 7050, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7052, '积分流水创建', 'points-log:create', 3, 2, 7050, 0);

-- 储值记录 二级菜单
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, component, component_name, icon, status) VALUES (7060, '储值记录', '', 2, 6, 7000, 'poker/recharge-record/index', 'PokerRechargeRecord', 'ep:coin', 0);

-- 储值记录 CRUD 按钮权限
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7061, '储值记录查询', 'recharge:query', 3, 1, 7060, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7062, '储值记录创建', 'recharge:create', 3, 2, 7060, 0);
INSERT IGNORE INTO system_menu (id, name, permission, type, sort, parent_id, status) VALUES (7063, '储值记录确认', 'recharge:update', 3, 3, 7060, 0);
