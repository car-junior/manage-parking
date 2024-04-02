CREATE SEQUENCE IF NOT EXISTS dbo.user_sequence START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS dbo.user
(
    id       INT8         NOT NULL DEFAULT NEXTVAL('dbo.user_sequence'),
    name     VARCHAR(255) NOT NULL,
    surname  VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status   VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);