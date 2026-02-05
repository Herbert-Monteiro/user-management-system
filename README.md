# User Management System

A user management system built in Java using JDBC and MySQL.

This project implements core backend concepts such as CRUD operations, authentication with password hashing, and pagination.  
It was developed as a hands-on study project to strengthen backend fundamentals and database integration.

---

## Features

-  Create, Read, Update and Delete users (CRUD)
-  Authentication system (Login)
-  Password hashing using SHA-256
-  Pagination for user listing
-  Organized layered structure (Model, Repository, View)
-  Input validation ( Gmail format, date parsing)

---

## Technologies Used

- Java
- JDBC
- MySQL
- Maven
- Git
- SHA-256 (MessageDigest)
- Object-Oriented Programming (OOP)

---

##  Project Structure
src/main/java/org/example

    
    org.example
        dataBase
            conxao.java
        model
            Usuario.java
        repository
            UsuarioRepository.java
        view
            Menu.java
        Main.java

The project follows a layered structure:

- **Model** → Entity representation
- **Repository** → Database access logic
- **Security** → Password hashing
- **View** → Console interaction
- **Main** → Application entry point 

---

## Database

The system uses MySQL.

Example table structure:

```sql
CREATE TABLE usuario (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    gmail VARCHAR(100) NOT NULL,
    senha VARCHAR(64) NOT NULL,
    rg VARCHAR(20),
    data_nascimento DATE,
    data_cadastro DATE DEFAULT CURRENT_DATE
);
```
##  Security

Passwords are never stored as plain text.

The system uses **SHA-256 hashing** through `MessageDigest`.

### How it works:

- Same input → Same hash  
- The system does NOT decrypt passwords  
- Login validation compares hashed values  

### Pagination Logic

User listing is implemented with:

Total user count

Page calculation using Math.ceil

LIMIT and OFFSET in SQL

Navigation between pages (Next / Previous)

### Learning Goals

This project was built to:

Practice JDBC and SQL integration

Understand repository pattern

Implement authentication logic

Work with password hashing

Improve clean code structure

Prepare for building REST APIs

### Next Step

This project will evolve into a Spring Boot REST API version, implementing:

REST controllers

DTOs

JPA / Hibernate

Proper authentication flow

Improved security

### Author

Developed by Francisco Herbert Monteiro Silva Filho
Backend-focused developer in progress 
