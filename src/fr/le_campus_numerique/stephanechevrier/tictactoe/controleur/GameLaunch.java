package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/*
Nom             GameLaunch
Description     Contrôleur jeu TicTacToe (MVC)
                Sélection et lancement du jeu choisi
@version        v1.0
Date            12 décembre 2022
@author         Stéphane CHEVRIER
*/

import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.*;

public class GameLaunch {

    // initialisation

    // définition des jeux possibles
    enum gameChoice {
        TICTACTOE,
        GOMOKU,
        PUISSANCE4,
        QUIT
    }

    // initialisation des objets
    Console console;
    TextesConsole textesConsole;
    Input input;

    /* Constructeur de la Class GameLaunch */
    public GameLaunch() {
        this.console = new Console();
        this.textesConsole = new TextesConsole();
        this.input = new Input();
    }

    /*
    Méthode de lancement de la plateforme et de sélection du jeu
     */
    public void gameLaunch() {

        // initialisation des variables locales
        String saisie;
        GameControleur jeu = null;

        // Initialisation de l'affichage
        console.displayEffacer();
        console.afficherEcran(textesConsole.messageBienvenue, 0, false);

        // Boucle tant que Quit n'est pas saisie
        do {

            // Affichage des jeux possible
            console.afficherEcran(textesConsole.messageListeDesJeux, 0, true);
            for (gameChoice g : gameChoice.values()) {
                console.afficherEcran(g.toString(), 0, true);
            }

            // Saisie du nom du jeu
            saisie = input.getString(textesConsole.messageFaitesVotreChoix, 0);

            // Traitement exception si saisie n'est pas dans gameChoice
            try {

                // Selection du jeu
                switch (gameChoice.valueOf(saisie.toUpperCase())) {

                    // Jeu TicTacToe
                    case TICTACTOE -> {
                        jeu = new TicTacToe();
                    }
                    // Jeu TicTacToe2
                    case GOMOKU -> {
                        jeu = new Gomoku();
                    }
                    // Jeu Puissance4
                    case PUISSANCE4 -> {
                    }
                    // Plateforme quittée
                    case QUIT -> {
                        console.afficherEcran(textesConsole.messageFin, 0, true);
                        jeu = null;
                    }
                }
                // Traitement exception
            } catch (Exception e) {
                console.afficherEcran(textesConsole.messageSaisieInvalide, 0, true);
            }

            // lancement du jeu sélectionné
            if (jeu != null) {
                jeu.play();
            }
        }
        // Fin boucle de sélection du jeu ou QUIT
        while (!(gameChoice.QUIT.toString().equalsIgnoreCase(saisie))) ;
    }
}
