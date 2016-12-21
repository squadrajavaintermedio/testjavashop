package dao;
import java.util.*;
import articoli.*;


public interface IPubblicazioniDAO {

	public ArrayList<Pubblicazione> leggi();
	public ArrayList<Pubblicazione> leggi(String tipo);
	public ArrayList<Pubblicazione> leggi(String tipo, String argomento, double prezzomassimo);
	
	
}
