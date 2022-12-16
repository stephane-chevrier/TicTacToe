import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player {

    // constructeur de la Class ArtificialPlayer
    public HumanPlayer(String name, int value, String representation) {
        super(name, value, representation);
    }
    // Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    @Override
    ArrayList<Integer> getMoveFromPlayer() {
        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<Integer>(2);
        Scanner clavier = new Scanner(System.in);
        boolean saisieNonOk = true;
        // Boucle faite tant que la saisie n'est pas correcte
        do {
            System.out.print("Joueur " + this.name + ",saisissez votre choix sous la forme Y.X (exit pour sortir du jeu): ");
            String saisie = clavier.nextLine();
            // sortie du prgramme si saisie exit
            if (saisie.toLowerCase().equals("exit")) {
                System.out.println("Fin du programme demandé par le joueur.");
                System.exit(0);
            }
            // vérifie que la saisie contient X.Y
            if (verifFormatSaisie(saisie)) {
                double saisieDbl = Double.valueOf(saisie);
                int xx = (int) Math.floor(saisieDbl);
                int yy = (int) Math.round((saisieDbl - xx) * 10);
                // vérifie que les coordonnées saisies sont entre 0 et size
                if (verifValeurSaisie(xx, yy)) {
                    retour.add(xx);
                    retour.add(yy);
                    saisieNonOk = false;
                }
            }
        } while (saisieNonOk) ;
            return retour;
    }
}
