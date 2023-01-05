package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/**
 * Nom             GameJoueurs
 * Description     Contrôleur jeu TicTacToe (MVC)
 *                 Méthodes communes à tous les jeux ; Controle des joueurs
 * @version v1.0
 * Date            3 janvier 2023
 * @author Stéphane CHEVRIER
 */

import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.*;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.*;

import java.util.ArrayList;

public class GameJoueurs {

    /**
     * initialisation du nombre de joueurs
     */
    public int nombreJoueurs;

    /**
     * initialisation des objets Input, Console, Damier, texteConsole
     */
    public Input input;
    public Console console;
    public Damier damier;
    public TextesConsole textesConsole;

    /**
     * initialisation des joueurs
     */
    public final ArrayList<Player> joueur = new ArrayList<>(nombreJoueurs);

    /**
     * Constructeur de la Class GameJoueurs
     * @param damier
     */
    public GameJoueurs(Damier damier) {
        this.nombreJoueurs=2;      // 3 joueurs : 1 joueur vide (index0) + 2 joueurs
        this.input = new Input();
        this.console = new Console();
        this.textesConsole = new TextesConsole();
        this.damier = damier;
    }

    /**
     * Fonction de renvoie des 2 joueurs définis
     * @return ArrayList<String> : liste des noms des joueurs, le joueur n°0 est automatiquement un joueur vide
     */
    protected ArrayList<String> definitionJoueurs() {

        // Initialisation des variables locales
        ArrayList<String> joueurs = new ArrayList<>(nombreJoueurs+1);
        String saisie;

        // Joueur vide index 0
        joueurs.add("JoueurVide");

        // Boucle de saisie des 2 joueurs
        for (int i=1; i<=2; i++) {
            saisie = input.getString(textesConsole.MESSAGE_SAISIE_NOM1, i);
            joueurs.add(saisie);
        }
        // retour de la ArrayList des 3 joueurs (vide, joueur n°1, joueur n°2)
        return joueurs;
    }

    /**
     * Méthode d'initialisation de chaque joueur avec la bonne classe
     * @param listeJoueurs
     * @param size
     */
    protected void allocationPlayers(ArrayList<String> listeJoueurs, int size) {

        // Boucle de création des 3 joueurs : index0:Joueur vide, index1:Joueur n°1, index2: joueur n°2
        for (int i=0; i<=nombreJoueurs; i++) {
            switch (listeJoueurs.get(i).toLowerCase()) {
                case "random": // Joueur Aléatoire
                    joueur.add(new RandomPlayer("Random"+i, TextesConsole.CASE_VALUE[i], TextesConsole.REPRESENTATION_JOUEUR[i], TextesConsole.CASE_COULEUR[i],i ,size));
                    break;
                default: // Joueur Humain
                    joueur.add(new HumanPlayer(listeJoueurs.get(i), TextesConsole.CASE_VALUE[i], TextesConsole.REPRESENTATION_JOUEUR[i], TextesConsole.CASE_COULEUR[i],i ,size));
            }
        }
    }

    /**
     * Fonction de saisie du coup répétée tant que la case n'est pas vide
     * @param activePlayer
     * @param size
     * @return ArrayList<Integer> : Liste du coup y,x
     */
    protected ArrayList<Integer> saisieCoup(Player activePlayer, int size) {

        // initialisation variables locales
        ArrayList<Integer> coup;
        boolean resultat;

        // Boucle tant que le coup n'est pas sur une case vide
        do {

            // récupération du coup
            coup = activePlayer.getMoveFromPlayer(size, activePlayer.indexCouleur, console, textesConsole, input);

            // si resultat = true alors la case est occupée, et affiche un message si le joueur n'est pas random
            resultat = !damier.verifCaseLibre(coup);
            if (resultat && !activePlayer.name.toLowerCase().startsWith("random")) {
                console.afficherEcran(textesConsole.MESSAGE_CASE +coup.get(0)+"-"+coup.get(1)+ textesConsole.MESSAGE_CASE_OCCUPEE,0, true);
            }

            // fin boucle
        } while (resultat);

        // retour du coup
        return coup;
    }
}
