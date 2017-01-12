package rete;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

	private static final String KEYWORDEXIT = "exit";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		try {
			Socket client = new Socket("localhost", 8080);
			
			DataInputStream toClient = new DataInputStream(client.getInputStream());
			DataOutputStream fromClient = new DataOutputStream(client.getOutputStream());

			System.out.println("E' Accettata il server.");
			fromClient.writeUTF("C'è un cliente.");
			
			do {
				System.out.println("Risposta del server:" + toClient.readUTF());
				while(toClient.available()>0){
					//System.out.println("client log4");
					System.out.println("Risposta del server:" + toClient.readUTF());
				}
//				System.out.println("Prova digitare qualche parole, lui risponderà.");
				//System.out.println("client log1");
				input = in.readLine();
				//System.out.println("client log2");
				fromClient.writeUTF(input);
				//System.out.println("client log3");
				//System.out.println("client log5");
			} while (!input.equals(KEYWORDEXIT));
			
			System.out.println("close client session.");
			toClient.close();
			fromClient.close();
			client.close();
			System.out.println("disconnect.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
