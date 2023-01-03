package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/*
Nom             TicTacToe
Description     Contrôleur jeu TicTacToe (MVC)
@version        v1.0
Date            12 décembre 2022
@author         Stéphane CHEVRIER
*/

import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Console;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Input;
import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Damier;
import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Player;
import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.HumanPlayer;
import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.RandomPlayer;
import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Cell;
import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToe {

    /* initialisation de la taille du plateau */
    /* =n défini un plateau de n+1 * n+1 cellules pour le jeu TicTacToe */
    private int size = 2;

    /* initialisation des objets Class Viewer, TextesEcran, Damier et Input */
    private final Console console = new Console();
    private final TextesConsole textesConsole = new TextesConsole();
    private final Damier damier = new Damier(size);
    private final Input input = new Input();

    /* initialisation des joueurs */
    private final ArrayList<Player> joueur = new ArrayList<>(3);

    /* Constructeur de la Class TicTacToe */
    public TicTacToe() {
    }

    /*
    méthode de calcul : des sommes de chaque alignement,
                        des minimum et maximum de toutes ces sommes,
                        du nombre de coups joués
    @return ArrayList<Integer>(3) : minimum, maximum, nombre de coups joués
     */
    private ArrayList<Integer> situationCalcul() {

        // ArrayList de sortie
        ArrayList<Integer> retour = new ArrayList<>(3);

        // RAZ du tableau de calcul des sommes et du nombre de coups joués
        int nombreCoupsJoues = 0;
        int[] calcul = new int[(size+1)*3];

        // Copie locale du plateau
        Cell[][] plateau = damier.getPlateau();
        for (int i = 0; i <= calcul.length - 1; i++) {
            calcul[i] = 0;
        }
        // Double boucle de calcul des sommes (size lignes, size colonnes, 2 diagonales)
        for (int i = 0; i <= size; i++) {
            calcul[(size*2+2)+1] += plateau[i][i].getValue();      // somme de la diagonale 0.0+1.1+2.2+... dans index size*size + 1
            calcul[(size*2+2)+2] += plateau[i][size - i].getValue(); // somme de la diagonale 0.size+1.1+... dans index size*size + 2
            for (int j = 0; j <= size; j++) {
                calcul[j] += plateau[i][j].getValue();   // somme des lignes 0-1-2 dans index 0-1-2
                calcul[j + size + 1] += plateau[j][i].getValue(); // somme des colonnes 0-1-2 dans index 3-4-5
                // Calcul du nombre de coups joués
                if (plateau[i][j].getValue() != TextesConsole.caseValue[0]) {
                    nombreCoupsJoues++;
                }
            }
        }

        // calcul des maximum et minimum des 2 tableaux calculX et calculY qui contiennent la somme de chaque alignement
        retour.add(Arrays.stream(calcul).max().getAsInt());
        retour.add(Arrays.stream(calcul).min().getAsInt());

        // retour du nombre de coups joués
        retour.add(nombreCoupsJoues);

        // fin de la fonction
        return retour;
    }

    /*
    Fonction de calcul de fin de partie :   soit un joueur a gagné,
                                            soit il n'y a plus de coups à jouer
    @return boolean : true si la partie est finie, false sinon
     */
    private boolean isOver() {
        int alignementComplet = (size+1);

        // Par défaut la partie n'est pas finie
        boolean retour = false;

        // calcul des sommes de chaque alignement et du nombre de coups joués
        ArrayList<Integer> minmaxnbre = (ArrayList<Integer>) situationCalcul().clone();

        // boucle de test si le joueur i a gagné
        for (int i=0; i<2; i++) {
            if (minmaxnbre.get(i) == alignementComplet* TextesConsole.caseValue[i+1]) {
                console.afficherEcran(textesConsole.messagePartieTerminee + textesConsole.messageleJoueur + joueur.get(i + 1).name + textesConsole.messageAGagne, i + 1, true);
                retour = true;
            }
        }

        // Il n'y a plus de coups à jouer et il n'y a aucun vainqueur
        if ((minmaxnbre.get(2) == alignementComplet*alignementComplet) && (!retour)) {
            console.afficherEcran(textesConsole.messagePartieTerminee + textesConsole.messageEgalite, 0, true);
            retour = true;
        }
        return retour;
    }

    /*
    Fonction de renvoie des 2 joueurs définis
    @return ArrayList<String> : liste des noms des joueurs, le joueur n°0 est automatiquement un joueur vide
 */
    private ArrayList<String> definitionJoueurs () {

        // Initialisation des variables locales
        ArrayList<String> joueurs = new ArrayList<>(2);
        String saisie;

        // Joueur vide index 0
        joueurs.add("JoueurVide");

        // Boucle de saisie des 2 joueurs
        for (int i=1; i<=2; i++) {
            saisie = input.getString(textesConsole.messageSaisieNom1, i);
            joueurs.add(saisie);
        }
        // retour de la ArrayList des 3 joueurs (vide, joueur n°1, joueur n°2)
        return joueurs;
    }
    /*
    Méthode d'initialisation de chaque joueur avec la bonne classe
     */
    private void allocationPlayers(ArrayList<String> listeJoueurs) {

        // Boucle de création des 3 joueurs : index0:Joueur vide, index1:Joueur n°1, index2: joueur n°2
        for (int i=0; i<=2; i++) {
            switch (listeJoueurs.get(i).toLowerCase()) {
                case "random": // Joueur Aléatoire
                    joueur.add(new RandomPlayer("Random"+i, TextesConsole.caseValue[i], TextesConsole.representationJoueur[i], TextesConsole.caseCouleur[i],i ,size));
                    break;
                default: // Joueur Humain
                    joueur.add(new HumanPlayer(listeJoueurs.get(i), TextesConsole.caseValue[i], TextesConsole.representationJoueur[i], TextesConsole.caseCouleur[i],i ,size));
            }
        }
    }
    /*
    Fonction de saisie du coup répétée tant que la case n'est pas vide
    @return ArrayList<Integer> : Liste du coup y,x
     */
    private ArrayList<Integer> saisieCoup(Player activePlayer) {

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
                console.afficherEcran(textesConsole.messageCase+coup.get(0)+"-"+coup.get(1)+ textesConsole.messageCaseOccupee,0, true);
            }

        // fin boucle
        } while (resultat);

        // retour du coup
        return coup;
    }

    /*
    Méthode de jeu de la partie
     */
    public void play () {

        //initialisation de la variable locale coup
        ArrayList<Integer> coup; // = new ArrayList<Integer>(2);

        // Effacement écran
        console.displayEffacer();

        // Définition et allocation des joueurs
        ArrayList<String> joueurs = definitionJoueurs();
        allocationPlayers(joueurs);

        // définition aléatoire du 1er joueur à jouer
        Player activePlayer = joueur.get((int)Math.round((Math.random()+1)));

        // boucle d'enchainement des coups
        do {

            //Afichage du plateau
            console.display(damier.getPlateau());

            // saisie du coup tant que la case choisie n'est pas vide
            coup = saisieCoup(activePlayer);

            // affichage du coup
            console.afficherEcran(textesConsole.messageCoupJoue1 + coup.get(0) + "-" + coup.get(1) + textesConsole.messageCoupJoue2 + activePlayer.name, activePlayer.indexCouleur, true);

            // créé le coup du joueur actif
            damier.setOwner(activePlayer,coup);

            // Permute alternativement les joueurs
            activePlayer = (activePlayer.name.equals(joueur.get(2).name)) ? joueur.get(1) : joueur.get(2);

        // répétition de la boucle tant que la partie n'est pas finie
        } while (!isOver());

        // Affichage du damier final
        console.display(damier.getPlateau());
    }

}
