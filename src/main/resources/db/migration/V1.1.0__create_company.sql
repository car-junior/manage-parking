CREATE SEQUENCE IF NOT EXISTS dbo.company_sequence START 1 INCREMENT 1;

CREATE TABLE dbo.company
(
    id                          INT8         NOT NULL DEFAULT NEXTVAL('dbo.company_sequence'),
    name                        VARCHAR(255) NOT NULL,
    cnpj                        VARCHAR(14)  NOT NULL,
    address                     VARCHAR(255) NOT NULL,
    phone_number                VARCHAR(11)  NOT NULL,
    number_spaces_motorcycles   INT4         NOT NULL,
    occupied_spaces_motorcycles INT4         NOT NULL DEFAULT (0),
    number_spaces_cars          INT4         NOT NULL,
    occupied_spaces_cars        INT4         NOT NULL DEFAULT (0),
    PRIMARY KEY (id)
);