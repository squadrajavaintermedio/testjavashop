package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import dao.DBNegozioSQL;
import articoli.Articolo;
import articoli.PC;
import articoli.Console;
import articoli.VideogameConsole;
import articoli.VideogamePC;
import articoli.Libro;
import articoli.Rivista;
import articoli.Ram;
import articoli.Processore;
import articoli.Hd;

public class DemoDBNegozioSQL {

	private final static String JDBC = "jdbc:sqlite:C:\\sqlite\\negozio.db";
	//private final static String JDBC = "jdbc:sqlite:/Users/hirokiinoue/sqlite/negozio.db";
	
	private final static String TYPE_STR_PC = "pc";
	private final static String TYPE_STR_CONSOLE = "console";
	private final static String TYPE_STR_VIDEOGAMES = "videogames";
	private final static String TYPE_STR_LIBRI = "libri";
	private final static String TYPE_STR_RIVISTE = "riviste";
	private final static String TYPE_STR_PROCESSORE = "processore";
	private final static String TYPE_STR_RAM = "ram";
	private final static String TYPE_STR_HD = "hd";

	private static void visualizzaScheda(ArrayList<Articolo> lista){
		for(int i = 0; i < lista.size(); i++){
			if (lista.get(i) instanceof Console) {
				Console console = (Console)lista.get(i);
				System.out.println(console.scheda());
			} else if (lista.get(i) instanceof PC) {
				PC pc = (PC)lista.get(i);
				System.out.println(pc.scheda());
			} else if (lista.get(i) instanceof VideogamePC ){
				VideogamePC vgPC = (VideogamePC) lista.get(i);
				System.out.println(vgPC.scheda());				
			} else if (lista.get(i) instanceof VideogameConsole ) {
				VideogameConsole vgConsole = (VideogameConsole) lista.get(i);
				System.out.println(vgConsole.scheda());								
			} else if (lista.get(i) instanceof Libro ) {
				Libro libro = (Libro) lista.get(i);
				System.out.println(libro.scheda());				
			} else if (lista.get(i) instanceof Rivista ) {
				Rivista rivista = (Rivista) lista.get(i);
				System.out.println(rivista.scheda());				
			}
		}		
	}

	public static void main(String[] args) throws IOException {
		
		DBNegozioSQL dbsql = new DBNegozioSQL(JDBC, true);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String strInput = "";
		int intInput = 0;
		double doubleInput = 0.0;
		int rtnValue = 0;
		String nome;
		String descrizione;
		double prezzo;
		int quantita;
		String autore;
		
		// count
		System.out.println("------- start check logic for count method -------");
		System.out.println("count cash:console		= " + dbsql.numeroArticoli(TYPE_STR_CONSOLE, true));
		System.out.println("count cash:videogame	= " + dbsql.numeroArticoli(TYPE_STR_VIDEOGAMES, true));
		System.out.println("count cash:libri		= " + dbsql.numeroArticoli(TYPE_STR_LIBRI, true));
		System.out.println("count cash:riviste		= " + dbsql.numeroArticoli(TYPE_STR_RIVISTE, true));
		System.out.println("count cash:pc			= " + dbsql.numeroArticoli(TYPE_STR_PC, true));
		
		System.out.println("count no cash:console	= " + dbsql.numeroArticoli(TYPE_STR_CONSOLE, false));
		System.out.println("count no cash:videogame	= " + dbsql.numeroArticoli(TYPE_STR_VIDEOGAMES, false));
		System.out.println("count no cash:libri		= " + dbsql.numeroArticoli(TYPE_STR_LIBRI, false));
		System.out.println("count no cash:riviste	= " + dbsql.numeroArticoli(TYPE_STR_RIVISTE, false));
		System.out.println("count no cash:pc		= " + dbsql.numeroArticoli(TYPE_STR_PC, false));
		System.out.println("------- e n d check logic for count method -------\n");

		// scheda
		System.out.println("------- start check logic for visualizza scheda method -------");
		System.out.println("\n****	scheda	console	****");
		visualizzaScheda(dbsql.articoli(TYPE_STR_CONSOLE));
		System.out.println("\n****	scheda	videogame	****");
		visualizzaScheda(dbsql.articoli(TYPE_STR_VIDEOGAMES));
		System.out.println("\n****	scheda	libri	****");
		visualizzaScheda(dbsql.articoli(TYPE_STR_LIBRI));
		System.out.println("\n****	scheda	riviste	****");
		visualizzaScheda(dbsql.articoli(TYPE_STR_RIVISTE));
		System.out.println("\n****	scheda	pc	****");
		visualizzaScheda(dbsql.articoli(TYPE_STR_PC));
		System.out.println("------- e n d check logic for visualizza scheda method -------");		
		
		// Insert
		System.out.println("------- start check logic for aggiunge articolo method -------");
		System.out.println("Cosa vuole aggiungere nel Database?\nPotrebbe sceriere nel sotto lista.");
		System.out.println("1. Console\n2. Videogame\n3. Libri\n4. Riviste\n5. PC\n");
		try {
			intInput = Integer.parseInt(br.readLine());			
		} catch (NumberFormatException e) {
			System.err.print("Invalid Format!");
		}
		switch(intInput) {
		case 1:// Console
			System.out.println("Inserisca i dati.");
			System.out.print("Nome -> ");
			strInput = br.readLine();
			nome = strInput;
			System.out.print("Descrizione -> ");
			strInput = br.readLine();
			descrizione = strInput;
			System.out.print("Prezzo -> ");
			doubleInput =Double.parseDouble(br.readLine());
			prezzo = doubleInput;
			System.out.print("quantita' -> ");
			intInput = Integer.parseInt(br.readLine());
			quantita = intInput;
			System.out.print("Autore -> ");
			strInput = br.readLine();
			autore = strInput;
			Console console = new Console(nome,descrizione,prezzo,quantita);
			if (autore == null || autore.equals(""))
				rtnValue = dbsql.addArticolo(console);
			else
				rtnValue = dbsql.addArticolo(console, autore);
			break;
		case 2:// Videogame
			break;
		case 3:// Libri
			break;
		case 4:// Riviste
			break;
		case 5:// PC
			break;
		default:
			System.out.println("Il numero che ha digitato non è valido.");
		}
		System.out.println(rtnValue);
		System.out.println("------- e n d check logic for aggiunge articolo method -------");		
		
	}
}
