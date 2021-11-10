package battleship;
public class Ocean {
    private char[][] space = new char[10][10];

    public Ocean() {
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                space[i][k] = '~';
            }
        }
    }

    public void printOcean(boolean isFog) {
        System.out.println("\n  1 2 3 4 5 6 7 8 9 10");
        char letter = 'A';
        for (char[] i : space) {
            System.out.print(letter++);
            for (char k : i) {
                if (k == 'O' && isFog) {
                    System.out.print(" ~");
                } else {
                    System.out.printf(" %s", k);
                }
            }
            System.out.println();
        }
    }

    public char[][] getSpace() {
        return this.space;
    }

    public void setSpace(char[][] space){
        this.space = space;
    }
}
