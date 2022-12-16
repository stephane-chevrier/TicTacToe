import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player {

    // Initialisation constantes static
    static final int[] caseValue = {0,1,-1}; // tableau de valeurs des cases vides, joueur n°1 (int>=1), joueur n°2 (int<=-1)
    static final String[] representationJoueur = {" ","X","O"}; // tableau des représentations des cases vide, du joueur n°1, du joueur n°2

    // Initialisation variables d'instance
    public int value;
    public String name;
    public String representation;

    // constructeur de la Class Player
    public Player(String name, int value, String representation) {
        this.name = name;
        this.value = value;
        this.representation = representation;
    }

    // vérifie que la saisie contient X.Y
    boolean verifFormatSaisie(String saisieVal) {
        if (saisieVal.matches("[0-9].[0-9]")) {
            return true;
        } else {
            // message format saisie incorrect
            System.out.println("votre saisie ne correspond pas au format demandé, recommencez.");
            return false;
        }
    }

    // vérifie que les coordonnées saisies sont entre 0 et size
    boolean verifValeurSaisie(int i, int j) {
        if (i >= 0 && i <= TicTacToe.size && j >= 0 && j <= TicTacToe.size) {
            return true;
        } else {
            // message valeurs saisies incorrectes
            System.out.println("y et x doivent être compris entre 0 et " + TicTacToe.size + ", recommencez.");
            return false;
        }
    }

    // Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    abstract ArrayList<Integer> getMoveFromPlayer();

}
