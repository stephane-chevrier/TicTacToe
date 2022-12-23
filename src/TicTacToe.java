/*
Nom:            TicTacToe
Description :   Contrôleur jeu TicTacToe (MVC)
@ersion         v1.0
Date :          12 décembre 2022
@author         Stéphane CHEVRIER
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    /* initialisation de la taille du plateau */
/* =n défini un plate
au de n+1 * n+1 cellules pour le jeu TicTacToe */
    private int size = 2;

    /* initialisation des objets Class Viewer et Class Damier */
    private Viewer viewer = new Viewer();

    private modele_Damier damier = new modele_Damier(size);

    /* initialisation des joueurs */
    private ArrayList<modele_Player> joueur = new ArrayList<>(2);

    /*
    Constructeur de la Class TicTacToe
     */
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
        modele_Cell[][] plateau = damier.getPlateau();
        for (int i = 0; i <= calcul.length - 1; i++) {
            calcul[i] = 0;
        }
        // Double boucle de calcul des 8 sommes (3 lignes, 3 colonnes, 2 diagonales)
        for (int i = 0; i <= size; i++) {
            calcul[7] += plateau[i][i].getValue();      // somme de la diagonale 0.0+1.1+2.2 dans index 7
            calcul[8] += plateau[i][size - i].getValue(); // somme de la diagonale 0.2+1.1+2.0 dans index 8
            for (int j = 0; j <= size; j++) {
                calcul[j] += plateau[i][j].getValue();   // somme des lignes 0-1-2 dans index 0-1-2
                calcul[j + 3] += plateau[j][i].getValue(); // somme des colonnes 0-1-2 dans index 3-4-5
                // Calcul du nombre de coups joués
                if (plateau[i][j].getValue() != modele_Player.caseValue[0]) {
                    nombreCoupsJoues++;
                }
            }
        }
        // calcul des maximum et minimum des 2 tableaux calculX et calculY qui contiennent la somme de chaque alignement
        retour.add(Arrays.stream(calcul).min().getAsInt());
        retour.add(Arrays.stream(calcul).max().getAsInt());
        // retour du nombre de coups joués
        retour.add(nombreCoupsJoues);
        // fin de la fonction
        return retour;
    }

    /*
    Fonction de calcul de fin de partie :    soit un joueur a gagné,
                                            soit il n'y a plus de coups à jouer
    @return boolean : true si la partie est finie, false sinon
     */
    private boolean isOver() {
        int alignementComplet = (size+1);
        // Par défaut la partie n'est pas finie
        Boolean retour = false;
        // calcul des sommes de chaque alignement et du nombre de coups joués
        ArrayList<Integer> minmaxnbre = (ArrayList<Integer>) situationCalcul().clone();
        // Le joueur2 a gagné
        if (minmaxnbre.get(0) == alignementComplet* modele_Player.caseValue[2]) {
            System.out.println("partie terminée, le joueur " + joueur.get(2).couleur+joueur.get(2).name+ modele_Player.caseCouleur[0] + " a gagné !!!");
            retour = true;
        }
        // Le joueur1 a gagné
        if (minmaxnbre.get(1) == alignementComplet* modele_Player.caseValue[1]) {
            System.out.println("partie terminée, le joueur "+joueur.get(1).couleur+joueur.get(1).name+ modele_Player.caseCouleur[0] + " a gagné !!!");
            retour = true;
        }
        // Il n'y a plus de coups à jouer et il n'y a aucun vainqueur
        if ((minmaxnbre.get(2) == alignementComplet*alignementComplet) && (!retour)) {
            System.out.println("partie terminée, égalité");
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
        Scanner clavier = new Scanner(System.in);

        // Joueur vide index 0
        joueurs.add("JoueurVide");

        // Boucle de saisie des 2 joueurs
        for (int i=1; i<=2; i++) {
            viewer.afficherEcran(viewer.messageSaisieNom1,i, false);
            joueurs.add(clavier.nextLine());
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
                    joueur.add(new ArtificialPlayer("Random"+i, modele_Player.caseValue[i], modele_Player.representationJoueur[i], modele_Player.caseCouleur[i]));
                    break;
                default: // Joueur Humain
                    joueur.add(new HumanPlayer(listeJoueurs.get(i), modele_Player.caseValue[i], modele_Player.representationJoueur[i], modele_Player.caseCouleur[i]));
            }
        }
    }
    /*
    Fonction de saisie du coup répétée tant que la case n'est pas vide
    @return ArrayList<Integer> : Liste du coup y,x
     */
    private ArrayList<Integer> saisieCoup(modele_Player activePlayer) {
        ArrayList<Integer> coup;

        // Boucle tant que le coup n'est pas sur une case vide
        do {
            coup = activePlayer.getMoveFromPlayer(size);
        } while (!damier.verifCaseLibre(coup, activePlayer.name));

        // retour du coup
        return coup;
    }

    /*
    Méthode de jeu de la partie
     */
    public void play () {

        //initialisation de la variable locale coup
        ArrayList<Integer> coup = new ArrayList<Integer>(2);

        // Définition et allocation des joueurs
        ArrayList<String> joueurs = definitionJoueurs();
        allocationPlayers(joueurs);

        // définition aléatoire du 1er joueur à jouer
        modele_Player activePlayer = joueur.get((int)(Math.random()+1));

        // boucle d'enchainement des coups
        do {

            //Afichage du plateau
            viewer.display(damier.getPlateau());

            // saisie du coup tant que la case choisie n'est pas vide
            coup = saisieCoup(activePlayer);

            // créé le coup du joueur actif
            damier.setOwner(activePlayer,coup);

            // Permute alternativement les joueurs
            activePlayer = (activePlayer.name == joueur.get(2).name) ? joueur.get(1) : joueur.get(2);
        } while (!isOver()); // répétition de la boucle tant que la partie n'est pas finie

        // Affichage du damier final
        viewer.display(damier.getPlateau());
    }

}
