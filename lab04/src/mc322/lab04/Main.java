package mc322.lab04;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args){
		AppRestaUm jogo = new AppRestaUm();
		String EstadosDoJogo[];
		EstadosDoJogo = jogo.executaJogo("mc322/lab04/comandos.csv");
	}	
}
