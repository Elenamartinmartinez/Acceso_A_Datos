# ***Acceso\_A\_Datos***

## ***AD\_Practica2***





1. ¿Dónde se conocía el formato CSV en la primera versión?

--> En la primera versión, el formato CSV solo se conocía en "AlmacenamientoEnCSV". Esa clase leía y escribía clientes.csv y pagos.csv, separaba los campos con ; (split(";")) y convertía cada línea en un Cliente o un "Pagos_Repostajes" y viceversa.
      El resto ("Menu", "Entrada", "Gasolinera", "Cliente", "Pagos_Repostajes") no sabía nada del formato: "Gasolinera" solo usa la interfaz Almacenamiento (leerCliente, escribirCliente, leerPagos, escribirPagos).



2. ¿Qué cambios fueron necesarios para utilizar JSON y cuáles introdujiste únicamente para permitir la migración?

--> **Creación de nuevas clases**:* 

  - "AlmacenamientoEnJSON"*: Clase para crear los nuevos clientes/pagos en un archivo .JSON (uno para los clientes y otro para los pagos), es muy parecida a la clase de  AlmacenamientoEnCSV",
     pero variando en el formato en el que se van a guardar los datos y  porque en vez de .csv pone .json.


  - "MigrarCSVToJSON"*: Clase para migrar todos los datos de los archivos .csv de los clientes y pagos ya registrados a los nuevos archivos .json, los copiará los datos en la  nueva sin eliminar
     la carpeta de csv. Esta clase solo se ejecutará al principio y cuando se vayan a escribir los nuevos datos en .json, se mostrará la opción antes del menú de opciones  y debería hacer la
     migración sin problemas, aunque en caso de haberlos saltarían los errores correspondientes.



--> **Modificaciones en otras clases**:*

  - "AlmacenamientoEnCSV"*: En un principio tenía los métodos para codificar y descodificar los datos, pero al no ser necesario porque no buscamos que este codificado en ningún momento,
     necesitamos que sea legible para nosotros, se han eliminado los métodos de "anexar", "descodificar" y "codificar" para adaptar mejor el código, ahora la clase de "AlmacenamientoEnCSV"
     tiene la misma estructura que "AlmacenamientoEnJSON", pero con diferentes adaptaciones para visualizarlos en sus ficheros correspondientes.



3. ¿Tuviste que modificar el menú, las validaciones o las operaciones con clientes y pagos? Explica
por qué, tanto si la respuesta es sí como si es no.

--> No tuve que modificar ninguna de las anteriores, el menú, las validaciones, cualquier operación ya creada no era necesario cambiarla, al crear la clase de "AlmacenamientoEnJSON" y
     "MigrarCSVToJSON" solo fue crear las estructuras (o adaptarla, como es el caso de AlmacenamientoEnJSON) y todas se inicializan en la clase main



4. ¿Qué partes permanecieron intactas y qué decisiones del diseño lo hicieron posible?

--> Ignorando el cambio de la clase de "AlmacenamientoEnCSV" por el malentendido que tuve, no se tuvo que cambiar nada en especial para la estructura final, por lo mismo que en la
      pregunta anterior




5. ¿Qué cambiarías ahora para facilitar una futura sustitución del formato?

--> No cambiaría nada en especial, solo bastaría con crear las nuevas clases para sustituir el formato de json por otro, ya sea solo la clase de "AlmacenamientoEn___" o también otra clase
    para migrar los datos de json al nuevo formato de guardado de datos y usar la estructura que se ha usado para las otras clases de Almacenamiento y en la clase de 'MigrarCSVToJSON'
