package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tabuleiro.*;
import xadrez.pecas.Bishop;
import xadrez.pecas.King;
import xadrez.pecas.Knight;
import xadrez.pecas.Pawn;
import xadrez.pecas.Queen;
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
    private PecaXadrez enPassantVulneravel;

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

    public PecaXadrez getEnPassantVulneravel() {
        return enPassantVulneravel;
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

        PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.peca(destino);

        check = (testandoCheck(oponente(JogadorAtual))) ? true : false;

        if (testandoCheckMate(oponente(JogadorAtual))) {
            checkMate = true;
        } else {
            proximoTurno();
        }

        ///MOVIMENTO ESPECIAL ENPASSANT
        if (pecaMovida instanceof Pawn && (tabuleiro.getLinhas() == origem.getLinha() - 2) || (destino.getLinha() == origem.getLinha() - 2)) {
            enPassantVulneravel = pecaMovida;
        } else {
            enPassantVulneravel = null;
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

        //### MOVIMENTO DE ROQUE NA ALA DO REI
        if (p instanceof King && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
            tabuleiro.colocarPeca(torre, destinoTorre);
            torre.aumentandoContMovimentos();
        }

        //### MOVIMENTO DE ROQUE NA ALA DA DAMA
        if (p instanceof King && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(origemTorre);
            tabuleiro.colocarPeca(torre, destinoTorre);
            torre.aumentandoContMovimentos();
        }

        //MOVIMENTO ESPECIAL ENPASSANT
        if (p instanceof Pawn) {

            if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {

                Posicao posicaoPeao;
                if (p.getCor() == Cor.WHITE) {
                    posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());

                } else {
                    posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }

                pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
                pecasCapturadas.add(pecaCapturada);
                pecasNoTabuleiro.remove(pecaCapturada);
            }
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

        //### MOVIMENTO DE ROQUE NA ALA DO REI
        if (p instanceof King && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
            tabuleiro.colocarPeca(torre, origemTorre);
            torre.diminuindoContMovimentos();
        }

        //### MOVIMENTO DE ROQUE NA ALA DA DAMA
        if (p instanceof King && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removerPeca(destinoTorre);
            tabuleiro.colocarPeca(torre, origemTorre);
            torre.diminuindoContMovimentos();
        }

        //MOVIMENTO ESPECIAL ENPASSANT
        if (p instanceof Pawn) {

            if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulneravel) {

                PecaXadrez peao = (PecaXadrez) tabuleiro.removerPeca(destino);
                Posicao posicaoPeao;
                if (p.getCor() == Cor.WHITE) {
                    posicaoPeao = new Posicao(3, destino.getColuna());

                } else {
                    posicaoPeao = new Posicao(4, destino.getColuna());
                }

                tabuleiro.colocarPeca(peao, posicaoPeao);

            }
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
        colocandoNovaPeca('b', 8, new Knight(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('c', 8, new Bishop(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('d', 8, new Queen(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('e', 8, new King(tabuleiro, Cor.BLACK, this));
        colocandoNovaPeca('f', 8, new Bishop(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('g', 8, new Knight(tabuleiro, Cor.BLACK));
        colocandoNovaPeca('h', 8, new Rook(tabuleiro, Cor.BLACK));

        colocandoNovaPeca('a', 7, new Pawn(tabuleiro, Cor.BLACK, this));
        colocandoNovaPeca('b', 7, new Pawn(tabuleiro, Cor.BLACK, this));
        colocandoNovaPeca('c', 7, new Pawn(tabuleiro, Cor.BLACK, this));
        colocandoNovaPeca('d', 7, new Pawn(tabuleiro, Cor.BLACK, this));
        colocandoNovaPeca('e', 7, new Pawn(tabuleiro, Cor.BLACK, this));
        colocandoNovaPeca('f', 7, new Pawn(tabuleiro, Cor.BLACK, this));
        colocandoNovaPeca('g', 7, new Pawn(tabuleiro, Cor.BLACK, this));
        colocandoNovaPeca('h', 7, new Pawn(tabuleiro, Cor.BLACK, this));

        colocandoNovaPeca('a', 1, new Rook(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('b', 1, new Knight(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('c', 1, new Bishop(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('d', 1, new Queen(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('e', 1, new King(tabuleiro, Cor.WHITE, this));
        colocandoNovaPeca('f', 1, new Bishop(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('g', 1, new Knight(tabuleiro, Cor.WHITE));
        colocandoNovaPeca('h', 1, new Rook(tabuleiro, Cor.WHITE));

        colocandoNovaPeca('a', 2, new Pawn(tabuleiro, Cor.WHITE, this));
        colocandoNovaPeca('b', 2, new Pawn(tabuleiro, Cor.WHITE, this));
        colocandoNovaPeca('c', 2, new Pawn(tabuleiro, Cor.WHITE, this));
        colocandoNovaPeca('d', 2, new Pawn(tabuleiro, Cor.WHITE, this));
        colocandoNovaPeca('e', 2, new Pawn(tabuleiro, Cor.WHITE, this));
        colocandoNovaPeca('f', 2, new Pawn(tabuleiro, Cor.WHITE, this));
        colocandoNovaPeca('g', 2, new Pawn(tabuleiro, Cor.WHITE, this));
        colocandoNovaPeca('h', 2, new Pawn(tabuleiro, Cor.WHITE, this));

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
