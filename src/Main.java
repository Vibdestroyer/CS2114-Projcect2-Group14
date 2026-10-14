import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your character's name: ");
        Player player = new Player(scan.nextLine(), 50);
        Dungeon dungeon = new Dungeon();
        dungeon.makeDungeon();

        System.out.println("Welcome to the dungeon, " + player.getName() + "!");
        while (dungeon.canMoveToNextRoom()) {
            System.out.println("You are in: " + dungeon.getCurrentRoom().getName() + "\n" + dungeon.getCurrentRoom().getDescription());
            dungeon.moveToNextRoom();
        }
    }
}