package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

/**
 *
 * @author felip
 */
public abstract class PecaXadrez extends Peca {

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
    protected boolean haPecaOponente(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return (p != null) && (p.getCor() != cor);
    }

}
