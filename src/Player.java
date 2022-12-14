public class Player {

    // Initialisation constantes static
    static final int caseVide = 0;
    static final int caseJoueur1 = 1; // mettre un entier >= à 1
    static final int caseJoueur2 = -1; // mettre un entier <= à -1

    // Initialisation variables d'instance
    public int value;
    public String name;

    // constructeur de la Class Player
    public Player(String name, int value) {
        this.name = name;
        this.value = value;
    }

}
