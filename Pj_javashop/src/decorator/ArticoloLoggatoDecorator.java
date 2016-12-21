package decorator;

import articoli.IArticolo;


public class ArticoloLoggatoDecorator extends ArticoloDecorator {

	public ArticoloLoggatoDecorator(IArticolo a)
	{
		super(a);
	}
	
	public String scheda()
	{
		//Logga(super.scheda());
		return "LOGGATO \n"+
				super.scheda();
		
	}
	
}
