package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.*;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Console;

import java.util.ArrayList;

/**
 * Nom             GameControleur
 * Description     Contrôleur jeu TicTacToe (MVC)
 *                 Interface des différents jeux
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */

public abstract class GameControleur {

    /**
     * initialisation de la taille du plateau
     */
    public int size;
    public int nbreAlignements;
    public int alignementGagnant;

    /**
     * initialisation des objets
     */
    public TextesConsole textesConsole;
    public Console console;
    public Damier damier;
    public Persistence persistence;
    public GameJoueurs gameJoueurs;

    /*
    private Model model => TextesConsole & Damier & GameJoueurs
    private View view => Console
     */

    /**
     * Constructeur de la Class TicTacToe
     */
    public GameControleur() {
        this.persistence = new GameSerialization();
    }

    /**
     *     Fonction de calcul de fin de partie :   soit un joueur a gagné,
     *                                             soit il n'y a plus de coups à jouer
     * @return boolean : true si la partie est finie, false sinon
     */
    public boolean isOver(GameControleur jeu) {

        // initialisation des séries gagnantes
        String[] alignementComplet = new String[2];

        // récupération des sommes de chaque alignement
        String[] calcul = jeu.calculerAlignements();

        // Par défaut la partie n'est pas finie
        boolean retour = false;

        // boucle de test si le joueur i a gagné
        for (int i=0; i<gameJoueurs.nombreJoueurs; i++) {
            alignementComplet[i]=TextesConsole.REPRESENTATION_JOUEUR[i+1].repeat(alignementGagnant);

            for (String j : calcul) {
                if (j.indexOf(alignementComplet[i])!=-1) {
                    console.afficherEcran(textesConsole.MESSAGE_PARTIE_TERMINEE + textesConsole.MESSAGE_LE_JOUEUR + gameJoueurs.joueur.get(i + 1).name + textesConsole.MESSAGE_A_GAGNE, i + 1, TextesConsole.SAUT);
                    retour = true;
                }
            }
        }

        // récupération du nombre de coups joués dans le dernier éléments du tableau de String
        int nombreCoupsJoues = Integer.parseInt(calcul[calcul.length-1]);

        // Il n'y a plus de coups à jouer et il n'y a aucun vainqueur
        if ((nombreCoupsJoues==(size +1)*(size +1)) && (!retour)) {
            console.afficherEcran(textesConsole.MESSAGE_PARTIE_TERMINEE + textesConsole.MESSAGE_EGALITE, TextesConsole.NO_INDEX, TextesConsole.SAUT);
            retour = true;
        }
        return retour;
    }

    /**
     * Méthode de jeu de la partie
     */
    public void play (GameControleur jeu, boolean nouvellePartie) {

        //initialisation des variables locales
        ArrayList<Integer> coup;
        Player activePlayer = null;

        // Saut ligne
        console.sautLigne();

        // S'il s'agit d'une nouvelle partie
        if (nouvellePartie) {

            // Définition et allocation des joueurs
            ArrayList<String> joueurs = gameJoueurs.definitionJoueurs();
            gameJoueurs.allocationPlayers(joueurs, size);

            // définition aléatoire du 1er joueur à jouer
            activePlayer = gameJoueurs.joueur.get((int) Math.round((Math.random() + 1)));

            //Afichage du plateau
            console.display(damier.getPlateau());

        } else {

            // récupération de la dernière partie sauvegardée
        }

        // boucle d'enchainement des coups
        while (!isOver(jeu)) {

            // saisie du coup tant que la case choisie n'est pas vide
            coup = gameJoueurs.saisieCoup(activePlayer, size);

            // affichage du coup
            console.afficherEcran(textesConsole.MESSAGE_COUP_JOUE1 + coup.get(0) + "-" + coup.get(1) + textesConsole.MESSAGE_COUP_JOUE2 + activePlayer.name, activePlayer.indexCouleur, TextesConsole.SAUT);

            // créé le coup du joueur actif
            damier.setOwner(activePlayer,coup);

            // sauvegarde l'état du jeu
            persistence.sauvegarder(jeu,activePlayer);

            //Afichage du plateau
            console.display(damier.getPlateau());

            // Permute alternativement les joueurs
            activePlayer = (activePlayer.name.equals(gameJoueurs.joueur.get(2).name)) ? gameJoueurs.joueur.get(1) : gameJoueurs.joueur.get(2);

            // répétition de la boucle tant que la partie n'est pas finie
        }

    }

    /**
     *     méthode de calcul : des sommes de chaque alignement,
     *                         du nombre de coups joués
     * @return ArrayList<Integer>(3) : Somme de chaque alignement, nombre de coups joués
     */
    public abstract String[] calculerAlignements();

}
