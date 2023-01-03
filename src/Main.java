/*
Nom             Main
Description     Classe principale jeu TicTacToe (MVC)
@version        v1.0
Date            12 décembre 2022
@author         Stéphane CHEVRIER
*/

import fr.le_campus_numerique.stephanechevrier.tictactoe.controleur.*;

public class Main {

    /* méthode principale */
    public static void main(String[] args) {

        // Initialisation de l'objet TicTacToe
        GameControleur ticTacToe = new TicTacToe2();

        // lancement du jeu TicTacToe
        ticTacToe.play();
    }
}
