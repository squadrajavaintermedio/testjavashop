package dao;

import java.util.ArrayList;

import articoli.Articolo;
import xml.DBNegozioXML;

public class TestComponentiDAO {

	public static void main(String[] args) {

		ComponentiDAO db = 
				new ComponentiDAO(
						new DBNegozioXML("C:\\Users\\corso1\\Desktop\\negozio.xml"));
		
		ArrayList<Articolo> ris = db.leggi();
		
		for(int i=0;i<ris.size();i++)
			if(ris.get(i)!=null)
				System.out.println(ris.get(i).scheda());
		
	}

}