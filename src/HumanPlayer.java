import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends modele_Player {

    // constructeur de la Class ArtificialPlayer
    public HumanPlayer(String name, int value, String representation, String couleur) {
        super(name, value, representation, couleur);
    }

    // vérifie que la saisie contient X.Y
    protected boolean verifFormatSaisie(String saisieVal) {
        if (saisieVal.matches("[0-9].[0-9]")) {
            return true;
        } else {
            // message format saisie incorrect
            System.out.println("votre saisie ne correspond pas au format demandé, recommencez.");
            return false;
        }
    }

    // vérifie que les coordonnées saisies sont entre 0 et size
    public boolean verifValeurSaisie(int i, int j, int size) {
        if (i >= 0 && i <= size && j >= 0 && j <= size) {
            return true;
        } else {
            // message valeurs saisies incorrectes
            System.out.println("y et x doivent être compris entre 0 et " + size + ", recommencez.");
            return false;
        }
    }

    // Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    @Override
    ArrayList<Integer> getMoveFromPlayer(int size) {
        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<Integer>(2);
        Scanner clavier = new Scanner(System.in);
        boolean saisieNonOk = true;
        // Boucle faite tant que la saisie n'est pas correcte
        do {
            System.out.print("Joueur " + this.couleur + this.name + modele_Player.caseCouleur[0] + ",saisissez votre choix sous la forme Y.X (exit pour sortir du jeu): ");
            String saisie = clavier.nextLine();
            // sortie du programme si saisie exit
            checkExitProg(saisie);
            // vérifie que la saisie contient X.Y
            if (verifFormatSaisie(saisie)) {
                double saisieDbl = Double.valueOf(saisie);
                int xx = (int) Math.floor(saisieDbl);
                int yy = (int) Math.round((saisieDbl - xx) * 10);
                // vérifie que les coordonnées saisies sont entre 0 et size
                if (verifValeurSaisie(xx, yy, size)) {
                    retour.add(xx);
                    retour.add(yy);
                    saisieNonOk = false;
                }
            }
        } while (saisieNonOk) ;
            return retour;
    }
}
