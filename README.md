TicketMagpie
============

This project aims at demonstrating various security vulnerabilities such as the ones listed in the OWASP Top10.

 
Vulnerabilities
===============


Unsecured shutdown endpoint
---------------------------

Spring Boot provides a `/shutdown` endpoint that kills the application.

By default, this endpoint is disabled, but the TicketMagpie developers use it on their Continuous Integration server to stop the application after running UI tests.

However no precaution has been taken to disable this endpoint in production.
 
Corresponding OWASP Top10 vulnerability: [A5 Security Misconfiguration](https://www.owasp.org/index.php/Top_10_2013-A5-Security_Misconfiguration)

To fix this, either disable the endpoint entirely, or use environment-specific configuration to only enable it on the continuous integration server.

