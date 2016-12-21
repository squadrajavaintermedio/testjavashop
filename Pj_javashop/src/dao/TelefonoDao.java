package dao;

import java.util.ArrayList;

import articoli.Articolo;
import articoli.Telefono;


public class TelefonoDao extends DaoUser implements ITelefonoDAO{

	public TelefonoDao(IDBNegozio dao) {
		super(dao);
	}

	@Override
	public ArrayList<Telefono> leggi() {
		
		ArrayList<Telefono> telefoni = new ArrayList<Telefono>();
		ArrayList<Articolo> lista = this.dao.articoli("telefono");
		
		for(Articolo a: lista){
			Telefono t;
			if(a instanceof Telefono){
				t = (Telefono) a;
				telefoni.add(t);
			}
		}
		return telefoni;
	}

	@Override
	public ArrayList<Telefono> leggi(String marca, int ramMinima, double prezzoMax, int memoriaMinima, String sistemaOperativo, String LTE, String dualSim) {

		ArrayList<Telefono> workList = new ArrayList<Telefono>();
		ArrayList<Telefono> ris = new ArrayList<Telefono>();
		workList = leggi();
		
				for(int i = 0; i<workList.size(); i++)
					if
					((workList.get(i).getMarca().equals(marca) || marca.equals(""))										&&
					(workList.get(i).getRam()>ramMinima)																&&
					(workList.get(i).getPrezzo()<prezzoMax || prezzoMax==0)												&&
					(workList.get(i).getHd()>memoriaMinima)																&&
					(workList.get(i).getSistemaOperativo().equals(sistemaOperativo) || sistemaOperativo.equals(""))		&&
					(workList.get(i).getLTE().equals(LTE) || LTE.equals(""))											&&
					(workList.get(i).getDualSim().equals(dualSim) || dualSim.equals("")))
							ris.add(workList.get(i));
				
		return ris;
	}
}