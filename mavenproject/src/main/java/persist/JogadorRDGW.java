package persist;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JogadorRDGW {
	String nome;
	int tel;
	
	/**
	* Table name
	*/
	private static final String TABLE_NAME = "PLAYERS";
	/**
	* Field names
	*/
	private static final String NAME_COLUMN_NAME = "NAME";
	private static final String PHONE_NUMBER_COLUMN_NAME = "PHONE_NUMBER";
	
	/**
	* A query for insertion
	*/
	private static final String INSERT_PLAYER_SQL =
	"INSERT INTO " + TABLE_NAME + " " +
	"(" + PHONE_NUMBER_COLUMN_NAME +
	", " + NAME_COLUMN_NAME + ") " +
	"VALUES (?, ?)";
	/**
	* A query to find a player by his phonenumber
	*/
	private static final String GET_PLAYER_BY_PNONE_NUMBER_SQL =
	"SELECT " + PHONE_NUMBER_COLUMN_NAME + ", " +
	NAME_COLUMN_NAME + " " +
	"FROM " + TABLE_NAME + " " +
	"WHERE " + PHONE_NUMBER_COLUMN_NAME + " = ?";
	
	public JogadorRDGW(String nome, int tel){
		this.nome = nome;	
		this.tel = tel;
	}
	
	String getNome(){
		return this.nome;
	}
	
	int getTel(){
		return this.tel;
	}
	
	public void insert () throws PersistenceException {
		try (PreparedStatement statement =
		DataSource.prepareGetGenKey(INSERT_PLAYER_SQL)) {
		// set statement arguments
		statement.setInt(1, tel);
		statement.setString(2, nome);
		// executes SQL
		statement.executeUpdate();
		} catch (SQLException e) {
		System.err.println("Find error: " + e.getMessage());
		throw new PersistenceException ("Internal error!", e);
		}
	}
	
	public static JogadorRDGW find (int id)
			throws PersistenceException, RecordNotFoundException {
		JogadorRDGW res;
		try (PreparedStatement statement =
			DataSource.prepareGetGenKey(GET_PLAYER_BY_PNONE_NUMBER_SQL)) {
			// executes SQL
			res = (JogadorRDGW) statement.executeQuery();
			} catch (SQLException e) {
			System.err.println("Find error: " + e.getMessage());
			throw new PersistenceException ("Internal error!", e);
			};
			return res;
	}
	
	public static boolean exists(int id) throws PersistenceException{
			boolean res = false;
			try (PreparedStatement statement =
				DataSource.prepareGetGenKey(GET_PLAYER_BY_PNONE_NUMBER_SQL)) {
				// executes SQL
				res = statement.execute();
				} catch (SQLException e) {
				System.err.println("Find error: " + e.getMessage());
				throw new PersistenceException ("Internal error!", e);
				};
				return res;
		
	}

}
