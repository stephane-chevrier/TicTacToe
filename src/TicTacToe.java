import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class TicTacToe {
    // initialisation des constantes
    static final int size = 2;  // =n défini un plateau de n+1 x n+1 cellules
    static final int alignementComplet = (size+1);

    // initialisation du damier (tableau 2 dimensions de l'objet cellule)
    public Cell[][] damier = new Cell[size+1][size+1];
    public int[] calculX = new int[size+2];
    public int[] calculY = new int[size+2];

    public Player joueur1 = new Player();
    public Player joueur2 = new Player();

    // nombre de coups joués
    private int nombreCoupsJoues = 0;
    private int minimum = 0;
    private int maximum = 0;

    public TicTacToe() {
    }

    // Méthode d'initialisation du damier
    void initialiser() {
        // initialisation du damier
        for (int i=0;i<=size;i++) {
            for (int j=0;j<=size;j++) {
                damier[i][j] = new Cell();
                // damier[i][j].value = 0; --> inutile car .value définie à 0 par défaut
            }
        }
        // initialisation des joueurs
        joueur1.value = Player.caseJoueur1;
        joueur2.value = Player.caseJoueur2;
        joueur1.name = "Toto";
        joueur2.name = "Lulu";
    }
    // Méthode d'affichage du damier
    void display() {
        System.out.println();
        // Afichage de l'axe des x
        System.out.println(Cell.LineIndex);
        System.out.println(Cell.lineSeparator);
        // boucle de balayage de lignes
        for (int i = 0; i <= size; i++) {
            // affichage du n° de ligne
            System.out.print(i);
            // boucle de balayage des colonnes
            for (int j = 0; j <= size; j++) {
                System.out.print(damier[i][j].getRepresentation());
            }
            // Affichage de la bordure de droite et de la ligne de séparation des lignes
            System.out.println(Cell.bordureSeparator);
            System.out.println(Cell.lineSeparator);
        }
        // affichage de l'axe des Y
        System.out.println("|");
        System.out.println("Y");
        // Affichage des valeurs du damier
        // for (int i = 0; i <= size; i++) {
//            for (int j = 0; j <= size; j++) {
//                ystem.out.print("("+damier[i][j].value+")");
//            }
//        }
        // Saut de ligne
        System.out.println();
    }
    // vérifie que la saisie contient X.Y
    Boolean verifFormatSaisie(String saisieVal) {
        if (saisieVal.matches("[0-9].[0-9]+")) {
            return true;
        } else {
            // message format saisie incorrect
            System.out.println("votre saisie ne correspond pas au format demandé, recommencez.");
            return false;
        }
    }
    // vérifie que les coordonnées saisies sont entre 0 et size
    Boolean verifValeurSaisie(int i, int j) {
        if (i >= 0 && i <= size && j >= 0 && j <= size) {
            return true;
        } else {
            // message valeurs saisies incorrectes
            System.out.println("y et x doivent être compris entre 0 et " + size + ", recommencez.");
            return false;
        }
    }
    // vérifie que la case n’est pas occupée
    Boolean verifCaseOccupee(int i, int j) {
        if (damier[i][j].value == 0) {
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
        String saisie = "";
        Double saisieDbl = 0.0;
        int xx = 0;
        int yy = 0;
        ArrayList<Integer> retour = new ArrayList<Integer>(2);
        Scanner clavier = new Scanner(System.in);
        Boolean saisieNonOk = true;
        // Boucle faite tant que la saisie n'est pas correcte
        do {
            System.out.print("Joueur "+joueur+",saisissez votre choix sous la forme Y.X: ");
            saisie = clavier.nextLine();
            // vérifie que la saisie contient X.Y
            if (verifFormatSaisie(saisie)) {
                saisieDbl = Double.valueOf(saisie);
                xx = (int) Math.floor(saisieDbl);
                yy = (int) Math.round((saisieDbl - xx) * 10);
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
    void setOwner (int joueur, ArrayList<Integer> coord) {
        damier[coord.get(0)][coord.get(1)].value = joueur;
    }
    // méthode de calcul des sommes de chaque alignement, des minimum et maximum de ces sommes, du nombre de coups joués
        void situationCalcul() {
        // RAZ du nombre de coups joués
        nombreCoupsJoues = 0;
        // Calcul de la somme de chaque alignement dans calculX et calculY
        // La somme des diagonales est stockée dans calculX[size+1] et calculY[size+1]
        // RAZ de la somme des diagonales
        calculX[size+1] = 0;
        calculY[size+1] = 0;
        // 1ère boucle de balayage
        for (int i=0;i<=size;i++) {
            // Raz de la 1ère série de sommes
            calculX[i] = 0;
            calculY[i] = 0;
            // calcul de la somme des diagonales
            calculX[size + 1] += damier[i][i].value;
            calculY[size + 1] += damier[size - i][i].value;
            // 2ème boucle de balayage des lignes
            for (int j = 0; j <= size; j++) {
                // calcul de la 2ème série de somme
                calculX[i] += damier[i][j].value;
                calculY[i] += damier[j][i].value;
                // calcul du nombre coups joués
                if (damier[i][j].value != Player.caseVide ) {
                    nombreCoupsJoues++;
                }
            }
        }
        // calcul des maximum et minimum des 2 tableaux calculX et calculY qui contiennent la somme de chaque alignement
        minimum = 0;
        maximum = 0;
        for (int i=0;i<=size;i++) {
            if (calculX[i]<minimum) {
                minimum = calculX[i];
            }
            if (calculY[i]<minimum) {
                minimum = calculX[i];
                }
            if (calculX[i]>maximum) {
                maximum = calculX[i];
            }
            if (calculY[i]>maximum) {
                maximum = calculX[i];
            }
        }
    }
    // Méthode de calcul de fin de partie : soit un joueur a gagné, soit il n'y a plus de coups à jouer
        Boolean isOver() {
        // Par défaut la partie n'est pas finie
        Boolean retour = false;
        // calcul des sommes de chaque alignement et du nombre de coups joués
        situationCalcul();
        // System.out.println("mini:"+minimum+" -- maxi:"+maximum+" -- Nbre de coups joués: "+nombreCoupsJoues);
        // Le joueur2 a gagné
        if (minimum == alignementComplet*Player.caseJoueur2) {
            System.out.println("partie terminée, le joueur "+joueur2.name+" a gagné !!!");
            retour = true;
        }
        // Le joueur1 a gagné
        if (maximum == alignementComplet*Player.caseJoueur1) {
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
        initialiser();
        // 1er joueur à jouer
        String joueur = joueur1.name;
        // boucle d'enchainement des coups
        do {
            display();
            // si le précédent joueur était joueur2, le joueur1 joue
            if (joueur == joueur1.name) {
                setOwner(joueur1.value, getMoveFromPlayer(joueur1.name));
                joueur =joueur2.name;
            } else {
                // sinon le joueur2 joue
                setOwner(joueur2.value, getMoveFromPlayer(joueur2.name));
                joueur = joueur1.name;
            }
        }
        // répéition de la boucle tant que la partie n'est pas finie
        while (isOver()==false);
    }
}
