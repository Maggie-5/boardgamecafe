package dbutils;
import java.sql.SQLException;
import main.BoardGameCafe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import persist.DataSource;
import persist.PersistenceException;
public class ListDatabase {
public static void doListDB() throws PersistenceException, SQLException {
DataSource.connect(BoardGameCafe.DB_CONNECTION_STRING,
"BoardGameCafe", "");
PreparedStatement statement = DataSource.prepare("SELECT * FROM PLAYERS");
ResultSet rs = statement.executeQuery();
System.out.println("+++++++ Players +++++++");
while (rs.next()) {
String name = rs.getString("NAME");
int tel = rs.getInt("PHONE_NUMBER");
System.out.println("Name: " + name + " Phone: " + tel);
}
statement.close();
rs.close();
System.out.println("+++++++ Tables +++++++");
statement = DataSource.prepare("SELECT * FROM TABLES");
rs = statement.executeQuery();
while (rs.next()) {
String num = rs.getString("NUM");
int p = rs.getInt("PLAYER");
int n = rs.getInt("N_OCCUPANTS");
System.out.println("Table num: " + num + " Player: " +
p + " N_OCCUPANTS " + n);
}
statement.close();
rs.close();
}
public static void main(String[] args) throws PersistenceException, SQLException {
doListDB();
DataSource.close();
}
}