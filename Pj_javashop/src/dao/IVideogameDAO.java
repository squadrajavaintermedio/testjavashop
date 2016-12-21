package dao;
import java.util.ArrayList;
import articoli.*;

public interface IVideogameDAO {
	
	public ArrayList<Videogame> leggi();
	
	public ArrayList<Videogame> leggi(String genere, String piattaforma, double prezzoMax);

}
