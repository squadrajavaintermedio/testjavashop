package test;

import decorator.ArticoloLoggatoDecorator;
import decorator.ArticoloScontatoDecorator;
import articoli.IArticolo;
import articoli.VideogamePC;

public class TestDecoratore {

	public static void main (String[] argv)
	{
		IArticolo a = 
			new ArticoloScontatoDecorator(
				new ArticoloLoggatoDecorator(
						new VideogamePC("Dishonored","",20, null, null, null, 0)
						));
		
		System.out.println(a.scheda());
		System.out.println(a.getPrezzo());
	}
	
}
