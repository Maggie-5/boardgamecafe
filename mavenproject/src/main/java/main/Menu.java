package main;
import java.sql.SQLException;
import java.util.Scanner;

import org.omg.CORBA.portable.ApplicationException;

import persist.PersistenceException;
import scripts.CheckInTransactionScript;

public class Menu {
private static CheckInTransactionScript checkIn = new CheckInTransactionScript();
static void mainMenu(Scanner in) throws ApplicationException, PersistenceException {
boolean end = false;
do {
System.out.println("Choose an option: ");
System.out.println("Check in....................1");
System.out.println("Requisitar Jogo.............2");
System.out.println("List DB (for debug).........3");
System.out.println("Exit........................4");

System.out.println("> ");
switch (nextInt(in)) {
case 1:
checkIn(in);
break;
case 2:
requisitarJogo();
break;
case 3:
listDB();
break;
case 4:
end = true;
break;
}
} while (!end);
}
private static void requisitarJogo() {
}
private static void listDB() {
try {
dbutils.ListDatabase.doListDB();
} catch (PersistenceException e) {
System.err.println(e.getMessage());
e.printStackTrace();
} catch (SQLException e) {
System.err.println(e.getMessage());
e.printStackTrace();
}
}
private static void checkIn(Scanner in) throws ApplicationException, PersistenceException {
try {
System.out.println("Phone number: ");
int phoneNumber = nextInt(in);
String name = null;
if (!checkIn.exists(phoneNumber)) {
System.out.println("Your name ?");
name = nextLine(in);
checkIn.createPlayer(phoneNumber, name);
}
System.out.println("Table number: ");
int tableNumber = nextInt(in);
checkIn.checkIn(name, phoneNumber, tableNumber);
} catch (ApplicationException e) {
System.err.println("Application error while checkIn.");
System.err.println(e.getMessage());
e.printStackTrace();
}
}
/*
* The following methods read several kinds of values from a Scanner.
* The Scanner may correspond to System.in of to an input file. This allows
* automatic testing of the application through "use cases" that are tested

* using the executeUseCase method in the App class.
* The reason for using these methods instead of Scanner's nextXXX() methods
* is they allow comments in the use case files. Comments are begin with #
* and end at the end of the line.
*
*/
private static int nextInt(Scanner in) {
String s = in.nextLine();
while (s.startsWith("#")) {
s = in.nextLine();
}
if (s.contains("#")) {
try (Scanner sc = new Scanner(s)) {
s = sc.next();
}
}
int value = 0;
try {
value = Integer.parseInt(s);
} catch (NumberFormatException e) {
System.out.println("> ");
return nextInt(in);
}
return value;
}
private static double nextDouble(Scanner in) {
String s = in.nextLine();
while (s.startsWith("#")) {
s = in.nextLine();
}
if (s.contains("#")) {
try (Scanner sc = new Scanner(s)) {
s = sc.next();
}
}
return Double.parseDouble(s);
}
private static String nextLine(Scanner in) {
String s = in.nextLine();
while (s.startsWith("#")) {
s = in.nextLine();
}
if (s.contains("#")) {
int p = s.indexOf("#");
s = s.substring(0, p).trim();
}
return s;
}
private static int[] nextDate(Scanner in) {
String s = in.nextLine();
while (s.startsWith("#")) {
s = in.nextLine();
}
if (s.contains("#")) {
	
	int p = s.indexOf("#");
	s = s.substring(0, p).trim();
	}
	String[] a = s.split("/");
	int[] d = new int[3];
	d[0] = Integer.parseInt(a[0]);
	d[1] = Integer.parseInt(a[1]);
	d[2] = Integer.parseInt(a[2]);
	return d;
	}
	private static int[] nextTime(Scanner in) {
	String s = in.nextLine();
	while (s.startsWith("#")) {
	s = in.nextLine();
	}
	if (s.contains("#")) {
	int p = s.indexOf("#");
	s = s.substring(0, p).trim();
	}
	String[] a = s.split(":");
	int[] time = new int[2];
	time[0] = Integer.parseInt(a[0]);
	time[1] = Integer.parseInt(a[1]);
	return time;
	}
	}
