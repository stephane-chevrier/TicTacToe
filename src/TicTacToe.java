
import java.util.*;

public class TicTacToe {
    // initialisation des constantes
    private static final int size = 2;  // =n défini un plateau de n+1 x n+1 cellules

    // initialisation du damier (tableau 2 dimensions de l'objet cellule) et du tableau Calcul = Somme des alignements
    private Cell[][] damier = new Cell[size+1][size+1];

    // initialisation des joueurs,
    private Player joueur1 = new Player("Toto", Player.caseJoueur1, Player.representationJoueur1);
    private Player joueur2 = new Player("Lulu", Player.caseJoueur2, Player.representationJoueur2);

    // nombre de coups joués, minimum et maximum du tableau calcul
    private int minimumTabCalcul = 0;  // mieux : passer les valeurs dans un Arraylist de retour de la fonction Situation(), utilisé que dans over()
    private int maximumTabCalcul = 0;  // mieux : passer les valeurs dans un Arraylist de retour de la fonction Situation(), utilisé que dans over()

    // Constructeur de la Class TicTacToe
    public TicTacToe() {
        initialiser();
    }

    // Méthode d'initialisation du damier
    void initialiser() {
        damier = new Cell[size+1][size+1];
        // initialisation du damier
        for (int i=0;i<=size;i++) {
            for (int j=0;j<=size;j++) {
                damier[i][j] = new Cell();
            }
        }
    }
    // Méthode d'affichage du damier
    void display() {
        System.out.println();
        // Affichage de l'axe des x
        System.out.println(Cell.LineIndex);
        System.out.println(Cell.lineSeparator);
        // boucle de balayage de lignes
        for (int i = 0; i <= size; i++) {
            // affichage du n° de ligne
            System.out.print(i);
            // boucle de balayage des colonnes
            for (int j = 0; j <= size; j++) {
//                System.out.print("|   ");
                System.out.print(damier[i][j].getRepresentation());
            }
            // Affichage de la bordure de droite et de la ligne de séparation des lignes
            System.out.println(Cell.bordureSeparator);
            System.out.println(Cell.lineSeparator);
        }
        // affichage de l'axe des Y
        System.out.println(Cell.bordureSeparator);
        System.out.println("Y");
        System.out.println();
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
        if (i >= 0 && i <= size && j >= 0 && j <= size) {
            return true;
        } else {
            // message valeurs saisies incorrectes
            System.out.println("y et x doivent être compris entre 0 et " + size + ", recommencez.");
            return false;
        }
    }
    // vérifie que la case n’est pas occupée
    boolean verifCaseOccupee(int i, int j) {
        if (damier[i][j].getValue() == 0) {
            return true;
        } else {
            // message case déjà occupée
            System.out.println("Cette case est déjà occupée, recommencez.");
            return false;
        }
    }
    // Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    ArrayList<Integer> getMoveFromPlayer(String joueur) {
        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<Integer>(2);
        Scanner clavier = new Scanner(System.in);
        boolean saisieNonOk = true;
        // Boucle faite tant que la saisie n'est pas correcte
        do {
            System.out.print("Joueur "+joueur+",saisissez votre choix sous la forme Y.X: ");
            String saisie = clavier.nextLine();
            // vérifie que la saisie contient X.Y
            if (verifFormatSaisie(saisie)) {
                double saisieDbl = Double.valueOf(saisie);
                int xx = (int) Math.floor(saisieDbl);
                int yy = (int) Math.round((saisieDbl - xx) * 10);
                // vérifie que les coordonnées saisies sont entre 0 et size
                if (verifValeurSaisie(xx,yy)) {
                    // vérifie que la case n’est pas occupée
                    if (verifCaseOccupee(xx,yy)) {
                        retour.add(xx);
                        retour.add(yy);
                        saisieNonOk = false;
                    }
                }
            }
        }
        while (saisieNonOk);
        return retour;
    }
    // execution du coup d'un joueur

    void setOwner (Player joueur,  ArrayList<Integer> coord) {
        damier[coord.get(0)][coord.get(1)].joueur = joueur;
    }
    // méthode de calcul des sommes de chaque alignement, des minimum et maximum de ces sommes, du nombre de coups joués
    int situationCalcul() {
        int[] calcul = new int[(size+1)*3];
        // RAZ du tableau de calcul des sommes et du nombre de coups joués
        int nombreCoupsJoues = 0;
        for (int i = 0; i <= calcul.length - 1; i++) {
            calcul[i] = 0;
        }
        // Double boucle de calcul des 8 sommes (3 lignes, 3 colonnes, 2 diagonales)
        for (int i = 0; i <= size; i++) {
            calcul[7] += damier[i][i].getValue();      // somme de la diagonale 0.0+1.1+2.2 dans index 7
            calcul[8] += damier[i][size - i].getValue(); // somme de la diagonale 0.2+1.1+2.0 dans index 7
            for (int j = 0; j <= size; j++) {
                calcul[j] += damier[i][j].getValue();   // somme des lignes 0-1-2 dans index 0-1-2
                calcul[j + 3] += damier[j][i].getValue(); // somme des colonnes 0-1-2 dans index 3-4-5
                // Calcul du nombre de coups joués
                if (damier[i][j].getValue() != Player.caseVide) {
                    nombreCoupsJoues++;
                }
            }
        }
        // calcul des maximum et minimum des 2 tableaux calculX et calculY qui contiennent la somme de chaque alignement
        minimumTabCalcul = Arrays.stream(calcul).min().getAsInt();
        maximumTabCalcul = Arrays.stream(calcul).max().getAsInt();
        // fin de la fonction
        return nombreCoupsJoues;
    }
    // Méthode de calcul de fin de partie : soit un joueur a gagné, soit il n'y a plus de coups à jouer
    boolean isOver() {
        int alignementComplet = (size+1);
        // Par défaut la partie n'est pas finie
        Boolean retour = false;
        // calcul des sommes de chaque alignement et du nombre de coups joués
        int nombreCoupsJoues = situationCalcul();
        // System.out.println("mini:"+minimum+" -- maxi:"+maximum+" -- Nbre de coups joués: "+nombreCoupsJoues);
        // Le joueur2 a gagné
        if (minimumTabCalcul == alignementComplet*Player.caseJoueur2) {
            System.out.println("partie terminée, le joueur "+joueur2.name+" a gagné !!!");
            retour = true;
        }
        // Le joueur1 a gagné
        if (maximumTabCalcul == alignementComplet*Player.caseJoueur1) {
            System.out.println("partie terminée, le joueur "+joueur1.name+" a gagné !!!");
            retour = true;
        }
        // Il n'y a plus de coups à jouer
        if (nombreCoupsJoues == alignementComplet*alignementComplet) {
            System.out.println("partie terminée, égalité");
            retour = true;
        }
        return retour;
    }
    void play () {
        // 1er joueur à jouer
        Player activePlayer = this.joueur1;
        // boucle d'enchainement des coups
        do {
            display();
            setOwner(activePlayer, getMoveFromPlayer(activePlayer.name));
            // Permute alternativement les joueurs
            if (activePlayer.name == joueur1.name) {
                activePlayer = joueur2;
            } else {
                activePlayer= joueur1;
            }
        }
        // répétition de la boucle tant que la partie n'est pas finie
        while (isOver()==false);
    }
}
