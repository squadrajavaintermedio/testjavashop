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
	

	// JDBC
	private final static String SQLITEJDBC = "org.sqlite.JDBC";

	private final static String TYPE_STR_PC = "pc";
	private final static String TYPE_STR_CONSOLE = "console";
	private final static String TYPE_STR_VIDEOGAMES = "videogames";
	private final static String TYPE_STR_LIBRI = "libri";
	private final static String TYPE_STR_RIVISTE = "riviste";
	private final static String TYPE_STR_PROCESSORE = "processore";
	private final static String TYPE_STR_RAM = "ram";
	private final static String TYPE_STR_HD = "hd";
	
	// SQL statement(SELECT)
	private final static String SELECTCOUNTCONSOLE 		= "SELECT COUNT(*) FROM CONSOLE;";
	private final static String SELECTCOUNTVIDEOGIOCHI 	= "SELECT COUNT(*) FROM VIDEOGIOCHI;";
	private final static String SELECTCOUNTLIBRI 		= "SELECT COUNT(*) FROM LIBRI;";
	private final static String SELECTCOUNTRIVISTE 		= "SELECT COUNT(*) FROM RIVISTE;";
	private final static String SELECTCOUNTPC 			= "SELECT COUNT(*) FROM PC;";	

	//
	private final static String SELECT_CONSOLE 			= "SELECT nome, descrizione, prezzo, quantita FROM console;";
	private final static String TBL_CONSOLE_NOME = "nome";
	private final static String TBL_CONSOLE_DESCRIZIONE = "descrizione";
	private final static String TBL_CONSOLE_PREZZO = "prezzo";
	private final static String TBL_CONSOLE_QUANTITA = "quantita";

	private final static String SELECT_VG 				= "SELECT "
			+ "videogiochi.*, CONSOLE.nome as console, vgcompa.PREZZO, vgcompa.disponibilita, vgcompa.quantita "
			+ "FROM "
			+ "CONSOLE,videogiochi,vgcompa "
			+ "WHERE "
			+ "videogiochi.ID = vgcompa.idvideogiochi"
			+ " AND "
			+ "CONSOLE.ID = vgcompa.IDCONSOLE;";
	private final static String TBL_VIDEOGAME_NOME = "nome";
	private final static String TBL_VIDEOGAME_DESCRIZIONE = "descrizione";
	private final static String TBL_VIDEOGAME_PREZZO = "prezzo";
	private final static String TBL_VIDEOGAME_CONSOLE = "console";
	private final static String TBL_VIDEOGAME_DISPONIBILITA = "disponibilita";
	private final static String TBL_VIDEOGAME_GENERE = "genere";
	private final static String TBL_VIDEOGAME_QUANTITA = "quantita";	

	private final static String SELECT_PCANDCOMPONENTI 	= "SELECT pc.*, componenti.nome componente, componenti.descrizione cmpdescrizione, componenti.tipo "
			+ " FROM pc, componenti, pccompatibile"
			+ " WHERE "
			+ " pc.id = pccompatibile.idpc "
			+ " AND "
			+ " componenti.id = pccompatibile.idcomponenti "
			+ " ORDER BY pc.id;";
	private final static String TBL_PC_NOME = "nome";
	private final static String TBL_PC_DESCRIZIONE = "descrizione";
	private final static String TBL_PC_RICARICO = "ricarico";
	private final static String TBL_PC_QUANTITA = "quantita";
	private final static String TBL_PC_TIPO = "tipo";
	private final static String TBL_PC_COMPONENTE = "componente";
	private final static String TBL_PC_CMPDESCRIZIONE = "cmpdescrizione";

	private final static String SELECT_LIBRI = "SELECT * FROM libri;";
	private final static String TBL_LIBRI_NOME = "nome";
	private final static String TBL_LIBRI_DESCRIZIONE = "descrizione";
	private final static String TBL_LIBRI_QUANTITA = "quantita";
	private final static String TBL_LIBRI_PREZZO = "prezzo";

	private final static String SELECT_RIVISTE = "SELECT "
			+ " riviste.*, autori.nome as autori"
			+ " FROM "
			+ " riviste, autori "
			+ " WHERE "
			+ " autori.id = riviste.idautori;";
	private final static String TBL_RIVISTE_NOME = "nome";
	private final static String TBL_RIVISTE_DESCRIZIONE = "descrizione";
	private final static String TBL_RIVISTE_AUTORI = "autori";
	private final static String TBL_RIVISTE_PERIODICITA = "periodicita";
	private final static String TBL_RIVISTE_PREZZO = "prezzo";

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
	private final static String INSERT_CONSOLE= "INSERT INTO console (nome,descrizione,prezzo,quantita) VALUES (?,?,?,?);";
	private final static String INSERT_CONSOLE_IDAUTORE= "INSERT INTO console (name,descrizione,prezzo,quantita,idautori) VALUES (?,?,?,?,?);";
	
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
		
		this(percorso);

		if (cash) {
			lista.addAll(articoli(TYPE_STR_PC));
			lista.addAll(articoli(TYPE_STR_VIDEOGAMES));
			lista.addAll(articoli(TYPE_STR_LIBRI));
			lista.addAll(articoli(TYPE_STR_RIVISTE));
			lista.addAll(articoli(TYPE_STR_CONSOLE));
			lista.addAll(articoli(TYPE_STR_PROCESSORE));
			lista.addAll(articoli(TYPE_STR_RAM));
			lista.addAll(articoli(TYPE_STR_HD));			
		}
	}

	/*
	 * Aggiunge l'articolo nel Database
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	: Articolo articolo
	 * Output	: boolean
	 */
	public int addArticolo(Articolo articolo) {

		int returnValue = -1;
		ArrayList<Object> params = new ArrayList<Object>();
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
			returnValue = add(INSERT_CONSOLE, params);
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
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
	 */
	public int addArticolo(Articolo articolo, String autori) {
		String sql = "";
		int returnValue = 0;
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
				returnValue = p.executeUpdate();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
		if(articolo instanceof VideogameConsole)
		{
			VideogameConsole vgConsole = (VideogameConsole) articolo;
		}
				
		return returnValue;
	}
	
	/*
	 * Conta la quantita dell articoli
	 * Precondizione	:
	 * 	I parametri devono essere "pc", "console", "videogames", "libri" e "riviste"
	 * Argoritmo	:
	 * 	chiama leggiDati(tipo)
	 * Input	: String tipo
	 * Output	: int quantita dell articoli
	 */
	public int numeroArticoli(String tipo) {
		
		ResultSet rs = null;
		Statement stmt = null;
		int ris = 0;
		
		try {
			stmt = db.createStatement();
			switch(tipo) {
			case TYPE_STR_CONSOLE:
				rs = leggiDati(SELECTCOUNTCONSOLE);
				break;
			case TYPE_STR_VIDEOGAMES:
				rs = leggiDati(SELECTCOUNTVIDEOGIOCHI);
				break;
			case TYPE_STR_LIBRI:
				rs = leggiDati(SELECTCOUNTLIBRI);
				break;
			case TYPE_STR_RIVISTE:
				rs = leggiDati(SELECTCOUNTRIVISTE);
				break;
			case TYPE_STR_PC:
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
	 * Conta la quantita dell articoli
	 * Precondizione	:
	 * 	I parametri devono essere "pc", "console", "videogames", "libri" e "riviste"
	 * Argoritmo	:
	 * 	chiama leggiDatiCash(tipo)
	 * Input	: String tipo
	 * Output	: int quantita dell articoli
	 */
	public int numeroArticoliCash(String tipo){

		int ris = 0;
		
		switch(tipo) {
		case TYPE_STR_CONSOLE:
			ris = leggiDatiCash(TYPE_STR_CONSOLE).size();
			break;
		case TYPE_STR_VIDEOGAMES:
			ris = leggiDatiCash(TYPE_STR_VIDEOGAMES).size();
			break;
		case TYPE_STR_LIBRI:
			ris = leggiDatiCash(TYPE_STR_LIBRI).size();
			break;
		case TYPE_STR_RIVISTE:
			ris = leggiDatiCash(TYPE_STR_RIVISTE).size();
			break;
		case TYPE_STR_PC:
			ris = leggiDatiCash(TYPE_STR_PC).size();
			break;
		default:
		}
			
		return ris;
		
	}
	
	/*
	 * 
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
	 */
	public int numeroArticoli(String tipo, boolean cash){
		int ris = 0;
		
		if (cash)
			ris = numeroArticoliCash(tipo);
		else
			ris = numeroArticoli(tipo);
		
		return ris;
	}

	/*
	 * 
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
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
		
		try {
			switch(tipo){
			case TYPE_STR_CONSOLE:
				dati = leggiDati(SELECT_CONSOLE);
				while(dati.next())
				{
					lista.add(new Console(dati.getString(TBL_CONSOLE_NOME)
							,dati.getString(TBL_CONSOLE_DESCRIZIONE)
							,dati.getDouble(TBL_CONSOLE_PREZZO)
							,dati.getInt(TBL_CONSOLE_QUANTITA)
							));
				}
				break;
			case TYPE_STR_VIDEOGAMES:
				dati = leggiDati(SELECT_VG);
				while(dati.next())
				{
					if(dati.getString(TBL_VIDEOGAME_CONSOLE).equals(TYPE_STR_PC))
						lista.add(new VideogamePC(dati.getString(TBL_VIDEOGAME_NOME)
								,dati.getString(TBL_VIDEOGAME_DESCRIZIONE)
								,dati.getDouble(TBL_VIDEOGAME_PREZZO)
								,dati.getString(TBL_VIDEOGAME_CONSOLE)
								,dati.getString(TBL_VIDEOGAME_DISPONIBILITA)
								,dati.getString(TBL_VIDEOGAME_GENERE)
								,dati.getInt(TBL_VIDEOGAME_QUANTITA)
								));
					else
							lista.add(new VideogameConsole(dati.getString(TBL_VIDEOGAME_NOME)
									,dati.getString(TBL_VIDEOGAME_DESCRIZIONE)
									,dati.getDouble(TBL_VIDEOGAME_PREZZO)
									,dati.getString(TBL_VIDEOGAME_CONSOLE)
									,dati.getString(TBL_VIDEOGAME_DISPONIBILITA)
									,dati.getString(TBL_VIDEOGAME_GENERE)
									,dati.getInt(TBL_VIDEOGAME_QUANTITA)
									));
				}
				break;
			case TYPE_STR_LIBRI:
				dati = leggiDati(SELECT_LIBRI);
				while(dati.next())
				{
					lista.add(new Libro(dati.getString(TBL_LIBRI_NOME)
							,dati.getString(TBL_LIBRI_DESCRIZIONE)
							,dati.getDouble(TBL_LIBRI_PREZZO)
							,dati.getInt(TBL_LIBRI_QUANTITA)
							));
				}
				break;
			case TYPE_STR_RIVISTE:
				dati = leggiDati(SELECT_RIVISTE);
				while(dati.next())
				{
					lista.add(new Rivista(dati.getString(TBL_RIVISTE_NOME)
							,dati.getString(TBL_RIVISTE_DESCRIZIONE)
							,dati.getString(TBL_RIVISTE_AUTORI)
							,dati.getString(TBL_RIVISTE_PERIODICITA)
							,dati.getDouble(TBL_RIVISTE_PREZZO)
							));
				}
				break;
			case TYPE_STR_PC:
				dati = leggiDati(SELECT_PCANDCOMPONENTI);
				while(dati.next())
				{
					if(!(nome.equals(dati.getString(TBL_PC_NOME))) && (skipFlg == true)) {
						lista.add(new PC(nome
								,descrizione
								,proc
								,ram
								,hd
								,ricarico
								,quantita
								));
					}
					nome = dati.getString(TBL_PC_NOME);
					descrizione = dati.getString(TBL_PC_DESCRIZIONE);
					ricarico = dati.getDouble(TBL_PC_RICARICO);
					quantita = dati.getInt(TBL_PC_QUANTITA);
					switch(dati.getString(TBL_PC_TIPO)) {
					case TYPE_STR_PROCESSORE:
						proc = new Processore(dati.getString(TBL_PC_COMPONENTE),dati.getString(TBL_PC_CMPDESCRIZIONE),0,0,0);
						break;
					case TYPE_STR_HD:
						hd = new Hd(dati.getString(TBL_PC_COMPONENTE),dati.getString(TBL_PC_CMPDESCRIZIONE),0,0,0);
						break;
					case TYPE_STR_RAM:
						ram = new Ram(dati.getString(TBL_PC_COMPONENTE),dati.getString(TBL_PC_CMPDESCRIZIONE),0,0,0);
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
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
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
			case TYPE_STR_CONSOLE:
				rs = leggiDatiConParam(SELECTCONSOLEPARAM,params);
				while(rs.next()){
					resultList.add(new Console(rs.getString("nome")
							,rs.getString("descrizione")
							,rs.getDouble("prezzo")
							,rs.getInt("quantita")
							));					
				}
				break;
			case TYPE_STR_VIDEOGAMES:
				rs = leggiDatiConParam(SELECTVIDEOGAMESPARAM,params);
				break;
			case TYPE_STR_LIBRI:
				rs = leggiDatiConParam(SELECTLIBRIPARAM,params);
				break;
			case TYPE_STR_RIVISTE:
				rs = leggiDatiConParam(SELECTRIVISTEPARAM,params);
				break;
			case TYPE_STR_PC:
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
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
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
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
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
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
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
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
	 */
	private ArrayList<Articolo> leggiDatiCash(String tipo){
		ArrayList<Articolo> rtnList = new ArrayList<Articolo>();
		
		switch(tipo) {
		case TYPE_STR_CONSOLE:
			for (int i = 0; i < lista.size(); i++) {
				if(lista.get(i) instanceof Console)
					rtnList.add(lista.get(i));
			}			
			break;
		case TYPE_STR_PC:
			for (int i = 0; i < lista.size(); i++) {
				if(lista.get(i) instanceof PC)
					rtnList.add(lista.get(i));
			}			
			break;
		case TYPE_STR_VIDEOGAMES:
			for (int i = 0; i < lista.size(); i++) {
				if((lista.get(i) instanceof VideogameConsole) || (lista.get(i) instanceof VideogamePC))
					rtnList.add(lista.get(i));
			}			
		break;
		case TYPE_STR_LIBRI:
			for (int i = 0; i < lista.size(); i++) {
				if(lista.get(i) instanceof Libro)
					rtnList.add(lista.get(i));
			}			
			break;
		case TYPE_STR_RIVISTE:
			for (int i = 0; i < lista.size(); i++) {
				if(lista.get(i) instanceof Rivista)
					rtnList.add(lista.get(i));
			}			
			break;
		default:
		}
		
		return rtnList;
	}
	
	/*
	 * 
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
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
	 * Aggiunge articolo nel Database
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: int risultato del sql.
	 */
	private int add(String SQLInsert, ArrayList<Object> params) {
		int rtnValue = -1;
		int result = 0;
		try {
			PreparedStatement preparedstatement = db.prepareStatement(SQLInsert);
			preparedstatement.setString(1, (String) params.get(0));
			preparedstatement.setString(2, (String) params.get(1));
			preparedstatement.setDouble(3, (Double) params.get(2));
			preparedstatement.setInt(4, (Integer) params.get(3));
			// executeUpdate:return the row count of insert
			rtnValue = preparedstatement.executeUpdate();
			
		} catch (Exception e){
			System.out.println(e.getMessage());
		}

		return rtnValue;
	}

	/*
	 * 
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
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
	 * Precondizione	:
	 * 	
	 * Argoritmo	:
	 * 	
	 * Input	:
	 * Output	: 
	 */
	private String preparaSql(ArrayList<Object> param) {
		String sql = "";
		return sql;
	}
}
