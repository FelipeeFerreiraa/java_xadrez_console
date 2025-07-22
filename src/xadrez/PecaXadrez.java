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
    private int contMovimentos;

    //---------- CONSTRUTORES
    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    //---------- GETs
    public Cor getCor() {
        return cor;
    }

    public XadrezPosicao getPecaXadrez() {
        return XadrezPosicao.invertePosicao(posicao);
    }

    public int getContMovimentos() {
        return contMovimentos;
    }

    //---------- METODOS
    protected boolean haPecaOponente(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return (p != null) && (p.getCor() != cor);
    }

    public void aumentandoContMovimentos() {
        this.contMovimentos++;
    }

    public void diminuindoContMovimentos() {
        this.contMovimentos--;
    }
}
