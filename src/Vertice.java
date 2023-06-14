public class Vertice {
    private int indice;
    private int linha;
    private int coluna;
    private char caracter;

    public Vertice(int indice, int linha, int coluna, char caracter) {
        this.indice = indice;
        this.linha = linha;
        this.coluna = coluna;
        this.caracter = caracter;
    }

    public int getIndice() {
        return this.indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getLinha() {
        return this.linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return this.coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public char getCaracter() {
        return this.caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }
}