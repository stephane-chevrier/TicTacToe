package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

/*
Nom             Player, Class abstract
Description     Modèle jeu TicTacToe (MVC)
                Joueurs
@version        v1.0
Date            12 décembre 2022
@author         Stéphane CHEVRIER
*/

import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Viewer;
import java.util.ArrayList;

public abstract class Player {

    // définition des constantes pour les cases : couleur, valeur, représentation
    public static final String[] caseCouleur = {Viewer.couleurDef.get("defaut"), Viewer.couleurDef.get("bleue"), Viewer.couleurDef.get("jaune")};
    public static final int[] caseValue = {0,1,-1}; // tableau de valeurs des cases vides, joueur n°1 (int>=1), joueur n°2 (int<=-1)
    public static final String[] representationJoueur = {" ","X","O"}; // tableau des représentations des cases vide, du joueur n°1, du joueur n°2

    // Initialisation variables d'instance
    public int indexCouleur;
    public int value;
    public String name;
    public String representation;
    public String couleur;
    public int size;

    /* constructeur de la Class Player */
    public Player(String name, int value, String representation, String couleur, int indexCouleur, int size) {
        this.indexCouleur = indexCouleur;
        this.name = name;
        this.value = value;
        this.representation = representation;
        this.couleur = couleur;
        this.size = size;
    }

    /* Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    @return ArrayList<Integer> : {Y,X}
     */
    public abstract ArrayList<Integer> getMoveFromPlayer (int size, int index);
}
