package xml;

import articoli.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.IDBNegozio;
import java.io.File;
import java.util.ArrayList;

public class DBNegozioXML implements IDBNegozio {
	// Una helper class.
	// Restituisce un Document, vale a dire un documento
	// Ci sono dei problemi... dei problemi di risorse, ma per ora...
	Document doc = null;

	public DBNegozioXML(String percorso) {
		try {
			File fXmlFile = new File(percorso);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int numeroArticoli(String tipo) {
		return doc.getElementsByTagName(tipo).getLength();
	}

	public ArrayList<Articolo> articoli(String tipo) {
		ArrayList<Articolo> ris = new ArrayList<Articolo>();
		NodeList lista = doc.getElementsByTagName(tipo);
		for (int i = 0; i < lista.getLength(); i++)
			ris.add(converti(lista.item(i)));

		return ris;

	}

	private Articolo converti(Node n) {
		Articolo ris = null;
		switch (n.getNodeName()) {
		case "videogame":
			ris = this.videogameDaXML(n);
			break;
		case "ram":
			ris = this.ramDaXML(n);
			break;
		case "hd":
			ris = this.hdDaXML(n);
			break;
		case "processore":
			ris = this.processoreDaXML(n);
		case "pc":
			ris = this.pcDaXML(n);
			break;
		case "joypad":
			ris = this.joypadDaXML(n);
			break;
		case "schedavideo":
			ris = this.schedagraficaDaXML(n);
			break;
		case "telefono":
			ris = this.telefonoDaXml(n);
			break;
		case "rivista":
			ris = this.rivistaXML(n);
			break;
		case "fumetto":
			ris = this.fumettoXML(n);
			break;
		case "libro":
			ris = this.libroXML(n);
			break;
		default:
		}
		// Fine switch
		return ris;
	}

	private Videogame videogameDaXML(Node n)
	{
		Videogame ris=null;
		String nome = "";
		String descrizione = "";
		int prezzo = 0;
		String piattaforma = "";
		String genere="";
		String disponibilita ="";
		int quantita = 0;
		//Cambia il proprio stato
		NodeList campi = n.getChildNodes();
		
		for(int k=0;k<campi.getLength();k++)
		{
			switch(campi.item(k).getNodeName())
			{
			case "titolo":
				nome = campi.item(k).getTextContent();
				break;
			case "descrizione":
				descrizione = campi.item(k).getTextContent();
				break;
			case "prezzo":
				prezzo = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "piattaforma":
				piattaforma = campi.item(k).getTextContent();
				break;
			case "genere":
				genere = campi.item(k).getTextContent();
				break;
			case "disponibilita":
				disponibilita = campi.item(k).getTextContent();
				break;
			case "quantita":
				quantita = Integer.parseInt(campi.item(k).getTextContent());
				break;
				
			default:
			}
		}
		
		
		if(piattaforma.equals("") || piattaforma.equals("PC")) 
			ris = new VideogamePC(nome,descrizione,prezzo, piattaforma,genere,disponibilita,quantita);
		else
			ris = new VideogameConsole(nome,descrizione,prezzo,piattaforma,genere,disponibilita,quantita);
		
		return ris;
	}

	private Ram ramDaXML(Node n) {
		Ram ris = null;
		String nome = "";
		String descrizione = "";
//		String autore = "";
		double prezzo = 0;
		int giga = 0;
		int quantita = 0;
//		int estensione = 0;
		// Cambia il proprio stato
		NodeList campi = n.getChildNodes();

		for (int k = 0; k < campi.getLength(); k++) {
			switch (campi.item(k).getNodeName()) {
			case "nome":
				nome = campi.item(k).getTextContent();
				break;
			case "descrizione":
				descrizione = campi.item(k).getTextContent();
				break;
			case "prezzo":
				prezzo = Integer.parseInt(campi.item(k).getTextContent());
				break;
				
			case "giga":
				giga = Integer.parseInt(campi.item(k).getTextContent());
				break;
				
			case "quantita":
				quantita = Integer.parseInt(campi.item(k).getTextContent());
				break;
//			case "estensione":
//				estensione = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "autore":
//				autore = campi.item(k).getTextContent();
//				break;

			default:
			}
		}

		ris = new Ram(nome, descrizione, prezzo, giga, quantita);
		return ris;
	}

	private Processore processoreDaXML(Node n) {
		Processore ris = null;
		String nome = "";
		String descrizione = "";
		double prezzo = 0;
		int benchmark = 0;
		int quantita = 0;
		// Cambia il proprio stato
		NodeList campi = n.getChildNodes();

		for (int k = 0; k < campi.getLength(); k++) {
			switch (campi.item(k).getNodeName()) {
			case "nome":
				nome = campi.item(k).getTextContent();
				break;
			case "descrizione":
				descrizione = campi.item(k).getTextContent();
				break;
			case "prezzo":
				prezzo = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "benchmark":
				benchmark = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "quantita":
				quantita = Integer.parseInt(campi.item(k).getTextContent());
				break;

			default:
			}
		}

		ris = new Processore(nome, descrizione, prezzo, benchmark, quantita);
		return ris;
	}

	private Hd hdDaXML(Node n) {
		Hd ris = null;
		String nome = "";
		String descrizione = "";
		double prezzo = 0;
		int capacita = 0;
		int quantita = 0;
		// Cambia il proprio stato
		NodeList campi = n.getChildNodes();

		for (int k = 0; k < campi.getLength(); k++) {
			switch (campi.item(k).getNodeName()) {
			case "nome":
				nome = campi.item(k).getTextContent();
				break;
			case "descrizione":
				descrizione = campi.item(k).getTextContent();
				break;
			case "prezzo":
				prezzo = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "capacita":
				capacita = Integer.parseInt(campi.item(k).getTextContent());
				break;
				
			case "quantita":
				quantita = Integer.parseInt(campi.item(k).getTextContent());
				break;

			default:
			}
		}

		ris = new Hd(nome, descrizione, prezzo, capacita, quantita);
		return ris;
	}

	public ArrayList<Articolo> articoli(String tipo, String padre) {

		ArrayList<Articolo> ris = new ArrayList<Articolo>();
		NodeList lista = doc.getElementsByTagName(tipo);
		for (int i = 0; i < lista.getLength(); i++)
			if (lista.item(i).getParentNode().getNodeName().equals(padre))
				ris.add(converti(lista.item(i)));
		return ris;
	}

	public double sommaPrezzo(String tipo) {
		ArrayList<Articolo> lista = this.articoli(tipo);
		double somma = 0;
		for (Articolo a : lista)
			somma += a.getPrezzo();
		return somma;

	}

	@Override
	public double sommaPrezzo(String tipo, String padre) {
		ArrayList<Articolo> lista = this.articoli(tipo, padre);
		double somma = 0;
		for (Articolo a : lista)
			somma += a.getPrezzo();
		return somma;
	}

	@Override
	public double sommaQuantita(String tipo) {
		ArrayList<Articolo> lista = this.articoli(tipo);
		double sommaQta = 0;
		for (Articolo a : lista)
			sommaQta += a.getPrezzo();
		return sommaQta;
	}

	@Override
	public double sommaQuantita(String tipo, String padre) {
		ArrayList<Articolo> lista = this.articoli(tipo, padre);
		double sommaQta = 0;
		for (Articolo a : lista)
			sommaQta += a.getPrezzo();
		return sommaQta;

	}

	public int numeroArticoli() {
		return doc.getDocumentElement().getChildNodes().getLength();
	}

	private PC pcDaXML(Node n) {
		
		PC ris = null;
		String nome = "";
		String descrizione = "";
		double prezzo = 0;
		int quantita = 0;
		Processore processore = null;
		String nomeProcessore = "";
		int giga = 0;
		Ram ram = null;
		Hd hd = null;
		int capacita = 0;
		int ricarico = 0;
		String marca = "";

		NodeList campi = n.getChildNodes();

		for (int k = 0; k < campi.getLength(); k++) {
			switch (campi.item(k).getNodeName()) {
			case "nome":
				nome = campi.item(k).getTextContent();
				break;
			case "descrizione":
				descrizione = campi.item(k).getTextContent();
				break;
			case "quantita":
				quantita = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "prezzo":
				prezzo = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "processore":
				nomeProcessore = campi.item(k).getTextContent();
				
				switch (nomeProcessore) {
				case "i3":
					processore = new Processore(nomeProcessore, " ", 50, 1000, quantita);
					break;
				case "i5":
					processore = new Processore(nomeProcessore, " ", 70, 1000, quantita);
					break;
				case "i7":
					processore = new Processore(nomeProcessore, " ", 100, 1000, quantita);
					break;
				case "atom":
					processore = new Processore(nomeProcessore, " ", 30, 1000, quantita);
					break;
				default:processore = new Processore (nomeProcessore, "", 50, 100, quantita);
				}
				break;
//			case "ram":
//				giga = Integer.parseInt(campi.item(k).getTextContent());
//				ram = new Ram("", "", giga*3.5, giga, quantita);
//				break;

			case "hd":
				capacita = Integer.parseInt(campi.item(k).getTextContent());
				hd = new Hd("hd", " ", capacita * 0.3, capacita, quantita);
				break;
			case "ricarico":
				ricarico = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "marca":
				marca = campi.item(k).getTextContent();
				break;
				
				

			default:
			}// fine switch
		} // fine for
		//Ho finito di scorrere i campi...
		ris = new PC(nome,descrizione, processore, ram, hd, 50, quantita);
		((articoli.PC) ris.setQuantita(quantita)).setMarca(marca);
		
		return ris;
	}
	
	private Joypad joypadDaXML(Node n)
	{
		Joypad ris=null;
		String nome = "";
		String descrizione = "";
		int prezzo = 0;
		int quantita = 0;
		String autore = "";
		//Cambia il proprio stato
		NodeList campi = n.getChildNodes();
		
		for(int k=0;k<campi.getLength();k++)
		{
			switch(campi.item(k).getNodeName())
			{
			case "nome":
				nome = campi.item(k).getTextContent();
				break;
			case "descrizione":
				descrizione = campi.item(k).getTextContent();
				break;
			case "prezzo":
				prezzo = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "autore":
				autore = campi.item(k).getTextContent();
				break;
			case "quantita":
				quantita = Integer.parseInt(campi.item(k).getTextContent());
				break;
				
			default:
			}
		}
	ris = new Joypad(nome,descrizione,prezzo, quantita);
		
		return ris;
	}
	
	
	private SchedaGrafica schedagraficaDaXML(Node n)
	{
		SchedaGrafica ris=null;
		String nome = "";
		String descrizione = "";
		int prezzo = 0;
		int quantita = 0;
		String autore = "";
		//Cambia il proprio stato
		NodeList campi = n.getChildNodes();
		
		for(int k=0;k<campi.getLength();k++)
		{
			switch(campi.item(k).getNodeName())
			{
			case "nome":
				nome = campi.item(k).getTextContent();
				break;
			case "descrizione":
				descrizione = campi.item(k).getTextContent();
				break;
			case "prezzo":
				prezzo = Integer.parseInt(campi.item(k).getTextContent());
				break;
			case "autore":
				autore = campi.item(k).getTextContent();
				break;
			case "quantita":
				quantita = Integer.parseInt(campi.item(k).getTextContent());
				break;
				
			default:
			}
		}
		
		ris = new SchedaGrafica(nome,descrizione,prezzo, quantita);
		
		return ris;
	}

	//INSERISCO IL TIPO DI COMPONENTE, CHIEDO IL PADRE E CHIEDO SE VOGLIO O NO CHE APPARTENGANO A QUEL PADRE
		public ArrayList<Articolo> articoli(String tipo, String padre , boolean appartiene) {
			
			ArrayList<Articolo> ris = new ArrayList<Articolo>();
			NodeList lista = doc.getElementsByTagName(tipo);
			for(int i=0; i<lista.getLength();i++)
			{
				if(lista.item(i).getParentNode().getNodeName().equals(padre)==appartiene) //se false=false---->entro
				{
					ris.add(converti(lista.item(i)));
				}
				
			}	
			return ris;
		}
		
		private Telefono telefonoDaXml(Node n)
		{
			Telefono ris = null;
			String nome = "";
			String descrizione = "";
			String sistemaOperativo = "";
			String LTE = "";
			String dualSim = "";
			String marca = "";
			double prezzo = 0;
			int ram = 0;
			int hd = 0;
			int quantita = 0;
			
			//Cambia il proprio stato
			NodeList campi = n.getChildNodes();
			
			for(int k=0;k<campi.getLength();k++)
			{
				switch(campi.item(k).getNodeName())
				{
				case "nome":
					nome = campi.item(k).getTextContent();
					break;
				case "descrizione":
					descrizione = campi.item(k).getTextContent();
					break;
				case "marca":
					marca = campi.item(k).getTextContent();
					break;
				case "lte":
					LTE = campi.item(k).getTextContent();
					break;
				case "dualsim":
					dualSim = campi.item(k).getTextContent();
					break;
				case "sistemaOperativo":
					sistemaOperativo = campi.item(k).getTextContent();
					break;
				case "ram":
					ram = Integer.parseInt(campi.item(k).getTextContent());
					break;
				case "hd":
					hd = Integer.parseInt(campi.item(k).getTextContent());
					break;
				case "prezzo":
					prezzo = Integer.parseInt(campi.item(k).getTextContent());
					break;
				case "quantita":
					quantita = Integer.parseInt(campi.item(k).getTextContent());
					break;
				default:
				}
			}
			
			ris = new Telefono(nome, descrizione, marca, ram, prezzo, hd, sistemaOperativo, LTE, dualSim, quantita);
			
			return ris;
		}
		
		private Rivista rivistaXML(Node n){

			Rivista ris=null;
			String nome = "";
			String descrizione = "";
			int prezzo = 0;
			int quantita=0;
			//Cambia il proprio stato
			NodeList campi = n.getChildNodes();
			
			for(int k=0;k<campi.getLength();k++)
			{
				switch(campi.item(k).getNodeName())
				{
				case "titolo":
					nome = campi.item(k).getTextContent();
					break;
				case "descrizione":
					descrizione = campi.item(k).getTextContent();
					break;
				case "prezzo":
					prezzo = Integer.parseInt(campi.item(k).getTextContent());
					break;
				case "quantita":
					quantita = Integer.parseInt(campi.item(k).getTextContent());
					break;
				default:	
				}
			}
			ris = new Rivista(nome,descrizione);
			ris.setQuantita(quantita).setPrezzo(prezzo);
			return ris;
		}
	
		private Libro libroXML(Node n){

			Libro ris=null;
			String nome = "";
			String descrizione = "";
			int prezzo = 0;
			int quantita=0;
			//Cambia il proprio stato
			NodeList campi = n.getChildNodes();
			
			for(int k=0;k<campi.getLength();k++)
			{
				switch(campi.item(k).getNodeName())
				{
				case "titolo":
					nome = campi.item(k).getTextContent();
					break;
				case "descrizione":
					descrizione = campi.item(k).getTextContent();
					break;
				case "prezzo":
					prezzo = Integer.parseInt(campi.item(k).getTextContent());
					break;
				case "quantita":
					quantita = Integer.parseInt(campi.item(k).getTextContent());
					break;
				default:	
				}
			}
			ris = new Libro(nome,descrizione);
			ris.setQuantita(quantita).setPrezzo(prezzo);
			return ris;
		}
		
		private Fumetto fumettoXML(Node n){

			Fumetto ris=null;
			String nome = "";
			String descrizione = "";
			int prezzo = 0;
			int quantita=0;
			//Cambia il proprio stato
			NodeList campi = n.getChildNodes();
			
			for(int k=0;k<campi.getLength();k++)
			{
				switch(campi.item(k).getNodeName())
				{
				case "titolo":
					nome = campi.item(k).getTextContent();
					break;
				case "descrizione":
					descrizione = campi.item(k).getTextContent();
					break;
				case "prezzo":
					prezzo = Integer.parseInt(campi.item(k).getTextContent());
					break;
				case "quantita":
					quantita = Integer.parseInt(campi.item(k).getTextContent());
					break;
				default:	
				}
			}
			ris = new Fumetto(nome,descrizione);
			ris.setQuantita(quantita).setPrezzo(prezzo);
			return ris;
		}
		
}
