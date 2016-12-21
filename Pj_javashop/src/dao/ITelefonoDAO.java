package dao;

import java.util.ArrayList;

import articoli.Telefono;

public interface ITelefonoDAO {
	
	public ArrayList<Telefono> leggi();
	public ArrayList<Telefono> leggi(
						String marca, int ramMinima, double prezzoMax, int memoriaMinima, 
						String sistemaOperativo, String LTE, String dualSim
						);
}