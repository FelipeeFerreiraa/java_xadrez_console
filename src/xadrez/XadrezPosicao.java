package xadrez;

import tabuleiro.Posicao;

/**
 *
 * @author felip
 */
public class XadrezPosicao {

    //---------- VARIAVEIS
    private char coluna;
    private int linha;

    //---------- CONSTRUTORES
    public XadrezPosicao(char coluna, int linha) {

        if ((coluna < 'a') || (coluna > 'h') || (linha < 1) || (linha > 8)) {
            throw new XadrezException("-------- ERRO NA INSTANCIAÇÃO DA POSIÇÃO NO TABULEIRO. VALORES VÁLIDOS DE a1 ATÉ h8.");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    //---------- GETs
    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    //---------- METODOS
    protected Posicao convertePosicao() {
        return new Posicao(8 - this.linha, this.coluna - 'a');
    }

    protected static XadrezPosicao invertePosicao(Posicao posicao) {
        return new XadrezPosicao((char) ('a' + posicao.getColuna()), 8 - posicao.getLinha());
    }

    @Override
    public String toString() {
        return "" + coluna + linha;
    }

}
