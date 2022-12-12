public class Cell {
    // Création variable d'instance .value
    int value;
    // représentation de le l'objet en fonction de sa valeur
    public String getRepresentation() {
        if (this.value == 0) {
            return "|   ";
        }
        if (this.value == -1) {
            return "| O ";
        }
        if (this.value == 1) {
            return "| X ";
        }
        return "";
    }
}