public class Cell {
    // Création variable d'instance .value
    public int value;

    public Cell(){}

    // représentation de le l'objet en fonction de sa valeur
    public String getRepresentation() {
        if (this.value == 0) {
            return "|   ";
        }
        if (this.value == TicTacToe.joueur1Value) {
            return "| X ";
        }
        if (this.value == joueur2Value) {
            return "| O ";
        }
        return "";
    }
}