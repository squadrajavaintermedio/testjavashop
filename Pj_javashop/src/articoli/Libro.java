package articoli;

public class Libro extends Pubblicazione {
	public double prezzo;
	
	
	public Libro(String nome, String descrizione, double prezzo, int quantita) {
		super(nome, descrizione, quantita);
		this.prezzo = prezzo;
	}


	public Libro(String nome, String descrizione) {
		super(nome, descrizione);
	}


	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
}
