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
    public static final Map<String,String>
            COULEUR_DEF = Map.of(
                "rouge", "\u001B[31m",
                "bleue", "\u001B[34m",
                "defaut", "\u001B[0m",
                "jaune", "\u001B[33m"
    );

    /**
     * tableau des couleurs de chaque joueur : joueur_vide : couleur par défaut, joueur1, joueur2
     */
    public static final String[]
            CASE_COULEUR = {COULEUR_DEF.get("defaut"), COULEUR_DEF.get("bleue"), COULEUR_DEF.get("jaune")};

    /**
     * tableau de valeurs des cases 0:vides, 1:joueur n°1 (int>=1), 2:joueur n°2 (int<=-1)
     */
    public static final int[]
            CASE_VALUE = {0,1,-1};

    /**
     * tableau des représentations des cases vide, du joueur n°1, du joueur n°2
     */
    public static final String[]
            REPRESENTATION_JOUEUR = {" ","X","O"};

    /**
     * initialisation des constantes utilisées par afficherEcran
     */
    public static final boolean
            saut = true,
            noSaut = false;
    public static final int noIndex = 0;

    /**
     * Initialisation des messages affichés à l'écran
     * @C:insère la couleur du joueur,  @D:insère la couleur par défaut, @i:insère l'index, @E insère la couleur des messages d'erreur
     */
    public static final String
            CODE_DEFAUT = "@D",
            CODE_COULEUR = "@C",
            CODE_INDEX = "@i",
            CODE_ERREUR = "@E",
            MESSAGE_SAISIE_NOM1 = CODE_DEFAUT+"Saisissez le nom du "+CODE_COULEUR+"joueur n°"+CODE_INDEX+CODE_DEFAUT+" (Random pour joueur aléatoire) : ",
            MESSAGE_COUP_JOUE1 = CODE_DEFAUT+"coup ",
            MESSAGE_COUP_JOUE2 = " joué par "+CODE_COULEUR,
            MESSAGE_PARTIE_TERMINEE = CODE_DEFAUT+"partie terminée",
            MESSAGE_LE_JOUEUR = ", le joueur "+CODE_COULEUR,
            MESSAGE_A_GAGNE = CODE_DEFAUT+" a gagné !!!",
            MESSAGE_EGALITE = ", égalité.",
            MESSAGE_JOUEUR = CODE_DEFAUT+"Joueur "+CODE_COULEUR,
            MESSAGE_SAISIE_COUP = CODE_DEFAUT+" ,saisissez votre choix sous la forme Y.X (exit pour sortir du jeu): ",
            MESSAGE_CASE = CODE_ERREUR+"Case ",
            MESSAGE_CASE_OCCUPEE = " déjà occupée, recommencez.",
            MESSAGE_SAISIE_INVALIDE = CODE_ERREUR+"votre saisie n'est pas correcte, recommencez.@D",
            MESSAGE_SORTIE = CODE_ERREUR+"Fin du programme demandé par le joueur."+CODE_DEFAUT,
            MESSAGE_BIENVENUE = "Bienvenue sur ma plateforme de jeux, ",
            MESSAGE_LISTE_DES_JEUX = "liste des jeux possibles :",
            MESSAGE_FAITES_VOTRE_CHOIX = "Choisissez votre jeu (quit pour quitter la plateforme) : ",
            MESSAGE_FIN = "Merci d'être venu, à bientôt !!!";
}
