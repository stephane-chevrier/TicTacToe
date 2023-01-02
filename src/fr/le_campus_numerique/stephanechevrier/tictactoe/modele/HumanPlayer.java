package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

/*
Nom             HumanPlayer, extends Player
Description     Modèle jeu TicTacToe (MVC)
                Joueurs Humain
@version        v1.0
Date            19 décembre 2022
@author         Stéphane CHEVRIER
*/

import fr.le_campus_numerique.stephanechevrier.tictactoe.controleur.TicTacToe;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Viewer;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Input;
import java.util.ArrayList;

public class HumanPlayer extends Player {

    /* constructeur de la Class HumanPlayer */
    public HumanPlayer(String name, int value, String representation, String couleur, int indexCouleur, int size) {
        super(name, value, representation, couleur, indexCouleur, size);
    }

    /* initialisation des objets Viewer et Input */
    private Viewer viewer = new Viewer();
    private Input input = new Input();

    /* initialisation texte du format regex correspondant au plateau */
    private final String texteRegexSaisie = calculTexteRegexSaisie();

    /* Méthode de calcul du texte au format regex correspondant au plateau */
    private String calculTexteRegexSaisie() {

        // initialisation des variables locales
        String conversion = new String();

        // si size<10 regex = [0-size].[0-size]
        if (size<10) {
            conversion = conversion.valueOf(size);
        // si size >=10 regex = [0-9A-(55+size)].[0-9A-(55+size)]
        } else {
            conversion = "9A-"+Character.toString(55+size);
        }
        return "[0-"+conversion+"].[0-"+conversion+"]";
    }


    /* fonction de vérification que la saisie contient X.Y
    @return boolean : true si saisie valide
    */
    protected boolean verifFormatSaisie(String saisieVal) {
        if (saisieVal.matches(texteRegexSaisie)) {
            return true;
        } else {
            // message format saisie incorrect
            viewer.afficherEcran(viewer.messageSaisieInvalide, 0, true);
            return false;
        }
    }

    /* fonction de vérification que les coordonnées saisies sont entre 0 et size
    @return boolean : true si Y et X valides
    */
//    public boolean verifValeurSaisie(int i, int j, int size) {
//        if (i >= 0 && i <= size && j >= 0 && j <= size) {
//            return true;
//        } else {
//            // message valeurs saisies incorrectes
//            viewer.afficherEcran(viewer.messageXYinvalides1 + size + viewer.messageXYinvalides2, 0 , true);
//            return false;
//        }
//    }

    private String convert(String saisie) {
        String conversion = new String();
        for (int i=10; i<=size; i++) {
            saisie = saisie.replaceAll(Character.toString(55+i),conversion.valueOf(i));
        }
        return saisie;
    }

    /* méthode de sortie du programme si saisie exit */
    public void checkExitProg(String saisie) {
        if (saisie.equalsIgnoreCase("exit")) {
            viewer.afficherEcran(viewer.messageSortie,0,true);
            System.exit(0);
        }
    }

    /* Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    @return ArrayList<Integer> : {Y,X}
    */
    @Override
    public ArrayList<Integer> getMoveFromPlayer(int size, int index) {

        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<>(2);
        boolean saisieNonOk = true;
        int multiplicateur;

        // Boucle faite tant que la saisie n'est pas correcte
        do {

            // saisie du coup avec message
            String saisie = input.getString(viewer.messageJoueur + this.name + viewer.messageSaisissezCoup,index);

            // sortie du programme si saisie exit
            checkExitProg(saisie);

            // vérifie que la saisie contient X.Y
            if (verifFormatSaisie(saisie)) {

                // convertit éventuellement les lettres en chiffres
                saisie = convert(saisie);
                if (saisie.length()-saisie.indexOf(".")==2) {
                    multiplicateur = 1;
                } else {
                    multiplicateur = 10;
                }

                // extraction de X et Y à partir du chiffre Y.X
                double saisieDbl = Double.valueOf(saisie);
                int xx = (int) Math.floor(saisieDbl);
                int yy = (int) Math.round((saisieDbl - xx) * 10 * multiplicateur);

//                // vérifie que les coordonnées saisies sont entre 0 et size
//                if (verifValeurSaisie(xx, yy, size)) {
                    retour.add(xx);
                    retour.add(yy);
                    saisieNonOk = false;
//                }
            }

        // fin boucle
        } while (saisieNonOk) ;

        // retour
        return retour;
    }
}
