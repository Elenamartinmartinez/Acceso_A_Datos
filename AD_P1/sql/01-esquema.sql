/*Creación de las tablas*/
PRAGMA FOREIGN_KEYS = ON;

CREATE TABLE CLIENTES (
    ID INTEGER PRIMARY KEY CHECK ( ID > 0 ), --No puede ser nulo ni menor o igual a 0
    NOMBRE TEXT NOT NULL CHECK ( LENGTH(TRIM(NOMBRE)) > 0 ),--No puede ser nulo ni menor o igual a 0 y borra los espacios del texto
    TLFNO TEXT NOT NULL CHECK ( LENGTH(TRIM(TLFNO)) > 0 ),--No puede ser nulo ni menor o igual a 0 y borra los espacios del texto
    MATRICULA TEXT NOT NULL CHECK ( LENGTH(TRIM(MATRICULA)) > 0 )--No puede ser nulo ni menor o igual a 0 y borra los espacios del texto
) STRICT;

CREATE TABLE PAGOS_REPOSTAJE (
    ID INTEGER PRIMARY KEY CHECK ( ID > 0 ), ----No puede ser nulo ni menor o igual a 0
    CLIENTE_ID INTEGER NOT NULL,
    FECHA TEXT NOT NULL CHECK ( LENGTH(FECHA) = 10 AND FECHA GLOB '[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]'),--Para el formato de la fecha se usa GLOB
    IMPORTE REAL NOT NULL CHECK ( IMPORTE > 0 ), --No puede ser nulo ni menor o igual a 0
    LITROS REAL NOT NULL CHECK ( LITROS > 0 ), --No puede ser nulo ni menor o igual a 0
    COMBUSTIBLE TEXT NOT NULL CHECK ( LENGTH(TRIM(COMBUSTIBLE)) > 0 ),--No puede ser nulo ni menor o igual a 0 y borra los espacios del texto

    FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTES(ID)
) STRICT;