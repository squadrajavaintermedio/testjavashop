package dao;

import java.util.ArrayList;
import java.sql.*;
import articoli.Articolo;
import articoli.Console;
import articoli.VideogamePC;
import articoli.VideogameConsole;
import articoli.PC;
import articoli.Hd;
import articoli.Processore;
import articoli.Ram;

public class DBNegozioSQL implements IDBNegozio {

	
	private Connection db;
	
	private final static String CONSOLE = "console";
	private final static String VIDEOGAME = "videogame";
	private final static String LIBRI = "libri";
	private final static String RIVISTE = "riviste";
	private final static String PC = "pc";
	private final static String PROCESSORE = "processore";
	private final static String RAM = "ram";
	private final static String HD = "hd";
	
	// SQL statement
	private final static String SELECTCOUNTCONSOLE = "SELECT COUNT(*) FROM CONSOLE;";
	private final static String SELECTCOUNTVIDEOGIOCHI = "SELECT COUNT(*) FROM VIDEOGIOCHI;";
	private final static String SELECTCOUNTLIBRI = "SELECT COUNT(*) FROM LIBRI;";
	private final static String SELECTCOUNTRIVISTE = "SELECT COUNT(*) FROM RIVISTE;";
	private final static String SELECTCOUNTPC = "SELECT COUNT(*) FROM PC;";
	
	private final static String SELECTCONSOLE = "SELECT NOME,DESCRIZIONE,PREZZO,QUANTITA FROM CONSOLE;";
	private final static String SELECTVG = "SELECT "
			+ "videogiochi.*, CONSOLE.nome as console, vgcompa.PREZZO, vgcompa.disponibilita, vgcompa.quantita "
			+ "FROM "
			+ "CONSOLE,videogiochi,vgcompa "
			+ "WHERE "
			+ "videogiochi.ID = vgcompa.idvideogiochi"
			+ " AND "
			+ "CONSOLE.ID = vgcompa.IDCONSOLE;";
	
	private final static String SELECTPCANDCOMPONENTI = "SELECT pc.*, componenti.nome componente, componenti.descrizione cmpdescrizione, componenti.tipo "
			+ " FROM pc, componenti, pccompatibile"
			+ " WHERE "
			+ " pc.id = pccompatibile.idpc "
			+ " AND "
			+ " componenti.id = pccompatibile.idcomponenti "
			+ " ORDER BY pc.id;";
	
	private final static String SELECTCONSOLEPARAM		= "SELECT * "
															+ " FROM CONSOLE "
															+ " WHERE prezzo < ? AND quantita > ? AND descrizione = ?;";
															//+ " WHERE prezzo < ? AND quantita > ?;";
	private final static String SELECTVIDEOGAMESPARAM	= "SELECT videogames.*, console.nome as console, vgcomp.prezzo, vgcomp.qta "
															+ " FROM videogames, console, vgcomp "
															+ " WHERE videogames.id = vgcomp.idvideogames "
															+ " AND console.id = vgcomp.idconsole"
															+ " AND vgcomp.prezzo < ? AND vgcomp.qta > ? AND videogames.descrizione = ?;";
	private final static String SELECTLIBRIPARAM		= "SELECT * "
															+ " FROM libri "
															+ " WHERE prezzo < ? AND qta > ? AND descrizione = ?;";
	private final static String SELECTRIVISTEPARAM		= "SELECT * "
															+ " FROM riviste "
															+ " WHERE prezzo < ? AND qta > ? AND descrizione = ?;";
	private final static String SELECTPCPARAM			= "SELECT pc.*, componenti.nome componente, componenti.tipo "
															+ " FROM pc, componenti, pccompatibile "
															+ " WHERE pc.id = pccompatibile.idpc AND componenti.id = pccompatibile.idcomponenti "
															+ " AND pc.prezzo < ? AND pc.qta > ? AND pc.descrizione = ?";

