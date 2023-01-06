package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/**
 * Nom             Gomoku
 * Description     Contrôleur jeu TicTacToe (MVC)
 * @version v1.0
 * Date            4 janvier 2023
 * @author Stéphane CHEVRIER
 */

import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.*;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Console;

public class Gomoku extends GameControleur {

    /**
     * initialisation variables locales
     */


    public Gomoku() {
        this.size = 14;      // =n défini un plateau de n+1 * n+1 cellules pour le jeu TicTacToe
                             // ATTENTION n doit être supérieur ou égal à 4
        this.nbreAlignements = (size +1)*6;  // on aura besoin de 6 blocs : 1 de lignes, 1 de colonnes, 4 de diagonales
        this.alignementGagnant = 5;
        this.textesConsole = new TextesConsole();
        this.console = new Console();
        this.damier = new Damier(this.size);                 // this.size permet de spécifier size de l'objet Gomoku
        this.gameJoueurs = new GameJoueurs(this.damier);     // Après avoir instancié le damier, sinon Pb !
    }

    /**
     *     méthode de calcul : des sommes de chaque alignement,
     *                         du nombre de coups joués
     * @return ArrayList<Integer>(3) : Somme de chaque alignement, nombre de coups joués
     */
    @Override
    public String[] calculerAlignements() {

        // RAZ du tableau de calcul des sommes et du nombre de coups joués
        int nombreCoupsJoues = 0;
        String[] calcul = new String[nbreAlignements];
        int nombreDiag = (size + 1 - alignementGagnant);

        // Copie locale du plateau
        Cell[][] plateau = damier.getPlateau();
        for (int i = 0; i <= calcul.length - 1; i++) {
            calcul[i] = "";
        }

        // Double boucle de calcul des sommes (size lignes, size colonnes, 18 diagonales)
        for (int i = 0; i <= size; i++) {

            // boucle de calcul des diagonales
            for (int j = -nombreDiag; j <= +nombreDiag; j++) {

                // somme de la diagonale 0.0+1.1+...+size.size dans index size*2-size*2+1-....-size*2+size
                if ((i + j >= 0) && (i + j <= size)) {
                    calcul[(size + 1) * 2 + nombreDiag + j] += plateau[i + j][i].getRepresentationBrut();
                }
                // somme de la diagonale 0.size-1.size-1-....-size.0 dans index size*3-size*3+1-....-size*3+size
                if (((i + j) >= 0) && (i + j <= size)) {
                    calcul[(size + 1) * 4 + nombreDiag + j] += plateau[i + j][size - i].getRepresentationBrut();
                }
            }
            // boucle de calcul des lignes et des colonnes
            for (int j = 0; j <= size; j++) {

                // somme des lignes 0-1-....-size dans index 0-1-.....-size
                calcul[j] += plateau[i][j].getRepresentationBrut();

                // somme des colonnes 0-1-....-size dans index size+1-size+2-.....-size+size+1
                calcul[(size + 1) + 1 + j] += plateau[j][i].getRepresentationBrut();

                // Calcul du nombre de coups joués
                if (!plateau[i][j].getRepresentationBrut().equals(" ")) {
                    nombreCoupsJoues++;
                }
            }
        }

        // retour du nombre de coups joués
        calcul[nbreAlignements - 1] = calcul[(size + 1) * 3 - 1].valueOf(nombreCoupsJoues);

        // fin de la fonction
        return calcul;
    }
}
