package fr.le_campus_numerique.stephanechevrier.tictactoe.viewer;

/*
Nom:            Viewer
Description :   Viewer jeu TicTacToe (MVC)
@version         1.0
Date :          23 décembre 2022
@author         Stéphane CHEVRIER
 */

import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Cell;
import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Player;
import java.util.HashMap;
import java.util.Map;

public class Viewer {

    public static final Map<String,String> couleurDef = Map.of(
            "rouge", "\u001B[31m",
            "bleue", "\u001B[34m",
            "defaut", "\u001B[0m",
            "jaune", "\u001B[33m"
    );

    /*
    Initialisation des messages affichés à l'écran
    @C:insère la couleur du joueur,  @D:insère la couleur par défaut, @i:insère l'index, @E insère la couleur des messages d'erreur
     */
    public final String messageSaisieNom1 = "@DSaisissez le nom du @Cjoueur n°@i@D (Random pour joueur aléatoire) : ";
    public final String messageCoupJoue1 = "@Dcoup ";
    public final String messageCoupJoue2 = " joué par @C";

    public final String messagePartieTerminee = "@Dpartie terminée";
    public final String messageleJoueur = ", le joueur @C";
    public final String messageAGagne = "@D a gagné !!!";
    public final String messageEgalite = ", égalité.";

    public final String messageJoueur = "@DJoueur @C";
    public final String messageSaisissezCoup = "@D ,saisissez votre choix sous la forme Y.X (exit pour sortir du jeu): ";
    public final String messageCase = "@D@ECase ";
    public final String messageCaseOccupee = " déjà occupée, recommencez.";
    public static String messageSaisieInvalide = "@D@Evotre saisie n'est pas correcte, recommencez.@D";
//    public static String messageXYinvalides1 = "@D@Ey et x doivent être compris entre 0 et ";
//    public static String messageXYinvalides2 = ", recommencez.@D";

    public static String messageSortie = "@@EFin du programme demandé par le joueur.@D";


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
    public static final String col = "│";

    // initialisation de la MAP
    private final Map<Integer, String> index = new HashMap<>();

    /*
    Fonction de création de la ligne index
    @return String : représentation de la ligne des index de l'axe X
     */
    public String setIndex(int size, String col) {

        // Initialisation variables locales
        String sortie = col+"   ";  // Affichage d'une case vide pour la premiere colonne du damier
        String representation;
        String conversion = new String();

        // boucle sur le nombre de cases du damier
        for (int i=0; i<size; i++) {

            // création des index 0.1.2.3.4.5.6.7.8.9.A.B.C.D.E.F..... en fonction de la taille du damier
            if (i<=9) {
                representation = conversion.valueOf(i);
            } else {
                representation = Character.toString(55+i);
            }
            index.put(i,representation);

            // préparation de la String de sortie
            sortie += col+" "+representation+" ";
        }
        // Retour de la String de sortie terminée par une bordure verticale
        return sortie+col;
    }

    /*
    Fonction de création des lignes intermédiaires du damier
    @return String : ligne intermédiaire du damier
     */
    private String setMotifs(String coin1, String inter, String coin2, int size) {

        // initialisation de la variable de sortie
        String sortie = "@D"+coin1;

        // boucle sur le nombre de cases -1 du damier
        for (int i = 0; i < size; i++) {
            sortie += ligne.repeat(
                    3) + inter;
        }
        // Dernière case
        sortie += ligne.repeat(3) + coin2;

        // Retour de la ligne créée
        return sortie;
    }

    /*
    Méthode d'affichage du damier
     */
//    public void display(Cell[][] cell) {
    public void display(Cell[][] cell) {

        // initialisation des variables locales
        int size = cell.length;
        String LineIndex = setIndex(size, col);

        // effacement du terminal
//        displayEffacer();

        // création des 3 types de lignes d'affichage
        String LineUp = setMotifs(coinHG, interH, coinHD,size);
        String LineDown = setMotifs(coinBG, interB, coinBD,size);
        String LineMid = setMotifs(interG, inter, interD, size);

        // Affichage de l'index de l'axe des x
        afficherEcran(LineUp,0,true);
        afficherEcran(LineIndex, 0, true);
        afficherEcran(LineMid, 0, true);

        // boucle de balayage de lignes
        for (int i = 0; i <= size-1; i++) {

            // affichage du n° de ligne
            afficherEcran(Viewer.col + " " + index.get(i) + " ", 0, false);

            // boucle de balayage des colonnes
            for (int j = 0; j <= size-1; j++) {
                afficherEcran(cell[i][j].getRepresentation(col), 0, false);
//                afficherEcran(cellRepr[i][j], 0, false);
            }
            // Affichage de la bordure de droite et de la ligne de séparation des lignes
            afficherEcran(Viewer.col, 0, true);
            if (i < size-1) {
                afficherEcran(LineMid, 0 , true);
            }
        }
        // affichage de la dernière ligne du damier
        afficherEcran(LineDown, 0, true);
        afficherEcran("", 0, true);
    }

    /*
     Méthode pour effacer la console
     */
    public void displayEffacer() {

        // boucle de 5 lignes vides avec couleur par défaut
        for (int i=0; i<=5; i++) {
            afficherEcran("@D", 0, true);
        }
    }

    /*
    méthode d'affichage d'un message
    en incluant la couleur du joueur quand le nom est affiché
    en incluant le n° du joueur quand il est indiqué
     */
    public void afficherEcran(String texte, int i, boolean saut) {

        // Conversion de l'index i en chaine
        String convertTexte = new String();
        convertTexte = convertTexte.valueOf(i);

        // Remplacement des codes @x par leur séquence
        texte = texte.replaceAll("@C", Player.caseCouleur[i]);
        texte = texte.replaceAll("@D", Player.caseCouleur[0]);
        texte = texte.replaceAll("@E", couleurDef.get("rouge"));
        texte = texte.replaceAll("@i",convertTexte);

        // Affichage du texte
        if (saut) {
            System.out.println(texte);
        } else {
            System.out.print(texte);
        }
    }
}
