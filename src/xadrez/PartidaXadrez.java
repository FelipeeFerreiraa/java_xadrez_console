package xadrez;

import tabuleiro.*;

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

}
