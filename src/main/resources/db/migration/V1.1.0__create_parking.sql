CREATE SEQUENCE IF NOT EXISTS dbo.parking_sequence START 1 INCREMENT 1;

CREATE TABLE IF NOT EXISTS dbo.parking
(
    id                          INT8         NOT NULL DEFAULT NEXTVAL('dbo.parking_sequence'),
    name                        VARCHAR(255) NOT NULL,
    cnpj                        VARCHAR(14)  NOT NULL UNIQUE,
    address                     VARCHAR(255) NOT NULL,
    phone_number                VARCHAR(11)  NOT NULL,
    number_spaces_motorcycles   INT4         NOT NULL,
    number_spaces_cars          INT4         NOT NULL,
    occupied_spaces_motorcycles INT4,
    occupied_spaces_cars        INT4,
    PRIMARY KEY (id)
);