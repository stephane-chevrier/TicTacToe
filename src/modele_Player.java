import java.util.ArrayList;
import java.util.Map;

public abstract class modele_Player {

    // Définition des séquences pour chaque couleur souhaitée
    private static final Map<String,String> couleurDef = Map.of(
            "rouge", "\u001B[31m",
            "bleue", "\u001B[34m",
            "defaut", "\u001B[0m"
    );

    // définition des constantes pour les cases : couleur, valeur, représentation
    public static final String[] caseCouleur = {couleurDef.get("defaut"), couleurDef.get("bleue"), couleurDef.get("rouge")};
    public static final int[] caseValue = {0,1,-1}; // tableau de valeurs des cases vides, joueur n°1 (int>=1), joueur n°2 (int<=-1)
    public static final String[] representationJoueur = {" ","X","O"}; // tableau des représentations des cases vide, du joueur n°1, du joueur n°2

    // Initialisation variables d'instance
    public int value;
    public String name;
    public String representation;
    public String couleur;

    // constructeur de la Class Player
    public modele_Player(String name, int value, String representation, String couleur) {
        this.name = name;
        this.value = value;
        this.representation = representation;
        this.couleur = couleur;
    }

    // Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    abstract ArrayList<Integer> getMoveFromPlayer (int size);


    // sortie du prgramme si saisie exit
    public void checkExitProg(String saisie) {
        if (saisie.toLowerCase().equals("exit")) {
            System.out.println("Fin du programme demandé par le joueur.");
            System.exit(0);
        }
    }
}
