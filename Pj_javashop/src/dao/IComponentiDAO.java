package dao;
import java.util.ArrayList;
import articoli.Articolo;


public interface IComponentiDAO {

	ArrayList<Articolo> leggi();
	ArrayList<Articolo> leggi(String tipo);
	
}
