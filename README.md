# CS319 – Java Programming

A Java project demonstrating object-oriented programming concepts through a simple **GradeBook** application.

## Project Structure

```
src/
  main/java/cs319/
    Student.java      # Student entity (id, name)
    GradeBook.java    # Grade management for a course
  test/java/cs319/
    GradeBookTest.java # JUnit 5 unit tests
pom.xml               # Maven build configuration
```

## Building and Testing

Requires **Java 11+** and **Maven 3.6+**.

```bash
# Compile and run all tests
mvn test

# Package the project into a JAR
mvn package
```

## Features

- **Student** – immutable value object identified by a student ID.
- **GradeBook** – enroll students, record numeric scores (0–100), compute averages, and derive letter grades (A/B/C/D/F).

## Letter Grade Scale

| Range  | Grade |
|--------|-------|
| ≥ 90   | A     |
| ≥ 80   | B     |
| ≥ 70   | C     |
| ≥ 60   | D     |
| < 60   | F     |
