package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

/**
 *
 * @author felip
 */
public class Queen extends PecaXadrez { //-------- TORRE

    //---------- CONSTRUTORES
    public Queen(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    //---------- METODOS
    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // ACIMA
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // ESQUERDA
        p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // DIREITA
        p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        // ABAIXO
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().existePosicao(p) && !getTabuleiro().haUmaPeca(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().existePosicao(p) && haPecaOponente(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

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
