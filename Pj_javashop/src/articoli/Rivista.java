package articoli;

public class Rivista extends Periodico {

	private double prezzo;
	
	public Rivista (String nome, String descrizione){
		super(nome, descrizione);
		
	}

	public Rivista(String nome, String descrizione, String autore,
			String periodicita, double prezzo) {
		super(nome, descrizione);
		this.setPrezzo(prezzo);
	}

	public double getPrezzo() {
		return prezzo;
	}

	//Fluent restituisce l'oggetto stesso, .this invece di void (SET) Rivista
	public Rivista setPrezzo(double prezzo) {
		this.prezzo = prezzo;
		return this;
	}


}

