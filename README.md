A "cloud" server that supports the upload, download and deletion of files, and full CRUD of notes and login credentials. 

Backend service is written in Java Spring boot. Utilizes Spring Security for authentication and authorization. For the storage of login credentials, passwords are encrypted, and decerpyted on the front end (there is room for improvement here). The last service is CRUD of "notes" or Todos for the user to do. 

Persistence layer technologies include MyBatis for ORM, and an in memory database technology H2.
