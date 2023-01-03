package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/*
Nom:            TextesEcran
Description :   Controleur jeu TicTacToe (MVC)
                Contient tous les messages affichés à la console
@version        1.0
Date :          3 janvier 2023
@author         Stéphane CHEVRIER
 */

import java.util.Map;

public class TextesConsole {

    // définition des constantes pour les cases : couleur, valeur, représentation
    public static final Map<String,String> couleurDef = Map.of(
            "rouge", "\u001B[31m",
            "bleue", "\u001B[34m",
            "defaut", "\u001B[0m",
            "jaune", "\u001B[33m"
    );

    public static final String[] caseCouleur = {couleurDef.get("defaut"), couleurDef.get("bleue"), couleurDef.get("jaune")};
    public static final int[] caseValue = {0,1,-1}; // tableau de valeurs des cases 0:vides, 1:joueur n°1 (int>=1), 2:joueur n°2 (int<=-1)
    public static final String[] representationJoueur = {" ","X","O"}; // tableau des représentations des cases vide, du joueur n°1, du joueur n°2


    /*
    Initialisation des messages affichés à l'écran
    @C:insère la couleur du joueur,  @D:insère la couleur par défaut, @i:insère l'index, @E insère la couleur des messages d'erreur
     */
    protected final String messageSaisieNom1 = "@DSaisissez le nom du @Cjoueur n°@i@D (Random pour joueur aléatoire) : ";
    protected final String messageCoupJoue1 = "@Dcoup ";
    protected final String messageCoupJoue2 = " joué par @C";

    protected final String messagePartieTerminee = "@Dpartie terminée";
    protected final String messageleJoueur = ", le joueur @C";
    protected final String messageAGagne = "@D a gagné !!!";
    protected final String messageEgalite = ", égalité.";

    public final String messageJoueur = "@DJoueur @C";
    public final String messageSaisissezCoup = "@D ,saisissez votre choix sous la forme Y.X (exit pour sortir du jeu): ";
    protected final String messageCase = "@D@ECase ";
    protected final String messageCaseOccupee = " déjà occupée, recommencez.";
    public static String messageSaisieInvalide = "@D@Evotre saisie n'est pas correcte, recommencez.@D";
//    public static String messageXYinvalides1 = "@D@Ey et x doivent être compris entre 0 et ";
//    public static String messageXYinvalides2 = ", recommencez.@D";

    public static String messageSortie = "@@EFin du programme demandé par le joueur.@D";

}
