package decorator;

import articoli.IArticolo;


public class ArticoloScontatoDecorator extends ArticoloDecorator {

	public ArticoloScontatoDecorator(IArticolo a)
	{
		super(a);
	}
	
	public String scheda()
	{
		//Logga(super.scheda());
		return "SCONTO! \n"+
				super.scheda();
	}
	
	public double getPrezzo()
	{
		return super.getPrezzo()*0.8;
	}
	
}
