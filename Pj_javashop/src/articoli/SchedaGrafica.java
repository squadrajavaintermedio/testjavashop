package articoli;

public class SchedaGrafica extends Articolo {

	double prezzo;
	
	public SchedaGrafica(String nome, String descrizione, double prezzo, int quantita) 
	{
		super(nome, descrizione, quantita);
		this.prezzo = prezzo;
	}

	public String scheda() {
		return getNome() + "\n" + getDescrizione() + "\n";
	}

	public String toXML() {
		return "<schedagrafica>" + "<nome>" + getNome() + "</nome>" + "<descrizione>" + getDescrizione()
				+ "</descrizione>" + "<prezzo>" + getPrezzo() + "</prezzo>" + "</schedagrafica>";
	}
}