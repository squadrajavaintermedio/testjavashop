package articoli;

public class Console extends Articolo  {

	private double prezzo;
		
	public Console(String nome, String descrizione, double prezzo, int quantita) {
		super(nome, descrizione, quantita);
		this.prezzo = prezzo;
	}

	@Override
	public double getPrezzo() {
		// TODO Auto-generated method stub
		return prezzo;
	}

	@Override
	public String scheda() {
		// TODO Auto-generated method stub
		return getNome()+ " " + getPrezzo();
	}
	
	public String toXML()
	{
		return "<console>"+getNome()+"</console>";
		
	}

}
