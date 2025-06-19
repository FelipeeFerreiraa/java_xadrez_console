package xadrez;

import tabuleiro.*;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

/**
 *
 * @author felip
 */
public class PartidaXadrez {

    //---------- VARIAVEIS
    private Tabuleiro tabuleiro;

    //---------- CONSTRUTORES
    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        iniciarPosicaoPecas();
    }

    //---------- GETs
    //---------- SETs
    //---------- METODOS
    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int x = 0; x < tabuleiro.getLinhas(); x++) {
            for (int y = 0; y < tabuleiro.getColunas(); y++) {
                mat[x][y] = (PecaXadrez) tabuleiro.peca(x, y);
            }
        }
        return mat;
    }

    private void iniciarPosicaoPecas() {
        this.tabuleiro.colocarPeca(new Rook(tabuleiro, Cor.WHITE), new Posicao(0, 1));
        this.tabuleiro.colocarPeca(new Rook(tabuleiro, Cor.BLACK), new Posicao(7, 0));
        this.tabuleiro.colocarPeca(new Rook(tabuleiro, Cor.BLACK), new Posicao(7, 0));

        this.tabuleiro.colocarPeca(new King(tabuleiro, Cor.WHITE), new Posicao(4, 4));
        this.tabuleiro.colocarPeca(new King(tabuleiro, Cor.BLACK), new Posicao(7, 4));
    }
}
