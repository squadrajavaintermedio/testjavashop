package articoli;

public class Periodico extends Pubblicazione {
	
	private String periodicita;

	
	public String getPeriodicita() {
		return periodicita;
	}


	public void setPeriodicita(String periodicita) {
		this.periodicita = periodicita;
	}


	public Periodico(String nome, String descrizione, int quantita) {
		super(nome, descrizione, quantita);
		this.periodicita = periodicita;
	}

}
