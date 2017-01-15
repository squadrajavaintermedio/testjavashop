package articoli;

public class VideogameConsole extends Videogame {

	public VideogameConsole(String nome, String descrizione, double prezzo, String piattaforma, String genere ,String disponibilita , int quantita )
	{
		super(nome,descrizione,prezzo,piattaforma, genere, disponibilita, quantita);
	}

	public String scheda()
	{
		return toString();
	}
	
	public boolean compatibile(Articolo piattaforma)
	{
		return (piattaforma.getNome()==getPiattaforma());
	}
		
	@Override
	public String toString() {
		return "VideogameConsole \n"
				+ "Piattaforma\t:" + getPiattaforma()
				+ "\nPrezzo\t\t:" + getPrezzo()
				+ "\nNome\t\t:" + getNome()
				+ "\nDescrizione\t:" + getDescrizione()
				+ "\nQuantita\t:" + getQuantita();
	}
	
	
	
}

