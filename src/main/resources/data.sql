INSERT INTO public.users
(id, email, "password", created_at, updated_at)
VALUES
(1, 'abcde@gmail.com', 'qwerasdf', '2021-12-21 12:42:43.633', NULL),
(2, 'qwert@gmail.com', 'qwerasdf', '2021-12-21 13:42:43.633', NULL),
(3, 'asdfg@gmail.com', 'qwerasdf', '2021-12-21 14:42:43.633', NULL);

INSERT INTO public.posts
(author, title, "content", created_at, updated_at)
VALUES
('abcde@gmail.com', 'express server', 'express server ...', '2021-12-21 12:46:31.150', NULL),
('abcde@gmail.com', 'django server', 'django server ...', '2021-12-22 12:46:31.150', NULL),
('abcde@gmail.com', 'koa server', 'koa server ...', '2021-12-23 12:46:31.150', NULL),
('qwert@gmail.com', 'flask server', 'flask server ...', '2021-12-24 12:46:31.150', NULL),
('qwert@gmail.com', 'spring server', 'spring server ...', '2021-12-25 12:46:31.150', NULL),
('zxcvb@gmail.com', 'php server', 'php server ...', '2021-12-26 12:46:31.150', NULL),
('zxcvb@gmail.com', 'go server', 'go server ...', '2021-12-27 12:46:31.150', NULL);

-- seller
INSERT INTO public.sellers
(created_at, updated_at, business_number, email, seller_name, status)
VALUES('2023-06-24 16:43:22.514269', '2023-06-24 16:43:22.514477', 'b12345', 'abc@gmail.com', '김판매', 'ENABLED');

-- item
INSERT INTO public.items (created_at,updated_at,item_name,item_price,seller_id,status) VALUES
	 ('2023-06-26 00:31:25.753678','2023-06-26 00:31:25.753733','티셔츠',30000,1,'PREPARING_FOR_SALE');

INSERT INTO public.item_option_groups (created_at,updated_at,item_option_group_name,"ordering",item_id) VALUES
	 ('2023-06-26 00:31:25.769382','2023-06-26 00:31:25.769406','사이즈',1,1),
	 ('2023-06-26 00:31:25.773111','2023-06-26 00:31:25.773148','컬러',2,1);

INSERT INTO public.item_options (created_at,updated_at,item_option_name,item_option_price,"ordering",item_option_group_id) VALUES
	 ('2023-06-26 00:31:25.781602','2023-06-26 00:31:25.781629','SMALL',0,1,1),
	 ('2023-06-26 00:31:25.785169','2023-06-26 00:31:25.785193','MEDIUM',0,2,1),
	 ('2023-06-26 00:31:25.787695','2023-06-26 00:31:25.78772','LARGE',0,3,1),
	 ('2023-06-26 00:31:25.790325','2023-06-26 00:31:25.790363','RED',0,1,2),
	 ('2023-06-26 00:31:25.794127','2023-06-26 00:31:25.794161','GOLD',1000,2,2);
