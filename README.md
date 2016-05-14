TicketMagpie
============

This project aims at demonstrating various security vulnerabilities such as the ones listed in the OWASP Top10.


Running the project
---------------------------

This project requires [Maven 3](https://maven.apache.org/).

Once you have Maven, you can start the application by running this command from the root folder of the project:


```
mvn spring-boot:run
```

Database configuration
---------------------------

By default, the application expects a MySQL database to be available on localhost, default port 3306.
The application will use the user `root` to connect to a database called `ticketmagpie`.

You can pass custom database configuration as follows:

```
mvn spring-boot:run -Dspring.datasource.url=jdbc:mysql://MYSQL_SERVER:PORT/DB_NAME -Dspring.datasource.username=USER -Dspring.datasource.password=PASSWORD
```

If you do not have a database server, you can run the application with an HSQLDB in-memory database:


```
mvn spring-boot:run -Dspring.profiles.active=hsqldb
```
