package contenitori;

import java.util.ArrayList;
import articoli.Articolo;

public class Carrello {
	
	ArrayList<Articolo>listaSpesa = new ArrayList<Articolo>();
	
	public void aggiungi(Articolo articolo){
		listaSpesa.add(articolo);
	}
	
	public void svuota(){
		listaSpesa.clear();
	}
	
	public String vedi(){
		String ris = "";
			
		if(listaSpesa.isEmpty())
			ris += "Carrello vuoto";
		else{
			ris += "Lista della spesa: ";
		for(int i = 0; i < listaSpesa.size(); i++){
			ris += "\nProdotto " + (i+1) + ") " + listaSpesa.get(i).scheda() + "\n";
		}
		}

		return ris;
	}
	
	public ArrayList<Articolo> getListaSpesa() {
		return listaSpesa;
	}
	
	public double calcoloTot(){
		double ris = 0.0;
		for(int i = 0; i < listaSpesa.size(); i++){
			ris += listaSpesa.get(i).getPrezzo();
		}
		return ris;
	}

	public void setListaSpesa(ArrayList<Articolo> listaSpesa) {
		this.listaSpesa = listaSpesa;
	}

}
