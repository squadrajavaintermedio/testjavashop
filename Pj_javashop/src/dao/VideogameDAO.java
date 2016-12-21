package dao;

import java.util.ArrayList;

import articoli.Articolo;
import articoli.Videogame;
import articoli.VideogameConsole;
import articoli.VideogamePC;


public class VideogameDAO extends DaoUser implements IVideogameDAO {
	
	
	public VideogameDAO(IDBNegozio dao) {
	super(dao);
}

	//Metodo che restituisce un ArrayList di Videogame,
	//con tutti i videogame presenti nel file xml
@Override
public ArrayList<Videogame> leggi(){
	ArrayList<Videogame> vl = new ArrayList<Videogame>();
	ArrayList<Articolo> lista = this.dao.articoli("videogame");
	for(Articolo a:lista){
		if(a instanceof Videogame){
			Videogame v = (Videogame) a;
			vl.add(v);
		}
		
	}
	return vl;
}
		//Metodo che restituisce un ArrayList di Videogame,
		//con i videogame filtrati per genere, piattaforma e prezzo massimo
public ArrayList<Videogame> leggi(String genere, String piattaforma,
		double prezzoMax ) {
	ArrayList<Videogame> vl = new ArrayList<Videogame>();
	ArrayList<Videogame> lista = leggi();
	for (Videogame v: lista){
		System.out.println(v.getGenere());
		if( v.getGenere().equals(genere) &&
			v.getPrezzo() <= prezzoMax &&
			v.getPiattaforma().equals(piattaforma))
			vl.add(v);
	}
		return vl;
}

}
