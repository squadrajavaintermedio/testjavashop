package articoli;

public class Joypad extends Articolo{

	double prezzo;
	
	public Joypad(String nome, String descrizione, double prezzo, int quantita) {
		super(nome, descrizione, quantita);
		this.prezzo = prezzo;
	}



	public String scheda()
	{
		return getNome()+"\n"+getDescrizione()+"\n";
	}
	
	public String toXML(){
		return "<joypad>"
					+"<nome>"+getNome()+"</nome>"
					+"<descrizione>"+getDescrizione()+"</descrizione>"
					+"<prezzo>"+getPrezzo()+"</prezzo>"
				+"</joypad>";
	}
}