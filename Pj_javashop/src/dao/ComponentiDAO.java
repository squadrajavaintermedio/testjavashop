package dao;

import java.util.ArrayList;

import articoli.Articolo;

public class ComponentiDAO extends DaoUser implements IComponentiDAO {

	//COSTRUTTORE: VIENE INSERITO SEMPLICEMENTE IL dao DELLA CLASSE DAO (PADRE)
	public ComponentiDAO(IDBNegozio dao) {
		super(dao);
	}

	//METODO CHE RESTITUISCE TUTTI I COMPONENTI CHE ABBIAMO IN NEGOZIO, MA CHE NON APPARTENGONO AI PC
	public ArrayList <Articolo> leggi() {

		ArrayList<Articolo> ris = new ArrayList<Articolo>();
		
		//SE IL METODO CI RESTITUISCE ALMENO UN ARTICOLO DI QUELLI RICHIESTI ALLORA APPLICO IL METODO E LO AGGIUNGO A RIS
		if (this.dao.articoli ("ram","pc").size() > 0) ris.addAll(this.dao.articoli("ram","pc"));
		if (this.dao.articoli ("processore","pc").size() > 0) ris.addAll(this.dao.articoli ("processore","pc"));
		if (this.dao.articoli ("hd","pc").size() > 0) ris.addAll(this.dao.articoli ("hd","pc"));
		if (this.dao.articoli ("schedavideo","pc").size() > 0) ris.addAll(this.dao.articoli ("schedavideo","pc"));
		if (this.dao.articoli ("joypad","pc").size() > 0) ris.addAll(this.dao.articoli ("joypad","pc"));

		return ris;
	}

	//METODO CHE RESTITUISCE TUTTI I COMPONENTI DI UN DETERMINATO TIPO SCELTO DALL'UTENTE MA CHE NON APPARTENGONO AI PC
	public ArrayList<Articolo> leggi(String tipo) {
		
		ArrayList<Articolo> ris = new ArrayList<Articolo>();
		
		if (this.dao.articoli (tipo,"pc").size() > 0) ris.addAll(this.dao.articoli (tipo,"pc"));

		return ris;
	}



}
