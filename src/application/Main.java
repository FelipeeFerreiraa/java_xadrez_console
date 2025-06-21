package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import tabuleiro.*;
import xadrez.*;

/**
 *
 * @author felip
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaXadrez px1 = new PartidaXadrez();

        while (true) {
            try {
                UserInterface.limparTela();
                UserInterface.printTabuleiro(px1.getPecas());
                System.out.println();
                System.out.print("Origem: ");
                XadrezPosicao origem = UserInterface.lerPosicaoXadrez(sc);

                System.out.println();
                System.out.println("Destino: ");
                XadrezPosicao destino = UserInterface.lerPosicaoXadrez(sc);

                PecaXadrez pecaCapturada = px1.realizandoMovimentoXadrez(origem, destino);

            } catch (XadrezException e) {
                System.out.println(e.getMessage());
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }

    }

}
