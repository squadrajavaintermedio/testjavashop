package articoli;

public class Processore extends Articolo {

	double prezzo;
	int benchmark;
	
	public Processore(String nome, String descrizione, double prezzo,
			int benchmark, int quantita) {
		super(nome, descrizione, quantita);
		this.prezzo = prezzo;
		this.benchmark = benchmark;
	}

	@Override
	public double getPrezzo() {
		// TODO Auto-generated method stub
		return prezzo;
	}

	@Override
	public String scheda() {
		// TODO Auto-generated method stub
		return getNome()+" "+getDescrizione()+" "+benchmark;
	}

}
