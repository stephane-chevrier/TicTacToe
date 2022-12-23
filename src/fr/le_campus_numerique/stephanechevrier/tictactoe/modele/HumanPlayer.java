package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Viewer;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Input;
import java.util.ArrayList;


public class HumanPlayer extends Player {

    // constructeur de la Class HumanPlayer
    public HumanPlayer(String name, int value, String representation, String couleur, int indexCouleur) {
        super(name, value, representation, couleur, indexCouleur);
    }
    private Viewer viewer = new Viewer();
    private Input input = new Input();

    // vérifie que la saisie contient X.Y
    protected boolean verifFormatSaisie(String saisieVal) {
        if (saisieVal.matches("[0-9].[0-9]")) {
            return true;
        } else {
            // message format saisie incorrect
            viewer.afficherEcran("votre saisie ne correspond pas au format demandé, recommencez.", 0, true);
            return false;
        }
    }

    // vérifie que les coordonnées saisies sont entre 0 et size
    public boolean verifValeurSaisie(int i, int j, int size) {
        if (i >= 0 && i <= size && j >= 0 && j <= size) {
            return true;
        } else {
            // message valeurs saisies incorrectes
            viewer.afficherEcran("y et x doivent être compris entre 0 et " + size + ", recommencez.", 0 , true);
            return false;
        }
    }

    // Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    @Override
    public ArrayList<Integer> getMoveFromPlayer(int size, int index) {

        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<Integer>(2);
        boolean saisieNonOk = true;

        // Boucle faite tant que la saisie n'est pas correcte
        do {

            // saisie du coup avec message
            String saisie = input.getString(viewer.messageJoueur + this.name + viewer.messageSaisissezCoup,index);

            // sortie du programme si saisie exit
            checkExitProg(saisie);

            // vérifie que la saisie contient X.Y
            if (verifFormatSaisie(saisie)) {
                double saisieDbl = Double.valueOf(saisie);
                int xx = (int) Math.floor(saisieDbl);
                int yy = (int) Math.round((saisieDbl - xx) * 10);

                // vérifie que les coordonnées saisies sont entre 0 et size
                if (verifValeurSaisie(xx, yy, size)) {
                    retour.add(xx);
                    retour.add(yy);
                    saisieNonOk = false;
                }
            }

        // fin boucle
        } while (saisieNonOk) ;

        // retour
        return retour;
    }


}
