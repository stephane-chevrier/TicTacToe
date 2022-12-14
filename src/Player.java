public class Player {

    // Initialisation constantes static
    static final int caseVide = 0;
    static final int caseJoueur1 = 1; // mettre un entier >= à 1
    static final int caseJoueur2 = -1; // mettre un entier <= à -1
    static final String representationVide = " ";       // voir Enum pour déclarer cela
    static final String representationJoueur1 = "X";
    static final String representationJoueur2 = "O";

    // Initialisation variables d'instance
    public int value;
    public String name;
    public String representation;

    // constructeur de la Class Player
    public Player(String name, int value, String representation) {
        this.name = name;
        this.value = value;
        this.representation = representation;
    }

}
