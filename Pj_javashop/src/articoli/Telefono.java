package articoli;

public class Telefono extends Articolo{
	/*
	   ITelefonoDAO
 * 		-leggi() restituisce tutti gli oggetti si tipo telefono;
 * 		-leggi(String marca)
 * 		-leggi(Stirng marca, int ramMinima, double prezzo max, int memoriaMinima, String sistemaOperativo, String LTE(si o no), boolean dualSim(si o no))
	 */
	private String marca;
	private int ram;
	private double prezzo;
	private int hd;
	private String sistemaOperativo;
	private String LTE;
	private String dualSim;
	
	public Telefono(String nome, String descrizione, String marca, int ram, double prezzo, int hd, String sistemaOperativo, String LTE, String dualSim, int quantita) {
		super(nome, descrizione, quantita);
		this.marca = marca;
		this.ram = ram;
		this.prezzo = prezzo;
		this.hd = hd;
		this.sistemaOperativo = sistemaOperativo;
		this.LTE = LTE;
		this.dualSim = dualSim;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public int getHd() {
		return hd;
	}

	public void setMemoria(int hd) {
		this.hd = hd;
	}

	public String getSistemaOperativo() {
		return sistemaOperativo;
	}

	public void setSistemaOperativo(String sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}

	public String getLTE() {
		return LTE;
	}

	public void setLTE(String LTE) {
		this.LTE = LTE;
	}

	public String getDualSim() {
		return dualSim;
	}

	public void setDualSim(String dualSim) {
		this.dualSim = dualSim;
	}
	
	public String scheda(){
		return super.scheda() + "\nRam: " + ram +
								"\nMarca: " + marca +
								"\nPrezzo: " + prezzo + " €" +
								"\nHard disk: " + hd +
								"\nSistema operativo: " + sistemaOperativo +
								"\nLTE: " + LTE + 
								"\nDualSim: " + dualSim;
	}

}
