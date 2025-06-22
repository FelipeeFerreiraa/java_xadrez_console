package tabuleiro;

/**
 *
 * @author felip
 */
public abstract class Peca {

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

    //---------- METODOS
    public abstract boolean[][] possiveisMovimentos();

    public boolean possivelMovimento(Posicao posicao) {
        //---- METODO QUE FAZ UM GANCHO COM A SUBCLASSE --> POO  METODO TEMPLATE 
        return possiveisMovimentos()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean haAlgumPossivelMovimento() {
        boolean[][] mat = possiveisMovimentos();

        for (int x = 0; x < mat.length; x++) {
            for (int y = 0; y < mat.length; y++) {
                if (mat[x][y]) {
                    return true;
                }
            }
        }

        return false;
    }
}
