package main;
import java.util.Scanner;


import persist.DataSource;
import persist.PersistenceException;
public class BoardGameCafe {
public static final String DB_CONNECTION_STRING = "jdbc:derby:data/derby/boardgamecafedb";
public static void main(String[] args) throws ApplicationException, ApplicationException, Exception {
try {
DataSource.connect(DB_CONNECTION_STRING + ";create=false", "BoardGameCafe", "");
Menu.mainMenu(new Scanner(System.in));
} catch (PersistenceException e) {
throw new ApplicationException("Error connecting database", e);
}
}
}