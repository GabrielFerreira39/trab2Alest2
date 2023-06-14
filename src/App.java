
public class App {
    public static void main(String[] args) throws Exception {
        long tempoInicial = System.currentTimeMillis();
        new Mapa();
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) + " ms");
    }
}
