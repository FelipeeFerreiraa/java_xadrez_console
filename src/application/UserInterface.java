package application;

import xadrez.PecaXadrez;

/**
 *
 * @author felip
 */
public class UserInterface {

    //---------- VARIAVEIS
    //---------- CONSTRUTORES
    //---------- GETs
    //---------- SETs
    
    //---------- METODOS
    public static void printTabuleiro(PecaXadrez[][] pecas) {
        for (int x = 0; x < pecas.length; x++) {
            System.out.print((8 - x) + " ");
            for (int y = 0; y < pecas.length; y++) {
                printPeca(pecas[x][y]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPeca(PecaXadrez peca) {
        if (peca == null) {
            System.out.print("-");
        } else {
            System.out.print(peca);
        }
        System.out.print(" ");
    }
}
