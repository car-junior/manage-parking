CREATE SEQUENCE IF NOT EXISTS dbo.user_roles_sequence START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS dbo.user_roles
(
    id      INT8 NOT NULL DEFAULT NEXTVAL('dbo.user_roles_sequence'),
    user_id INT8 NOT NULL,
    role_id INT8 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES dbo.user,
    FOREIGN KEY (role_id) REFERENCES dbo.role
);