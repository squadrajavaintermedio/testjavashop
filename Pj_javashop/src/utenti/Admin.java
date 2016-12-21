package utenti;

import java.util.ArrayList;
import contenitori.Magazzino;
import articoli.Articolo;
import dao.IDBNegozio;

public class Admin extends Utente{

	public Magazzino magazzino;
	
	public Admin(String nome, String cognome, String nomeUtente, String password, IDBNegozio dao) {
		super(nome, cognome, nomeUtente, password, dao);
		this.magazzino = new Magazzino(dao);
	}

	@Override //Categoria si intende il padre dell'articolo cercato
	public ArrayList<Articolo> ricerca(String categoria, double prezzoMassimo, int quantitaMinima, boolean controllavisibilita, String nome) {
			
			ArrayList<Articolo> lista = this.dao.articoli(categoria);
			ArrayList<Articolo> ris = new ArrayList<Articolo>();
			//	QUESTO SERVE PER DEBUGGARE
					System.out.println("La lista, che dovrebbe contenerli tutti, contiene:"+lista.size());
			
			for(Articolo a: lista){
				System.out.println(		"Condizione sul prezzo. Io valgo "
										+a.getPrezzo()
										+". La condizione sul prezzo è "+(a.getPrezzo() <= prezzoMassimo || prezzoMassimo == 0));
				
				System.out.println(		"Condizione sulla quantita. Io valgo "
										+a.getQuantita()
										+". La condizione sul quantita è "+(a.getQuantita() >= quantitaMinima));
				
				System.out.println(		"Condizione sulla visibilita. Io valgo "
						+a.isVisibilita()
						+". La condizione sulla visibilita è "+ (a.isVisibilita() || controllavisibilita==false));
				
				System.out.println(		"Condizione sul nome. Io valgo "
						+a.getNome()
						+". La condizione sul Nome è "+ (a.getNome().equals(nome) || nome.equals("")));
				
				
				if
				(
				(a.getPrezzo() <= prezzoMassimo || prezzoMassimo == 0)										&&
				(a.getQuantita() >= quantitaMinima)															&&
				(a.isVisibilita()  || controllavisibilita==false)											&&
				(a.getNome().equals(nome) || nome.equals(""))
				)
					ris.add(a);
			}
			
			return ris;
		}

}
