package articoli;

import logger.Logger;

public abstract class Articolo implements IArticolo, Cloneable {

	private String nome;
	private String descrizione;
	private int quantita = 1;
	private boolean visibilita;
	private String categoria;
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Articolo()
	{
		//Costruttore vuoto
	}
	
	public Articolo(String nome, String descrizione, int quantita) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.quantita = quantita;
	}
	public int getQuantita() {
		return quantita;
	}

	public Articolo setQuantita(int quantita) {
		this.quantita = quantita;
		return this;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public boolean isVisibilita() {
		return visibilita;
	}

	public void setVisibilita(boolean visibilita) {
		this.visibilita = visibilita;
	}

	public void log()
	{
		Logger
			.getInstance()
			.add("Visualizzata scheda articolo " + nome);
	}
	
	public String scheda()
	{
		log();
		return nome+" "+descrizione;
	}
	

	public Articolo clone()
	{
		Object o = null;
		try
		{
			o = super.clone();
		}
		catch(Exception e)
		{
			o = null;
			System.out.println("ooops");
		}
		
		return (Articolo) o;
	}
	
	public double getPrezzo()
	{
		return 0;
	}
	
	public String toXML(){return "";}

	public void setPrezzo(int prezzo) {
	
	}

	
	
	
}
