### ***# Contexto - AD\_1 / RA2 JDBC***



###### ***## Objetivo***

Este fichero sirve como contexto de trabajo para el proyecto `RA2-JDBC`. Su finalidad es dejar instrucciones claras sobre cómo está organizado el proyecto y cómo trabajar en él.



###### ***## Alcance***

\- \*\*Solo trabajaremos con JDBC\*\*.

\- No ampliar el alcance a otras tecnologías de acceso a datos salvo que se pida explícitamente.

\- El dominio de ejemplo es el \*\*Jardín Botánico\*\* (scripts SQL en `sql/sqlite` y `sql/mysql`).



###### ***## Estructura relevante***

\- **`build.gradle.kts`**: configuración Gradle, Java 21, dependencias JDBC y tareas de ejecución.



\- **`src/main/java/es/iesluisbraille/accesodatos/jardin/bloque02/`**: ejemplos de conexión JDBC.

&#x20; - `PrimeraConexionSqlite.java`

&#x20; - `PrimeraConexionMysql.java`

&#x20; - `MetadatosSqlite.java`

&#x20; - `ErrorDeConexion.java`



\- **`src/main/java/es/iesluisbraille/accesodatos/jardin/bloque03/`**: ejemplos de sentencias JDBC.

&#x20; - `CrearTabla.java`

&#x20; - `VariasSentencias.java`

&#x20; - `ErrorSql.java`



\- **`src/test/java/.../bloque02/ConexionSqliteTest.java`**: prueba JUnit con SQLite temporal.



\- **`sql/sqlite/01-esquema.sql`, `sql/sqlite/02-datos.sql`**: esquema y datos SQLite.



\- **`sql/mysql/01-esquema.sql`, `sql/mysql/02-datos.sql`**: esquema y datos MySQL.



\- **`tools/`**: utilidades Python de apoyo (generar/validar/preparar datos SQLite).



###### ***## Estructura de carpetas en AD\_P1 (`src`)***

Referencia: `2DAM/Acceso\_A\_Datos/Acceso\_A\_Datos/AD\_P1/src`



```text

src/

├── app/

│   ├── DemoDominio.java

│   └── Main.java

├── dominio/

│   ├── catalogo/

│   │   ├── AcuerdoSuministro.java

│   │   ├── Producto.java

│   │   ├── ProductoEnvasado.java

│   │   ├── ProductoGranel.java

│   │   └── Proveedor.java

│   ├── clientes/

│   │   ├── Campana.java

│   │   ├── InscripcionCampana.java

│   │   └── TarjetaCliente.java

│   ├── estaciones/

│   │   ├── EstacionServicio.java

│   │   ├── FichaFacturacion.java

│   │   ├── Incidencia.java

│   │   └── Surtidor.java

│   ├── personal/

│   │   ├── AsignacionFuncion.java

│   │   ├── Empleado.java

│   │   └── TipoFuncion.java

│   └── ventas/

│       ├── Devolucion.java

│       ├── LineaVenta.java

│       ├── PagoVenta.java

│       └── Venta.java

├── modelo/

│   ├── Cliente.java

│   └── Pagos\_Repostajes.java

├── persistencia/

│   ├── Almacenamiento.java

│   ├── bd/

│   │   ├── ConexionBD.java

│   │   ├── CrearBBDD.java

│   │   └── InsertsBBDDJava.java

│   ├── csv/

│   │   └── AlmacenamientoEnCSV.java

│   └── json/

│       ├── AlmacenamientoEnJSON.java

│       └── MigraCSVToJSON.java

├── servicio/

│   └── Gasolinera.java

└── ui/

&#x20;   ├── Entrada.java

&#x20;   └── Menu.java

```



> ***Nota***: aunque AD\_P1 contiene otras capas/persistencias, el trabajo solicitado en este contexto se centra en \*\*JDBC\*\*.



###### ***## Requisitos y ejecución***

\- **JDK 21** (configurado en `build.gradle.kts` mediante toolchain).



