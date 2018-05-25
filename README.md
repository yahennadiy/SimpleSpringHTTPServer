  This is a very simple example of a HTTP Server using Spring, Hibernate, MySQL.
  The application has a USERS table in usersrepository database.
  The server uses the port 1111 (can be changed in the resourses/application.properties file).
  We can make a GET HTTP request with the username at localhost as http://localhost:1111/?userName=userName
    (eg http://localhost:1111/?userName=bobking) and get the user's data from database.
  We can make a DELETE HTTP request with the username at localhost as http://localhost:1111/?userName=userName
    (eg http://localhost:1111/?userName=bobking) and remove the user's data from database, if userName exist in
    database.
  We can make a POST HTTP request with the userdata at localhost as
    http://localhost:1111/?userName=userName&firstName=firstName&lastName=lastName
    (eg http://localhost:1111/?userName=tomgrey&firstName=Tom&lastName=Grey) and write the user's data into database,
    if userName not exist in database.
  We can make a PUT HTTP request with the userdata at localhost as
    http://localhost:1111/?userName=userName&firstName=firstName&lastName=lastName
    (eg http://localhost:1111/?userName=tomgrey&firstName=TOM&lastName=GREY) and update the user's data in database,
    if userName exist in database.
  We can perform requests using Postman or some other way.

  It is necessary:
- change hibernate.cfg.xml (password) for your database.
- create "usersrepository" database.
- create USERS table (there are scheme for creating and filling the USERS table in the /setupfiles directory).
