Job Applications Tracking System

A full stack microservices based application that allows users to manage and track their job applications across multiple companies.

The system helps job seekers organize applications, track their status, and store company information in a clean dashboard interface.

Features
User Authentication

Create a new account
Secure login system
Logout functionality

Application Management

Add new job applications
Automatically generate company IDs
Delete applications
View applications for the logged in user only

Search and Organization

Search applications by
Company name
Industry
Location

Sort applications by most recent

Dashboard

Modern user interface
Application status badges
Company logos
Location and application date displayed

System Architecture

The project follows a microservices architecture.

Frontend (HTML / CSS / JavaScript)
        |
        |
------------------------------------------------
|                     |                        |
User Service     Application Service     Company Service
(Spring Boot)     (Spring Boot)           (Spring Boot)
        |
        |
     Oracle Database


Tech Stack
Backend

Java
Spring Boot
Spring Data JPA
REST APIs

Database

Oracle Database

Frontend

HTML
CSS
Vanilla JavaScript

Testing

Cucumber
JUnit
Java HTTP Client

Tools

Maven
Git
GitHub

Project Structure
job-applications-tracking-system

user-service
Handles user authentication and login

applicationservice
Manages job applications

company
Stores company information

frontend
Contains user interface files
index.html
dashboard.html
register.html
api.js
styles.css

README.md
Running the Application

Each microservice runs separately.

Start User Service
cd user-service
./mvnw spring-boot:run

Runs on

http://localhost:8081
Start Application Service
cd applicationservice
./mvnw spring-boot:run

Runs on

http://localhost:8080
Start Company Service
cd company
./mvnw spring-boot:run

Runs on

http://localhost:8082

Running the Frontend

Open the frontend file in your browser

frontend/index.html

API Endpoints

User Service

Create user

POST /users/create

Login

POST /users/login
Application Service

Create application

POST /applications

Get applications for a user

GET /applications/user/{userId}

Delete application

DELETE /applications/{id}

Fetch company details for application

GET /applications/{id}/company
Company Service

Get company information

GET /companies/{id}
Example Workflow

User creates an account

User logs in

User creates a job application

Company information is automatically generated

Dashboard displays applications

User can search, sort, or delete applications

Future Improvements

JWT authentication
Pagination for applications
Application status filters
Cloud deployment
CI/CD pipeline
Mobile responsive design

Author

Param Chauhan
Computer Science Student
Toronto Metropolitan University



