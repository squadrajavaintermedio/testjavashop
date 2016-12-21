package utenti;

import java.util.ArrayList;

import articoli.Articolo;

public interface IUtenti {
	
	public ArrayList<Articolo> ricerca(String categoria , double prezzoMassimo , int quantitaMinima , boolean visibilita , String nome);
	
}
