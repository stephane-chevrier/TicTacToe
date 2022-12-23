package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Viewer;

import java.util.ArrayList;
import java.util.Map;

public abstract class Player {

    // Définition des séquences pour chaque couleur souhaitée


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

    // constructeur de la Class fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Player
    public Player(String name, int value, String representation, String couleur, int indexCouleur) {
        this.indexCouleur = indexCouleur;
        this.name = name;
        this.value = value;
        this.representation = representation;
        this.couleur = couleur;
    }

    // Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    public abstract ArrayList<Integer> getMoveFromPlayer (int size, int index);

    // sortie du prgramme si saisie exit
    public void checkExitProg(String saisie) {
        if (saisie.toLowerCase().equals("exit")) {
            System.out.println("Fin du programme demandé par le joueur.");
            System.exit(0);
        }
    }
}
