package builder;

import articoli.PC;

public interface IPCBuilder {

	public void base();
	public void aggiunteGaming();
	public void aggiunteFinali();
	public PC build();
	
	
}
