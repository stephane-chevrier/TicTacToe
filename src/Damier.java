import java.util.ArrayList;

public class Damier {

    // initialisation du damier (tableau 2 dimensions de l'objet cellule) et du tableau Calcul = Somme des alignements
    private Cell[][] plateau;

    // Constructeur de la Class Damier
    public Damier(int size) {
        this.plateau = initialiserDamier(size);
    }

    // renvoie le damier
    public Cell[][] getPlateau() {
        return plateau;
    }

    // Méthode d'initialisation du damier
    private Cell[][] initialiserDamier(int size) {
        Cell[][] cells = new Cell[size + 1][size + 1];
        // initialisation du damier
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                cells[i][j] = new Cell();
            }
        }
        // retour du damier
        return cells;
    }

    // méthode d'entrée du coup joué dans le damier
    public void setOwner(Player joueur, ArrayList<Integer> coord) {
        plateau[coord.get(0)][coord.get(1)].joueur = joueur;
    }

    // vérifie que la case n’est pas occupée
    public boolean verifCaseLibre(ArrayList<Integer> coup, String name) {
        String message = "coup " + coup.get(0) + "-" + coup.get(1) + " joué par " + name;
        if (plateau[coup.get(0)][coup.get(1)].getValue() == 0) {
            System.out.println(message);
            return true;
        } else {
            // message case déjà occupée
            if (!name.startsWith("Random"))
                System.out.println(message + " , la case est déjà occupée.");
            return false;
        }
    }


}