	ArrayList<Articolo> lista = new ArrayList<Articolo>();
	ArrayList<Articolo> utenti = new ArrayList<Articolo>();
	/*
	 * costruttore
	 * 
	 */
	public DBNegozioSQL(String percorso) {
		
		db = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			db = DriverManager.getConnection(percorso);
		} catch(Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
//		lista.addAll(articoli(PC));
//		lista.addAll(articoli(VIDEOGAME));
//		lista.addAll(articoli(LIBRI));
//		lista.addAll(articoli(PROCESSORE));
//		lista.addAll(articoli(RAM));
//		lista.addAll(articoli(HD));
		
	}
	
	/*
	 * 
	 */
	public boolean salvaArticolo(Articolo articolo) {
		boolean returnValue = false;

		return returnValue;		
	}
	
	/*
	 * 
	 */
	public boolean salvaArticolo(Articolo articolo, String autori) {
		String sql = "";
		int rs = 0;
		boolean returnValue = false;
		int idAutori = 0; 
		
		idAutori = getIdAutori(autori);
		
		if(articolo instanceof Ram)
		{
			Ram ram = (Ram) articolo;
			sql = "INSERT INTO COMPONENTI (nome,tipo,idautori) VALUES(?,?,?);";
			try {
				PreparedStatement p = db.prepareStatement(sql);
				p.setString(1, ram.getNome());
				p.setString(2, "ram");
				if (idAutori == 0){
					p.setString(3,null);
				} else {
					p.setInt(3,idAutori);
				}
				rs = p.executeUpdate();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		if(articolo instanceof VideogameConsole)
		{
			VideogameConsole vgConsole = (VideogameConsole) articolo;
		}
		
		if (rs == 1)
			returnValue = true;
		
		return returnValue;
	}
	
	/*
	 * 
	 */
	public int getIdAutori(String autori) {
		int rs = 0;
		ResultSet sqlrs = null;
		Statement stmt = null;
		String sql = "";
		
		sql = "SELECT ID FROM AUTORI WHERE nome = ' " + autori + "';";
		try {
			stmt = db.createStatement();
			sqlrs = stmt.executeQuery(sql);
			rs = sqlrs.getInt("ID");
		}catch(Exception e){
			e.getMessage();
		}
		
		return rs;
	}
	
	/*
	 * 
	 */
	public ResultSet leggiDatiConParam(String sql, ArrayList<Object> param) {
		ResultSet rs = null;
		String ParaStr = null;
		int ParaInt = 0;
		double ParaDouble = 0;
		
		try {
			PreparedStatement preparedstatement = db.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				if (param.get(i) instanceof String) {
					ParaStr = (String) param.get(i);
					preparedstatement.setString(i+1, ParaStr);
				} else if (param.get(i) instanceof Integer) {
					ParaInt = (Integer) param.get(i);
					preparedstatement.setInt(i+1, ParaInt);
				} else if (param.get(i) instanceof Double) {
					ParaDouble = (Double) param.get(i);
					preparedstatement.setDouble(i+1, ParaDouble);
				}
			}
			
			rs = preparedstatement.executeQuery();
			
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return rs;
	}
	/*
	 * 
	 */
	public ResultSet leggiDati(String SQLSelect) {
		ResultSet rs = null;
		Statement stmt = null;
		
		try {
			stmt = db.createStatement();
			rs = stmt.executeQuery(SQLSelect);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/*
	 *
	 */
	public int numeroArticoli(String tipo) {
		
		ResultSet rs = null;
		Statement stmt = null;
		int ris = 0;
		
		try {
			stmt = db.createStatement();
			switch(tipo) {
			case "console":
				rs = leggiDati(SELECTCOUNTCONSOLE);
				break;
			case "videogames":
				rs = leggiDati(SELECTCOUNTVIDEOGIOCHI);
				break;
			case "libri":
				rs = leggiDati(SELECTCOUNTLIBRI);
				break;
			case "riviste":
				rs = leggiDati(SELECTCOUNTRIVISTE);
				break;
			case "pc":
				rs = leggiDati(SELECTCOUNTPC);
				break;
			default:
			}
			
			ris = rs.getInt("COUNT(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ris;
	}

	/*
	 *
	 */
	public ArrayList<Articolo> articoli(String tipo) {
		
		ArrayList<Articolo> lista = new ArrayList<Articolo>();
		String nome = "";
		String descrizione = "";
		double ricarico = 0;
		int quantita = 0;
		ResultSet dati = null;
		Processore proc = null;
		Hd hd = null;
		Ram ram = null;
				
		try {
			switch(tipo){
			case "console":
				dati = leggiDati(SELECTCONSOLE);
				while(dati.next())
				{
					lista.add(new Console(dati.getString("nome")
							,dati.getString("descrizione")
							,dati.getDouble("prezzo")
							,dati.getInt("quantita")
							));
				}
				break;
			case "videogames":
				dati = leggiDati(SELECTVG);
				while(dati.next())
				{
					if(dati.getString("console").equals("PC"))
						lista.add(new VideogamePC(dati.getString("nome")
								,dati.getString("descrizione")
								,dati.getDouble("prezzo")
								,dati.getString("console")
								,dati.getString("disponibilita")
								,dati.getString("genere")
								,dati.getInt("quantita")
								));
					else
							lista.add(new VideogameConsole(dati.getString("nome")
									,dati.getString("descrizione")
									,dati.getDouble("prezzo")
									,dati.getString("console")
									,dati.getString("disponibilita")
									,dati.getString("genere")
									,dati.getInt("quantita")
									));
				}
			case "libri":
				break;
			case "riviste":
				break;
			case "pc":
				dati = leggiDati(SELECTPCANDCOMPONENTI);
				while(dati.next())
				{
					nome = dati.getString("nome");
					descrizione = dati.getString("descrizione");
					ricarico = dati.getDouble("ricarico");
					quantita = dati.getInt("quantita");
					switch(dati.getString("tipo")) {
					case "processore":
						proc = new Processore(dati.getString("componente"),dati.getString("cmpdescrizione"),0,0,0);
						break;
					case "hd":
						hd = new Hd(dati.getString("componente"),dati.getString("cmpdescrizione"),0,0,0);
						break;
					case "ram":
						ram = new Ram(dati.getString("componente"),dati.getString("cmpdescrizione"),0,0,0);
						break;
					default:
					}
					lista.add(new PC(nome
							,descrizione
							,proc
							,ram
							,hd
							,ricarico
							,quantita
							));
				}
				break;
			default:
			}
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return lista;
	}

	/*
	 * 
	 */
	public ArrayList<Articolo> articoli(String tipo, double prezzomax, int quantitamin, String descrizione){
		
		ArrayList<Articolo> resultList = new ArrayList<Articolo>();
		ArrayList<Object> params = new ArrayList<Object>();
		ResultSet rs = null;
		Processore proc = null;
		Ram ram = null;
		Hd hd = null;
		
		params.add(prezzomax);
		params.add(quantitamin);
		params.add(descrizione);
		
		try {
			
			switch(tipo){
			case CONSOLE:
				rs = leggiDatiConParam(SELECTCONSOLEPARAM,params);
				while(rs.next()){
					resultList.add(new Console(rs.getString("nome")
							,rs.getString("descrizione")
							,rs.getDouble("prezzo")
							,rs.getInt("quantita")
							));					
				}
				break;
			case VIDEOGAME:
				rs = leggiDatiConParam(SELECTVIDEOGAMESPARAM,params);
				break;
			case LIBRI:
				rs = leggiDatiConParam(SELECTLIBRIPARAM,params);
				break;
			case RIVISTE:
				rs = leggiDatiConParam(SELECTRIVISTEPARAM,params);
				break;
			case PC:
				rs = leggiDatiConParam(SELECTPCPARAM,params);
				break;
			default:
				
			}
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
		return resultList;
	}
	
	/*
	 * 
	 */
	public boolean login(String email, String password){
		boolean rs = false;
		return rs;
	}
	
	/*
	 *
	 */
	public ArrayList<Articolo> articoli(String tipo, String padre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double sommaQuantita(String tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double sommaQuantita(String tipo, String padre) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double sommaPrezzo(String tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double sommaPrezzo(String tipo, String padre) {
		// TODO Auto-generated method stub
		return 0;
	}

}
