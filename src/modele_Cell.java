public class modele_Cell {


    // création variable d'instance de Cell
    public modele_Player joueur;

    // constructeur de Cell()
    public modele_Cell() {
        this.joueur = new HumanPlayer(" ", modele_Player.caseValue[0], modele_Player.representationJoueur[0], modele_Player.caseCouleur[0]);
    }

    // représentation de la cellule à partir des repré+entations de joueurs : | XouOou""
    public String getRepresentation() {
        return Viewer.col + " " + joueur.couleur + joueur.representation + modele_Player.caseCouleur[0] + " ";
    }

    // récupération de la valeur de la cellule en fonction du joueur de la cellule
    public int getValue() {
        return joueur.value;
    }
}