CREATE TABLE IF NOT EXISTS dbo.user_roles
(
    user_id INT8        NOT NULL,
    role    VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES dbo.user
);