# Validation Project

## Overview
This project is designed to validate folder names and ensure they do not pose a security risk, such as path traversal vulnerabilities.

## Technologies Used
- **Java**
- **Maven**

## Project Structure
- `src/main/java/tsv/design/exception/` - Custom exceptions
- `src/main/java/tsv/design/service/` - Service classes
- `src/main/java/tsv/design/model/` - Model classes
- `src/test/java/tsv/design/` - Unit tests

## How to Build
To build the project, run the following command:
```sh
mvn clean install
```
## How to Test
To run the unit tests, run the following command:
```sh
mvn test
```
## Custom Exceptions
- SecurityTraversalException - Thrown when a potential path traversal is detected.

## How to Run
We don't need to run the application as it an example