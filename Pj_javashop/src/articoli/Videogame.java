package articoli;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class Videogame extends Articolo {

	private double prezzo;
	private String piattaforma;
	private String genere;
	private String disponibilita;

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(String disponibilita) {
		this.disponibilita = disponibilita;
	}
	
	

	public Videogame(String nome, String descrizione, double prezzo,
			String piattaforma, String genere, String disponibilita,int quantita) {
		super(nome, descrizione, quantita);
		this.prezzo = prezzo;
		this.piattaforma = piattaforma;
		this.genere = genere;
		this.disponibilita = disponibilita;
	}
	
//	public static Videogame crea(String nome, String descrizione, double prezzo, String piattaforma)
//	{
//		Videogame v = null;
//		switch(piattaforma)
//		{
//		case "PC":
//			v = new VideogamePC(nome,descrizione, prezzo);
//			break;
//		case "YBOX":
//			v = new VideogameConsole(nome,descrizione,prezzo,piattaforma);
//			break;
//		case "PL4":
//			v = new VideogameConsole(nome,descrizione,prezzo,piattaforma);
//			break;
//		default:
//			v = null;
//		}
//		return v;
//		
//	}

	public abstract boolean compatibile(Articolo piattaforma);
	
	
	public String getPiattaforma() {
		return piattaforma;
	}

	public void setPiattaforma(String piattaforma) {
		this.piattaforma = piattaforma;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	@Override
	public double getPrezzo() {
		// TODO Auto-generated method stub
		return prezzo;
	}
	
	public String scheda()
	{
		return super.scheda()+" "+piattaforma+" "+genere;
	}
	
	public static Articolo creaDaXML(Node n)
	{
		return null;
	}

	@Override
	public String toString() {
		return "VideogameConsole \n"
				+ "Piattaforma: " + getPiattaforma()
				+ "\nPrezzo: " + getPrezzo() + " €"
				+ "\nNome: " + getNome()
				+ "\nDescrizione: " + getDescrizione()
				+ "\nQuantità: " + getQuantita();
	}
	
	
	
	
}
