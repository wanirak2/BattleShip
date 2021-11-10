package battleship;
import java.util.Scanner;
public class Main {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Players player1 = new Players("Player 1");
        final Players player2 = new Players("Player 2");
        player1.placeShips();
        sc.nextLine();
        player2.placeShips();
        sc.nextLine();
        while (player1.getLengthOfAllShips() >= 0 && player2.getLengthOfAllShips() >= 0) {
            player1.play(player2);
            sc.nextLine();
            player2.play(player1);
            sc.nextLine();
        }
    }
}
