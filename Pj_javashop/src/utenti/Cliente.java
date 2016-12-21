package utenti;

import java.util.ArrayList;

import articoli.Articolo;
import dao.IDBNegozio;

public class Cliente extends Utente{

	public Cliente(String nome, String cognome, String nomeUtente, String password, IDBNegozio dao) {
		super(nome, cognome, nomeUtente, password, dao);
		}

	@Override
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
			(controllavisibilita == true)																	&&
			(a.getNome().equals(nome) || nome.equals(""))
			)
				ris.add(a);
		}
		
		return ris;
	}

	//Metodi di prova
	public String leggi(String nome){
		ArrayList<Articolo> lista = this.dao.articoli(nome);
		String ris = "";
		
		for(int i = 0; i < lista.size(); i++)
			ris += lista.get(i).getQuantita() + "\n";
		
		return ris;
	}

}
