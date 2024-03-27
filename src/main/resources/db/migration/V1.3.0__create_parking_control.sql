CREATE SEQUENCE IF NOT EXISTS dbo.parking_control_sequence START 1 INCREMENT 1;

-- status = ESTACIONADO, RESERVADO, FINALIZADO

CREATE TABLE dbo.parking_control
(
    id             INT8        NOT NULL DEFAULT NEXTVAL('dbo.parking_control_sequence'),
    parking_id     INT8        NOT NULL,
    vehicle_id     INT8        NOT NULL,
    status         VARCHAR(30) NOT NULL,
    entry_datetime TIMESTAMP,
    exit_datetime  TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (parking_id) REFERENCES dbo.parking,
    FOREIGN KEY (vehicle_id) REFERENCES dbo.vehicle
);