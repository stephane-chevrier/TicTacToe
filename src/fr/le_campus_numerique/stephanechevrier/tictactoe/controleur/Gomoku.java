package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/**
 * Nom             Gomoku
 * Description     Contrôleur jeu TicTacToe (MVC)
 * @version v1.0
 * Date            4 janvier 2023
 * @author Stéphane CHEVRIER
 */

import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.*;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.*;

import java.util.ArrayList;

public class Gomoku implements GameControleur {

    /**
     * initialisation de la taille du plateau
     */
    public final int SIZE;
    public int nbreAlignements;
    public int alignementGagnant;

    /**
     * initialisation des objets textesConsole, Console, Damier, GameJoueurs
     */
    public TextesConsole textesConsole;
    public Console console;
    public Damier damier;
    public GameJoueurs gameJoueurs;

    /**
     * Constructeur de la Class TicTacToe
     */
    public Gomoku() {
        this.SIZE = 14;      // =n défini un plateau de n+1 * n+1 cellules pour le jeu TicTacToe
                             // ATTENTION n doit être supérieur ou égal à 4
        this.nbreAlignements = (SIZE +1)*6;  // on aura besoin de 6 blocs : 1 de lignes, 1 de colonnes, 4 de diagonales
        this.alignementGagnant = 5;
        this.textesConsole = new TextesConsole();
        this.console = new Console();
        this.damier = new Damier(this.SIZE);                 // this.size permet de spécifier size de l'objet TicTacToe
        this.gameJoueurs = new GameJoueurs(this.damier);     // Après avoir instancié le damier, sinon Pb !
    }

    /**
     * méthode de calcul : des sommes de chaque alignement,
     *                     des minimum et maximum de toutes ces sommes,
     *                     du nombre de coups joués
     * @return ArrayList<Integer>(3) : minimum, maximum, nombre de coups joués
     */
    @Override
    public String[] situationCalcul() {

        // RAZ du tableau de calcul des sommes et du nombre de coups joués
        int nombreCoupsJoues = 0;
        String[] calcul = new String[nbreAlignements];
        int nombreDiag = (SIZE +1-alignementGagnant);

        // Copie locale du plateau
        Cell[][] plateau = damier.getPlateau();
        for (int i = 0; i <= calcul.length - 1; i++) {
            calcul[i] = "";
        }

        // Double boucle de calcul des sommes (size lignes, size colonnes, 18 diagonales)
        for (int i = 0; i <= SIZE; i++) {

            // boucle de calcul des diagonales
            for (int j = -nombreDiag; j <= +nombreDiag; j++) {

                // somme de la diagonale 0.0+1.1+...+size.size dans index size*2-size*2+1-....-size*2+size
                if ((i + j >= 0) && (i + j <= SIZE)) {
                    calcul[(SIZE + 1) * 2 + nombreDiag + j] += plateau[i + j][i].getRepresentationBrut();
                }
                // somme de la diagonale 0.size-1.size-1-....-size.0 dans index size*3-size*3+1-....-size*3+size
                if (((i + j) >= 0) && (i + j <= SIZE)) {
                    calcul[(SIZE + 1) * 4 + nombreDiag + j] += plateau[i + j][SIZE - i].getRepresentationBrut();
                }
            }
            // boucle de calcul des lignes et des colonnes
            for (int j = 0; j <= SIZE; j++) {

                // somme des lignes 0-1-....-size dans index 0-1-.....-size
                calcul[j] += plateau[i][j].getRepresentationBrut();

                // somme des colonnes 0-1-....-size dans index size+1-size+2-.....-size+size+1
                calcul[(SIZE +1)+1+j] += plateau[j][i].getRepresentationBrut();

                // Calcul du nombre de coups joués
                if (!plateau[i][j].getRepresentationBrut().equals(" ")) {
                    nombreCoupsJoues++;
                }
            }
        }

        // retour du nombre de coups joués
        calcul[nbreAlignements-1] = calcul[(SIZE +1)*3-1].valueOf(nombreCoupsJoues);

        // fin de la fonction
        return calcul;
    }

    /**
     *  Fonction de calcul de fin de partie :   soit un joueur a gagné,
     *                                          soit il n'y a plus de coups à jouer
     * @return boolean : true si la partie est finie, false sinon
     */
    @Override
    public boolean isOver() {

        // initialisation des séries gagnantes
        String[] alignementComplet = new String[2];

        // récupération des sommes de chaque alignement
        String[] calcul = situationCalcul();

        // Par défaut la partie n'est pas finie
        boolean retour = false;

        // boucle de test si le joueur i a gagné
        for (int i=0; i<gameJoueurs.nombreJoueurs; i++) {
            alignementComplet[i]=TextesConsole.REPRESENTATION_JOUEUR[i+1].repeat(alignementGagnant);

            for (String j : calcul) {
                if (j.indexOf(alignementComplet[i])!=-1) {
                    console.afficherEcran(textesConsole.MESSAGE_PARTIE_TERMINEE + textesConsole.MESSAGE_LE_JOUEUR + gameJoueurs.joueur.get(i + 1).name + textesConsole.MESSAGE_A_GAGNE, i + 1, true);
                    retour = true;
                }
            }

        }

        // récupération du nombre de coups joués dans le dernier éléments du tableau de String
        int nombreCoupsJoues = Integer.parseInt(calcul[calcul.length-1]);

        // Il n'y a plus de coups à jouer et il n'y a aucun vainqueur
        if ((nombreCoupsJoues==(SIZE +1)*(SIZE +1)) && (!retour)) {
            console.afficherEcran(textesConsole.MESSAGE_PARTIE_TERMINEE + textesConsole.MESSAGE_EGALITE, TextesConsole.noIndex, TextesConsole.saut);
            retour = true;
        }
        return retour;
    }

    /**
     * Méthode de jeu de la partie
     */
    @Override
    public void play () {

        //initialisation de la variable locale coup
        ArrayList<Integer> coup;

        // Effacement écran
//        console.displayEffacer();

        // Définition et allocation des joueurs
        ArrayList<String> joueurs = gameJoueurs.definitionJoueurs();
        gameJoueurs.allocationPlayers(joueurs, SIZE);

        // définition aléatoire du 1er joueur à jouer
        Player activePlayer = gameJoueurs.joueur.get((int)Math.round((Math.random()+1)));

        // boucle d'enchainement des coups
        do {

            //Afichage du plateau
            console.display(damier.getPlateau());

            // saisie du coup tant que la case choisie n'est pas vide
            coup = gameJoueurs.saisieCoup(activePlayer, SIZE);

            // affichage du coup
            console.afficherEcran(textesConsole.MESSAGE_COUP_JOUE1 + coup.get(0) + "-" + coup.get(1) + textesConsole.MESSAGE_COUP_JOUE2 + activePlayer.name, activePlayer.indexCouleur, TextesConsole.saut);

            // créé le coup du joueur actif
            damier.setOwner(activePlayer,coup);

            // Permute alternativement les joueurs
            activePlayer = (activePlayer.name.equals(gameJoueurs.joueur.get(2).name)) ? gameJoueurs.joueur.get(1) : gameJoueurs.joueur.get(2);

            // répétition de la boucle tant que la partie n'est pas finie
        } while (!isOver());

        // Affichage du damier final
        console.display(damier.getPlateau());
    }

}
