package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

import java.util.ArrayList;
import java.util.Random;

public class ArtificialPlayer extends Player {

    // constructeur de la Class ArtificialPlayer
    public ArtificialPlayer(String name, int value, String representation, String couleur, int indexCouleur) {
        super(name, value, representation, couleur, indexCouleur);
    }

    // Fonction de choix aléatoire de coordonnées + vérification + renvoie les coordonnées
    @Override
    public ArrayList<Integer> getMoveFromPlayer(int size, int index) {
        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<Integer>(2);
        // Création instance aleatoire de Random()
        Random aleatoire = new Random();
       // Initialisation de la liste retour avec un entier aléatoire entre 0 et 2
        retour.add(aleatoire.nextInt(size+1));
        retour.add(aleatoire.nextInt(size+1));
        // Return de la liste retour
        return retour;
    }
}
