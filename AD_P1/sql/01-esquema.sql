/*Creación de las tablas*/
CREATE TABLE clientes (
    id INTEGER PRIMARY KEY CHECK ( id > 0 ), --No puede ser nulo ni menor o igual a 0
    nombre TEXT NOT NULL CHECK ( length(trim(nombre)) > 0 ),--No puede ser nulo ni menor o igual a 0 y borra los espacios del texto
    tlfno TEXT NOT NULL CHECK ( length(trim(tlfno)) > 0 ),--No puede ser nulo ni menor o igual a 0 y borra los espacios del texto
    matricula TEXT NOT NULL CHECK ( length(trim(matricula)) > 0 )--No puede ser nulo ni menor o igual a 0 y borra los espacios del texto
) STRICT;

CREATE TABLE pagos_repostaje (
    id INTEGER PRIMARY KEY CHECK ( id > 0 ), ----No puede ser nulo ni menor o igual a 0
    cliente_id INTEGER NOT NULL,
    fecha TEXT NOT NULL CHECK ( length(fecha) = 10 AND fecha GLOB '[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]'),--Para el formato de la fecha se usa GLOB
    importe REAL NOT NULL CHECK ( importe > 0 ), --No puede ser nulo ni menor o igual a 0
    litros REAL NOT NULL CHECK ( litros > 0 ), --No puede ser nulo ni menor o igual a 0
    combustible TEXT NOT NULL CHECK ( length(trim(combustible)) > 0 ),--No puede ser nulo ni menor o igual a 0 y borra los espacios del texto

    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
) STRICT;