package battleship;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static final Ocean ocean = new Ocean();
    public static final char[][] space = ocean.getSpace();
    public static Scanner scanner = new Scanner(System.in);

    class A {
        public void method() {
            System.out.print("A");
        }
    }
    class B extends A {
        public void method() {
            System.out.print("B");
        }
    }
    public static void main(String[] args) {
      /*  ocean.printOcean(false);
        Ships.takeCoordinate();
        System.out.println("\nThe game starts!\n");
        ocean.printOcean(true);
        System.out.print("\nTake a shot!\n");
        Ships.takeAShot();
        */
        Ocean pc = new Ocean();
        A b = new B();
        A a = new A();
    }
}
