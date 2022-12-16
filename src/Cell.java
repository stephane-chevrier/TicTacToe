public class Cell {
    // Initialisation des constantes propres à la Class cell
    static final String bordureSeparator = "|";
    static final String carSeparator = "-";
    static final String lineSeparator = carSeparator+bordureSeparator+carSeparator.repeat(12);
    static final String LineIndex = carSeparator+bordureSeparator+carSeparator+"0" +
                                    carSeparator+bordureSeparator+carSeparator+"1" +
                                    carSeparator+bordureSeparator+carSeparator+"2" +
                                    carSeparator+bordureSeparator+carSeparator+">X";

    // création variable d'instance de Cell
    public Player joueur;

    // constructeur de Cell()
    public Cell() {
        this.joueur = new HumanPlayer(" ", Player.caseValue[0], Player.representationJoueur[0]);
    }

    // représentation de la cellule à partir des représentations de joueurs : | XouOou""
    public String getRepresentation() {
        return bordureSeparator + " " + joueur.representation + " ";
    }
    // récupération de la valeur de la cellule en fonction du joueur de la cellule
    public int getValue() {
        return joueur.value;
    }
}