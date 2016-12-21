package dao;

public abstract class DaoUser {

	protected IDBNegozio dao;
	
	public DaoUser (IDBNegozio dao)
	{ 
		this.dao = dao;
	}
	
	
	
	
}