\- **Gradle Wrapper** incluido (`gradlew`, `gradlew.bat`).



\- **Dependencias JDBC**:

&#x20; - `org.xerial:sqlite-jdbc`

&#x20; - `com.mysql:mysql-connector-j`



\- **Tests**: JUnit Jupiter.



###### ***### Tareas Gradle disponibles (bloque 02)***

Definidas en `build.gradle.kts`:

\- **`runSqlite`** → `es.iesluisbraille.accesodatos.jardin.bloque02.PrimeraConexionSqlite`



\- **`runSqliteMetadata`** → `es.iesluisbraille.accesodatos.jardin.bloque02.MetadatosSqlite`



\- **`runMysql`** → `es.iesluisbraille.accesodatos.jardin.bloque02.PrimeraConexionMysql`



\- **`runErrorConexion`** → `es.iesluisbraille.accesodatos.jardin.bloque02.ErrorDeConexion`



*Ejemplo* (Windows PowerShell):

```powershell

.\\gradlew.bat runSqliteMetadata

.\\gradlew.bat test

```



###### ***## Configuración de conexiones JDBC***

\- **SQLite (fichero)**: patrón usado en ejemplos/pruebas `jdbc:sqlite:<ruta>`; por defecto se usa `data/jardin-botanico.db` en varios ejemplos.



\- **SQLite en memoria**: `jdbc:sqlite::memory:` para demostraciones/tests temporales; la base vive mientras la conexión esté abierta.



\- **MySQL**: se obtiene por variables de entorno en `PrimeraConexionMysql`:

&#x20; - `JARDIN\_MYSQL\_URL`

&#x20; - `JARDIN\_MYSQL\_USER`

&#x20; - `JARDIN\_MYSQL\_PASSWORD` (opcional, vacío si no se define)



\- No 'hardcodear' credenciales en el código fuente.



###### ***## Patrones JDBC a seguir***

\- Usar *`DriverManager.getConnection(...)`* para obtener la conexión.



\- Usar **try-with-resources** para *`Connection`*, *`Statement`/`PreparedStatement`* y *`ResultSet`*.



\- Gestionar errores con *`SQLException`*, mostrando información útil (*`getMessage()`*, *`getSQLState()`* cuando proceda).



\- Usar *`DatabaseMetaData`* y *`conexion.isValid(...)`*/*`isClosed()`* para inspección de conexión cuando sea útil.



\- Diferenciar usos de *`Statement`*:

&#x20; - **`execute(...)`**: cualquier SQL; devuelve si el primer resultado es `ResultSet`.

&#x20; - **`executeUpdate(...)`**: DDL/DML; devuelve filas afectadas (en SQLite, DDL suele informar 0).

&#x20; - **`executeQuery(...)`**: consultas `SELECT`; devuelve `ResultSet`.



\- En **SQLite**, activar claves foráneas por conexión cuando aplique: *`PRAGMA foreign\_keys = ON`*.



\- Para comprobar existencia de tablas/objetos, puede usarse *`DatabaseMetaData.getTables(...)`*.



###### ***## Convenciones de trabajo***

\- Mantener cambios pequeños y coherentes con el estilo existente.



\- Priorizar ejemplos claros y didácticos de JDBC.



\- Antes de modificar comportamiento, revisar clases relacionadas del mismo bloque.



\- No incluir artefactos generados (`build/`, `.gradle/`, `.idea/`, bases locales) como parte de cambios funcionales salvo necesidad explícita.



\- Al añadir código JDBC nuevo, ubicarlo en el paquete/bloque adecuado y mantener nombres descriptivos.



###### ***## Notas observadas***

\- `PrimeraConexionSqlite.java` contiene lógica/variables orientadas a MySQL (variables de entorno y `DriverManager` con URL de entorno); revisar antes de usarlo como referencia de SQLite puro.



\- El `README.md` actual describe principalmente los bloques 01 y 02; el código también incluye `bloque03`.



