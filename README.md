TicketMagpie
============

This project aims at demonstrating various security vulnerabilities such as the ones listed in the OWASP Top10.


Running the project
---------------------------

This project requires [Maven 3](https://maven.apache.org/). Once you have Maven, you can start the application like this:


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

If you do not have database server, you can run the application with an HSQLDB in-memory database:


```
mvn spring-boot:run -Dspring.profiles.active=hsqldb

```

 
Vulnerabilities
===============


Unsecured shutdown endpoint
---------------------------

Spring Boot provides a `/shutdown` endpoint that kills the application.

By default, this endpoint is disabled, but the TicketMagpie developers use it on their Continuous Integration server to stop the application after running UI tests.

However no precaution has been taken to disable this endpoint in production.
 
Corresponding OWASP Top10 vulnerability: [A5 Security Misconfiguration](https://www.owasp.org/index.php/Top_10_2013-A5-Security_Misconfiguration)

To fix this, either disable the endpoint entirely, or use environment-specific configuration to only enable it on the continuous integration server.

