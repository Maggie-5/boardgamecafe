package scripts;

import org.omg.CORBA.portable.ApplicationException;

import persist.JogadorRDGW;
import persist.MesaRDGW;
import persist.PersistenceException;
import persist.RecordNotFoundException;

public class CheckInTransactionScript {

	@SuppressWarnings("null")
	public void checkIn(String name, int phoneNumber, int tableNumber) throws ApplicationException, PersistenceException{
		JogadorRDGW j;
		MesaRDGW m = null;
		boolean f;
		try {
			j = JogadorRDGW.find(phoneNumber);
			m = MesaRDGW.find(tableNumber);
			} catch (RecordNotFoundException e) {
			// The problem of this approach is that the player inserted in
			// the DB has no name.
			System.err.println("Player " + phoneNumber + " not found");
			j = new JogadorRDGW(name, phoneNumber);
			j.insert();
			f = m.isFree();
			if(f == true) {
				m.setPlayer(j);
				m.setOcup(2);
				m.update();
			}
			
			}

	}

	public boolean exists(int phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public void createPlayer(int phoneNumber, String name) {
		// TODO Auto-generated method stub
		
	}

}
