package dao;

import java.util.ArrayList;

import articoli.PC;

public interface IPCDAO {
	
	public ArrayList<PC> leggi();
	
	public ArrayList<PC> leggi( 
			
			String marca,
			int ramminima,
			int hdminimo,
//			String tipohd,
			double prezzomax
			
			);

}
