import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC Example: Demonstrating database connectivity with MySQL
 * This class shows how to connect to MySQL database, execute queries,
 * and properly close database resources.
 */
public class JdbcExample {
    
    // Database connection parameters
    // Note: In production, these should be stored in configuration files
    private static final String DB_URL = "jdbc:mysql://localhost:3306/internship_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "your_password";
    
    public static void main(String[] args) {
        // Declare resources outside try block for proper cleanup
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Step 1: Load MySQL JDBC Driver
            // This registers the driver with DriverManager
            // Note: In newer JDBC versions (4.0+), this step is optional
            // as drivers are automatically loaded via ServiceLoader
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully.");
            
            // Step 2: Establish Connection to MySQL Database
            // DriverManager.getConnection() creates a connection using the URL,
            // username, and password
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connection established successfully.");
            
            // Step 3: Create Statement Object
            // Statement is used to execute SQL queries against the database
            statement = connection.createStatement();
            
            // Step 4: Execute SELECT Query
            // executeQuery() is used for SELECT statements and returns a ResultSet
            String selectQuery = "SELECT id, name, email FROM students";
            resultSet = statement.executeQuery(selectQuery);
            System.out.println("\nQuery executed successfully.");
            System.out.println("Student Records:");
            System.out.println("----------------------------------------");
            
            // Step 5: Process ResultSet and Print Data
            // next() moves the cursor to the next row and returns false when no more rows
            while (resultSet.next()) {
                // Retrieve data using column names or indices
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                
                // Print the retrieved data
                System.out.println("ID: " + id + " | Name: " + name + " | Email: " + email);
            }
            System.out.println("----------------------------------------");
            
        } catch (ClassNotFoundException e) {
            // Handle driver loading failure
            System.err.println("Error: MySQL JDBC Driver not found.");
            System.err.println("Make sure mysql-connector-java.jar is in the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            // Handle database connection or query execution errors
            System.err.println("Error: Database operation failed.");
            e.printStackTrace();
        } finally {
            // Step 6: Close Resources Properly
            // Always close resources in finally block to ensure cleanup
            // Close in reverse order: ResultSet -> Statement -> Connection
            
            try {
                if (resultSet != null) {
                    resultSet.close();
                    System.out.println("ResultSet closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing ResultSet: " + e.getMessage());
            }
            
            try {
                if (statement != null) {
                    statement.close();
                    System.out.println("Statement closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing Statement: " + e.getMessage());
            }
            
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing Connection: " + e.getMessage());
            }
        }
    }
}

