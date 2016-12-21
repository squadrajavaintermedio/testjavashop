package dao;
import articoli.*;

import java.util.ArrayList;

public class PubblicazioniDAO extends DaoUser implements IPubblicazioniDAO {

	public PubblicazioniDAO(IDBNegozio dao) {
		super(dao);
		
	}

	/*public ArrayList<Pubblicazioni> leggi() {
		ArrayList<Pubblicazioni> pu = new ArrayList<Pubblicazioni>();
		pu.addAll(leggi("rivista"));		//cambio con il metodo
		pu.addAll(dao.articoli("libro"));
		pu.addAll(dao.articoli("fumetto"));
		return pu;
	}*/

	public ArrayList<Pubblicazione> leggi() {
		ArrayList<Pubblicazione> pu = leggi("rivista");
		pu.addAll(leggi("libro"));
		pu.addAll(leggi("fumetto"));
		return pu;
	}

	public ArrayList<Pubblicazione> leggi(String tipo) {
		ArrayList<Pubblicazione> pi = new ArrayList<Pubblicazione>();
		for(Articolo a:dao.articoli(tipo))		
			pi.add((Pubblicazione)a);
		return pi;
	}

	// METODO CON LA FINZIONE DI FILTRO VECCHIO
	/*public ArrayList<Pubblicazioni> leggi(String tipo, String argomento, double prezzomassimo) {
		ArrayList<Pubblicazioni> pe = new ArrayList<Pubblicazioni>();
		ArrayList<Pubblicazioni> prov = new ArrayList<Pubblicazioni>();
		prov.addAll(leggi(tipo));
		For (Pubblicazione pb: prov)
			{
			if(pb.getPrezzo()<prezzomassimo && pb.getArgomento.equals(argomento))
				{
				pe.add(pb);
				}
			}
		return pe;
	}*/
	
	// METODO CON LA FINZIONE DI FILTRO VECCHIO
	/*public ArrayList<Pubblicazioni> leggi(String tipo, String argomento, double prezzomassimo) {
		ArrayList<Pubblicazioni> pe = new ArrayList<Pubblicazioni>();  	//CREO UN V
		ArrayList<Pubblicazioni> prov = leggi(tipo));					// USO UN VETTORE CREATI D'UN ALTRO METODO
		for (Pubblicazione pb: prov)
		{
			if(pb.getPrezzo()<prezzomassimo && pb.getArgomento.equals(argomento))
			{
				pe.add(pb);
			}
		}
		
		return pe;
	}*/
	
	// METODO CON LA FINZIONE DI FILTRO VECCHIO
	public ArrayList<Pubblicazione> leggi(String tipo, String argomento, double prezzomassimo) {
		ArrayList<Pubblicazione> pe = new ArrayList<Pubblicazione>();  	//CREO UN V
		ArrayList<Pubblicazione> prov = leggi(tipo);					// USO UN VETTORE CREATI D'UN ALTRO METODO
		for (Pubblicazione pb: prov)
		{
			boolean sg=true;
			if (argomento != "" && argomento != pb.getArgomento()) {sg = false; continue;}
			if (prezzomassimo != 0 && prezzomassimo < pb.getPrezzo()) {sg = false; continue;}
			if (sg) pe.add(pb);
	}
		return pe;
	}
}
