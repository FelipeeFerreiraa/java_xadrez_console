package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

/**
 *
 * @author felip
 */
public class Bishop extends PecaXadrez {
//---------- CONSTRUTORES

    public Bishop(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    //---------- METODOS
    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // NOROESTE
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // NORDESTE
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() - 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // SUDESTE
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // SUDOESTE
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setValores(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        return mat;
    }

}
