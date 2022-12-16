public class Cell {
    // Initialisation des constantes propres à la Class cell
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

    static final String LineUp = coinHG + ligne.repeat(3) + interH
                                + ligne.repeat(3) + interH
                                + ligne.repeat(3) + interH
                                + ligne.repeat(3) + coinHD;
    static final String LineDown = coinBG + ligne.repeat(3) + interB
                                + ligne.repeat(3) + interB
                                + ligne.repeat(3) + interB
                                + ligne.repeat(3) + coinBD;
    static final String LineMid = interG + ligne.repeat(3) + inter
                                + ligne.repeat(3) + inter
                                + ligne.repeat(3) + inter
                                + ligne.repeat(3) + interD;
    static final String LineIndex = col + "   "
                                  + col + " 0 "
                                  + col + " 1 "
                                  + col + " 2 " + col;

// version avec - & |
//    static final String bordureSeparator = "\";
//    static final String carSeparator = "-";
//    static final String lineSeparator = carSeparator+bordureSeparator+carSeparator.repeat(12);
//    static final String LineIndex = carSeparator+bordureSeparator+carSeparator+"0" +
//                                    carSeparator+bordureSeparator+carSeparator+"1" +
//                                    carSeparator+bordureSeparator+carSeparator+"2" +
//                                    carSeparator+bordureSeparator+carSeparator+">X";

    // création variable d'instance de Cell
    public Player joueur;

    // constructeur de Cell()
    public Cell() {
        this.joueur = new HumanPlayer(" ", Player.caseValue[0], Player.representationJoueur[0], Player.caseCouleur[0]);
    }

    // représentation de la cellule à partir des représentations de joueurs : | XouOou""
    public String getRepresentation() {
        return col + " " + joueur.representation + " ";
    }
    // récupération de la valeur de la cellule en fonction du joueur de la cellule
    public int getValue() {
        return joueur.value;
    }
}