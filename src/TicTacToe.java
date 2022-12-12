public class TicTacToe {
    // initialisation des constantes
    final int size = 2;  // =n défini un plateau de n+1 x n+1 cellules
    final String lineSeparator = "--------------";
    // initialisation des variables
    Cell[][] damier = new Cell[size+1][size+1];

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
        for (int i=0;i<=size;i++) {
            for (int j=0;j<=size;j++) {
                System.out.print(damier[i][j].getRepresentation());
            }
            System.out.println("|");
            System.out.println(lineSeparator);
        }
    }
}
