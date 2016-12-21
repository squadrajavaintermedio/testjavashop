package utenti;

import java.util.ArrayList;

import articoli.Articolo;
import dao.DaoUser;
import dao.IDBNegozio;

public abstract class Utente extends DaoUser implements IUtenti{
	
	private String nome;
	private String cognome;
	private String nomeUtente;
	private String password;
	private ArrayList<Articolo> listaSpesa;

	
	public ArrayList<Articolo> getListaSpesa() {
		return listaSpesa;
	}

	public void setListaSpesa(ArrayList<Articolo> listaSpesa) {
		this.listaSpesa = listaSpesa;
	}

	public Utente(String nome, String cognome, String nomeUtente, String password, IDBNegozio dao) {
		super(dao);
		this.nome = nome;
		this.cognome = cognome;
		this.nomeUtente = nomeUtente;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
