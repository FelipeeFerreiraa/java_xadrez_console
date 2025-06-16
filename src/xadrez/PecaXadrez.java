package xadrez;

import tabuleiro.Peca;
import tabuleiro.Tabuleiro;

/**
 *
 * @author felip
 */
public class PecaXadrez extends Peca {

    //---------- VARIAVEIS
    private Cor cor;

    //---------- CONSTRUTORES
    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    //---------- GETs
    public Cor getCor() {
        return cor;
    }

    //---------- METODOS
}
