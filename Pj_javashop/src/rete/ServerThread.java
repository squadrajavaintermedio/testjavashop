package rete;

import dao.DBNegozioSQL;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import articoli.Articolo;

public class ServerThread extends Thread {

	int ThreadNum = 0;
	DBNegozioSQL db;
	Socket client;

	private static final String KEYWORDEXIT = "exit";
	private static final String SEPARATOR = ",";

	// Un thread che si occuperà di lavorare con un solo client. Alla morte del client, anche questo thread morirà
	public ServerThread(int threadNum, DBNegozioSQL db, Socket client){
		this.ThreadNum = threadNum;
		this.db = db;
		this.client = client;
	}
	
	public void run(){
		String command = "";
		String nomeMetodo = "";
		int ThreadNum = 0;
		
		try {			
			DataInputStream fromClient = new DataInputStream(client.getInputStream());
			DataOutputStream toClient = new DataOutputStream(client.getOutputStream());
			
			do {
				command = fromClient.readUTF();
				String[] aryStr = command.split(SEPARATOR);
				nomeMetodo = aryStr[0];
				if(!command.equals(KEYWORDEXIT)){
					switch(nomeMetodo){
					case "articoli":
						ArrayList<Articolo> lista = new ArrayList<Articolo>();
						lista = db.articoli(aryStr[1]);
						for(int k = 0; k < lista.size(); k++){
							toClient.writeUTF(lista.get(k).scheda());
						}					
						toClient.writeUTF("Desidera qualcos'altro?");
						break;
					case "login":
					case "salva":
						break;
					default:
						toClient.writeUTF("Salve Gentile Cliente"+ ThreadNum + ", può digitare questo formato, per favore.");
						toClient.writeUTF("comando, parametro1, parametro2, ...");
						toClient.writeUTF("es: articolo,pc");
						toClient.writeUTF("Se vuole uscire da questo discorso, digiti \"exit\"." );
					}
				} else {
					toClient.writeUTF("Chiudo questo discorso, grazie mille." );					
				}
			} while (!command.equals(KEYWORDEXIT));

			System.out.println("close server session.");
			fromClient.close();
			toClient.close();
			client.close();
			System.out.println("disconnect.");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
