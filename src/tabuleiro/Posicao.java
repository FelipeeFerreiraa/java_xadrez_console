package tabuleiro;

/**
 *
 * @author felip
 */
public class Posicao {

    //---------- VARIAVEIS
    private int linha, coluna;

    //---------- CONSTRUTORES
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    //---------- GETs
    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    //---------- SETs
    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    //---------- METODOS
    @Override
    public String toString() {
        return linha + ", " + coluna;
    }
}
