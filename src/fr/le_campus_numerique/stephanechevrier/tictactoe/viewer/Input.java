package fr.le_campus_numerique.stephanechevrier.tictactoe.viewer;

/*
Nom:            Input
Description :   Class de saisie du jeu TicTacToe (MVC)
@version        v1.0
Date :          24 décembre 2022
@author         Stéphane CHEVRIER
 */

import java.util.Scanner;

public class Input {

    /* constructeur Class Input */
    public Input() {
    }

    /* initialisation Objet Viewer */
    private Viewer viewer = new Viewer();

    /*
    Fonction de saisie d'une string avec un message de saisie
     */
    public String getString(String message, int index) {

        // initialisation objet Scanner
        Scanner clavier = new Scanner(System.in);

        // affiche le message
        viewer.afficherEcran(message, index, false);

        // saisie de la chaine
        String saisie = clavier.nextLine();

        // retour de la chaine saisie
        return saisie;
    }
}
