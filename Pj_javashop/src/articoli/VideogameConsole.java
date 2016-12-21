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
				+ "Piattaforma: " + getPiattaforma()
				+ "\nPrezzo: " + getPrezzo() + " €"
				+ "\nNome: " + getNome()
				+ "\nDescrizione: " + getDescrizione()
				+ "\nQuantità: " + getQuantita();
	}
	
	
	
}

