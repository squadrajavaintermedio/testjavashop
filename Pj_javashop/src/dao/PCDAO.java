package dao;

import java.util.ArrayList;

import articoli.Articolo;
import articoli.PC;

public class PCDAO extends DaoUser implements IPCDAO {

	public PCDAO(IDBNegozio dao) {
		super(dao);
	}
	
	
	@Override
	public ArrayList<PC> leggi()
	{
		ArrayList<PC> ris = new ArrayList<>();
		ArrayList<Articolo> lista = this.dao.articoli("pc");
		for (Articolo a : lista) {
			if(a instanceof PC)
			{
				PC p = (PC) a;
				ris.add(p);
				//SCAFFHOLDING
				System.out.println(a.getNome());
			}
			//SCAFFHOLDING
			if(a==null) System.out.println("Hey amico, io sono vuoto!");
		}
		return ris;}
		
		
//		
//	public ArrayList<PC> pc(int ram) 
//	{
//
//		ArrayList<PC> ris = new ArrayList<>();
//		ArrayList<Articolo> lista = this.dao.articoli("pc");
//		for (Articolo a : lista) {
//			PC p = (PC) a;
//			if (p.getRam().getGiga() >= ram)
//				ris.add(p);
//		}
//		return ris;
//	}

	

	@Override
	public ArrayList<PC> leggi(String marca, int ramminima, int hdminimo,  double prezzomax) {
		
		ArrayList<PC> pc = new ArrayList<>();
		ArrayList<Articolo> lista = this.dao.articoli("pc");
		
		for (Articolo a : lista){
			PC p = (PC) a;
			
			
			if(
					//o la marca corrisponde O la marca è vuota e non la sto cercando
				(p.getMarca().equals(marca) || marca=="")
				&&
				(p.getRam().getGiga()>=ramminima)
				&&
				(p.getHd().getCapacita() >= hdminimo)
//				&&	
//				(p.getHd().getNome().equals(tipohd) && tipohd== "")
				&&
				(p.getPrezzo()<prezzomax || prezzomax==0)	
					
				)
				
				pc.add(p);
			
//			
//			if (p.getMarca().equals(marca) && marca!= null)
//				
//			if (p.getRam().getGiga() >= ramminima && ramminima!=0 )
//				
//			if (p.getHd().getCapacita() >= hdminimo && hdminimo!=0)
//				//nome sta per tipo
//			if (p.getHd().getNome().equals(tipohd) && tipohd!= null)
//				
//			if (p.getPrezzo() <= prezzomax && prezzomax!=0)
//				
//				pc.add(p);
//			
			
		}//fine for articolo a : lista
		
		return pc;
	}
}
