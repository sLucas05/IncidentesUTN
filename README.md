# Sistema de Reporte de Incidentes

### Descripción
Proyecto desarrollado en el marco del curso **Desarrollador Java Intermedio** de la **Universidad Tecnológica Nacional** utilizando el lenguaje **Java**. Consiste en un sistema que permite administrar un grupo de clientes y técnicos con diversas especialidades, definiendo la asignación de reportes según el tipo de servicio. Se mantiene informadas a ambas partes sobre el estado del reporte via email tras cada actualización.

Destaca de este proyecto la gestión mediante interfaz gráfica **Swing**, el procesamiento de la persistencia **MySQL** a traves de **JPA** y la implementación de notificaciones con **Email API**.

### Oportunidades de Mejora
```
1. Separar la interfaz de la lógica en clases independientes.
2. Dividir procesos en funciones para facilitar la reutilización.
3. Implementar métodos find y findAll para evitar queries manuales.
4. Centralizar la configuración necesaria en un archivo JSON.
5. Renombrar las variables para mejorar la legibilidad.
6. Normalizar la acentuación en los strings.
7. Agregar pruebas unitarias para garantizar el funcionamiento al 100% del programa.
```

### Setup
   ● _Base de Datos_
  1. Crear la base de datos MySQL `incidentes` 
  2. Configurar las credenciales en el archivo `persistence.xml`
  	
   ● _Email API_
  1. Configurar los parámetros del servidor SMTP en `Email.java`
  2. Configurar las credenciales en `Email.java`
  	
   ● _Ejecución_
  1. Importar como proyecto Maven.
  2. Ejecutar `cMain.java`

### Previsualización
![Incidentes](https://github.com/user-attachments/assets/84b27f63-5be0-4422-b9f7-27eeecdb8897)
