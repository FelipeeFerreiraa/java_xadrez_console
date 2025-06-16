package tabuleiro;

/**
 *
 * @author felip
 */
public class Peca {

    //---------- VARIAVEIS
    protected Posicao posicao;
    private Tabuleiro tabuleiro;

    //---------- CONSTRUTORES
    public Peca(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        this.posicao = null;
    }

    //---------- GETs
    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
}
