-- Run this SQL in your MySQL (or compatible) server to create the Attendance table
CREATE DATABASE IF NOT EXISTS schooldb;
USE schooldb;

CREATE TABLE IF NOT EXISTS Attendance (
    StudentID INT,
    Date DATE,
    Status VARCHAR(10)
);
