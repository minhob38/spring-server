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


