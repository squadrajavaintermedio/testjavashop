//package xml;
//
//import articoli.*;
//import dao.IDBNegozio;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.DocumentBuilder;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import java.io.File;
//import java.util.ArrayList;
//
//public class DBNegozioXMLoriginal implements IDBNegozio {
//	// Una helper class.
//	// Restituisce un Document, vale a dire un documento
//	// Ci sono dei problemi... dei problemi di risorse, ma per ora...
//	Document doc = null;
//
//	public DBNegozioXMLoriginal(String percorso) {
//		try {
//			File fXmlFile = new File(percorso);
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//			doc = dBuilder.parse(fXmlFile);
//			doc.getDocumentElement().normalize();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public int numeroArticoli(String tipo) {
//		return doc.getElementsByTagName(tipo).getLength();
//	}
//
//	public ArrayList<Articolo> articoli(String tipo) {
//		ArrayList<Articolo> ris = new ArrayList<Articolo>();
//		NodeList lista = doc.getElementsByTagName(tipo);
//		for (int i = 0; i < lista.getLength(); i++)
//			ris.add(converti(lista.item(i)));
//
//		return ris;
//
//	}
//
//	private Articolo converti(Node n) {
//		Articolo ris = null;
//		switch (n.getNodeName()) {
//		case "videogame":
//			ris = this.videogameDaXML(n);
//			break;
//		case "ram":
//			ris = this.ramDaXML(n);
//			break;
//		case "hd":
//			ris = this.hdDaXML(n);
//			break;
//		case "processore":
//			ris = this.processoreDaXML(n);
//			break;
//		default:
//		}
//		// Fine switch
//		return ris;
//	}
//
//	private Processore processoreDaXML(Node n) {
//		Processore ris = null;
//		String nome = "";
//		String descrizione = "";
//		int quantita = 0;
//		int prezzo = 0;
//		int benchmark = 0;
//
//		NodeList campi = n.getChildNodes();
//
//		for (int k = 0; k < campi.getLength(); k++) {
//			switch (campi.item(k).getNodeName()) {
//			case "titolo":
//				nome = campi.item(k).getTextContent();
//				break;
//			case "descrizione":
//				descrizione = campi.item(k).getTextContent();
//				break;
//			case "quantita":
//				quantita = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "prezzo":
//				prezzo = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "benchmark":
//				benchmark = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			default:
//			}// fine switch
//		} // fine for
//
//		ris = new Processore(nome, descrizione, prezzo, benchmark);
//		ris.setQuantita(quantita);
//		return ris;
//	}
//
//	private Ram ramDaXML(Node n) {
//		Ram ris = null;
//		String nome = "";
//		String descrizione = "";
//		int quantita = 0;
//		int prezzo = 0;
//		String tipo = null;
//		int giga = 0;
//
//		NodeList campi = n.getChildNodes();
//
//		for (int k = 0; k < campi.getLength(); k++) {
//			switch (campi.item(k).getNodeName()) {
//			case "titolo":
//				nome = campi.item(k).getTextContent();
//				break;
//			case "descrizione":
//				descrizione = campi.item(k).getTextContent();
//				break;
//			case "quantita":
//				quantita = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "prezzo":
//				prezzo = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "tipo":
//				tipo = campi.item(k).getTextContent();
//				break;
//			case "giga":
//				giga = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//
//			default:
//			}// fine switch
//		} // fine for
//
//		ris = new Ram(nome, descrizione, prezzo, giga, tipo);
//		ris.setQuantita(quantita);
//		return ris;
//	}
//
//	private Videogame videogameDaXML(Node n) {
//		Videogame ris = null;
//		String nome = "";
//		String descrizione = "";
//		int quantita = 0;
//		int prezzo = 0;
//		String piattaforma = "";
//		// Cambia il proprio stato
//		NodeList campi = n.getChildNodes();
//
//		for (int k = 0; k < campi.getLength(); k++) {
//			switch (campi.item(k).getNodeName()) {
//			case "titolo":
//				nome = campi.item(k).getTextContent();
//				break;
//			case "descrizione":
//				descrizione = campi.item(k).getTextContent();
//				break;
//			case "quantita":
//				quantita = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "prezzo":
//				prezzo = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "piattaforma":
//				piattaforma = campi.item(k).getTextContent();
//				break;
//
//			default:
//			}
//		}
//
//		if (piattaforma.equals("") || piattaforma.equals("PC"))
//			ris = new VideogamePC(nome, descrizione, prezzo);
//		else
//			ris = new VideogameConsole(nome, descrizione, prezzo, piattaforma);
//		ris.setQuantita(quantita);
//
//		return ris;
//	}
//
//	private Hd hdDaXML(Node n) {
//		Hd ris = null;
//		String nome = "";
//		String descrizione = "";
//		int quantita = 0;
//		int prezzo = 0;
//		int capacita = 0;
//
//		NodeList campi = n.getChildNodes();
//
//		for (int k = 0; k < campi.getLength(); k++) {
//			switch (campi.item(k).getNodeName()) {
//			case "titolo":
//				nome = campi.item(k).getTextContent();
//				break;
//			case "descrizione":
//				descrizione = campi.item(k).getTextContent();
//				break;
//			case "quantita":
//				quantita = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "prezzo":
//				prezzo = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//			case "capacita":
//				capacita = Integer.parseInt(campi.item(k).getTextContent());
//				break;
//
//			default:
//			}// fine switch
//		} // fine for
//
//		ris = new Hd(nome, descrizione, prezzo, capacita);
//		ris.setQuantita(quantita);
//		return ris;
//	}
//
//	@Override
//	public ArrayList<Articolo> articoli(String tipo, String padre) {
//
//		ArrayList<Articolo> ris = new ArrayList<Articolo>();
//		NodeList lista = doc.getElementsByTagName(tipo);
//		for (int i = 0; i < lista.getLength(); i++)
//			ris.add(converti(lista.item(i)));
//		return ris;
//	}
//
//	@Override
//	public double sommaQuantita(String tipo) {
//		ArrayList<Articolo> lista = this.articoli(tipo);
//		double somma = 0;
//		for (Articolo a : lista)
//			somma += a.getQuantita();
//		return somma;
//	}
//
//	@Override
//	public double sommaQuantita(String tipo, String padre) {
//
//		return 0;
//
//	}
//
//	public double sommaPrezzo(String tipo) {
//		ArrayList<Articolo> lista = this.articoli(tipo);
//		double somma = 0;
//		for (Articolo a : lista){
//			somma += a.getPrezzo() * a.getQuantita();}
//		return somma;
//	}
//
//	// iquesto metodo delve stare nel PC dao (DAO principale è solo di uso degli
//	// altri dao)
//	@Override
//	public double sommaPrezzo(String tipo, String padre) {
//		double somma = 0;
//		ArrayList<Articolo> listarticoli = new ArrayList<Articolo>();
//		NodeList lista = doc. getElementsByTagName(tipo);
//		for (int i = 0; i< listarticoli ; i++)
//		{if(lista.item(i).getParentNode().getNodname.equals(padre));
//		for(Articolo a: listarticoli)
//			
//		{
//			somma += a.getPrezzo()*a.getQuantita();
//			}
//		return somma;	
//		}
//		
//	}
//}
//
//
