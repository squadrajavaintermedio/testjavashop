package prototype;

import articoli.Console;

public class PrototipiConsole {

	Console[] console = new Console[3];
	
	public PrototipiConsole()
	{
		console[0] = new Console("NES", "Nintendo 8 bit", 200);
		console[1] = new Console("SNES", "Nintendo 16 bit", 300);
		console[2] = new Console("MEGADRIVE", "Sega 16 bit", 300);
	}
	

	public Console copia(int p)
	{
		return (Console) console[p].clone();
		
	}
	
	
}
