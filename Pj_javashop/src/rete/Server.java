package rete;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import dao.DBNegozioSQL;
import java.util.ArrayList;
import articoli.Articolo;

public class Server {
	
	public static void main(String args[]){
		
		DBNegozioSQL db = new DBNegozioSQL("jdbc:sqlite:C:\\Users\\corso1\\Downloads\\sqlitestudio-3.1.1\\SQLiteStudio\\negozio");
		String command = "";
		String nomeMetodo = "";
		int ThreadNum = 0;
		
		try {
			// Dentro le tonde ci va il numero della porta
			ServerSocket server = new ServerSocket(8080);
			
			while(true){
				// Questo serve per far aprire/accettare la porta del server
				System.out.println("Ok, sto servendo.");
				Socket client = server.accept();
				ThreadNum++;
				System.out.println("E' arrivato un thread, lo do un numero " + ThreadNum);
				new ServerThread(ThreadNum, db, client).start();
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
