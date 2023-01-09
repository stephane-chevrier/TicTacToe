package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/**
 * Nom             Persistence
 * Description     Contrôleur jeu TicTacToe (MVC)
 *                 Interface des types de sauvegarde (1 seule à ce jour)
 * @version v1.0
 * Date            6 janvier 2023
 * @author Stéphane CHEVRIER
 */

public interface Persistence {

    /**
     * Méthode de sauvegarde d'un objet dans un fichier
     * @param objet
     * @param nomFichier
     */
    void sauvegarder(Object objet, String nomFichier);

    /**
     * Méthode de restauration d'un objet depuis un fichier
     * @param nomFichier
     * @return
     */
    Object restaurer(String nomFichier);
}
