package fr.le_campus_numerique.stephanechevrier.tictactoe.viewer;

/**
 * Nom:            Input
 * Description :   Class de saisie du jeu TicTacToe (MVC)
 * @version v1.0
 * Date :          24 décembre 2022
 * @author Stéphane CHEVRIER
 */

import java.util.Scanner;

public class Input {

    /**
     * initialisation Objet Console
     */
    private final Console console;

    /**
     * constructeur Class Input
     */
    public Input() {
        this.console = new Console();
    }
    /**
     * Fonction de saisie d'une string avec un message de saisie
     * @param message
     * @param index
     * @return String : saisie du clavier
     */
    public String getString(String message, int index) {

        // initialisation objet Scanner
        Scanner clavier = new Scanner(System.in);

        // affiche le message
        console.afficherEcran(message, index, false);

        // retour de la chaine saisie
        return clavier.nextLine();
    }
}
