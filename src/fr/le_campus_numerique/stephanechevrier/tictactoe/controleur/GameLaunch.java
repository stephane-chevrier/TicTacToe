package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Console;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Input;

/**
 * Nom             GameLaunch
 * Description     Contrôleur jeu TicTacToe (MVC)
 *                 Sélection et lancement du jeu choisi
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */

public class GameLaunch {

    /**
     * définition des jeux possibles
     */
    private enum gameChoice {
        SAUVEGARDE,
        TICTACTOE,
        GOMOKU,
        PUISSANCE4,
        QUIT
    }

    /**
     * initialisation des variables locales
     */
    private static final boolean partieNouvelle = true;
    private static final boolean partieSauvegardee = false;

    /**
     * initialisation des objets
     */
    Console console;
    TextesConsole textesConsole;
    Input input;
    Persistence persistence;


    /**
     * Constructeur de la Class GameLaunch
     */
    public GameLaunch() {
        this.console = new Console();
        this.textesConsole = new TextesConsole();
        this.input = new Input();
        this.persistence = new GameSerialization();
    }

    /**
     * Méthode de lancement de la plateforme et de sélection du jeu
     */
    public void gameLaunch() {

        // initialisation des variables locales
        String saisie;
        GameControleur jeu = null;
        boolean nouvellePartie = partieNouvelle;

        // Initialisation de l'affichage
        console.displayEffacer();
        console.afficherEcran(textesConsole.MESSAGE_BIENVENUE, TextesConsole.NO_INDEX, TextesConsole.SAUT);

        // Boucle tant que Quit n'est pas saisi
        do {

            // Affichage des jeux possible
            console.sautLigne();
            console.afficherEcran(textesConsole.MESSAGE_LISTE_DES_JEUX, TextesConsole.NO_INDEX, TextesConsole.SAUT);
            for (gameChoice g : gameChoice.values()) {
                console.afficherEcran(g.toString(), TextesConsole.NO_INDEX, TextesConsole.SAUT);
            }

            // Saisie du nom du jeu
            saisie = input.getString(textesConsole.MESSAGE_FAITES_VOTRE_CHOIX, TextesConsole.NO_INDEX);

            // Traitement exception si saisie n'est pas dans gameChoice
            try {

                // Selection du jeu
                switch (gameChoice.valueOf(saisie.toUpperCase())) {

                    // partie sauvegardée
                    case SAUVEGARDE -> {
                        nouvellePartie = partieSauvegardee;
                        jeu = (GameControleur) persistence.restaurer(GameSerialization.fichierSauvegardeGameControleur);
                    }

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
                        console.afficherEcran(textesConsole.MESSAGE_PUISSANCE4, TextesConsole.NO_INDEX, TextesConsole.SAUT);
                        jeu = null;
                    }
                    // Plateforme quittée
                    case QUIT -> {
                        console.afficherEcran(textesConsole.MESSAGE_FIN, TextesConsole.NO_INDEX, TextesConsole.SAUT);
                        jeu = null;
                    }
                }
                // Traitement exception saisie non valide
            } catch (Exception e) {
                console.afficherEcran(textesConsole.MESSAGE_SAISIE_INVALIDE, TextesConsole.NO_INDEX, TextesConsole.SAUT);
                jeu = null;
            }

            // lancement du jeu sélectionné
            if (jeu != null) {
                jeu.play(jeu,nouvellePartie);
                jeu = null;
                nouvellePartie = partieNouvelle;
            }
        }
        // Fin boucle de sélection du jeu ou QUIT
        while (!(gameChoice.QUIT.toString().equalsIgnoreCase(saisie))) ;
    }

}
