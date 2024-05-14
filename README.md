# VeloceRentals

Veloce Rentals is an online platform for renting cars and motorcycles. This guide describes how to configure and start your Java web application using Jakarta, Tomcat, and MySQL.

## Requirements

- **Java Development Kit (JDK)**: Make sure you have JDK 21 installed.
- **Apache Tomcat**: Download and install Apache Tomcat 10.
- **MySQL**: Install MySQL Server 8.3.
- **Maven**: Use Maven to manage project dependencies.

## MySQL configuration

1. Start the MySQL server.
2. Create a database for the application:
     ```sql
     CREATE DATABASE velocerentals;
     ```
3. Create the tables found in the `src/main/resources/database.sql` file:
    
## Project Settings

1. Clone the project repository:
     ```bash
     git clone https://github.com/tu-usuario/velocerentals.git
     cd velocerentals
     ```

2. Configure the database connection properties in the `src/main/resources/database.properties.example` file: (Remember to remove the .example)
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

3. Build the project using Maven:
     ```bash
     mvn clean install
     ```

## Deployment in Tomcat

1. Download Tomcat 10 at the following link https://tomcat.apache.org/download-10.cgi
2. Download the kernel tar.gz, unzip and save the folder address
3. ![image](https://github.com/Maicol-19ty/Veloce-Rentals/assets/66977052/85821279-1f75-4517-bdcd-b49c41a17bae)
    We display this option and select `Edit Configuration`
4. We select `Add new configuration` and look for Tomcat Server Local
5. ![image](https://github.com/Maicol-19ty/Veloce-Rentals/assets/66977052/087e6df4-db08-42cb-bb04-49aa5f7a79f4)
    We select the `Configure` option and enter the address of the folder where Tomcat was unzipped.
6. We go to the `Deplyment` section and add a new artifact specifically `:war`
7. We apply the changes and we can now run our application
8. Access the application from your web browser at the URL:
     ```
     http://localhost:8080/veloce_rentals_war/
     ```

## Project Structure

- `src/main/java/cue/edu/co/velocerentals/`: Contains the project's Java files.
- `src/main/resources/`: Contains configuration files and static resources.
- `src/main/webapp/`: Contains JSP files and other web resources.

## Contribute

To contribute to the project:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes and commit (`git commit -am 'Add new functionality'`).
4. Push to the branch (`git push origin feature/new-feature`).
5. Open a Pull Request.

## License

This project is under the MIT license. For more details, check the [LICENSE](LICENSE) file.
