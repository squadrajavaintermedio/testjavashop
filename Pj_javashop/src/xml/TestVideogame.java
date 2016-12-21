package xml;
import java.util.ArrayList;
import articoli.*;

public class TestVideogame {
	public static void main(String argv[])
	{
		
			DBNegozioXML db = 
					new DBNegozioXML(
					"C:\\Users\\Utente\\Desktop\\JAVA ANT\\xml\\videogames.xml");

			ArrayList<Articolo> ris = db.articoli("videogame");
			
			for(int i=0;i<ris.size();i++)
				if(ris.get(i)!=null)
					System.out.println(ris.get(i).scheda());
			
			
		
	}
}