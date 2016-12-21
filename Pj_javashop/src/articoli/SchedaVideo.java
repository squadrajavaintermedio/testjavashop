package articoli;

public class SchedaVideo extends Articolo {

	double prezzo = 0;
	int benchmark = 0;
	
	
	public SchedaVideo(String nome, String descrizione, double prezzo,
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
		return getNome()+" "+prezzo+" "+benchmark;
	}

}
