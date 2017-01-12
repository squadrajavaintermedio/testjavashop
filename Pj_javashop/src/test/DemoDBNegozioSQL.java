package test;

import dao.DBNegozioSQL;
import articoli.Articolo;
import articoli.PC;
import java.util.ArrayList;
import articoli.Ram;
import articoli.Processore;
import articoli.Hd;

public class DemoDBNegozioSQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBNegozioSQL dbsql = new DBNegozioSQL("jdbc:sqlite:C:\\Users\\corso1\\Downloads\\sqlitestudio-3.1.1\\SQLiteStudio\\negozio");
		ArrayList<Articolo> listaRisultato = new ArrayList<Articolo>();
		listaRisultato = dbsql.articoli("pc");
		System.out.println("prima di for " + listaRisultato.size());
		for(int i = 0; i < listaRisultato.size(); i++){
			PC pc = (PC)listaRisultato.get(i);
			System.out.println(pc.getNome());
			Processore proc = pc.getProcessore();
			Ram ram = pc.getRam();
			Hd hd = pc.getHd();
			
			if(proc != null){
				System.out.println(proc.scheda());
			} else {
				System.out.println(proc);
			}
		}
	}

}
