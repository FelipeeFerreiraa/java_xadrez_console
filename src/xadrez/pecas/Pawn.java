package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

/**
 *
 * @author felip
 */
public class Pawn extends PecaXadrez {

    //---------- CONSTRUTORES
    public Pawn(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    //---------- METODOS
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        if (getCor() == Cor.WHITE) {
            p.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p) && getContMovimentos() == 0 && getTabuleiro().existePosicao(p2) && !getTabuleiro().haUmaPeca(p2)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

        } else {

            p.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p) && getContMovimentos() == 0 && getTabuleiro().existePosicao(p2) && !getTabuleiro().haUmaPeca(p2)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

        }

        return mat;

    }

    @Override
    public String toString() {
        return "P";
    }

}
