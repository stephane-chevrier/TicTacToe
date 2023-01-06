package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

import fr.le_campus_numerique.stephanechevrier.tictactoe.modele.Player;

public interface Persistence {

    public void sauvegarder(GameControleur gameControleur, Player player);
}
