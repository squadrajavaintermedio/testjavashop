package test;
import java.util.ArrayList;
import articoli.*;

import dao.*;
public class TestPubblicazioneDAO implements ITest {

	PubblicazioniDAO p;
	
	public TestPubblicazioneDAO(PubblicazioniDAO p){
		this.p=p;
	}
	
	public String test(){
		String s="";
		ArrayList<Pubblicazione> pp=p.leggi();
		if(pp.size()!=1){
			s+="\nerrore conta ( metodo leggi() ) (pubblicazioni)";
		if(!pp.get(0).getNome().equals("Dishonored")) 
			s+="\nprima pubblicazione non quella prevista (pubblicazioni)";
		}
		if(s.equals(""))
			System.out.println("\ntesto riuscito (pubblicazioni)");
		
		return s;
	}
	
}
