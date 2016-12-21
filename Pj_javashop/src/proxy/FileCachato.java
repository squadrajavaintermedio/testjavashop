package proxy;
import java.io.*;
import java.util.Scanner;

public class FileCachato {
	
	int accessi = 0;
	public String percorso;
	public String contenuto;
	
	public FileCachato(String percorso, String contenuto)
	{
		this.percorso = percorso;
		this.contenuto = contenuto;
	}

	
	public String getContenuto()
	{
		accessi++;
		return contenuto;
	}
	
}
