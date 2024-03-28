CREATE SEQUENCE IF NOT EXISTS dbo.vehicle_sequence START 1 INCREMENT 1;

-- Placa/Plate Padrão: 3 letras e 4 Números
-- exemplo: QMA 0001

-- Placa/Plate Mercosul: 3 letras e 3 Numeros 1 Letra
-- exemplo: AAA 1B11

CREATE TABLE dbo.vehicle
(
    id    INT8         NOT NULL DEFAULT NEXTVAL('dbo.vehicle_sequence'),
    mark  VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    color VARCHAR(255) NOT NULL,
    plate VARCHAR(11)  NOT NULL UNIQUE,
    type  VARCHAR(30)  NOT NULL,
    PRIMARY KEY (id)
);