IMPORTANT!!!!

username: admin

password: password


scheduled background job runs at 00.00 every day. The job adds new flights to database.
make sure that you have required airports with responding ids in your database. The flight info is in mockdata.json It uses airports with ids ranging from 11-20 so you need to have airports with this ids in your database.

You can change database username and password in application.properties:

spring.datasource.username=springstudent
spring.datasource.password=springstudent

You can create the database with this script:

-- Create the flightsearch database
DROP SCHEMA IF EXISTS flightsearch22;

CREATE SCHEMA flightsearch22;

-- Use the flightsearch database
USE flightsearch22;

DROP TABLE IF EXISTS airport;

-- Create the airport table
CREATE TABLE airport (
    id INT PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(128) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS flight;

-- Create the flight table
CREATE TABLE flight (
    id INT PRIMARY KEY AUTO_INCREMENT,
    departure_airport_id INT,
    arrival_airport_id INT,
    departure_date_time DATETIME NOT NULL,
    return_date_time DATETIME,
    price DOUBLE,
    FOREIGN KEY (departure_airport_id) REFERENCES airport (id),
    FOREIGN KEY (arrival_airport_id) REFERENCES airport (id)
);
