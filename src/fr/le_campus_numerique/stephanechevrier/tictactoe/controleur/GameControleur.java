package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/*
Nom             GameControleur
Description     Contrôleur jeu TicTacToe (MVC)
                Interface des différents jeux
@version        v1.0
Date            12 décembre 2022
@author         Stéphane CHEVRIER
*/

import java.util.ArrayList;

public interface GameControleur {

    public String[] situationCalcul();

    public boolean isOver();

    public void play ();


}
