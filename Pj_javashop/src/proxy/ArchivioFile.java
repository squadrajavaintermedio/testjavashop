package proxy;

import java.io.FileInputStream;
import java.util.Scanner;

public class ArchivioFile implements IArchivioFile {

	@Override
	public String leggi(String percorso) {

		String contenuto = "";
		try
		{
			Scanner s = new Scanner(new FileInputStream(percorso));
			while(s.hasNextLine()) contenuto+=s.nextLine();
		}
		catch(Exception e)
		{
			//Non far niente
		}
		return contenuto;
		
	}

}
