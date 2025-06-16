package application;

import tabuleiro.*;
import xadrez.*;

/**
 *
 * @author felip
 */
public class Main {

    public static void main(String[] args) {
        //Posicao p1 = new Posicao(3, 5);

       // System.out.println(p1.toString());

       // Tabuleiro t1 = new Tabuleiro(8, 8);

        PartidaXadrez px1 = new PartidaXadrez();

        UserInterface.printTabuleiro(px1.getPecas());
    }

}
