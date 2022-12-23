/*
Nom:            Viewer
Description :   Viewer jeu TicTacToe (MVC)
@ersion         v1.0
Date :          23 décembre 2022
@author         Stéphane CHEVRIER
 */

import java.util.HashMap;
import java.util.Map;

public class Viewer {

    /*
    Initialisation des messages affichés à l'écran
    @C:insère la couleur du joueur,  @D:insère la couleur par défaut, @i:insère l'index
     */
    public final String messageSaisieNom1 = "Saisissez le nom du @Cjoueur n°@i@D (Random pour joueur aléatoire) : ";

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

    /*
    Fonction de création de la ligne index
    @return String : représentation de la ligne des index de l'axe X
     */
    public String setIndex(int size, String col) {

        // Initialisation variables locales
        String sortie = col+"   ";  // Affichage d'une case vide pour la premiere colonne du damier
        String representation ="";
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
    private String setMotifs(String coin1, String milieu, String inter, String coin2, int size) {

        // initialisation de la variable de sortie
        String sortie = coin1;

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
    public void display(modele_Cell[][] cell) {

        // initialisation des variables locales
        int size = cell.length;
        String LineIndex = setIndex(size, col);

        // effacement du terminal
        displayEffacer();

        // création des 3 types de lignes d'affichage
        String LineUp = setMotifs(coinHG, ligne, interH, coinHD,size);
        String LineDown = setMotifs(coinBG, ligne, interB, coinBD,size);
        String LineMid = setMotifs(interG, ligne, inter, interD, size);

        // Affichage de l'index de l'axe des x
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
        // affichage de la dernière ligne du damier
        System.out.println(LineDown);
        System.out.println();
    }

    /*
     Méthode pour effacer la console terminal de windows
     NON IMPLEMENTEE
     */
    private void displayEffacer() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
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
        texte = texte.replaceAll("@C", modele_Player.caseCouleur[i]);
        texte = texte.replaceAll("@D", modele_Player.caseCouleur[0]);
        texte = texte.replaceAll("@i",convertTexte);

        // Affichage du texte
        if (saut) {
            System.out.println(texte);
        } else {
            System.out.print(texte);
        }
    }
}
