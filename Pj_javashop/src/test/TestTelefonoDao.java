package test;

import java.util.ArrayList;

import articoli.Pubblicazione;
import articoli.Telefono;
import dao.PubblicazioniDAO;
import dao.TelefonoDao;
import xml.DBNegozioXML;

public class TestTelefonoDao implements ITest{

	TelefonoDao t;
	
	public TestTelefonoDao(TelefonoDao t){
		this.t=t;
	}
	
	public String test() {

		String s="";
		ArrayList<Telefono> pp=t.leggi();
		if(pp.size()!=3){
			s+="\nerrore conta ( metodo leggi() ) (telefono)";
		if(!pp.get(0).getNome().equals("Telefono")) 
			s+="\nprima pubblicazione non quella prevista (telefono)";
		}
		if(s.equals(""))
			System.out.println("\ntesto riuscito (telefono)");
		
		return s;
		
		
		
	}
	}
	


