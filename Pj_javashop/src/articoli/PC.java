package articoli;

public class PC extends Articolo {

	Processore processore;
	SchedaVideo schedavideo;
	Ram ram;
	Hd hd;
	Hd hd2;
	double ricarico;
	String marca;
	
	public String getMarca() {
		return marca;
	}

	public PC setMarca(String marca) {
		this.marca = marca;
		return this;
	}


	public PC(String nome, String descrizione, Processore processore, Ram ram,
			Hd hd, double ricarico, int quantita) {
		super(nome, descrizione, quantita);
		this.processore = processore;
		this.ram = ram;
		this.hd = hd;
		this.ricarico = ricarico;
	}
	
	

	public Processore getProcessore() {
		return processore;
	}



	public PC setProcessore(Processore processore) {
		this.processore = processore;
		return this;
	}



	public SchedaVideo getSchedavideo() {
		return schedavideo;
	}



	public PC setSchedavideo(SchedaVideo schedavideo) {
		this.schedavideo = schedavideo;
		return this;
	}



	public Ram getRam() {
		return ram;
	}



	public PC setRam(Ram ram) {
		this.ram = ram;
		return this;

	}



	public Hd getHd() {
		return hd;
	}



	public PC setHd(Hd hd) {
		this.hd = hd;
		return this;
	}



	public Hd getHd2() {
		return hd2;
	}



	public PC setHd2(Hd hd2) {
		this.hd2 = hd2;
		return this;
	}



	public double getRicarico() {
		return ricarico;
	}



	public PC setRicarico(int ricarico) {
		this.ricarico = ricarico;
		return this;
	}



	@Override
	public double getPrezzo() {
		// TODO Auto-generated method stub
		double s = 
			processore.getPrezzo()	+
			ram.getPrezzo()				+
			hd.getPrezzo()				+
			ricarico				;
		
		if(hd2!=null) s+=hd2.getPrezzo();
		if(schedavideo!=null) s+=schedavideo.getPrezzo();
		
		return s;
		
	}

	@Override
	public String scheda() {
		// TODO Auto-generated method stub
		super.scheda();
		String ris	= 	"Nome\t\t:"	+	getNome()	+	"\n";
		ris			+=	"Processore\t:"	+	processore.scheda()	+	"\n";
		ris			+= 	"Ram\t\t:"		+	ram.scheda()	+	"\n";
		ris			+= 	"Hd1\t\t:"	+	hd.scheda()	+	"\n";
		if(hd2!=null) ris	+= "Hd2\t\t:"	+hd2.scheda()	+	"\n";
		if(schedavideo!=null) ris	+=	"Scheda video\t:"	+	schedavideo.scheda()	+	"\n";
		return ris;
		
	}

}
