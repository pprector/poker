-- ==========================================
-- 门店模块 Mock 数据
-- ==========================================
-- 导入方式: docker exec -i pokergo-mysql mysql -uroot -p123456 --default-character-set=utf8mb4 pokergo < insert-club-mock-data.sql

-- 先清空已有数据
DELETE FROM club_business_config;
DELETE FROM club WHERE id > 10;

-- ==================== 门店表 club ====================
-- 已存在: id=1 老友精酿扑克吧（需先修复编码）

INSERT IGNORE INTO club (id, name, logo_url, address, phone, open_hours, photos, intro_text, theme_color, theme_template, owner_id, status, creator, create_time, updater, update_time, deleted, tenant_id) VALUES

(2, '银河扑克俱乐部',
'https://coresg-normal.trae.ai/api/ide/v1/text_to_image?prompt=a+poker+club+logo+with+a+galaxy+theme+night+purple+and+gold+minimalist&image_size=square',
'海淀区中关村大街48号银谷大厦3F',
'13912345678', '周一至周日 14:00-04:00', '[]',
'京城最专业的德扑竞技场，每周举办MTT赛事，配备顶级牌桌和专业荷官',
'#1a1a2e', 'night_purple', 1, 1,
'admin', '2025-06-01 10:00:00', 'admin', '2025-06-01 10:00:00', 0, 1),

(3, '精酿暖棕·棋牌社交馆',
'https://coresg-normal.trae.ai/api/ide/v1/text_to_image?prompt=a+warm+brown+wooden+pub+logo+with+beer+mugs+and+poker+chips+rustic+style&image_size=square',
'朝阳区望京SOHO T3-B1-08',
'13712345678', '周一至周五 17:00-02:00, 周末 14:00-04:00', '[]',
'精酿啤酒+德州扑克，打造最chill的社交棋牌空间，每周三会员之夜',
'#8B4513', 'warm_brown', 1, 1,
'admin', '2025-06-05 14:30:00', 'admin', '2025-06-05 14:30:00', 0, 1),

(4, '霓虹赛博·电竞棋牌',
'https://coresg-normal.trae.ai/api/ide/v1/text_to_image?prompt=a+neon+cyberpunk+style+gaming+poker+club+logo+with+neon+lights+and+cards&image_size=square',
'朝阳区国贸CBD正大中心B2层',
'13612345678', '24小时营业', '[]',
'赛博朋克风格棋牌空间，融合电竞与德扑，设有多媒体直播桌和VR体验区',
'#00d4ff', 'neon_cyber', 1, 1,
'admin', '2025-06-10 09:00:00', 'admin', '2025-06-10 09:00:00', 0, 1),

(5, '南山弈趣俱乐部',
'https://coresg-normal.trae.ai/api/ide/v1/text_to_image?prompt=a+nature+themed+poker+club+logo+with+green+mountains+and+cabin+style&image_size=square',
'南山区科技园南区A7栋2楼',
'13512345678', '周一至周日 13:00-01:00', '[]',
'南山科技园程序员的德州乐园，每周六免费教学局，新手友好',
'#2ecc71', 'night_purple', 1, 0,
'admin', '2025-06-15 16:00:00', 'admin', '2025-06-15 16:00:00', 0, 1),

(6, '浦江德扑精英汇',
'https://coresg-normal.trae.ai/api/ide/v1/text_to_image?prompt=a+sophisticated+poker+club+logo+with+gold+and+navy+bund+shanghai+style&image_size=square',
'浦东新区陆家嘴金融中心B1层',
'13412345678', '周一至周日 18:00-03:00', '[]',
'陆家嘴金融精英聚集地，高端私密德扑会所，仅限会员及邀约制',
'#1a237e', 'warm_brown', 1, 1,
'admin', '2025-06-20 11:00:00', 'admin', '2025-06-20 11:00:00', 0, 1),

(7, '蓉城雀友扑克坊',
'https://coresg-normal.trae.ai/api/ide/v1/text_to_image?prompt=a+cute+panda+playing+poker+club+logo+chengdu+style+with+bamboo&image_size=square',
'武侯区科华北路101号附3号',
'13312345678', '周一至周日 14:00-02:00', '[]',
'成都最好耍的德州扑克俱乐部，麻辣火锅配德扑，巴适得很',
'#e91e63', 'neon_cyber', 1, 1,
'admin', '2025-06-25 15:00:00', 'admin', '2025-06-25 15:00:00', 0, 1),

