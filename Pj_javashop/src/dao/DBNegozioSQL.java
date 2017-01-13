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
import articoli.Libro;
import articoli.Rivista;

public class DBNegozioSQL implements IDBNegozio {

	
	private Connection db;
	ArrayList<Articolo> lista = new ArrayList<Articolo>();	// for cash
	ArrayList<Articolo> utenti = new ArrayList<Articolo>();	// for cash
	
	private final static String CONSOLE = "console";
	private final static String VIDEOGAMES = "videogames";
	private final static String LIBRI = "libri";
	private final static String RIVISTE = "riviste";
	private final static String PROCESSORE = "processore";
	private final static String RAM = "ram";
	private final static String HD = "hd";
	private final static String SQLITEJDBC = "org.sqlite.JDBC";
	
	// SQL statement(SELECT)
	private final static String SELECTCOUNTCONSOLE 		= "SELECT COUNT(*) FROM CONSOLE;";
	private final static String SELECTCOUNTVIDEOGIOCHI 	= "SELECT COUNT(*) FROM VIDEOGIOCHI;";
	private final static String SELECTCOUNTLIBRI 		= "SELECT COUNT(*) FROM LIBRI;";
	private final static String SELECTCOUNTRIVISTE 		= "SELECT COUNT(*) FROM RIVISTE;";
	private final static String SELECTCOUNTPC 			= "SELECT COUNT(*) FROM PC;";	

	//
	private final static String SELECTCONSOLE 			= "SELECT NOME,DESCRIZIONE,PREZZO,QUANTITA FROM CONSOLE;";
	private final static String CONSOLE_NOME = "name";
	private final static String CONSOLE_DESCRIZIONE = "descrizione";
	private final static String CONSOLE_PREZZO = "prezzo";
	private final static String CONSOLE_QUANTITA = "quantita";

	private final static String SELECTVG 				= "SELECT "
			+ "videogiochi.*, CONSOLE.nome as console, vgcompa.PREZZO, vgcompa.disponibilita, vgcompa.quantita "
			+ "FROM "
			+ "CONSOLE,videogiochi,vgcompa "
			+ "WHERE "
			+ "videogiochi.ID = vgcompa.idvideogiochi"
			+ " AND "
			+ "CONSOLE.ID = vgcompa.IDCONSOLE;";
	private final static String VIDEOGAME = "videogame";
	private final static String VIDEOGAME_NOME = "nome";
	private final static String VIDEOGAME_DESCRIZIONE = "descrizione";
	private final static String VIDEOGAME_PREZZO = "prezzo";
	private final static String VIDEOGAME_CONSOLE = "console";
	private final static String VIDEOGAME_DISPONIBILITA = "disponibilita";
	private final static String VIDEOGAME_GENERE = "genere";
	private final static String VIDEOGAME_QUANTITA = "quantita";	

	private final static String SELECTPCANDCOMPONENTI 	= "SELECT pc.*, componenti.nome componente, componenti.descrizione cmpdescrizione, componenti.tipo "
			+ " FROM pc, componenti, pccompatibile"
			+ " WHERE "
			+ " pc.id = pccompatibile.idpc "
			+ " AND "
			+ " componenti.id = pccompatibile.idcomponenti "
			+ " ORDER BY pc.id;";
	private final static String PC = "pc";
	private final static String PC_NOME = "nome";
	private final static String PC_DESCRIZIONE = "descrizione";
	private final static String PC_RICARICO = "ricarico";
	private final static String PC_QUANTITA = "quantita";
	private final static String PC_TIPO = "tipo";
	private final static String PC_COMPONENTE = "componenti";
	private final static String PC_CMPDESCRIZIONE = "cmpdescrizione";

	//
	private final static String SELECTCONSOLEPARAM		= "SELECT * "
															+ " FROM CONSOLE "
															+ " WHERE prezzo < ? AND quantita > ? AND descrizione = ?;";
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

	// SQL statement(INSERT)
	private final static String INSERTCONSOLE= "";
	private final static String INSERTVIDEOGAMES= "";
	private final static String INSERTLIBRI= "";
	private final static String INSERTRIVISTE= "";
	private final static String INSERTPC= "";
	private final static String INSERTPROCESSORE= "";
	private final static String INSERTRAM= "";
	private final static String INSERTHD= "";

