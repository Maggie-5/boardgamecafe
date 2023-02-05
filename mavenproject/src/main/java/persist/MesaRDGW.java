package persist;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MesaRDGW {
	int mesa;
	int ocup;
	JogadorRDGW jog;
	
	private static final String TABLE_NAME = "MESAS";
	
	private static final String TABLE_COLUMN_NAME = "MESA";
	private static final String OCUPANTES_COLUMN_NAME = "OCUPANTES";
	private static final String PLAYER_COLUMN_NAME = "PLAYER";
	
	private static final String INSERT_MESA_SQL =
			"INSERT INTO " + TABLE_NAME + " " +
			"(" + TABLE_COLUMN_NAME + ", " +  OCUPANTES_COLUMN_NAME +
			", " + PLAYER_COLUMN_NAME + ") " +
			"VALUES (?, ?)";
			

	private static final String GET_TABLE_BY_NUMBER_SQL =
	"SELECT " + TABLE_COLUMN_NAME + ", " +
	OCUPANTES_COLUMN_NAME + " " + PLAYER_COLUMN_NAME + " " +  
	"FROM " + TABLE_NAME + " " +
	"WHERE " + TABLE_COLUMN_NAME + " = ?";
	
	private static final String UPDATE_MESA_SQL =
			"UPDATE " + TABLE_NAME + " " +
			"SET " + OCUPANTES_COLUMN_NAME +
			", " + PLAYER_COLUMN_NAME + ") " +
			"VALUES (?, ?) " + 
			"WHERE " + TABLE_COLUMN_NAME + " = ?";
			
	public MesaRDGW(int mesa) {
		this.mesa = mesa;
	}	
	
	public JogadorRDGW getPlayer() {
		return this.jog;
	}
	
	public int getOcup() {
		return this.ocup;
	}
	
	public void setPlayer(JogadorRDGW jog) {
		this.jog = jog;
	}
	
	public void  setOcup(int ocup) {
		this.ocup = ocup;
	}
	
	public String toString() {
		return "player = " + this.getPlayer() + "ocup = " + this.getOcup();
	}
	
	public boolean isFree() {
		if(this.ocup>0) {
			return false;
		}else {
			return true;
		}
	}
	
	public void insert () throws PersistenceException{
		
	}
	public void update() throws PersistenceException{
		try (PreparedStatement statement =
				DataSource.prepareGetGenKey(UPDATE_MESA_SQL)) {
				// executes SQL
				statement.executeUpdate();
				} catch (SQLException e) {
				System.err.println("Find error: " + e.getMessage());
				throw new PersistenceException ("Internal error!", e);
				}
	}
	public static MesaRDGW find (int id) throws PersistenceException,
	RecordNotFoundException{
		MesaRDGW res;
		try (PreparedStatement statement =
				DataSource.prepareGetGenKey(GET_TABLE_BY_NUMBER_SQL)) {
				// executes SQL
				res = (MesaRDGW) statement.executeQuery();
				} catch (SQLException e) {
				System.err.println("Find error: " + e.getMessage());
				throw new PersistenceException ("Internal error!", e);
				};
				return res;
		
	}
	
}
