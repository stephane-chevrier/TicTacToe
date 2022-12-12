import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    // initialisation des constantes
    protected final int size = 2;  // =n défini un plateau de n+1 x n+1 cellules
    final String lineSeparator = "--------------";
    // initialisation des variables
    public Cell[][] damier = new Cell[size+1][size+1];
    public String joueur1 = "joueur1";
    public String joueur2 = "joueur2";
    public int joueur1Value = -1;
    public int joueur2Value = 1;

    // Méthode d'initialisation du damier
    void initialiser() {
        for (int i=0;i<=size;i++) {
            for (int j=0;j<=size;j++) {
                damier[i][j] = new Cell();
                damier[i][j].value = 0;
            }
        }
    }
    // Méthode d'affichage du damier
    void display() {
        System.out.println(lineSeparator);
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                System.out.print(damier[i][j].getRepresentation());
            }
            System.out.println("|");
            System.out.println(lineSeparator);
        }
    }
    // Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    ArrayList<Integer> getMoveFromPlayer() {
        // Initialisation des variables locales
        String saisie = "";
        Double saisieDbl = 0.0;
        int xx = 0;
        int yy = 0;
        ArrayList<Integer> retour = new ArrayList<Integer>(2);
        Scanner clavier = new Scanner(System.in);
        Boolean saisieNonOk = true;
        // Boucle faite tant que la saisie n'est pas correcte
        do {
            System.out.print("Saisissez votre choix sous la forme X.Y: ");
            saisie = clavier.nextLine();
            // vérifie que la saisie contient un point et est un Double
            if (saisie.matches("[0-9.]+") && saisie.indexOf('.')==1) {
                saisieDbl = Double.valueOf(saisie);
                xx = (int)Math.floor(saisieDbl);
                yy = (int)((saisieDbl-xx)*10);
                // vérifie que les coordonnées saisies sont entre 0 et size
                if (xx >= 0 && xx <= size && yy >= 0 && yy <= size) {
                    if (damier[xx][yy].value == 0) {
                        retour.add(xx);
                        retour.add(yy);
                        saisieNonOk = false;
                    } else {
                        // message case déjà occupée
                        System.out.println("Cette case est déjà occupée, recommencez.");
                    }
                } else {
                    // message valeurs saisies incorrectes
                    System.out.println("X et Y doivent être compris entre 0 et "+size+", recommencez.");
                }
            } else {
                // message format saisie incorrect
                System.out.println("votre saisie ne correspond pas au format demandé, recommencez.");
            }
        }
        while (saisieNonOk);
        return retour;
    }
    void setOwner (String joueur, int x, int y) {
        int value;
        if (joueur.equals(joueur1)) {
            value = 1;
        } else {
            value = -1;
        }
        damier[x][y].value = value;
    }
}
