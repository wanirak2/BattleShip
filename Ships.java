package battleship;

import javax.print.DocFlavor;

public enum Ships {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5, 0, 0, 0, 0),
    BATTLESHIP("Battleship", 4, 0, 0, 0, 0),
    SUBMARINE("Submarine", 3,0,0,0,0),
    CRUISER("Cruiser", 3,0,0,0,0),
    DESTROYER("Destroyer", 2,0,0,0,0);

    String name;
    int length;
    int x1, x2, y1, y2;
    static int squadron = 17;

    Ships(String name, int length, int x1, int x2, int y1, int y2) {
        this.name = name;
        this.length = length;
    }

    public static void takeCoordinate() {
        for (Ships ship: Ships.values()) {
            System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", ship.name, ship.length);
            while (true) {
                String[] coordinates = Main.scanner.nextLine().split(" ");
                ship.x1 = coordinates[0].charAt(0) - 65;
                ship.y1 = Integer.parseInt(coordinates[0].substring(1)) - 1;
                ship.x2 = coordinates[1].charAt(0) - 65;
                ship.y2 = Integer.parseInt(coordinates[1].substring(1)) - 1;
                if (ship.x1 > ship.x2) {
                    ship.x1 = ship.x1 + ship.x2;
                    ship.x2 = ship.x1 - ship.x2;
                    ship.x1 = ship.x1 - ship.x2;
                } else if (ship.y1 > ship.y2) {
                    ship.y2 = ship.y1 + ship.y2;
                    ship.y1 = ship.y2 - ship.y1;
                    ship.y2 = ship.y2 - ship.y1;
                }
                if (checkCoordinate(ship.x1, ship.x2, ship.y1, ship.y2, ship)) {
                    for (int i = ship.x1; i <= ship.x2; i++) {
                        for (int k = ship.y1; k <= ship.y2; k++) {
                            Main.space[i][k] = 'O';
                        }
                    }
                    break;
                }
            }
            Main.ocean.printOcean(false);
        }
    }
    public static boolean checkCoordinate(int x1, int x2, int y1, int y2, Ships ship) {
        if (x2 - x1 +1 != ship.length && y2 - y1 +1 != ship.length) {
            System.out.println("\nError! Wrong length of the Submarine! Try again:\n");
            return false;
        } else if (x2 - x1 != 0 && y2 - y1 != 0) {
            System.out.println("\nError! Wrong ship location! Try again:\n");
            return false;
        }
        for (int i = x1 - 1; i <= x2 + 1 && i > -1 && i < 10; i++) {
            for (int k = y1 - 1; k <= y2 + 1 && k > -1 && k < 10; k++) {
                if (Main.space[i][k] == 'O') {
                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                    return false;
                }
            }
        }
        return true;
    }

     boolean isHit(int xShot, int yShot) {
        if (xShot >= x1 && xShot <= x2 && yShot >= y1 && yShot <= y2) {
            length--;
            squadron--;
            return true;
        }
        return false;
    }

   public static void takeAShot() {
        while (true) {
            String s = Main.scanner.next();
            int xShot = s.charAt(0) - 65;
            int yShot = Integer.parseInt(s.substring(1)) - 1;
            if (xShot > 9 || yShot > 9) {
                System.out.print("\nError! You entered wrong coordinates! Try again:\n");
                continue;
            }
            for (Ships ship: Ships.values()) {
                if (ship.isHit(xShot, yShot)) {
                    Main.space[xShot][yShot] = 'X';
                    Main.ocean.printOcean(true);
                    System.out.print(squadron == 0 ? "\nYou sank the last ship. You won. Congratulations!" :
                            ship.length == 0 ? "\nYou sank a ship! Specify a new target:\n" : "\nYou hit a ship! Try again:\n");
                    break;
                }
            }
            if (Main.space[xShot][yShot] == '~') {
                Main.space[xShot][yShot] = 'M';
                Main.ocean.printOcean(true);
                System.out.print("\nYou missed. Try again:\n");
            }
            if (squadron == 0) {
                return;
            }
        }
    }
}

