package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

/**
 *
 * @author felip
 */
public class Rook extends PecaXadrez { //-------- TORRE

    //---------- CONSTRUTORES
    public Rook(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    //---------- METODOS
    @Override
    public String toString() {
        return "R";
    }

}
