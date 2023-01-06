package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Player;

import java.io.*;
public class GameSerialization implements Persistence {

    private String nomfichierSauvegarde = "/sauvegardeTicTacToe.ser";

    @Override
    public void sauvegarder(GameControleur gameControleur, Player player) {
        try {
            FileOutputStream fichierSauvegarde = new FileOutputStream(nomfichierSauvegarde);
            ObjectOutputStream sortie = new ObjectOutputStream(fichierSauvegarde);
            sortie.writeObject(gameControleur);
            sortie.writeObject(player);
            sortie.close();
            fichierSauvegarde.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
