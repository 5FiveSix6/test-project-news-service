--liquibase formatted sql

--changeset Yuriy N.:1
--comment insert data into users table
INSERT INTO t_users(c_first_name, c_last_name, c_middle_name, c_role)
VALUES ('Анатолий', 'Решетников', 'Кириллович', 'READER'),
       ('Виктор', 'Пелевин', 'Олегович', 'OPERATOR'),
       ('Никита', 'Курский', 'Алексеевич', 'READER'),
       ('Стивен', 'Кинг', 'Эдвин', 'OPERATOR'),
       ('Александр', 'Притоков', 'Александрович', 'READER'),
       ('Юрий', 'Другов', 'Дмитриевич', 'MODERATOR'),
       ('Кирилл', 'Зубин', 'Олегович', 'MODERATOR');

--changeset Yuriy N.:2
--comment insert data into news table
INSERT INTO t_news(author_id, moderator_id, c_title, c_text, c_status)
VALUES (2, 6, 'Новость №1', 'Текст новости...', 'PUBLISHED'),
       (2, 7, 'Новость №2', 'Текст новости...', 'MODERATION'),
       (4, 6, 'Новость №3', 'Текст новости...', 'READY_TO_PUBLICATION'),
       (4, 7, 'Новость №4', 'Текст новости...', 'PUBLISHED'),
       (2, 6, 'Новость №5', 'Текст новости...', 'MODERATION'),
       (4, null, 'Новость №6', 'Текст новости...', 'DRAFT'),
       (4, null, 'Новость №7', 'Текст новости...', 'DRAFT'),
       (2, null, 'Новость №8', 'Текст новости...', 'DRAFT');