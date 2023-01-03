package fr.le_campus_numerique.stephanechevrier.tictactoe.modele;

/*
Nom             HumanPlayer, extends Player
Description     Modèle jeu TicTacToe (MVC)
                Joueurs Humain
@version        v1.0
Date            19 décembre 2022
@author         Stéphane CHEVRIER
*/

import fr.le_campus_numerique.stephanechevrier.tictactoe.controleur.TextesConsole;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Console;
import fr.le_campus_numerique.stephanechevrier.tictactoe.viewer.Input;
import java.util.ArrayList;

public class HumanPlayer extends Player {

    /* constructeur de la Class HumanPlayer */
    public HumanPlayer(String name, int value, String representation, String couleur, int indexCouleur, int size) {
        super(name, value, representation, couleur, indexCouleur, size);
    }

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
    protected boolean verifFormatSaisie(String saisieVal, Console console) {
        if (saisieVal.matches(texteRegexSaisie)) {
            return true;
        } else {
            // message format saisie incorrect
            console.afficherEcran(TextesConsole.messageSaisieInvalide, 0, true);
            return false;
        }
    }

    private String convert(String saisie) {
        String conversion = new String();
        for (int i=10; i<=size; i++) {
            saisie = saisie.replaceAll(Character.toString(55+i),conversion.valueOf(i));
        }
        return saisie;
    }

    /* méthode de sortie du programme si saisie exit */                                 // MIEUX DANS LE CONTROLEUR
    public void checkExitProg(String saisie, Console console) {
        if (saisie.equalsIgnoreCase("exit")) {
            console.afficherEcran(TextesConsole.messageSortie,0,true);
            System.exit(0);
        }
    }

    /* Fonction de saisie de coordonnées + vérification + renvoie les coordonnées
    @return ArrayList<Integer> : {Y,X}
    */
    @Override
    public ArrayList<Integer> getMoveFromPlayer(int size, int index, Console console, TextesConsole textesConsole, Input input) {

        // Initialisation des variables locales
        ArrayList<Integer> retour = new ArrayList<>(2);
        boolean saisieNonOk = true;
        int multiplicateur;

        // Boucle faite tant que la saisie n'est pas correcte
        do {

            // saisie du coup avec message
            String saisie = input.getString(textesConsole.messageJoueur + this.name + textesConsole.messageSaisissezCoup,index);

            // sortie du programme si saisie exit
            checkExitProg(saisie, console);

            // vérifie que la saisie contient X.Y
            if (verifFormatSaisie(saisie,console)) {

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