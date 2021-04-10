import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;  

//Let's code together 
/* Lucas Nogueira - Problema: */
public class AppLab03 {
	
	public static class AquarioLombriga{
		char lombriga[];
		int TamanhoDoAquario;
		int TamanhoDaLombriga;
		int Posicao_Cabeca;
		int Posicao_Cauda;
		int virado = 0;

		AquarioLombriga(int TamanhoDoAquario, int TamanhoDaLombriga, int Posicao_Cauda){
			if( TamanhoDoAquario < Posicao_Cauda + TamanhoDaLombriga){
				TamanhoDoAquario = TamanhoDaLombriga + Posicao_Cauda + 1;
			}
			if(Posicao_Cauda < 1) Posicao_Cauda = 1;

			this.TamanhoDoAquario = TamanhoDoAquario;
			this.TamanhoDaLombriga = TamanhoDaLombriga;
			this.Posicao_Cabeca = Posicao_Cauda + TamanhoDaLombriga - 1; 

			lombriga = new char[TamanhoDoAquario+1];

			for(int i = 1; i <= TamanhoDoAquario; i++) {
				if(i > Posicao_Cauda && i < Posicao_Cabeca) this.lombriga[i] = '@'; 
				else if(i == Posicao_Cauda){
					this.Posicao_Cauda = i;
					this.lombriga[i] = '@';
				}
				else if(i == Posicao_Cabeca) this.lombriga[i] = '0';
				else this.lombriga[i] = '#';
			}
		}

		void crescer(){
			if(this.virado == 0){
				if(this.lombriga[Posicao_Cauda-1] == '#'){
					this.lombriga[Posicao_Cauda-1] = '@';
					this.Posicao_Cauda--;
				}
			}
			else{
				if(this.lombriga[Posicao_Cauda+1] == '#'){
					this.lombriga[Posicao_Cauda+1] = '@';
					this.Posicao_Cauda++;
				}
			}
		}

		void mover(){
			if(Posicao_Cabeca == 1 || Posicao_Cabeca == TamanhoDoAquario) virar();
			else{
				if(virado == 0){
					for(int i = Posicao_Cabeca; i != Posicao_Cauda; i--){
						this.lombriga[i+1] = this.lombriga[i];
					}
					this.lombriga[Posicao_Cauda] = '#';
					this.Posicao_Cabeca++;
					this.Posicao_Cauda++;
				}
				else{
					for(int i = Posicao_Cabeca; i != Posicao_Cauda; i++){
						this.lombriga[i-1] = this.lombriga[i];
					}
					this.lombriga[Posicao_Cauda] = '#';
					this.Posicao_Cabeca--;
					this.Posicao_Cauda--;
				}
			}
		}

		void virar(){
			this.lombriga[Posicao_Cabeca] = '@';
			this.lombriga[Posicao_Cauda]  = '0';

			int tmp = this.Posicao_Cauda;
			this.Posicao_Cauda = this.Posicao_Cabeca;
			this.Posicao_Cabeca = tmp;

			this.virado = 1;
		}

		String apresenta(){
			String string_lombriga = String.valueOf(this.lombriga);
			return string_lombriga;
		}

	}

	public static class Animacao{
		AquarioLombriga lombriga;
		int TamanhoDoAquario;
		int TamanhoDaLombriga;
		int Posicao;

		int numPassoAtual;
		String Passos;

		Animacao(String SequenciaDaAnimacao){
			this.TamanhoDoAquario  = Integer.parseInt(SequenciaDaAnimacao.substring(0,2));
			this.TamanhoDaLombriga = Integer.parseInt(SequenciaDaAnimacao.substring(2,4));
			this.Posicao = Integer.parseInt(SequenciaDaAnimacao.substring(4,6));
			this.Passos = SequenciaDaAnimacao.substring(6,SequenciaDaAnimacao.length());

			this.lombriga = new AquarioLombriga(this.TamanhoDoAquario, this.TamanhoDaLombriga, this.Posicao);
			this.numPassoAtual = 0;
		}

		String apresenta(){
			return this.lombriga.apresenta();
		}

		void passo(){
			if(numPassoAtual < Passos.length()){
				if(Passos.charAt(numPassoAtual) == 'C') this.lombriga.crescer();
				else if(Passos.charAt(numPassoAtual) == 'M') this.lombriga.mover();
				else if(Passos.charAt(numPassoAtual) == 'V') this.lombriga.virar();
				numPassoAtual++;
			}
		}

	}
	
	public static void main(String[] args){
		String ss = "080603MCMVM";
		Animacao anim = new Animacao(ss);

		String pedro;

		for(int i = 0; i < ss.length()-5; i++){

			pedro = anim.apresenta();
			System.out.println(pedro);
			anim.passo();

			try {
				Thread.sleep(1000);     // Espera 1 segundo para ver a mágica acontecer
				new ProcessBuilder("clear").inheritIO().start().waitFor();      // Para linux (acho que para mac também)
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		}

	}
}