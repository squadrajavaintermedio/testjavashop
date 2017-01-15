package articoli;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class VideogamePC extends Videogame {

	public VideogamePC(String nome, String descrizione, double prezzo, String piattaforma, String disponibilita, String genere, int quantita )
	{
		super(nome,descrizione,prezzo,piattaforma, genere, disponibilita, quantita);
	}

	
	public boolean compatibile(Articolo piattaforma)
	{
		return piattaforma instanceof PC;
	}
	
	@Override
	public String scheda() {
		return "VideogamePC\n"
				+ "Piattaforma\t:" + getPiattaforma()
				+ "\nPrezzo\t\t:" + getPrezzo()
				+ "\nNome\t\t:" + getNome()
				+ "\nDescrizione\t:" + getDescrizione()
				+ "\nQuantita\t:" + getQuantita();
	}
	
	

}
