import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mapa {
    private int numeroDeLinhas;
    private int numeroDeColunas;
    private Grafo grafo;
    private Vertice[] vertices;
    private Vertice[] pontos1a9 = new Vertice[9];
    private char[][] matrizMapa;

    public Mapa() {

        try {
            Scanner sc = new Scanner(new File("exemplos/mapa4.txt"));
            String linhaUm = sc.nextLine();
            String[] arrayLinhaUm = (linhaUm.split(" "));

            this.numeroDeColunas = Integer.parseInt(arrayLinhaUm[1]);
            this.numeroDeLinhas = Integer.parseInt(arrayLinhaUm[0]);

            matrizMapa = new char[numeroDeLinhas][numeroDeColunas];
            vertices = new Vertice[numeroDeLinhas * numeroDeColunas];
            grafo = new Grafo(numeroDeLinhas * numeroDeColunas);

            int contador = 0;

            while (sc.hasNextLine()) {
                for (int i = 0; i < matrizMapa.length; i++) {
                    String linha = sc.nextLine();
                    for (int j = 0; j < matrizMapa[i].length; j++) {
                        matrizMapa[i][j] = linha.charAt(j);
                        Vertice v = new Vertice(contador, i, j, matrizMapa[i][j]);
                        this.vertices[contador] = v;
                        contador++;
                        try {
                            int vertice = Integer.parseInt(String.valueOf(matrizMapa[i][j]));
                            this.pontos1a9[vertice - 1] = v;
                        } catch (NumberFormatException nfe) {
                            continue;
                        }
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Arquivo não encontrado!");
        } catch (Exception e) {
            System.out.println(e);
        }

        Grafo grafo = new Grafo(numeroDeColunas * numeroDeLinhas);

        ligar(grafo, numeroDeLinhas, numeroDeColunas);

        int ondeEstou = 0;
        int ondeVou = ondeEstou + 1;
        int[] novasDistancias = new int[9];
        int maisLonginquo = 0;

        for (int i = 0; i < pontos1a9.length; i++) {
            Dijkstra inicio = new Dijkstra(grafo, pontos1a9[ondeEstou].getIndice());
            try {
                int d = inicio.getDistancia(pontos1a9[ondeVou].getIndice());
                ondeEstou = ondeVou;
                maisLonginquo = ondeEstou;
                novasDistancias[ondeVou] = d;
                ondeVou++;
            } catch (Exception ofb) {
                ondeVou++;
            }

        }
        Dijkstra fim = new Dijkstra(grafo, pontos1a9[maisLonginquo].getIndice());
        int d = fim.getDistancia(pontos1a9[0].getIndice());
        novasDistancias[0] = d;

        int somatorio = 0;

        for (int j = 1; j < novasDistancias.length; j++) {
            int distanciaJ = novasDistancias[j];
            if (distanciaJ == 0) {
                System.out.println("Não é possível chegar ao porto " + (j + 1));
            } else {
                System.out.println("A distancia do porto " + (j + 1) + " é: " + distanciaJ);
                somatorio += novasDistancias[j];
            }

        }

        System.out.println("A distancia do ponto 0 é: " + novasDistancias[0]);
        somatorio += novasDistancias[0];

        System.out.println("Somatório: " + somatorio);
    }

    public void ligar(Grafo grafo, int numeroDeLinhas, int numeroDeColunas) {
        for (int i = 0; i < numeroDeLinhas; i++) {
            for (int j = 0; j < numeroDeColunas; j++) {
                if (matrizMapa[i][j] == '*') {
                    continue;
                }
                if (i > 0 && matrizMapa[i - 1][j] != '*') {
                    grafo.adicionarAresta(i * numeroDeColunas + j, (i - 1) * numeroDeColunas + j);
                }
                if (i < numeroDeLinhas - 1 && matrizMapa[i + 1][j] != '*') {
                    grafo.adicionarAresta(i * numeroDeColunas + j, (i + 1) * numeroDeColunas + j);
                }
                if (j > 0 && matrizMapa[i][j - 1] != '*') {
                    grafo.adicionarAresta(i * numeroDeColunas + j, i * numeroDeColunas + j - 1);
                }
                if (j < numeroDeColunas - 1 && matrizMapa[i][j + 1] != '*') {
                    grafo.adicionarAresta(i * numeroDeColunas + j, i * numeroDeColunas + j + 1);
                }
            }
        }
    }

}