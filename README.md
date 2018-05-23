  This is a very simple example of a HTTP Server using Spring, Hibernate, MySQL.
  The application has a USERS table in usersrepository database.
  The server uses the port 1111 (can be changed in the resourses/application.properties file).
  We can make a GET HTTP request with the username at localhost as http://localhost:1111/?userName=userName
   (eg http://localhost:1111/?userName=bobking) and get the user's data from database.
  We can make a PUT HTTP request with the userdata at localhost as
    http://localhost:1111/?userData=userName,firstName,lastName (eg http://localhost:1111/?userData=tomgrey,Tom,Grey)
    and write the data into database.

  It is necessary:
- change hibernate.cfg.xml for your database.
- create "usersrepository" database.
- create USERS table (there are scheme for creating and filling the USERS table in the /setupfiles directory).
