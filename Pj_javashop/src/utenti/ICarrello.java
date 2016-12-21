package utenti;

import articoli.Articolo;

public interface ICarrello {
	
	public void aggiungi();
	public void svuota();
	public Articolo vedi();
	public double calcolaTot();

}
