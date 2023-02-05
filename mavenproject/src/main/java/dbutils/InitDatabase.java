package dbutils;
import java.io.IOException;
import java.sql.SQLException;
import main.BoardGameCafe;
import persist.DataSource;
import persist.PersistenceException;

public class InitDatabase {
public void addLineDerbyDB() throws IOException, SQLException, PersistenceException {
	DataSource.connect(BoardGameCafe.DB_CONNECTION_STRING + ";create=true", "BoardGameCafe", "");
	RunSQLScript.runScript(DataSource.getConnection(), "data/scripts/initDB-Derby.sql");
	DataSource.close();
}
public static void main(String[] args) throws PersistenceException, IOException, SQLException{
new InitDatabase().addLineDerbyDB();
}
}