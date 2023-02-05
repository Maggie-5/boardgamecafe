package dbutils;

import persist.JogadorRDGW;
import persist.PersistenceException;

public class TestJogadorRDGW {

	public static void main(String[] args) throws PersistenceException {
		// TODO Auto-generated method stub
		JogadorRDGW j1 = new JogadorRDGW("alex", 9123);
		j1.insert();
	}

}
