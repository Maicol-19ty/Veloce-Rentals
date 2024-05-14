# Veloce Rentals

Veloce Rentals es una plataforma en línea para alquilar autos y motocicletas. Esta guía describe cómo configurar e iniciar la aplicación Java web utilizando Jakarta, Tomcat y MySQL.

## Requisitos

- **Java Development Kit (JDK)**: Asegúrate de tener instalado JDK 21.
- **Apache Tomcat**: Descarga e instala Apache Tomcat 10.
- **MySQL**: Instala MySQL Server 8.3.
- **Maven**: Utiliza Maven para gestionar las dependencias del proyecto.

## Configuración de MySQL

1. Inicia el servidor MySQL.
2. Crea una base de datos para la aplicación:
    ```sql
    CREATE DATABASE velocerentals;
    ```
3. Crea las tablas que se encuentran en el archivo `src/main/resources/database.sql`:
    
## Configuración del Proyecto

1. Clona el repositorio del proyecto:
    ```bash
    git clone https://github.com/tu-usuario/velocerentals.git
    cd velocerentals
    ```

2. Configura las propiedades de conexión a la base de datos en el archivo `src/main/resources/database.properties.example`: (Recuerde eliminar el .example)
    ```properties
    db.url=jdbc:mysql://<ip>:<port>/<schema>
    db.user=
    db.password=
    db.driver=

    pool.initialSize= Initial pool size
    pool.maxTotal= Maximum number of active connections
    pool.maxIdle= Maximum number of idle connections
    pool.minIdle= Minimum number of idle connections
    pool.maxWaitMillis= Maximum wait time to get a connection
    ```

3. Compila el proyecto usando Maven:
    ```bash
    mvn clean install
    ```

## Despliegue en Tomcat

1. Descargue Tomcat 10 en el siguiente enlace https://tomcat.apache.org/download-10.cgi
2. Descarga el nucleo tar.gz descomprime y guarda la direccion de la carpeta
3. ![image](https://github.com/Maicol-19ty/Veloce-Rentals/assets/66977052/85821279-1f75-4517-bdcd-b49c41a17bae)
   Desplegamos esta opcion y seleccionamos `Edit Configuration`
4. Seleccionamos `Add nuw configuration` y buscamos Tomcat Server Local
5. ![image](https://github.com/Maicol-19ty/Veloce-Rentals/assets/66977052/087e6df4-db08-42cb-bb04-49aa5f7a79f4)
   Seleccionamos la opcion `Configure` ingresamos la direccion de la carpeta donde se descomprimio Tomcat
6. Vamos al apartado `Deplyment` y agregamos un nuevo artifac en especifico el `:war`
7. Aplicamos los cambios y ya podemos ejecutar nuestra aplicacion
8. Accede a la aplicación desde tu navegador web en la URL:
    ```
    http://localhost:8080/veloce_rentals_war/
    ```

## Estructura del Proyecto

- `src/main/java/cue/edu/co/velocerentals/`: Contiene los archivos Java del proyecto.
- `src/main/resources/`: Contiene archivos de configuración y recursos estáticos.
- `src/main/webapp/`: Contiene los archivos JSP y otros recursos web.

## Contribuir

Para contribuir al proyecto:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la licencia MIT. Para más detalles, revisa el archivo [LICENSE](LICENSE).

