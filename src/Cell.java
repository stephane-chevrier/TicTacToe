public class Cell {
    // Création variable d'instance .value
    static final String bordureSeparator = "|";
    static final String lineSeparator = "-|------------";
    static final String LineIndex = " |-0-|-1-|-2-|->X ";
    static final String representationVide = bordureSeparator+"   ";
    static final String representationJoueur1 = bordureSeparator+" X ";
    static final String representationJoueur2 = bordureSeparator+" O ";
    int value;
    public Cell() {
        this.value=0;
    }

    // représentation de le l'objet en fonction de sa valeur
    public String getRepresentation() {
        if (this.value == Player.caseVide ) {
            return representationVide;
        }
        if (this.value == Player.caseJoueur1) {
            return representationJoueur1;
        }
        if (this.value == Player.caseJoueur2) {
            return representationJoueur2;
        }
        return "";
    }
}