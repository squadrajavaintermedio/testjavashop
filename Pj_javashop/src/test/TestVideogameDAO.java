package test;

import java.util.ArrayList;

import articoli.Pubblicazione;
import articoli.Telefono;
import articoli.Videogame;
import dao.PubblicazioniDAO;
import dao.TelefonoDao;
import dao.VideogameDAO;
import xml.DBNegozioXML;

public class TestVideogameDAO implements ITest{

	VideogameDAO t;
	
	public TestVideogameDAO(VideogameDAO t){
		this.t=t;
	}
	
	public String test() {

		String s="";
		ArrayList<Videogame> pp=t.leggi();
		if(pp.size()!=3){
			s+="\nerrore conta ( metodo leggi() ) (videogame)";
		if(!pp.get(0).getNome().equals("Dishonored")) 
			s+="\nprimo videogame non quella previsto (videogame)";
		}
		if(s.equals(""))
			System.out.println("\ntesto riuscito (videogame)");
		
		return s;		
	}
	}
	


