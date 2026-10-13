# ***Acceso\_A\_Datos***

## ***AD\_Practica2***





##### *--> **Creación de nuevas clases**:* 

###### &#x09;*- "AlmacenamientoEnJSON"*: Clase para crear los nuevos clientes/pagos en un archivo .JSON (uno para los clientes y otro para los pagos), es muy parecida a la clase de  AlmacenamientoEnCSV", pero variando en el formato en el que se 

###### &#x09;van a guardar los datos y  porque en vez de .csv pone .json.



###### &#x09;*- "MigrarCSVToJSON"*: Clase para migrar todos los datos de los archivos .csv de los clientes y pagos ya registrados a los nuevos archivos .json, los copiará los datos en la  nueva sin eliminar la carpeta de csv. Esta clase solo se

###### &#x09;ejecutará al principio y cuando se vayan a escribir los nuevos datos en .json, se mostrará la opción antes del menú de opciones  y debería hacer la migración sin problemas, aunque en caso de haberlos saltarían los errores

###### &#x09;correspondientes.





##### *--> **Modificaciones en otras clases**:*

###### &#x09;*- "AlmacenamientoEnCSV"*: En un principio tenía los métodos para codificar y descodificar los datos, pero al no ser necesario porque no buscamos que este codificado en ningún momento, necesitamos que sea legible para nosotros, se han

###### &#x09;eliminado los métodos de "anexar", "descodificar" y "codificar" para adaptar mejor el código, ahora la clase de "AlmacenamientoEnCSV" tiene la misma estructura que "AlmacenamientoEnJSON", pero con diferentes adaptaciones para

###### &#x09;visualizarlos en sus ficheros correspondientes.

