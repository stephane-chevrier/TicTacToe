import java.util.ArrayList;
import java.util.Random;

public class ArtificialPlayer extends Player {

    // constructeur de la Class ArtificialPlayer
    public ArtificialPlayer(String name, int value, String representation) {
        super(name, value, representation);
    }

    // Fonction de choix aléatoire de coordonnées + vérification + renvoie les coordonnées
    @Override
    ArrayList<Integer> getMoveFromPlayer() {
        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<Integer>(2);
        // Création instance aleatoire de Random()
        Random aleatoire = new Random();
       // Initialisation de la liste retour avec un entier aléatoire entre 0 et 2
        retour.add(aleatoire.nextInt(3));
        retour.add(aleatoire.nextInt(3));
        // Return de la liste retour
        return retour;
    }
}
