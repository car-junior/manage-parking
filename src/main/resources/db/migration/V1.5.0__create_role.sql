CREATE SEQUENCE IF NOT EXISTS dbo.role_sequence START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS dbo.role
(
    id         INT8         NOT NULL DEFAULT NEXTVAL('dbo.role_sequence'),
    name       VARCHAR(255) NOT NULL UNIQUE,
    type       VARCHAR(50)  NOT NULL,
    parking_id INT8         NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (parking_id) REFERENCES dbo.parking
);