-- Run this SQL in your MySQL (or compatible) server to create the Employee table and sample data
CREATE DATABASE IF NOT EXISTS companydb;
USE companydb;

CREATE TABLE IF NOT EXISTS Employee (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(100),
    Salary DECIMAL(10,2)
);

INSERT INTO Employee (EmpID, Name, Salary) VALUES
(101, 'Amit Kumar', 55000.00),
(102, 'Neha Sharma', 62000.00),
(103, 'Raj Verma', 47000.00)
ON DUPLICATE KEY UPDATE Name=VALUES(Name), Salary=VALUES(Salary);
