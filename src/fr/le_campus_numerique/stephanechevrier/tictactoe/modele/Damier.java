package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

/*
Nom             Damier
Description     Modele jeu TicTacToe (MVC)
                Plateau du jeu
@version        v1.0
Date            12 décembre 2022
@author         Stéphane CHEVRIER
*/

import java.util.ArrayList;

public class Damier {

    // initialisation du damier (tableau 2 dimensions de l'objet cellule)
    private final Cell[][] plateau;

    /* Constructeur de la Class Damier */
    public Damier(int size) {
        this.plateau = initialiserDamier(size);
    }

    /* Fonction de renvoie du damier */
    public Cell[][] getPlateau() {
        return plateau;
    }

    /* Fonction d'initialisation du damier
    @return Cell[][] : Cell[size][size]
    */
    private Cell[][] initialiserDamier(int size) {
        Cell[][] cells = new Cell[size + 1][size + 1];
        // initialisation du damier
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= size; j++) {
                cells[i][j] = new Cell();
            }
        }
        // retour du damier
        return cells;
    }

    /* méthode d'entrée du coup joué dans le damier */
    public void setOwner(Player joueur, ArrayList<Integer> coord) {
        plateau[coord.get(0)][coord.get(1)].joueur = joueur;
    }

    /* Fonction de vérification que la case n’est pas occupée
    @return boolean : true si la case est libre
    */
    public boolean verifCaseLibre(ArrayList<Integer> coup) {
        return (plateau[coup.get(0)][coup.get(1)].getValue() == 0);
    }
}

