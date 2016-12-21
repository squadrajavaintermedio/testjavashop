package test;

import articoli.Console;

public class TestXML {

	public static void main (String args[])
	{
		XMLWriter x = new XMLWriter();
		x.scriviArticolo(
						new Console("PL 4","", 30), 
						"C:\\Users\\user\\Documents\\primo2.xml"
					);
		
	}
	
}