	// SQL statement(UPDATE)
	private final static String UPDATECONSOLE= "";
	private final static String UPDATEVIDEOGAMES= "";
	private final static String UPDATELIBRI= "";
	private final static String UPDATERIVISTE= "";
	private final static String UPDATEPC= "";
	private final static String UPDATEPC = "";
	private final static String UPDATEPROCESSORE = "";
	private final static String UPDATERAM = "";
	private final static String UPDATEHD = "";

	/*
	 * costruttore
	 * 
	 */
	public DBNegozioSQL(String percorso) {
		
		db = null;
		try
		{
			Class.forName(SQLITEJDBC);
			db = DriverManager.getConnection(percorso);
		} catch(Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}		
	}
	
	/*
	 * costruttore
	 * 
	 */
	public DBNegozioSQL(String percorso, boolean cash) {
		
		db = null;
		try
		{
			Class.forName(SQLITEJDBC);
			db = DriverManager.getConnection(percorso);
		} catch(Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		
		lista.addAll(articoli(PC));
		lista.addAll(articoli(VIDEOGAME));
		lista.addAll(articoli(LIBRI));
		lista.addAll(articoli(PROCESSORE));
		lista.addAll(articoli(RAM));
		lista.addAll(articoli(HD));
	}

	/*
	 * 
	 */
	public boolean addArticolo(Articolo Articolo) {
		
		boolean returnValue = false;
		ArrayList<Object> params = new ArrayList<Object>();
		Articolo articolo = null;
		Console console = null;
		VideogamePC vgpc= null;
		VideogameConsole vgconsole = null;
		Libro libro = null;
		Rivista rivista = null;
		PC pc = null;
		Processore proc = null;
		Ram ram = null;
		Hd hd = null;
		
		if (articolo instanceof Console){
			console = (Console) articolo;
			params.add(console.getNome());
			params.add(console.getDescrizione());
			params.add(console.getPrezzo());
			params.add(console.getQuantita());
			returnValue = add(INSERTCONSOLE, params);
		} else if(articolo instanceof VideogamePC){
			vgpc = (VideogamePC) articolo;
			params.add(vgpc.getNome());
			params.add(vgpc.getDescrizione());
			returnValue = add(INSERTVIDEOGAMES, params);
		} else if(articolo instanceof VideogameConsole){
			vgconsole = (VideogameConsole) articolo;
			params.add(vgconsole.getNome());
			params.add(vgconsole.getDescrizione());
			returnValue = add(INSERTVIDEOGAMES, params);
		} else if(articolo instanceof Libro){
			libro = (Libro) articolo;
			params.add(libro.getNome());
			params.add(libro.getDescrizione());
			returnValue = add(INSERTLIBRI, params);
		} else if(articolo instanceof Rivista){
			rivista = (Rivista) articolo;
			params.add(rivista.getNome());
			params.add(rivista.getDescrizione()); 
			returnValue = add(INSERTRIVISTE, params);
		} else if(articolo instanceof PC){
			pc = (PC) articolo;
			params.add(pc.getNome());
			params.add(pc.getDescrizione());
			returnValue = add(INSERTPC, params);
		} else if(articolo instanceof Processore){
			proc = (Processore) articolo;
			params.add(proc.getNome());
			params.add(proc.getDescrizione());
			returnValue = add(INSERTPROCESSORE, params);
		} else if(articolo instanceof Ram){
			ram = (Ram) articolo;
			params.add(ram.getNome());
			params.add(ram.getDescrizione());
			returnValue = add(INSERTRAM, params);
		} else if(articolo instanceof Hd){
			hd = (Hd) articolo;
			params.add(hd.getNome());
			params.add(hd.getDescrizione());
			returnValue = add(INSERTHD, params);
		} else {
			
		}
			
			
		
		return returnValue;		
	}
	
	/*
	 * 
	 */
	public boolean addArticolo(Articolo articolo, String autori) {
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
	public int numeroArticoli(String tipo) {
		
		ResultSet rs = null;
		Statement stmt = null;
		int ris = 0;
		
		try {
			stmt = db.createStatement();
			switch(tipo) {
			case CONSOLE:
				rs = leggiDati(SELECTCOUNTCONSOLE);
				break;
			case VIDEOGAMES:
				rs = leggiDati(SELECTCOUNTVIDEOGIOCHI);
				break;
			case LIBRI:
				rs = leggiDati(SELECTCOUNTLIBRI);
				break;
			case RIVISTE:
				rs = leggiDati(SELECTCOUNTRIVISTE);
				break;
			case PC:
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
		double ricarico = 0;	// for PC
		int quantita = 0;	// for PC
		ResultSet dati = null;
		Processore proc = null;	// for PC
		Hd hd = null;	// for PC
		Ram ram = null;	// for PC
		boolean skipFlg = false;
		String nomeForCompare = "";	// for PC
		
		try {
			switch(tipo){
			case CONSOLE:
				dati = leggiDati(SELECTCONSOLE);
				while(dati.next())
				{
					lista.add(new Console(dati.getString(CONSOLE_NOME)
							,dati.getString(CONSOLE_DESCRIZIONE)
							,dati.getDouble(CONSOLE_PREZZO)
							,dati.getInt(CONSOLE_QUANTITA)
							));
				}
				break;
			case VIDEOGAMES:
				dati = leggiDati(SELECTVG);
				while(dati.next())
				{
					if(dati.getString(CONSOLE).equals(PC))
						lista.add(new VideogamePC(dati.getString(VIDEOGAME_NOME)
								,dati.getString(VIDEOGAME_DESCRIZIONE)
								,dati.getDouble(VIDEOGAME_PREZZO)
								,dati.getString(VIDEOGAME_CONSOLE)
								,dati.getString(VIDEOGAME_DISPONIBILITA)
								,dati.getString(VIDEOGAME_GENERE)
								,dati.getInt(VIDEOGAME_QUANTITA)
								));
					else
							lista.add(new VideogameConsole(dati.getString(VIDEOGAME_NOME)
									,dati.getString(VIDEOGAME_DESCRIZIONE)
									,dati.getDouble(VIDEOGAME_PREZZO)
									,dati.getString(VIDEOGAME_CONSOLE)
									,dati.getString(VIDEOGAME_DISPONIBILITA)
									,dati.getString(VIDEOGAME_GENERE)
									,dati.getInt(VIDEOGAME_QUANTITA)
									));
				}
			case LIBRI:
				break;
			case RIVISTE:
				break;
			case PC:
				dati = leggiDati(SELECTPCANDCOMPONENTI);
				while(dati.next())
				{
					if(!(nome.equals(dati.getString(PC_NOME))) && (skipFlg == true)) {
						lista.add(new PC(nome
								,descrizione
								,proc
								,ram
								,hd
								,ricarico
								,quantita
								));
					}
					nome = dati.getString(PC_NOME);
					descrizione = dati.getString(PC_DESCRIZIONE);
					ricarico = dati.getDouble(PC_RICARICO);
					quantita = dati.getInt(PC_QUANTITA);
					switch(dati.getString(PC_TIPO)) {
					case PROCESSORE:
						proc = new Processore(dati.getString(PC_COMPONENTE),dati.getString(PC_CMPDESCRIZIONE),0,0,0);
						break;
					case HD:
						hd = new Hd(dati.getString(PC_COMPONENTE),dati.getString(PC_CMPDESCRIZIONE),0,0,0);
						break;
					case RAM:
						ram = new Ram(dati.getString(PC_COMPONENTE),dati.getString(PC_CMPDESCRIZIONE),0,0,0);
						break;
					default:
					}
					skipFlg = true;
				}
				lista.add(new PC(nome
						,descrizione
						,proc
						,ram
						,hd
						,ricarico
						,quantita
						));
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

	/*
	 * 
	 */
	private int getIdAutori(String autori) {
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
	private ResultSet leggiDati(String SQLSelect) {
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
	private ResultSet leggiDatiConParam(String sql, ArrayList<Object> param) {
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
	private boolean add(String SQLInsert, ArrayList<Object> params) {
		boolean rtnValue = false;
		int result = 0;
		try {
			PreparedStatement preparedstatement = db.prepareStatement(SQLInsert);
			
			// executeUpdate:return the row count of insert
			result = preparedstatement.executeUpdate(SQLInsert);
			
			if (!(result == 0))
				rtnValue = true;
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

		return rtnValue;
	}

	/*
	 * 
	 */
	private boolean modifica(String SQLUpdate) {
		boolean rtnValue = false;
		int result = 0;
		try {
			PreparedStatement preparedstatement = db.prepareStatement(SQLUpdate);
			
			// executeUpdate:return the row count of update
			result = preparedstatement.executeUpdate(SQLUpdate);
			
			if (!(result == 0))
				rtnValue = true;
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

		return rtnValue;
	}
	
	/*
	 * 
	 */
	private String preparaSql(ArrayList<Object> param) {
		String sql = "";
		return sql;
	}
}
