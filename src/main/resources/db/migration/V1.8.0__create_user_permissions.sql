CREATE TABLE IF NOT EXISTS dbo.user_permissions
(
    user_id    INT8        NOT NULL,
    permission VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES dbo.user
);