package decorator;

import articoli.IArticolo;


public abstract class ArticoloDecorator implements IArticolo {

	IArticolo articolo;
	
	public ArticoloDecorator(IArticolo articolo)
	{
		this.articolo = articolo;
	}
	
	@Override
	public double getPrezzo() {
		// TODO Auto-generated method stub
		return articolo.getPrezzo();
	}

	@Override
	public String scheda() {
		// TODO Auto-generated method stub
		return articolo.scheda();
	}

}
