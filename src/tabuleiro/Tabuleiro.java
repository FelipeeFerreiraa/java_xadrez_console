package tabuleiro;

/**
 *
 * @author felip
 */
public class Tabuleiro {

    //---------- VARIAVEIS
    private int linhas, colunas;
    private Peca[][] pecas;

    //---------- CONSTRUTORES
    public Tabuleiro(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }

    //---------- GETs
    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    //---------- SETs
    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

    //---------- METODOS
    public Peca peca(int linha, int coluna) {
        return this.pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao) {
        return this.pecas[posicao.getLinha()][posicao.getColuna()];
    }

}
