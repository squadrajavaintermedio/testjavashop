package test;

import java.util.ArrayList;

import articoli.Articolo;
import articoli.PC;
import dao.PCDAO;

import xml.DBNegozioXML;

public class TestPcDao {
	public static void main(String[] args) {
		
		
		
		
		
		DBNegozioXML db = 
				new DBNegozioXML(
				"C:\\Users\\Utente\\Desktop\\JAVA ANT\\xml\\pc.xml");
		
		PCDAO pcdao = new PCDAO(db);
		
		ArrayList<PC> pclista = pcdao.leggi("Lenovo", 5, 10,  900);
		
		
		for(PC pc:pclista)
			if(pc!=null) System.out.println(pc.scheda());
		
		
	}

}
