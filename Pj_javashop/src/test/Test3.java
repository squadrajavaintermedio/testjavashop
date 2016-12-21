package test;

import proxy.ArchivioFile;
import proxy.FileCache;

public class Test3 {

	public static void main (String argv[])
	{
		FileCache f = new FileCache(new ArchivioFile());
		
		String percorso = "C:\\Users\\user\\Documents\\test.txt.1";
	
		int livello;
		try
		{
			livello = Integer.parseInt(
							percorso.substring(
								percorso.length()-1, 
								percorso.length()
									)
						);
		}
		catch(Exception e)
		{
			livello = 99;
			
		}
			
		String f1 = f.leggi(percorso);
		System.out.println(livello);
		

		
		
		
	}
	
}
