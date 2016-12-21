package test;

import java.util.ArrayList;

import articoli.PC;
import dao.PCDAO;

public class TestPCDAO1 implements ITest {

	PCDAO pcd;

	public TestPCDAO1(PCDAO pcd)
	{
		this.pcd = pcd;
	}

	@Override
	public String test() {
		
		String ris = "";
		ArrayList<PC> pc = pcd.leggi();
		
		if (pc.size()!=2)
			ris += "Conta PC Errata \n";
		else 
			ris +="Test cardinalit� PC riuscito! \n";
		
		if (!pc.get(0).getNome().equals("Pc da rifilare alla ex"))
			ris += "Test fallito. Il computer esploder� tra 30 secondi. \n";
		else ris += "test nome primo PC riuscito \n";

	return ris;
	}
	

}
