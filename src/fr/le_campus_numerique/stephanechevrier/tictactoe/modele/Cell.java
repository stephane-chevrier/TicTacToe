package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

/*
Nom             Cell
Description     Modèle jeu TicTacToe (MVC)
                Cellules du Damier
@version        v1.0
Date            12 décembre 2022
@author         Stéphane CHEVRIER
*/

public class Cell {

    // création variable d'instance de Cell
    public Player joueur;

    /* constructeur de Cell() */
    public Cell() {
        this.joueur = new HumanPlayer(" ", Player.caseValue[0], Player.representationJoueur[0], Player.caseCouleur[0], 0, 0);
    }

    /* Fonction de représentation de la cellule à partir des représentations de joueurs
    @return String : "| X " ou "| O " ou "|   "
    */
    public String getRepresentation(String col) {
        return col + " " + joueur.couleur + joueur.representation + Player.caseCouleur[0] + " ";
    }

    /* Fonction de récupération de la valeur de la cellule en fonction du joueur de la cellule
    @return int : -1 ou 1
    */
    public int getValue() {
        return joueur.value;
    }
}