package articoli;

public class Ram extends Articolo {

	private double prezzo;
	private String tipo;
	private int giga = 0;
	
	public Ram(String nome, String descrizione, double prezzo, int giga, int quantita/*,String tipo*/) {
		super(nome, descrizione, quantita);
		this.prezzo = prezzo;
		this.giga = giga;
	}

	@Override
	public double getPrezzo() {
		return prezzo;
	}
	
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getGiga() {
		return giga;
	}

	public void setGiga(int giga) {
		this.giga = giga;
	}

	@Override
	public String scheda() {
		return getNome() + "\t" + getGiga();
	}

	public String toXML()
	{
		return
				"<ram>"
					+"<nome>"+getNome()+"</nome>"
					+"<giga>"+getGiga()+"</giga>"
					+
				"</ram>";
	}
}
