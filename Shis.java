package battleship;

import java.util.Scanner;

public class Ships {

   // public int lengthOfAllShips = 17;
    private static final Scanner scanner = new Scanner(System.in);
    private final String shipsName;
    private int length;
    private int firstCharCoord;
    private int firstIntCoord;
    private int secondCharCoord;
    private int secondIntCoord;

    public String getShipsName() {
        return shipsName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFirstCharCoord() {
        return firstCharCoord;
    }

    public int getFirstIntCoord() {
        return firstIntCoord;
    }

    public int getSecondCharCoord() {
        return secondCharCoord;
    }

    public int getSecondIntCoord() {
        return secondIntCoord;
    }

    public Ships(String shipsName, int length) {
        this.shipsName = shipsName;
        this.length = length;
    }
    private void takeCoord() {
        String[] s = scanner.nextLine().split(" ");
        firstCharCoord = s[0].charAt(0) - 65;
        firstIntCoord = Integer.parseInt(s[0].substring(1) + "") - 1;
        secondCharCoord = s[1].charAt(0) - 65;
        secondIntCoord = Integer.parseInt(s[1].substring(1) + "") - 1;
    }

    private void normalizeCoord() {
        if (secondCharCoord < firstCharCoord) {
            firstCharCoord += secondCharCoord;
            secondCharCoord = firstCharCoord - secondCharCoord;
            firstCharCoord = firstCharCoord - secondCharCoord;
        }
        if (secondIntCoord < firstIntCoord) {
            firstIntCoord += secondIntCoord;
            secondIntCoord = firstIntCoord - secondIntCoord;
            firstIntCoord = firstIntCoord - secondIntCoord;
        }
    }

    private boolean isCorrectCoord(Players player) {
        if (this.length != secondCharCoord - firstCharCoord + 1 && this.length != secondIntCoord - firstIntCoord + 1) {
            System.out.printf("Error! Wrong length of %s! Try again:\n", this.shipsName);
            return false;
        }
        if (secondCharCoord - firstCharCoord != 0
                && secondIntCoord - firstIntCoord != 0) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        for (int i = firstCharCoord - 1; i <= secondCharCoord + 1 && i >= 0 && i < 10; i++) {
            for (int k = firstIntCoord - 1; k <= secondIntCoord + 1 && k < 10 && k >= 0; k++) {
                if (player.getBattleField().getBattleField()[i][k] == 'O') {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return false;
                }
            }
        }
        return true;
    }

    public void acceptCoord(Players player) {
        while (true) {
            takeCoord();
            normalizeCoord();
            if (isCorrectCoord(player)) {
                break;
            }
        }
        char[][] temp = player.getBattleField().getBattleField();
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                if (i >= firstCharCoord && i <= secondCharCoord && k >= firstIntCoord && k <= secondIntCoord) {
                    temp[i][k] = 'O';
                }
            }
        }
        player.getBattleField().setBattleField(temp);
    }
}