(8, '西湖畔·棋牌雅集',
'https://coresg-normal.trae.ai/api/ide/v1/text_to_image?prompt=elegant+chinese+painting+style+poker+club+logo+with+west+lake+and+lotus&image_size=square',
'西湖区文三路478号华星科技大厦',
'13212345678', '周一至周五 16:00-01:00, 周末 13:00-03:00', '[]',
'西湖边的雅致棋牌空间，德扑+国粹麻将双模式，定期举办慈善赛事',
'#6a1b9a', 'night_purple', 1, 0,
'admin', '2025-07-01 10:30:00', 'admin', '2025-07-01 10:30:00', 0, 1);

-- ==================== 经营配置表 club_business_config ====================

INSERT IGNORE INTO club_business_config (id, club_id, template_name,
  consume_points_enabled, consume_points_ratio, consume_points_threshold, consume_points_fixed,
  event_points_enabled, event_points_template,
  first_visit_bonus, daily_checkin_points,
  recharge_enabled, recharge_mode, recharge_discount_rate,
  points_usage, exchange_rate, deduct_rate, entry_points_cost,
  creator, create_time, updater, update_time, deleted, tenant_id) VALUES

(1, 1, 'recharge_boost',
 1, 100, 0.00, 50, 1, 'standard', 200, 20,
 1, 'FULL', 120,
 '{"entryTicket":true,"merchandise":true,"gameFee":true}', 1000, 50, 500,
 'admin', '2025-06-01 10:00:00', 'admin', '2025-06-01 10:00:00', 0, 1),

(2, 2, 'social_light',
 1, 200, 0.00, 100, 1, 'standard', 500, 30,
 0, 'FULL', 100,
 '{"entryTicket":true,"merchandise":true}', 2000, 60, 1000,
 'admin', '2025-06-01 10:00:00', 'admin', '2025-06-01 10:00:00', 0, 1),

(3, 3, 'social_light',
 1, 150, 50.00, 30, 0, 'standard', 100, 10,
 0, 'FULL', 100,
 '{"entryTicket":true,"merchandise":true,"gameFee":true}', 1000, 30, 300,
 'admin', '2025-06-05 14:30:00', 'admin', '2025-06-05 14:30:00', 0, 1),

(4, 4, 'recharge_boost',
 1, 300, 0.00, 200, 1, 'standard', 1000, 50,
 1, 'FULL', 150,
 '{"entryTicket":true,"merchandise":true,"gameFee":true,"vip":true}', 1500, 70, 2000,
 'admin', '2025-06-10 09:00:00', 'admin', '2025-06-10 09:00:00', 0, 1),

(5, 5, 'social_light',
 0, 100, 0.00, 0, 0, 'standard', 50, 5,
 0, 'FULL', 100,
 '{"entryTicket":true}', 500, 20, 100,
 'admin', '2025-06-15 16:00:00', 'admin', '2025-06-15 16:00:00', 0, 1),

(6, 6, 'recharge_boost',
 1, 500, 100.00, 300, 1, 'standard', 2000, 100,
 1, 'FULL', 200,
 '{"entryTicket":true,"merchandise":true,"gameFee":true,"vip":true,"coach":true}', 3000, 80, 5000,
 'admin', '2025-06-20 11:00:00', 'admin', '2025-06-20 11:00:00', 0, 1),

(7, 7, 'social_light',
 1, 80, 30.00, 20, 1, 'standard', 100, 10,
 0, 'FULL', 100,
 '{"entryTicket":true,"merchandise":true}', 800, 30, 200,
 'admin', '2025-06-25 15:00:00', 'admin', '2025-06-25 15:00:00', 0, 1),

(8, 8, 'social_light',
 1, 120, 0.00, 60, 0, 'standard', 300, 20,
 0, 'FULL', 100,
 '{"entryTicket":true,"merchandise":true,"gameFee":true}', 1200, 40, 500,
 'admin', '2025-07-01 10:30:00', 'admin', '2025-07-01 10:30:00', 0, 1);
