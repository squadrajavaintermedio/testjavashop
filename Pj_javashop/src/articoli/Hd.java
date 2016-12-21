package articoli;

public class Hd extends Articolo {

	double prezzo=0;
	int capacita=0;
	
	
	
	@Override
	public double getPrezzo() {
		// TODO Auto-generated method stub
		return prezzo;
	}

public Hd(String nome, String descrizione, double prezzo, int capacita, int quantita) {
	super(nome, descrizione, quantita);
	this.prezzo = prezzo;
	this.capacita = capacita;
}

	@Override
	public String scheda() {
		// TODO Auto-generated method stub
		return getNome()+" "+capacita;
	}

	public int getCapacita() {
		return capacita;
	}

	public void setCapacita(int capacita) {
		this.capacita = capacita;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	

}
