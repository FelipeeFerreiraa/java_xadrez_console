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

        if (linhas < 1 || colunas < 1) {
            throw new TabuleiroExceptions("-------- ERRO AO CRIAR TABULEIRO: DEVE HAVER PELO MENOS 1 LINHA E 1 COLUNA. --------");
        }

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

    //---------- METODOS
    public Peca peca(int linha, int coluna) {

        if (!existePosicao(linha, coluna)) {
            throw new TabuleiroExceptions("-------- NÃO HÁ POSIÇÃO NO TABULEIRO. --------");
        }
        return this.pecas[linha][coluna];
    }

    public Peca peca(Posicao posicao) {
        if (!existePosicao(posicao)) {
            throw new TabuleiroExceptions("-------- NÃO HÁ POSIÇÃO NO TABULEIRO. --------");
        }
        return this.pecas[posicao.getLinha()][posicao.getColuna()];
    }

    public void colocarPeca(Peca peca, Posicao posicao) {

        if (haUmaPeca(posicao)) {
            throw new TabuleiroExceptions("-------- HÁ UMA OUTRA PEÇA NESTA POSIÇÃO DO TABULEIRO. --------" + posicao);
        }
        this.pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }

    private boolean existePosicao(int linha, int coluna) {
        return (linha >= 0) && (linha < this.linhas) && (coluna >= 0) && (coluna < this.colunas);
    }

    public boolean existePosicao(Posicao posicao) {
        return existePosicao(posicao.getLinha(), posicao.getColuna());
    }

    public boolean haUmaPeca(Posicao posicao) {

        if (!existePosicao(posicao)) {
            throw new TabuleiroExceptions("-------- NÃO HÁ POSIÇÃO NO TABULEIRO. --------");
        }
        return peca(posicao) != null;
    }

}
