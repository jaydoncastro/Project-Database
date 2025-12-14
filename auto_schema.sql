CREATE DATABASE Auto;
USE Auto;

CREATE TABLE AutoMPG (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mpg DOUBLE,
    cylinders INT,
    displacement DOUBLE,
    horsepower INT,
    weight INT,
    acceleration DOUBLE,
    model_year INT,
    origin INT,
    car_name VARCHAR(100)
);
