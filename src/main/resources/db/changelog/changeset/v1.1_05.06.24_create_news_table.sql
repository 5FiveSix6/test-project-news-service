--liquibase formatted sql

--changeset Yuriy N.:1
--comment create news table
CREATE TABLE t_news
(
    id           BIGSERIAL PRIMARY KEY,
    author_id    BIGINT       NOT NULL REFERENCES t_users (id),
    moderator_id BIGINT REFERENCES t_users (id),
    c_title      VARCHAR(255) NOT NULL CHECK ( LENGTH(TRIM(c_title)) > 3 ),
    c_text       TEXT         NOT NULL CHECK ( LENGTH(TRIM(c_text)) > 5 ),
    c_status     VARCHAR(64)  NOT NULL,
    CONSTRAINT chk_operator_not_moderator CHECK ( author_id != moderator_id )
);