package fr.le_campus_numerique.stephanechevrier.tictactoe.viewer;

/**
 * Nom:            Viewer
 * Description :   Viewer jeu TicTacToe (MVC)
 * @version 1.0
 * Date :          23 décembre 2022
 * @author Stéphane CHEVRIER
 */

import fr.le_campus_numerique.stephanechevrier.tictactoe.controleur.TextesConsole;
import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Cell;
import java.util.HashMap;
import java.util.Map;

public class Console {

    /**
     * initialisation objet TextesConsole
     */
    TextesConsole textesConsole = new TextesConsole();

    /**
     * Initialisation des constantes de représentation du damier
     */
    static final String
            COIN_HG = "┌",
            COIN_HD = "┐",
            COIN_BG = "└",
            COIN_BD = "┘",
            INTER_H = "┬",
            INTER_G = "├",
            INTER_D = "┤",
            INTER_B = "┴",
            INTER = "┼",
            LIGNE = "─",
            COL = "│";

    /**
     * initialisation de la MAP
     */
    private final Map<Integer, String> index = new HashMap<>();

    /**
     * Fonction de création de la ligne index
     * @param size
     * @param col
     * @return String : représentation de la ligne des index de l'axe X
     */
    private String setIndex(int size, String col) {

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

    /**
     * Fonction de création des lignes intermédiaires du damier
     * @param coin1
     * @param inter
     * @param coin2
     * @param size
     * @return String : ligne intermédiaire du damier
     */
    private String setMotifs(String coin1, String inter, String coin2, int size) {

        // initialisation de la variable de sortie
        String sortie = textesConsole.CODE_DEFAUT+coin1;

        // boucle sur le nombre de cases -1 du damier
        for (int i = 0; i < size; i++) {
            sortie += LIGNE.repeat(
                    3) + inter;
        }
        // Dernière case
        sortie += LIGNE.repeat(3) + coin2;

        // Retour de la ligne créée
        return sortie;
    }

    /**
     * Méthode d'affichage du damier
     * @param cell
     */
    public void display(Cell[][] cell) {

        // initialisation des variables locales
        int size = cell.length;
        String LineIndex = setIndex(size, COL);

        // création des 3 types de lignes d'affichage
        String LineUp = setMotifs(COIN_HG, INTER_H, COIN_HD,size);
        String LineDown = setMotifs(COIN_BG, INTER_B, COIN_BD,size);
        String LineMid = setMotifs(INTER_G, INTER, INTER_D, size);

        // Affichage de l'index de l'axe des x
        afficherEcran(LineUp,TextesConsole.NO_INDEX, TextesConsole.SAUT);
        afficherEcran(LineIndex, TextesConsole.NO_INDEX, TextesConsole.SAUT);
        afficherEcran(LineMid, TextesConsole.NO_INDEX, TextesConsole.SAUT);

        // boucle de balayage de lignes
        for (int i = 0; i <= size-1; i++) {

            // affichage du n° de ligne
            afficherEcran(Console.COL + " " + index.get(i) + " ", TextesConsole.NO_INDEX, TextesConsole.NO_SAUT);

            // boucle de balayage des colonnes
            for (int j = 0; j <= size-1; j++) {
                afficherEcran(cell[i][j].getRepresentation(COL), TextesConsole.NO_INDEX, TextesConsole.NO_SAUT);
            }
            // Affichage de la bordure de droite et de la ligne de séparation des lignes
            afficherEcran(Console.COL, TextesConsole.NO_INDEX, TextesConsole.SAUT);
            if (i < size-1) {
                afficherEcran(LineMid, TextesConsole.NO_INDEX, TextesConsole.SAUT);
            }
        }
        // affichage de la dernière ligne du damier
        afficherEcran(LineDown, TextesConsole.NO_INDEX, TextesConsole.SAUT);
        sautLigne();
    }

    /**
     * Méthode pour effacer la console
     */
    public void displayEffacer() {

        // boucle de 10 lignes vides avec couleur par défaut
        afficherEcran("\n".repeat(10), TextesConsole.NO_INDEX, TextesConsole.NO_SAUT);
    }

    /**
     *     méthode d'affichage d'un message
     *     en incluant la couleur du joueur quand le nom est affiché
     *     en incluant le n° du joueur quand il est indiqué
     * @param texte
     * @param i
     * @param saut
     */
    public void afficherEcran(String texte, int i, boolean saut) {

        // Conversion de l'index i en chaine
        String convertTexte = new String();
        convertTexte = convertTexte.valueOf(i);

        // Remplacement des codes @x par leur séquence
        texte = texte.replaceAll(textesConsole.CODE_COULEUR, TextesConsole.CASE_COULEUR[i]);
        texte = texte.replaceAll(textesConsole.CODE_DEFAUT, TextesConsole.CASE_COULEUR[0]);
        texte = texte.replaceAll(textesConsole.CODE_ERREUR, TextesConsole.COULEUR_DEF.get("rouge"));
        texte = texte.replaceAll(textesConsole.CODE_INDEX,convertTexte);

        // Affichage du texte
        if (saut) {
            System.out.println(texte);
        } else {
            System.out.print(texte);
        }
    }

    /**
     * méthode d'affichage d'un saut de ligne vide
     */
    public void sautLigne() {
        afficherEcran("",TextesConsole.NO_INDEX, TextesConsole.SAUT);
    }

}