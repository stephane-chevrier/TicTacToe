public class Cell {
    // Initialisation des constantes propres à la Class cell
    static final String bordureSeparator = "|";
    static final String carSeparator = "-";
    static final String lineSeparator = carSeparator+bordureSeparator+carSeparator.repeat(12);
    static final String LineIndex = bordureSeparator+carSeparator+"0" +
                                    carSeparator+bordureSeparator+carSeparator+"1"+
                                    carSeparator+bordureSeparator+carSeparator+"2"+
                                    carSeparator+bordureSeparator+carSeparator+">X";
    // Initialisation JoueurVide pour valeur par défaut des objets Cell
//    private Player joueurVide = new Player(" ", Player.caseVide, Player.representationVide);

    // création variable d'instance de Cell
    public Player joueur;

    // constructeur de Cell()
    public Cell() {
        this.joueur = new Player(" ", Player.caseVide, Player.representationVide);
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