import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("src/exemplo.txt"));
        String linhaUm = sc.nextLine();
        String[] arrayLinhaUm = (linhaUm.split(" "));

        int numeroDeColunas = Integer.parseInt(arrayLinhaUm[1]);
        int numeroDeLinhas = Integer.parseInt(arrayLinhaUm[0]);

        for (int i = 0; i < arrayLinhaUm.length; i++) {
            System.out.println(arrayLinhaUm[i]);
        }

        char[][] texto = new char[numeroDeLinhas][numeroDeColunas];

        int contador = 0;

        while (sc.hasNextLine()) {
            for (int i = 0; i < texto.length; i++) {
                String linha = sc.nextLine();
                for (int j = 0; j < texto[i].length; j++) {
                    texto[i][j] = linha.charAt(j);
                    Vertice v = new Vertice(contador, i, j, texto[i][j]);

                    contador++;

                }
            }
        }
        Grafo g = new Grafo(contador);
        for (int i = 0; i < contador; i++) {
            g.adicionarAresta(contador, i);
        }

    }
}
