package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

/**
 * Nom             Player, Class abstract
 * Description     Modèle jeu TicTacToe (MVC)
 *                 Joueurs
 * @version v1.0
 * Date            12 décembre 2022
 * @author Stéphane CHEVRIER
 */

// concerne que des constantes --> acceptable
import fr.le_campus_numerique.stephanechevrier.tictactoe.controleur.TextesConsole;
// concerne HumanPlayer qui utilise l'écran et le clavier
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.*;


import java.util.ArrayList;

public abstract class Player {

    /**
     * initialisation des constantes
     */
    public static final String nomJoueurAleatoire = "random";

    /**
     * Initialisation variables d'instance
     */
    public int indexCouleur;
    public int value;
    public String name;
    public String representation;
    public String couleur;
    public int size;

    /**
     * constructeur de la Class Player
     * @param name
     * @param value
     * @param representation
     * @param couleur
     * @param indexCouleur
     * @param size
     */
    public Player(String name, int value, String representation, String couleur, int indexCouleur, int size) {
        this.indexCouleur = indexCouleur;
        this.name = name;
        this.value = value;
        this.representation = representation;
        this.couleur = couleur;
        this.size = size;
    }

    /**
     * Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
     * @param size
     * @param index
     * @param console
     * @param textesConsole
     * @param input
     * @return ArrayList<Integer> : {Y,X}
     */
    public abstract ArrayList<Integer> getMoveFromPlayer (int size, int index, Console console, TextesConsole textesConsole, Input input);
}
