package battleship;

public class BattleField {

    private char[][] battleField = new char[10][10];

    public BattleField() {
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                battleField[i][k] = '~';
            }
        }
    }
    public char[][] getBattleField() {
        return battleField;
    }

    public void setBattleField(char[][] battleField) {
        this.battleField = battleField;
    }

    public void printField(boolean isFog) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char letter = 'A';
        for (char[] i : battleField) {
            System.out.print(letter++);
            for (char c : i) {
                System.out.print(isFog == true ? " ~" : " " + c);
            }
            System.out.println();
        }
    }
}
