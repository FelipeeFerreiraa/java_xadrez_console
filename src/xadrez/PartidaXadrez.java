package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tabuleiro.*;
import xadrez.pecas.King;
import xadrez.pecas.Pawn;
import xadrez.pecas.Rook;

/**
 *
 * @author felip
 */
public class PartidaXadrez {

    //---------- VARIAVEIS
    private Tabuleiro tabuleiro;
    private int turno;
    private Cor JogadorAtual;
    private boolean check;
    private boolean checkMate;

    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();

    //---------- CONSTRUTORES
    public PartidaXadrez() {
        this.tabuleiro = new Tabuleiro(8, 8);
        this.turno = 1;
        this.JogadorAtual = Cor.WHITE;
        this.check = false;
        this.checkMate = false;
        iniciarPosicaoPecas();
    }

    //---------- GETs
    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return JogadorAtual;
    }

    public boolean getCheck() {
        return this.check;
    }

    public boolean getCheckMate() {
        return this.checkMate;
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

    public boolean[][] possiveisMovimentos(XadrezPosicao posicaoOrigem) {
        Posicao posicao = posicaoOrigem.convertePosicao();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).possiveisMovimentos();
    }

    public PecaXadrez realizandoMovimentoXadrez(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {

        Posicao origem = posicaoOrigem.convertePosicao();
        Posicao destino = posicaoDestino.convertePosicao();
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = executarMovimento(origem, destino);

        if (testandoCheck(JogadorAtual)) {
            desFazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("--------------- VOCÊ NÃO PODE SE COLOCAR EM XEQUE! ---------------");
        }

        check = (testandoCheck(oponente(JogadorAtual))) ? true : false;

        if (testandoCheckMate(oponente(JogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }

        return (PecaXadrez) pecaCapturada;
    }

    private Peca executarMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        p.aumentandoContMovimentos();
        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.colocarPeca(p, destino);

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        return pecaCapturada;
    }

    private void desFazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
        p.diminuindoContMovimentos();
        tabuleiro.colocarPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.colocarPeca(p, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).possivelMovimento(destino)) {
            throw new XadrezException("-------- A PEÇA ESCOLHIDA NÃO PODE SE MOVER PARA A POSIÇÃO DESTINO --------");
        }
    }

    private void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.haUmaPeca(posicao)) {
            throw new XadrezException("-------- NÃO HÁ PEÇA NA POSIÇÃO DE ORIGEM --------");
        }

        if (JogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
            throw new XadrezException("-------- A PEÇA ESCOLHIDA NÃO É SUA --------");
        }

        if (!tabuleiro.peca(posicao).haAlgumPossivelMovimento()) {
            throw new XadrezException("-------- NÃO HÁ MOVIMENTOS POSSÍVEIS PARA A PEÇA ESCOLHIDA --------");
        }
    }

    private void colocandoNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.colocarPeca(peca, new XadrezPosicao(coluna, linha).convertePosicao());
        pecasNoTabuleiro.add(peca);
    }

    private void iniciarPosicaoPecas() {

        colocandoNovaPeca('a', 8, new Rook(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('h', 8, new Rook(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('e', 8, new King(tabuleiro, Cor.BLACK));

        colocandoNovaPeca('a', 7, new Pawn(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('b', 7, new Pawn(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('c', 7, new Pawn(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('d', 7, new Pawn(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('e', 7, new Pawn(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('f', 7, new Pawn(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('g', 7, new Pawn(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('h', 7, new Pawn(tabuleiro, Cor.BLACK));

        colocandoNovaPeca('a', 1, new Rook(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('h', 1, new Rook(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('e', 1, new King(tabuleiro, Cor.WHITE));

        colocandoNovaPeca('a', 2, new Pawn(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('b', 2, new Pawn(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('c', 2, new Pawn(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('d', 2, new Pawn(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('e', 2, new Pawn(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('f', 2, new Pawn(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('g', 2, new Pawn(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('h', 2, new Pawn(tabuleiro, Cor.WHITE));

    }

    private void proximoTurno() {
        this.turno++;
        this.JogadorAtual = (JogadorAtual == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
    }

    private Cor oponente(Cor cor) {
        return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
    }

    private PecaXadrez localizandoRei(Cor cor) {
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : lista) {
            if (p instanceof King) {
                return (PecaXadrez) p;
            }
        }

        throw new IllegalStateException("---------- NÃO HÁ UM REI " + cor + " NO TABULEIRO -------------");

    }

    private boolean testandoCheck(Cor cor) {
        Posicao posicaoRei = localizandoRei(cor).getPecaXadrez().convertePosicao();
        List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecasDoOponente) {
            boolean[][] mat = p.possiveisMovimentos();
            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
                return true;
            }
        }

        return false;

    }

    private boolean testandoCheckMate(Cor cor) {
        if (!testandoCheck(cor)) {
            return false;
        }

        // PEGA TODAS AS PEÇAS DA COR PASSADA COM PARAMETRO
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());

        for (Peca p : lista) {
            boolean[][] mat = p.possiveisMovimentos();
            for (int x = 0; x < tabuleiro.getLinhas(); x++) {
                for (int y = 0; y < tabuleiro.getColunas(); y++) {
                    if (mat[x][y]) {
                        Posicao origem = ((PecaXadrez) p).getPecaXadrez().convertePosicao();
                        Posicao destino = new Posicao(x, y);
                        Peca pecaCapturada = executarMovimento(origem, destino);
                        boolean testCheck = testandoCheck(cor);
                        desFazerMovimento(origem, destino, pecaCapturada);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
