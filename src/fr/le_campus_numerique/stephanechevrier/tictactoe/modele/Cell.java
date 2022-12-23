package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

public class Cell {

    // création variable d'instance de Cell
    public Player joueur;

    // constructeur de Cell()
    public Cell() {
        this.joueur = new HumanPlayer(" ", Player.caseValue[0], Player.representationJoueur[0], Player.caseCouleur[0], 0);
    }

    // représentation de la cellule à partir des repré+entations de joueurs : | XouOou""
    public String getRepresentation(String col) {
        return col + " " + joueur.couleur + joueur.representation + Player.caseCouleur[0] + " ";
    }

    // récupération de la valeur de la cellule en fonction du joueur de la cellule
    public int getValue() {
        return joueur.value;
    }
}