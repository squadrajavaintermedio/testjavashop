package test;

public class Test4 {
	public static void main(String argv[])
	{
		
		/*
		Document dati = AntXML.DocumentDaFile("C:\\Users\\Utente\\Desktop\\JAVA ANT\\xml\\AntXML");

		NodeList pcxml = dati.getElementsByTagName("pc");
		ArrayList<PC> pclist=new ArrayList<PC>();
		String nome="";
		String descrizione="";
		String nomeProcessore="";
		int prezzo=0;
		Processore proc=null;
		Ram ram=null;
		Hd hd=null;
		int nomeRam;
		int nomeHd;
		
		for(int i=0;i<pcxml.getLength();i++)
		{
			NodeList campi = pcxml.item(i).getChildNodes();
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
				case "processore":
					nomeProcessore = campi.item(k).getTextContent();
					switch(nomeProcessore)
					{case "i3":
						proc=new Processore(nomeProcessore," ",50,1000);
						break;
					case "i5":
						proc=new Processore(nomeProcessore," ",70,1000);
						break;
					case "i7":
						proc=new Processore(nomeProcessore," ",100,1000);
						break;
					case "atom":
						proc=new Processore(nomeProcessore," ",30,1000);
						break;
					default:
					}
					break;
				case "ram":
					nomeRam = Integer.parseInt(campi.item(k).getTextContent());
					ram=new Ram("ram"," ", nomeRam*3.5,10 ,"");
					break;
				case "hd":
					nomeHd=Integer.parseInt(campi.item(k).getTextContent());
					hd=new Hd("hd"," ", nomeHd*0.3 ,2);
					break;
				default:
				}
			}
			//Ho finito di scorrere i campi...
			pclist.add( new PC(nome,descrizione, proc, ram, hd, 50));
			
		}
		
			System.out.println(pclist.get(1).scheda());
		*/
	}
}