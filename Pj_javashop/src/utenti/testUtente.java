package utenti;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import contenitori.Carrello;
import contenitori.Magazzino;
import dao.IDBNegozio;
import utenti.*;
import xml.DBNegozioXML;
import articoli.Articolo;

public class testUtente {
	
	/*package dammi
	 * 
	 * UtentiDiProva(login, password);
	 * cerca();
	 */

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String nome;
		String password;
		IDBNegozio dao = new DBNegozioXML("C:\\Users\\corso1\\Desktop\\pc.xml");
		
		Admin ut = new Admin("Dennis", "JARA", "login", "password", dao );
		Cliente c = new Cliente("Dennis", "JARA", "login", "password", dao);
		Carrello carrello = new Carrello();
		Magazzino m = new Magazzino(dao);
		ArrayList<Articolo> listaVg = c.ricerca("videogame", 100000, 1, true, "" );
		//ArrayList<Articolo> lista = c.ricerca("videogame", 1000000, 1, false, "videogame");
		System.out.println("\n**********\n" + c.leggi("videogame") + "**********\n");
		
		System.out.println("si dice Malak "+listaVg.size());
		System.out.println("Primo elemento: \n"+listaVg.get(0).scheda() + "\n");
		
		for(int i = 0; i < listaVg.size(); i++){
			System.out.println("\n\n" + listaVg.get(i).scheda());
			carrello.aggiungi(listaVg.get(i));
		}
		
		System.out.println(carrello.vedi() + "E prezzo totale: " + carrello.calcoloTot()
				+ "\nOra lo svuoto...");
		
		carrello.svuota();
		
		System.out.println(carrello.vedi());
		
		System.out.println("\nMagazzino: " + m.scorte("ram") + " pezzi");
		
		System.exit(-1);
		
//		Cliente u = new Cliente("Simone", "S", "symo", "password", null);
//		
//		
//		System.out.println("Inserisci login: ");
//		nome = in.readLine();
//	
//		System.out.println("Inserisci la password: ");
//		password = in.readLine();
//		
////		
//		System.out.println(u.loggato(nome, password));
//		
//		System.out.println("\n**********************\n");
		
		
		
		
	}
	
	
}
