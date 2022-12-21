import java.util.*;

public class TicTacToe {
    // initialisation des constantes
    static final int size = 2;  // =n défini un plateau de n+1 * n+1 cellules

    // initialisation du damier (tableau 2 dimensions de l'objet cellule) et du tableau Calcul = Somme des alignements
    private Cell[][] damier;
    private Viewer viewer = new Viewer();

    // initialisation des joueurs,
    private ArrayList<Player> joueur = new ArrayList<>(2);

    // Constructeur de la Class TicTacToe
    public TicTacToe() {
        this.damier = initialiserDamier();
    }

    // Méthode d'initialisation du damier
    private Cell[][] initialiserDamier() {
        Cell[][] cells = new Cell[size+1][size+1];
        // initialisation du damier
        for (int i=0;i<=size;i++) {
            for (int j=0;j<=size;j++) {
                cells[i][j] = new Cell();
            }
        }
        // retour du damier
        return cells;
    }

    private void setOwner (Player joueur,  ArrayList<Integer> coord) {
        damier[coord.get(0)][coord.get(1)].joueur = joueur;
    }

    private void saisieCoupvalide() {

    }

    // méthode de calcul des sommes de chaque alignement, des minimum et maximum de ces sommes, du nombre de coups joués
    ArrayList<Integer> situationCalcul() {
        // ArrayList de sortie
        ArrayList<Integer> retour = new ArrayList<>(3);
        // RAZ du tableau de calcul des sommes et du nombre de coups joués
        int nombreCoupsJoues = 0;
        int[] calcul = new int[(size+1)*3];
        for (int i = 0; i <= calcul.length - 1; i++) {
            calcul[i] = 0;
        }
        // Double boucle de calcul des 8 sommes (3 lignes, 3 colonnes, 2 diagonales)
        for (int i = 0; i <= size; i++) {
            calcul[7] += damier[i][i].getValue();      // somme de la diagonale 0.0+1.1+2.2 dans index 7
            calcul[8] += damier[i][size - i].getValue(); // somme de la diagonale 0.2+1.1+2.0 dans index 8
            for (int j = 0; j <= size; j++) {
                calcul[j] += damier[i][j].getValue();   // somme des lignes 0-1-2 dans index 0-1-2
                calcul[j + 3] += damier[j][i].getValue(); // somme des colonnes 0-1-2 dans index 3-4-5
                // Calcul du nombre de coups joués
                if (damier[i][j].getValue() != Player.caseValue[0]) {
                    nombreCoupsJoues++;
                }
            }
        }
        // calcul des maximum et minimum des 2 tableaux calculX et calculY qui contiennent la somme de chaque alignement
        retour.add(Arrays.stream(calcul).min().getAsInt());
        retour.add(Arrays.stream(calcul).max().getAsInt());
        // retour du nombre de coups joués
        retour.add(nombreCoupsJoues);
        // fin de la fonction
        return retour;
    }

    // vérifie que la case n’est pas occupée
    private boolean verifCaseLibre(ArrayList<Integer> coup, String name) {
        String message = "coup "+coup.get(0)+"-"+coup.get(1)+" joué par "+name;
        if (damier[coup.get(0)][coup.get(1)].getValue() == 0) {
            System.out.println(message);
            return true;
        } else {
            // message case déjà occupée
            if (!name.startsWith("Random"))
            System.out.println(message+" , la case est déjà occupée.");
            return false;
        }
    }

    // Méthode de calcul de fin de partie : soit un joueur a gagné, soit il n'y a plus de coups à jouer
    private boolean isOver() {
        int alignementComplet = (size+1);
        // Par défaut la partie n'est pas finie
        Boolean retour = false;
        // calcul des sommes de chaque alignement et du nombre de coups joués
        ArrayList<Integer> minmaxnbre = (ArrayList<Integer>) situationCalcul().clone();
        // Le joueur2 a gagné
        if (minmaxnbre.get(0) == alignementComplet*Player.caseValue[2]) {
            System.out.println("partie terminée, le joueur "+joueur.get(2).couleur+joueur.get(2).name+Player.couleurDefaut+" a gagné !!!");
            retour = true;
        }
        // Le joueur1 a gagné
        if (minmaxnbre.get(1) == alignementComplet*Player.caseValue[1]) {
            System.out.println("partie terminée, le joueur "+joueur.get(1).couleur+joueur.get(1).name+Player.couleurDefaut+" a gagné !!!");
            retour = true;
        }
        // Il n'y a plus de coups à jouer et il n'y a aucun vainqueur
        if ((minmaxnbre.get(2) == alignementComplet*alignementComplet) && (!retour)) {
            System.out.println("partie terminée, égalité");
            retour = true;
        }
        return retour;
    }

    // Fonction de renvoie des 2 joueurs définis
    private ArrayList<String> definitionJoueurs () {
        // Initialisation des variables locales
        ArrayList<String> joueurs = new ArrayList<>(2);
        Scanner clavier = new Scanner(System.in);
        // Joueur vide index 0
        joueurs.add("JoueurVide");
        // Boucle de saisie des 2 joueurs
        for (int i=1; i<=2; i++) {
            System.out.print("Saisissez le nom du joueur n°"+i+" (Random pour joueur aléatoire) (exit pour quitter): ");
            joueurs.add(clavier.nextLine());
        }
        // retour de la ArrayList des 3 joueurs (vide, joueur n°1, joueur n°2)
        return joueurs;
    }

    // Méthode d'initialisation de chaque joueur avec la bonne classe
    private void allocationPlayers(ArrayList<String> listeJoueurs) {
            // Boucle de création des 3 joueurs : index0:Joueur vide, index1:Joueur n°1, index2: joueur n°2
            for (int i=0; i<=2; i++) {
                switch (listeJoueurs.get(i).toLowerCase()) {
                    case "random": // Joueur Aléatoire
                        joueur.add(new ArtificialPlayer("Random"+i, Player.caseValue[i], Player.representationJoueur[i], Player.caseCouleur[i]));
                        break;
                    default: // Joueur Humain
                        joueur.add(new HumanPlayer(listeJoueurs.get(i), Player.caseValue[i], Player.representationJoueur[i], Player.caseCouleur[i]));
                }
            }
    }

    // Méthode de lancement de la partie
    public void play () {
        ArrayList<Integer> coup = new ArrayList<Integer>(2);
        // Définition et allocation des joueurs
        ArrayList<String> joueurs = definitionJoueurs();
        allocationPlayers(joueurs);
        // 1er joueur à jouer
        Player activePlayer = joueur.get((int)(Math.random()+1));
        // boucle d'enchainement des coups
        do {
            viewer.display(damier);
            // boucle de saisie du coup tant que la case choisie n'est pas vide
            do {
                coup = activePlayer.getMoveFromPlayer();
            } while (!verifCaseLibre(coup, activePlayer.name));
            // créé le coup du joueur actif
            setOwner(activePlayer,coup);
            // Permute alternativement les joueurs
            activePlayer = (activePlayer.name == joueur.get(2).name) ? joueur.get(1) : joueur.get(2);
        } while (!isOver()); // répétition de la boucle tant que la partie n'est pas finie
        // Affichage du damier final
        viewer.display(damier);
    }
}
