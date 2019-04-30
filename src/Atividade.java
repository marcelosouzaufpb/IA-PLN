import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Atividade {

	static ArrayList<String> texto = new ArrayList<String>();
	static ArrayList<String> sugestao = new ArrayList<String>();

	public static void lerAraquivo() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\marce\\Desktop\\Nova pasta\\teste.txt"));
		while (br.ready()) {
			String[] linha = br.readLine().split(" ");
			for (String s : linha) {
				texto.add(s);
			}
		}
		br.close();
	}

	public static String sugerirBigrama(String palavra) {
		double probabilidade1 = bigrama(palavra, sugestao.get(0));
		String resultado = "";
		int tamanho = sugestao.size() / 2;
		for (int i = 0; i < tamanho; i++) {
			double probabilidade2 = bigrama(palavra, sugestao.get(i));
			if (probabilidade1 <= probabilidade2) {
				probabilidade1 = probabilidade2;
				resultado = sugestao.get(i);
			}

		}
		// System.out.println("resultado: " +resultado);
		while (sugestao.contains(resultado)) {
			sugestao.remove(resultado);
			// System.out.println("removeu: " +resultado);
		}

		return palavra + " " + resultado;
	}

	public static String sugeriTrigrama(String palavra1, String palavra2) {
		double probabilidade1 = trigrama(palavra1, palavra2, sugestao.get(0));
		String resultado = "";
		int tamanho = sugestao.size() / 2;
		for (int i = 0; i < tamanho; i++) {
			double probabilidade2 = trigrama(palavra1, palavra2, sugestao.get(i));
			if (probabilidade1 <= probabilidade2) {
				probabilidade1 = probabilidade2;
				resultado = sugestao.get(i);
			}
		}
		while (sugestao.contains(resultado)) {
			sugestao.remove(resultado);
		}
		return resultado;
	}

	public static int qtdPalavra(String palavra) {
		int cont = 0;
		for (String s : texto) {
			if (s.equals(palavra)) {
				cont++;
			}
		}
		return cont;
	}

	public static int qtdPalavrasJuntas2(String palavraAtual, String palavraProxi) {
		int cont = 0;
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).equals(palavraAtual) && texto.get(i + 1).equals(palavraProxi)) {
				cont++;
			}

		}

		if (sugestao.size() == 0) {
			for (int i = 0; i < texto.size() - 1; i++) {
				if (texto.get(i).equals(palavraProxi)) {
					sugestao.add(texto.get(i + 1));

				}
			}
		}

		return cont;
	}

	public static int qtdPalavrasJuntas3(String palavraAtual, String palavraProx1, String palavraProx2) {
		int cont = 0;
		for (int i = 0; i < texto.size(); i++) {
			if (texto.get(i).equals(palavraAtual) && texto.get(i + 1).equals(palavraProx1)
					&& texto.get(i + 2).equals(palavraProx2)) {
				cont++;
				sugestao.add(texto.get(i + 3));
			}
		}

		for (int i = 0; i < texto.size() - 1; i++) {
			if (texto.get(-1).equals(palavraProx2)) {
				sugestao.add(texto.get(i + 3));

			}
		}
		return cont;
	}

	public static double bigrama(String palavraAtual, String palavraProxima) {
		double qtdPalavrasJuntas = qtdPalavrasJuntas2(palavraAtual, palavraProxima);
		double qtdPalavra = qtdPalavra(palavraProxima);
		double resultado = qtdPalavrasJuntas / qtdPalavra;
		return resultado;
	}

	public static double trigrama(String palavraAtual, String palavraProx1, String palavraProx2) {
		double qtdPalavrasJuntas = qtdPalavrasJuntas3(palavraAtual, palavraProx1, palavraProx2);
		double qtdPalavra = qtdPalavrasJuntas2(palavraProx1, palavraProx2);
		double resultado = qtdPalavrasJuntas / qtdPalavra;
		return resultado;
	}

	public static void main(String[] args) throws IOException {
		lerAraquivo();
		bigrama("oi", "oi");
		System.out.println("Quadro de sugestoes: " + sugestao);
		System.out.println("Sugestao1: " + sugerirBigrama("oi"));
		System.out.println("Quadro de sugestoes: " + sugestao);
		System.out.println("Sugestao2: " + sugerirBigrama("oi"));
		System.out.println("Quadro de sugestoes: " + sugestao);
		System.out.println("Sugestao3: " + sugerirBigrama("oi"));

	}

}
