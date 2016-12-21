package test;

import dao.PCDAO;
import dao.PubblicazioniDAO;
import dao.TelefonoDao;
import dao.VideogameDAO;
import xml.DBNegozioXML;

public class TestIntegrazione {

	public static void main(String[] args) {

		
		System.out.println(
				new TestPCDAO1(
						new PCDAO(
								new DBNegozioXML(
										"C:\\Users\\corso1\\Desktop\\pc.xml")
								)
						).test());
		System.out.println(
				new TestPubblicazioneDAO(
						new PubblicazioniDAO(
								new DBNegozioXML(
										"C:\\Users\\corso1\\Documents\\prova\\JavaShopTeamB\\src\\pubblicazione.xml")
								)
						).test());
		System.out.println(
				new TestTelefonoDao(
						new TelefonoDao(
								new DBNegozioXML(
										"C:\\Users\\corso1\\Documents\\prova\\JavaShopTeamB\\src\\telefonoCasa.xml")
								)
						).test());
		
		System.out.println(
				new TestVideogameDAO(
						new VideogameDAO(
								new DBNegozioXML(
										"C:\\Users\\corso1\\Documents\\prova\\JavaShopTeamB\\src\\videogame.xml")
								)
						).test());
		
		
		
	}

}
