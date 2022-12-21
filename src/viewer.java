import java.util.HashMap;
import java.util.Map;

public class Viewer {

    // Initialisation des constantes de représentation du damier
    static final String coinHG = "┌";
    static final String coinHD = "┐";
    static final String coinBG = "└";
    static final String coinBD = "┘";

    static final String interH = "┬";
    static final String interG = "├";
    static final String interD = "┤";
    static final String interB = "┴";
    static final String inter = "┼";
    static final String ligne = "─";
    static final String col = "│";
    private Map<Integer, String> index = new HashMap<>();

    // Création ligne index
    public String setIndex(int size, String col) {
        String sortie = col+"   ";
        String representation ="";
        String conversion = new String();
        for (int i=0; i<size; i++) {
            if (i<=9) {
                representation = conversion.valueOf(i);
            } else {
                representation = Character.toString(55+i);
            }
            index.put(i,representation);
            sortie += col+" "+representation+" ";
        }
        sortie += col;
        return sortie;
    }

    private String getMotifs(String coin1, String milieu, String inter, String coin2, int size) {
        String sortie = coin1;
        for (int i = 0; i < size; i++) {
            sortie += ligne.repeat(3) + inter;
        }
        sortie += ligne.repeat(3) + coin2;
        return sortie;
    }

    // Méthode d'affichage du damier
    public void display(Cell[][] cell) {
        int size = cell.length;
        String LineIndex = setIndex(size, col);
        // effacement du terminal Windows
        displayEffacer();
        // création des variables d'affichage
        String LineUp = getMotifs(coinHG, ligne, interH, coinHD,size);
        String LineDown = getMotifs(coinBG, ligne, interB, coinBD,size);
        String LineMid = getMotifs(interG, ligne, inter, interD, size);
        // Affichage de l'axe des x
        System.out.println(LineUp);
        System.out.println(LineIndex);
        System.out.println(LineMid);
        // boucle de balayage de lignes
        for (int i = 0; i <= size-1; i++) {
            // affichage du n° de ligne
            System.out.print(Viewer.col + " " + index.get(i) + " ");
            // boucle de balayage des colonnes
            for (int j = 0; j <= size-1; j++) {
                System.out.print(cell[i][j].getRepresentation());
            }
            // Affichage de la bordure de droite et de la ligne de séparation des lignes
            System.out.println(Viewer.col);
            if (i < size-1) {
                System.out.println(LineMid);
            }
        }
        // affichage de l'axe des Y
//        System.out.println(Cell.col);
//        System.out.println("Y");
        System.out.println(LineDown);
        System.out.println();
    }
    // execution du coup d'un joueur

    // Méthode pour effacer la console terminal de windows
    private void displayEffacer() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
    }
}
