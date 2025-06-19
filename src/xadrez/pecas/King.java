package xadrez.pecas;

import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

/**
 *
 * @author felip
 */
public class King extends PecaXadrez { //----- REI

    //---------- CONSTRUTORES
    public King(Tabuleiro tabuleiro, Cor cor) {
        super(cor, tabuleiro);
    }

    //---------- METODOS
    @Override
    public String toString() {
        return "K";
    }

}
