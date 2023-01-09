package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

import java.io.*;

/**
 * Nom             GameSerialization
 * Description     Contrôleur jeu TicTacToe (MVC)
 *                 Sauvegarde et restauration sur fichier du jeu en-cours
 * @version v1.0
 * Date            9 janvier 2023
 * @author Stéphane CHEVRIER
 */

public class GameSerialization implements Persistence, Serializable {

    /**
     * initialisation des constantes locales
     */
    public static final String fichierSauvegardeGameControleur = "C:\\Users\\stephane.chevrier\\Downloads\\TicTacToeJeu.ser";
    public static final String fichierSauvegardeActivePlayer = "C:\\Users\\stephane.chevrier\\Downloads\\TicTacToeActivePlayer.ser";

    /**
     * constructeur
     */
    public GameSerialization() {
    }

    /**
     * Méthode de sauvegarde d'un objet dans un fichier
     * @param objet
     * @param nomFichier
     */
    @Override
    public void sauvegarder(Object objet, String nomFichier) {

        // initialisation des objets locaux
        FileOutputStream fichierSauvegarde = null;
        ObjectOutputStream sortie = null;

        // definition du fichier
        try {
            fichierSauvegarde = new FileOutputStream(nomFichier);
        } catch (IOException i) {
            i.printStackTrace();
        }

        // création du flux d'écriture
        try {
            sortie = new ObjectOutputStream(fichierSauvegarde);
        } catch (IOException i) {
            i.printStackTrace();
        }

        // écriture de l'objet, fermeture du fichier et du flux
        try {
           sortie.writeObject(objet);
           sortie.close();
           fichierSauvegarde.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Méthode de restauration d'un objet depuis un fichier
     * @param nomFichier
     * @return
     */
    @Override
    public Object restaurer(String nomFichier) {

        // initialisation des objets locaux
        FileInputStream fichierSauvegarde = null;
        ObjectInputStream entree = null;
        Object objet = null;

        // selection du fichier
        try {
            fichierSauvegarde = new FileInputStream(nomFichier);
        } catch (IOException i) {
            i.printStackTrace();
        }

        // création du flux de lecture
        try {
            entree = new ObjectInputStream(fichierSauvegarde);
        } catch (IOException i) {
            i.printStackTrace();
        }

        // écriture de l'objet, fermeture du fichier et du flux
        try {
            objet = entree.readObject();
            entree.close();
            fichierSauvegarde.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // retour de l'objet lu
        return objet;
    }

}
