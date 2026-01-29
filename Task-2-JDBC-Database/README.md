# Task 2: JDBC Database Connectivity

## What is JDBC?

JDBC (Java Database Connectivity) is a Java API that provides a standard interface for connecting Java applications to relational databases. It allows Java programs to execute SQL statements and retrieve results from databases.

**Key Features:**
- Platform-independent database access
- Supports multiple databases (MySQL, PostgreSQL, Oracle, etc.)
- Provides methods to execute queries, updates, and stored procedures
- Handles database connections and transactions

JDBC acts as a bridge between Java applications and databases, enabling developers to interact with databases using SQL without needing database-specific code.

---

## Steps to Connect Java with MySQL

### Prerequisites
1. **MySQL Server**: Install and run MySQL server on your machine
2. **MySQL JDBC Driver**: Download `mysql-connector-java.jar` (MySQL Connector/J)
3. **Java Development Kit (JDK)**: JDK 8 or higher

### Step-by-Step Connection Process

#### Step 1: Load MySQL JDBC Driver
```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
- Registers the MySQL driver with JDBC's DriverManager
- In JDBC 4.0+, this step is optional as drivers are auto-loaded

#### Step 2: Establish Database Connection
```java
Connection connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/database_name", 
    "username", 
    "password"
);
```
- `jdbc:mysql://` is the protocol for MySQL
- `localhost:3306` is the host and default MySQL port
- Replace `database_name`, `username`, and `password` with your credentials

#### Step 3: Create Statement Object
```java
Statement statement = connection.createStatement();
```
- Statement object is used to execute SQL queries

#### Step 4: Execute SQL Query
```java
ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
```
- `executeQuery()` is used for SELECT statements
- Returns a ResultSet containing query results

#### Step 5: Process Results
```java
while (resultSet.next()) {
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    // Process data...
}
```
- `next()` moves to the next row in the result set
- Use getter methods to retrieve column values

#### Step 6: Close Resources
```java
resultSet.close();
statement.close();
connection.close();
```
- Always close resources in reverse order
- Use try-finally or try-with-resources to ensure cleanup

---

## SQL Table Structure

The `students` table used in this example has the following structure:

```sql
CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);
```

**Table Schema:**
- `id`: Integer, Primary Key, Auto-increment
- `name`: Variable character string (max 100 characters), Not Null
- `email`: Variable character string (max 100 characters), Not Null

**Sample Data:**
```sql
INSERT INTO students (name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com'),
('Bob Johnson', 'bob.johnson@example.com');
```

---

## Sample Output

When the program executes successfully, you will see output similar to:

```
MySQL JDBC Driver loaded successfully.
Connection established successfully.

Query executed successfully.
Student Records:
----------------------------------------
ID: 1 | Name: John Doe | Email: john.doe@example.com
ID: 2 | Name: Jane Smith | Email: jane.smith@example.com
ID: 3 | Name: Bob Johnson | Email: bob.johnson@example.com
----------------------------------------
ResultSet closed.
Statement closed.
Connection closed.
```

**Error Output Example:**
If the connection fails, you might see:
```
Error: Database operation failed.
com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure
```

---

## Tools Used

### 1. Java Development Kit (JDK)
- **Version**: JDK 8 or higher
- **Purpose**: Compile and run Java applications
- **Download**: Oracle JDK or OpenJDK

### 2. MySQL Database
- **Version**: MySQL 5.7 or higher / MySQL 8.0
- **Purpose**: Relational database management system
- **Download**: [MySQL Official Website](https://dev.mysql.com/downloads/)

### 3. MySQL Connector/J
- **Version**: 8.0 or higher
- **Purpose**: JDBC driver for MySQL
- **JAR File**: `mysql-connector-java-8.0.xx.jar`
- **Download**: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)

### 4. IDE (Optional but Recommended)
- **Eclipse**: Popular Java IDE
- **IntelliJ IDEA**: JetBrains Java IDE
- **VS Code**: Lightweight editor with Java extensions
- **NetBeans**: Oracle's Java IDE

### 5. Build Tool (Optional)
- **Maven**: Dependency management and build automation
- **Gradle**: Build automation tool

---

## How to Run

### Method 1: Using Command Line

1. **Compile the Java file:**
   ```bash
   javac -cp "mysql-connector-java.jar" src/JdbcExample.java
   ```

2. **Run the program:**
   ```bash
   java -cp "mysql-connector-java.jar;." JdbcExample
   ```
   (Use `:` instead of `;` on Linux/Mac)

### Method 2: Using IDE

1. Add `mysql-connector-java.jar` to your project's classpath
2. Update database credentials in `JdbcExample.java`
3. Run the `main` method

---

## Important Notes

- **Security**: Never hardcode database credentials in production code. Use configuration files or environment variables.
- **Resource Management**: Always close database resources (Connection, Statement, ResultSet) to prevent memory leaks.
- **Exception Handling**: Proper exception handling ensures graceful error management.
- **Connection String**: For MySQL 8.0+, use `com.mysql.cj.jdbc.Driver` instead of `com.mysql.jdbc.Driver`.

---

## Common Issues and Solutions

### Issue 1: ClassNotFoundException
**Error**: `java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver`
**Solution**: Add `mysql-connector-java.jar` to the classpath

### Issue 2: Connection Refused
**Error**: `CommunicationsException: Communications link failure`
**Solution**: 
- Verify MySQL server is running
- Check host and port (default: localhost:3306)
- Verify database name exists

### Issue 3: Access Denied
**Error**: `Access denied for user`
**Solution**: 
- Verify username and password
- Check user permissions in MySQL

---

## Interview-Safe Explanation

**Q: What is JDBC?**
A: JDBC is a Java API that provides a standard way to connect Java applications to relational databases. It defines interfaces and classes for database operations, allowing Java programs to execute SQL statements and process results.

**Q: How does JDBC work?**
A: JDBC uses a driver-based architecture. The JDBC driver acts as a translator between Java code and database-specific protocols. When you load a driver (like MySQL Connector/J), it registers itself with DriverManager, which then uses it to establish connections based on the connection URL.

**Q: Why close resources in finally block?**
A: Closing resources in the finally block ensures they are always released, even if an exception occurs. This prevents resource leaks, which can lead to connection pool exhaustion and application failures.

