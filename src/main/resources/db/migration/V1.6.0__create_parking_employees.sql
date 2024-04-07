CREATE SEQUENCE IF NOT EXISTS dbo.parking_employees_sequence START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS dbo.parking_employees
(
    id         INT8 NOT NULL DEFAULT NEXTVAL('dbo.parking_employees_sequence'),
    parking_id INT8 NOT NULL,
    user_id    INT8 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (parking_id) REFERENCES dbo.parking,
    FOREIGN KEY (user_id) REFERENCES dbo.user
);