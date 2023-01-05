package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

/**
 * Nom             Cell
 * Description     Modèle jeu TicTacToe (MVC)
 *                 Cellules du Damier
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */

import fr.le_campus_numerique.stephanechevrier.tictactoe.controleur.TextesConsole;

public class Cell {

    /**
     * création variable d'instance de Cell
     */
    protected Player joueur;

    /**
     * constructeur de Cell()
     */
    protected Cell() {
        // par défaut les cellules contiennent un joueur vide (joueur n°0)
        this.joueur = new HumanPlayer("", TextesConsole.CASE_VALUE[0], TextesConsole.REPRESENTATION_JOUEUR[0], TextesConsole.CASE_COULEUR[0], 0, 0);
    }

    /**
     * Fonction de représentation de la cellule à partir des représentations de joueurs
     * @param col
     * @return String : "| X " ou "| O " ou "|   "  avec la sequence couleur du joueur
     */
    public String getRepresentation(String col) {
        return col + " " + joueur.couleur + joueur.representation + TextesConsole.CASE_COULEUR[0] + " ";
    }

    /**
     * Fonction de représentation de la cellule à partir des représentations de joueurs
     * @return String : X ou O ou ""
     */
    public String getRepresentationBrut() {
        return joueur.representation;
    }

    /**
     * Fonction de récupération de la valeur de la cellule en fonction du joueur de la cellule
     * @return int : -1 ou 1
     */
    public int getValue() {
        return joueur.value;
    }
}