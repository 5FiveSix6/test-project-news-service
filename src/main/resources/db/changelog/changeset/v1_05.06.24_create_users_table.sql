--liquibase formatted sql

--changeset Yuriy N.:1
--comment create users table
CREATE TABLE t_users
(
    id            BIGSERIAL PRIMARY KEY,
    c_first_name  VARCHAR(32) NOT NULL CHECK ( LENGTH(TRIM(c_first_name)) > 1 ),
    c_last_name   VARCHAR(32) NOT NULL CHECK ( LENGTH(TRIM(c_last_name)) > 3 ),
    c_middle_name VARCHAR(32),
    c_role        VARCHAR(64)  NOT NULL,
    CONSTRAINT unique_full_name UNIQUE (c_first_name, c_last_name, c_middle_name)
);