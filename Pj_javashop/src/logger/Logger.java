package logger;
import java.util.ArrayList;

public class Logger {

	private static Logger instance = null;
	//All'inizio questi cosi sono vuoti...
	private ArrayList<String> eventi = null;
	private Logger()
	{
	//Qui sarebbe carino salvare da file o da DB
	eventi = new ArrayList<String>();
	}
	//NON è statico...
	public void add(String evento)
	{ 
		eventi.add(evento); 
	}
	
	public synchronized static Logger getInstance()
	{
		if(instance==null) instance = new Logger();
		return instance;
	}
	
	
	public String toString()
	{
		return eventi.toString();
	}
	
	
}
