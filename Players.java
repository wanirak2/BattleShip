package battleship;
import java.util.Scanner;

public class Players {

    protected String name;
    BattleField battleField = new BattleField();
    Scanner scanner = new Scanner(System.in);
    protected Ships AircraftCarrier = new Ships("Aircraft Carrier", 5);
    protected Ships BattleShip = new Ships("Battleship", 4);
    protected Ships Submarine = new Ships("Submarine", 3);
    protected Ships Cruiser = new Ships("Cruiser", 3);
    protected Ships Destroyer = new Ships("Destroyer", 2);
    protected Ships[] allShips = {AircraftCarrier, BattleShip, Submarine, Cruiser, Destroyer};
    protected int lengthOfAllShips = 17;
    public Players(String name) {
        this.name = name;
    }
    public Ships[] getAllShips() {
        return allShips;
    }

    public int getLengthOfAllShips() {
        return lengthOfAllShips;
    }

    public void setLengthOfAllShips(int lengthOfAllShips) {
        this.lengthOfAllShips = lengthOfAllShips;
    }

    public String getName() {
        return name;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void placeShips() {
        System.out.println("\n" + this.name + ", place your ships on the game field\n");
        this.battleField.printField(false);
        for (Ships ships : allShips) {
            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", ships.getShipsName(), ships.getLength());
            ships.acceptCoord(this);
            this.getBattleField().printField(false);
        }
        System.out.println("\nPress Enter and pass the move to another player...\n");
    }

    public void play(Players player) {
        player.getBattleField().printField(true);
        System.out.println("--------------------");
        this.getBattleField().printField(false);
        System.out.println("\n" + this.name + ", it's your turn:");
        this.attack(player);
    }

    protected void attack(Players players) {
        char[][] battleField = new char[10][10];
        String s;
        int attackCharCoord;
        int attackIntCoord;
        while (true) {
            s = scanner.next();
            attackIntCoord = Integer.parseInt("" + s.substring(1)) - 1;
            attackCharCoord = s.charAt(0) - 65;
            if (attackCharCoord > 9 || attackIntCoord > 9) {
                System.out.println("Error");
                continue;
            }
            break;
        }
        if (players.getBattleField().getBattleField()[attackCharCoord][attackIntCoord] == '~') {
            System.out.println("\nYou missed! Press Enter and pass the move to another player...");
            battleField = players.battleField.getBattleField();
            battleField[attackCharCoord][attackIntCoord] = 'M';
            players.battleField.setBattleField(battleField);
            return;
        } else if (players.battleField.getBattleField()[attackCharCoord][attackIntCoord] == 'O'){
            for (Ships ships : players.getAllShips()) {
                for (int i = ships.getFirstCharCoord(); i <= ships.getSecondCharCoord(); i++) {
                    for (int k = ships.getFirstIntCoord(); k <= ships.getSecondIntCoord(); k++) {
                        if (attackCharCoord == i && attackIntCoord == k) {
                            players.lengthOfAllShips--;
                            ships.setLength(ships.getLength() - 1);
                            battleField = players.battleField.getBattleField();
                            battleField[attackCharCoord][attackIntCoord] = 'X';
                            players.battleField.setBattleField(battleField);
                            if (ships.getLength() == 0) {
                                if (players.lengthOfAllShips == 0) {
                                    System.out.println("\nYou sank the last ship. You won. Congratulations!");
                                    return;
                                }
                                System.out.println("\nYou sank a ship!");
                                System.out.println("Press Enter and pass the move to another player...\n");
                                return;
                            } else {
                                System.out.println("\nYou hit a ship!");
                                System.out.println("Press Enter and pass the move to another player...\n");
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
