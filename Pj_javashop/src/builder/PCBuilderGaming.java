package builder;

import articoli.Hd;
import articoli.PC;
import articoli.Processore;
import articoli.Ram;
import articoli.SchedaVideo;

public class PCBuilderGaming implements IPCBuilder {
	PC pc = null;
	@Override
	public void base() {
		pc = new PC
				(	"Les Miserable",
					"Pc per la mia ex",
					new Processore("Celeron","", 30, 500),
					new Ram("DDR3 SAMSUNG","",30,2,"DD3"),
					new Hd("Hd usato", "",30,500),
					300
				);

	}

	@Override
	public void aggiunteGaming() {
		pc.setSchedavideo(
				new SchedaVideo("Nvid", "", 50, 20000)
	);
		
	}

	@Override
	public void aggiunteFinali() {
		// TODO Auto-generated method stub

	}

	@Override
	public PC build() {
		// TODO Auto-generated method stub
		return pc;
	}

}
