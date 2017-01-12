package test;

import java.util.ArrayList;

public class Test2 {

	public static void main(String[] argv)
	{
		String comando = "add 100 unsacco";
		String parti[] = comando.split(" ");
		String verbo = parti[0];
		String ris = "non definita";
		
		String comandiValidi[] = new String[]{"pc","login","salva","add"};

		boolean valido = false;
		
		switch(parti[0]){
		case "add":
			ris = Integer.parseInt(parti[1])+Integer.parseInt(parti[2]);
		default:
		}

		System.out.println(esegui(comando));
	}
	
}
