# Nyx Test

This project was generated with spring initializer and is using java 17, spring 3.2, oauth, spring security, jpa, flyway, jwt, postgresql, jarkaka, lombok and maven to compile 

## Development environment

- Install java version 17.
- Install all the maven dependencies.
- Configure the environment variables, add `CLIENT_NAME=testeNyx;CLIENT_SECRET=@2024testeNyx`;
- Ready to go!

## Development server

Just run the project normally, the API will show the `8080` port to receive calls.

## About the project

- The database was created in AWS using the rds and ec2 services to provision the database, a dbeaver connection was used to load the data into the database and make it available to the api.
- The system uses an authentication system with jwt and Basic Authentication, Spring Security, using roles, users and person.