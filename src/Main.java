import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your character's name: ");
        Player player = new Player(scan.nextLine(), 50);
        Dungeon dungeon = new Dungeon();
        dungeon.makeDungeon();

        System.out.println("Welcome to the linked dungeon, " + player.getName() + "!\n");
        while (dungeon.canMoveToNextRoom()) {
            System.out.println("LINKED DUNGEON");
            System.out.println("room name: " + dungeon.getCurrentRoom().getName());
            System.out.println("room description: " + dungeon.getCurrentRoom().getDescription());
            System.out.println("--------------------------------------");
            System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth() + "| Weapon: " + (player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None") + "| Armor: " + (player.getEquippedArmor() != null ? player.getEquippedArmor().getName() : "None") + "\n");
            
            boolean loot =dungeon.getCurrentRoom().getLoot() != null;
            boolean encounter = dungeon.getCurrentRoom().getEncounter() != null;
            boolean sideRoom = dungeon.getCurrentRoom().isSideRoomAvailable();
            int choice = -1;

            if (loot) {
                System.out.println("You see a " + dungeon.getCurrentRoom().getLoot().getName() + " in the room.");
            }
            if (encounter) {
                System.out.println("You encounter a " + dungeon.getCurrentRoom().getEncounter().getName() + "!");
            }
            if (sideRoom) {
                System.out.println("There is a side room available to explore.");
            }
            
            System.out.println("\nWhat would you like to do?");

            if (encounter) {
                System.out.println("1. Attack");
                System.out.println("2. Flee");
            }
            else {
                if (loot) {
                    System.out.println("3. Pick up loot");
                }
                if (sideRoom) {
                    System.out.println("4. Explore side room");
                }
                System.out.println("5. Next room");
            }

            System.out.println("6. Inventory");
            System.out.println("7. Quit");
            choice = scan.nextInt();


            System.out.println("\nThis is your choice: " + choice);

            if(choice == 7){
                System.out.println("Thanks for playing! Goodbye.");
                System.exit(0);
            }
            dungeon.moveToNextRoom();
        }
        scan.close();
    }
}