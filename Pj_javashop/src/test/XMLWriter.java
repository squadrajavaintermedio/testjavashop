package test;
import java.io.*;

import articoli.Articolo;

public class XMLWriter {

//Questo coso funziona solo sulla RAM!
	public static void scriviArticolo(
			Articolo a, 
			String percorso
	)
	{
		FileWriter f = null;
		try
		{
			f = new FileWriter(percorso);
			f.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			f.write(a.toXML());
			//Non bello averlo qui...
			f.close();
		}
		catch(IOException e)
		{
			//Goodbye cruel world, it's over
			//Walk on by
			System.out.println(e.getMessage());
		}
		
	}
	
}
