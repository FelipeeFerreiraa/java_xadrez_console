package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import tabuleiro.*;
import xadrez.*;

/**
 *
 * @author felip
 */
public class Main {

    public static void main(String[] args) {

        //---------- VARIAVEIS
        Scanner sc = new Scanner(System.in);
        PartidaXadrez px1 = new PartidaXadrez();
        List<PecaXadrez> capturadas = new ArrayList<>();

        while (!px1.getCheckMate()) {
            try {
                UserInterface.limparTela();
                UserInterface.printPartida(px1, capturadas);
                System.out.println();
                System.out.print("Origem: ");
                XadrezPosicao origem = UserInterface.lerPosicaoXadrez(sc);

                boolean[][] possiveisMovimentos = px1.possiveisMovimentos(origem);
                UserInterface.limparTela();
                UserInterface.printTabuleiro(px1.getPecas(), possiveisMovimentos);

                System.out.println();
                System.out.println("Destino: ");
                XadrezPosicao destino = UserInterface.lerPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = px1.realizandoMovimentoXadrez(origem, destino);

                if (pecaCapturada != null) {
                    capturadas.add(pecaCapturada);
                }

                if (px1.getPromover() != null) {
                    System.out.print("=====---- FORNEÇA UMA PEÇA PARA PROMOÇÃO (R,H,B,Q) ----======");
                    String tipo = sc.nextLine();
                    px1.colocandoPecaPromovida(tipo);
                }

            } catch (XadrezException e) {
                System.out.println(e.getMessage());
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }

            UserInterface.limparTela();
            UserInterface.printPartida(px1, capturadas);

        }

    }

}
