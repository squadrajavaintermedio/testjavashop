package articoli;

public abstract class Pubblicazione extends Articolo{
	
	private int anno;
	private String autore;
	private String argomento;
	private String tipo;
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getArgomento() {
		return argomento;
	}

	public void setArgomento(String argomento) {
		this.argomento = argomento;
	}

	public Pubblicazione(String nome, String descrizione, int quantita) {
		super(nome, descrizione, quantita);
		
		
	}

	@Override
	public double getPrezzo() {
		return 0;
	}
	
}
