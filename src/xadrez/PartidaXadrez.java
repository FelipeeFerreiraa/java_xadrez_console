package xadrez;

import tabuleiro.*;
import xadrez.pecas.King;
import xadrez.pecas.Rook;

/**
 *
 * @author felip
 */
public class PartidaXadrez {

    //---------- VARIAVEIS
    private Tabuleiro tabuleiro;

    //---------- CONSTRUTORES
    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        iniciarPosicaoPecas();
    }

    //---------- METODOS
    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int x = 0; x < tabuleiro.getLinhas(); x++) {
            for (int y = 0; y < tabuleiro.getColunas(); y++) {
                mat[x][y] = (PecaXadrez) tabuleiro.peca(x, y);
            }
        }
        return mat;
    }

    public PecaXadrez realizandoMovimentoXadrez(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {

        Posicao origem = posicaoOrigem.convertePosicao();
        Posicao destino = posicaoDestino.convertePosicao();
        validarPosicaoOrigem(origem);
        Peca pecaCapturada = executarMovimento(origem, destino);
        return (PecaXadrez) pecaCapturada;
    }

    private Peca executarMovimento(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removerPeca(origem);
        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(p, destino);
        return pecaCapturada;
    }

    private void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.haUmaPeca(posicao)) {
            throw new XadrezException("-------- NÃO HÁ PEÇA NA POSIÇÃO DE ORIGEM --------");
        }
    }

    private void colocandoNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).convertePosicao());

    }

    private void iniciarPosicaoPecas() {

        colocandoNovaPeca('a', 8, new Rook(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('h', 8, new Rook(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('e', 8, new King(tabuleiro, Cor.WHITE));

        colocandoNovaPeca('a', 1, new Rook(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('h', 1, new Rook(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('d', 1, new King(tabuleiro, Cor.BLACK));

    }
}
