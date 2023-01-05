package fr.le_campus_numerique.stephanechevrier.tictactoe.controleur;

/**
 * Nom:            TextesEcran
 * Description :   Controleur jeu TicTacToe (MVC)
 *                 Contient tous les messages affichés à la console
 * @version 1.0
 * Date :          3 janvier 2023
 * @author Stéphane CHEVRIER
 */

import java.util.Map;

public class TextesConsole {

    /**
     * définition des constantes pour les cases : couleur, valeur, représentation
     */
    public static final Map<String,String> COULEUR_DEF = Map.of(
            "rouge", "\u001B[31m",
            "bleue", "\u001B[34m",
            "defaut", "\u001B[0m",
            "jaune", "\u001B[33m"
    );

    /**
     * tableau des couleurs de chaque joueur : joueur_vide : couleur par défaut, joueur1, joueur2
     */
    public static final String[] CASE_COULEUR = {COULEUR_DEF.get("defaut"), COULEUR_DEF.get("bleue"), COULEUR_DEF.get("jaune")};

    /**
     * tableau de valeurs des cases 0:vides, 1:joueur n°1 (int>=1), 2:joueur n°2 (int<=-1)
     */
    public static final int[] CASE_VALUE = {0,1,-1};

    /**
     * tableau des représentations des cases vide, du joueur n°1, du joueur n°2
     */
    public static final String[] REPRESENTATION_JOUEUR = {" ","X","O"};

    /**
     * Initialisation des messages affichés à l'écran
     * @C:insère la couleur du joueur,  @D:insère la couleur par défaut, @i:insère l'index, @E insère la couleur des messages d'erreur
     */
    public static final String
            MESSAGE_SAISIE_NOM1 = "@DSaisissez le nom du @Cjoueur n°@i@D (Random pour joueur aléatoire) : ",
            MESSAGE_COUP_JOUE1 = "@Dcoup ",
            MESSAGE_COUP_JOUE2 = " joué par @C",
            MESSAGE_PARTIE_TERMINEE = "@Dpartie terminée",
            MESSAGE_LE_JOUEUR = ", le joueur @C",
            MESSAGE_A_GAGNE = "@D a gagné !!!",
            MESSAGE_EGALITE = ", égalité.",
            MESSAGE_JOUEUR = "@DJoueur @C",
            MESSAGE_SAISIE_COUP = "@D ,saisissez votre choix sous la forme Y.X (exit pour sortir du jeu): ",
            MESSAGE_CASE = "@D@ECase ",
            MESSAGE_CASE_OCCUPEE = " déjà occupée, recommencez.",
            MESSAGE_SAISIE_INVALIDE = "@D@Evotre saisie n'est pas correcte, recommencez.@D",
            MESSAGE_SORTIE = "@@EFin du programme demandé par le joueur.@D",
            MESSAGE_BIENVENUE = "Bienvenue sur ma plateforme de jeux, ",
            MESSAGE_LISTE_DES_JEUX = "liste des jeux possibles :",
            MESSAGE_FAITES_VOTRE_CHOIX = "Choisissez votre jeu (quit pour quitter la plateforme) : ",
            MESSAGE_FIN = "Merci d'être venu, à bientôt !!!";
}
