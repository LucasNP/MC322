package mc322.lab04;

public class AppRestaUm {
	String source;
	String target;
		
	private static String[] lerComandos(String caminho){
		CSVReader csv = new CSVReader();
		csv.setDataSource(caminho);
		return csv.requestCommands();
	}
	
	public static String[] executaJogo(String caminhoComandos){
		Tabuleiro tab = new Tabuleiro("mc322/lab04/mapa.csv");
		
		String comandos[] = lerComandos(caminhoComandos);
		String EstadosDoJogo[] = null;
		
		System.out.println("Tabuleiro inicial:");
		System.out.println(tab.getTabuleiro()+"\n");
		
		for(int i = 0; i < comandos.length; i++){
			System.out.println("Source: " + comandos[i].charAt(0) + comandos[i].charAt(1));
			System.out.println("Target: " + comandos[i].charAt(3) + comandos[i].charAt(4));
			int imprimirTab = tab.mover(comandos[i]);
			if(imprimirTab == 1) System.out.println(tab.getTabuleiro()+"\n");
		}
		
		return EstadosDoJogo;
	}
	
}
