package mc322.lab04;

public class Tabuleiro {
	Peca mapa[][];
	
	private Peca[][] criadorPecas(String mapa[]){
		if(mapa.length ==0){
			System.out.println("OMG, criador de pecas recebeu mapa com tamanho 0");
			return null;
		}
			
		Peca pMapa[][] = new Peca[mapa.length][mapa[0].length()];
		
		for(int i = 0;i<mapa.length;i++){
			for(int j=0;j<mapa[0].length();j++){
				switch(mapa[i].charAt(j)){
					case '#':
						pMapa[i][j] = new Peca(' ');
						break;
					case 'P':
						pMapa[i][j] = new Peca('P');
						break;
					case '-':
						pMapa[i][j] = new Peca('-');
						break;
				}
					
			}
		}
		this.mapa = pMapa;
		return pMapa;
	}
	
	private Peca[][] carregarMapa(String caminho){
		CSVReader csv = new CSVReader();
		csv.setDataSource(caminho);
		String mapa[] = csv.requestCommands();
		return criadorPecas(mapa);
	}
	
	public String getTabuleiro(){
        String tabuleiroConvertido = "";
        for(int i =1;i<(mapa.length)-1;i++){
        	tabuleiroConvertido += (mapa.length-i-1) + " ";
            for(int j =1;j<(mapa[0].length)-1;j++){
            	tabuleiroConvertido += mapa[i][j].simbolo + " ";
            }
            tabuleiroConvertido += "\n";
        }
        String alfabeto = " abcdefg";
        tabuleiroConvertido += " ";
        for(int j=1;j<(mapa[0].length)-1;j++) tabuleiroConvertido += " " + alfabeto.charAt(j);
        return tabuleiroConvertido;
    }
	
	public int mover(String comando){
		int x1 = (comando.charAt(0) - 'a') + 1;
		int y1 =  comando.charAt(1) - '0';
		int x2 = (comando.charAt(3) - 'a') + 1;
		int y2 =  comando.charAt(4) - '0';
				
		int xm = (x1+x2)/2;
		int ym = (y1+y2)/2;
		
		boolean c1 = mapa[y2][x2].simbolo == '-';
		boolean c2 = mapa[ym][xm].simbolo == 'P';
		boolean c3 = Math.abs(x1-x2) == 2 && Math.abs(y1-y2) == 0;
		boolean c4 = Math.abs(y1-y2) == 2 && Math.abs(x1-x2) == 0;
	
		
		if(c1 && c2 && (c3 || c4) ){
			mapa[y1][x1].simbolo = '-';
			mapa[ym][xm].simbolo = '-';
			mapa[y2][x2].simbolo = 'P';
			return 1;
		}
		else {
			System.out.println("Movimento inválido");
			return 0;
		}

	}
	
	Tabuleiro(String caminhoMapa){
		Peca tab[][] = carregarMapa(caminhoMapa);
	}
}
