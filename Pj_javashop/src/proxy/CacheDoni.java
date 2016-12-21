package proxy;

import articoli.Articolo;
import articoli.Hd;
import articoli.Processore;
import articoli.Ram;
import articoli.VideogameConsole;
import articoli.VideogamePC;

public class CacheDoni {

	Articolo[] articoli = new Articolo[6];
	
	public CacheDoni()
	{
		//Creazione tramite prototipo
		articoli[1] = new VideogamePC("Dishonored 2", "TEST", 30, null, null, null, 0);
		articoli[2] = new VideogameConsole("Dishonored 2", "TEST", 30, "PL4", null, null, 0);
		articoli[3] = new Hd("Hard disk 1", "", 100, 30);
		articoli[4] = new Processore("Processore1", "Processore 1", 100, 3000);
		articoli[5] = new Ram("Ram 1", "",10, 100, "V");
	}

	
	public Articolo get(int i)
	{
		return articoli[i].clone();
	}
	
}
