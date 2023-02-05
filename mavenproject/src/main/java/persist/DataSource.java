package persist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
* The class DataSource is an abstraction for a database connection.
*
*/
public class DataSource {
private static Connection connection;
/**
* Constructs a database connection given the connection url, the username, and its passw* for the database engine.
*
* @param url The database connection URL
* @param username The username to login into the database
* @param password The user's password
* @return The data source.
* @throws PersistenceException In case the connection fails to establish
*/
public static void connect(String url, String username, String password)
throws PersistenceException {
try {
connection = DriverManager.getConnection(url, username, password);
} catch (SQLException e) {
System.err.println(e.getMessage());
e.printStackTrace();
System.err.println("*** Just in case, is your eclipse connected to the DB");
		throw new PersistenceException("Cannot connect to database", e);
}
}
/**
* @return The current database connection
*/
public static Connection getConnection() {
return connection;
}
public static PreparedStatement prepareGetGenKey(String sql) throws SQLException {
System.out.println("connection: " + connection);
return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
}
/**
* Prepare an SQL statement from an SQL string
*
* @param sql The SQL text to prepare the command
* @return The prepared statement for the SQL text
* @throws PersistenceException In case the prepare statement
* encounters an error.
*/
public static PreparedStatement prepare(String sql) throws PersistenceException {
try {
return connection.prepareStatement(sql);
} catch (SQLException e) {
throw new PersistenceException("Error preparing comment", e);
}
}
/**
* Close the database connection
*/
public static void close() throws PersistenceException {
try {
connection.close();
} catch (SQLException e) {
throw new PersistenceException("Cannot closing the database", e);
}
}
}
