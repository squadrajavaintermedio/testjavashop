package contenitori;

import java.util.ArrayList;

import articoli.Articolo;
import dao.DaoUser;
import dao.IDBNegozio;

public class Magazzino extends DaoUser{

	public Magazzino(IDBNegozio dao) {
		super(dao);
	}
	
	public int scorte(String tipo){
		int ris = 0;
		String prodotto = "";
		ArrayList<String>prodotti = new ArrayList<String>();
		
		prodotti.add("pc");
		prodotti.add("ram");
		prodotti.add("telefono");
		
		ArrayList<Articolo> lista = this.dao.articoli(prodotti.get(0));
		
		for(int i = 0; i < lista.size(); i++){
			ris += lista.get(i).getQuantita();
		}
		
		return ris;
	}

}