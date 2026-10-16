import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {

            System.out.println("Working directory: "
    + System.getProperty("user.dir"));

            System.out.print("Enter your character's name: ");
            Player player = new Player(scan.nextLine(), 100);
            Dungeon dungeon = new Dungeon();
            dungeon.makeDungeon();

            int defeatedCount = 0;
            int fledCount = 0;

            boolean playing = true;
            boolean won = false;

            System.out.println("\nWelcome to the linked dungeon, " + player.getName() + "!\n");

            while (playing && player.isAlive()) {
                Room room = dungeon.getCurrentRoom();
                System.out.println("LINKED DUNGEON");
                System.out.println("room name: " + dungeon.getCurrentRoom().getName());
                System.out.println("room description: " + dungeon.getCurrentRoom().getDescription());
                System.out.println("--------------------------------------");
                System.out.println("Health: " + player.getHealth() + "/" + player.getMaxHealth() + "| Weapon: " + (player.getEquippedWeapon() != null ? player.getEquippedWeapon().getName() : "None") + "| Armor: " + (player.getEquippedArmor() != null ? player.getEquippedArmor().getName() : "None") + "\n");
                
                boolean loot =dungeon.getCurrentRoom().getLoot() != null;
                boolean encounter = dungeon.getCurrentRoom().getEncounter() != null;
                boolean sideRoom = dungeon.getCurrentRoom().isSideRoomAvailable();
                int choice;

                if (loot) {
                    System.out.println("You see a " + dungeon.getCurrentRoom().getLoot().getName() + " in the room.");
                }
                if (encounter) {
                    if (loot){
                        System.out.println();
                    }
                    Encounter enemy = dungeon.getCurrentRoom().getEncounter();

                    System.out.println("Enemy: " + enemy.getName());
                    System.out.println("Enemy HP: " + enemy.getHealth());
                    System.out.println(enemy.getDescription());
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
                        if (player.getInventorySize() < 10){
                            System.out.println("3. Pick up loot");
                        }
                        else{
                            System.out.println("Inventory is to bit to pick up item");
                        }
                    
                    }
                    if (sideRoom) {
                        System.out.println("4. Explore side room");
                    }
                    System.out.println("5. Next room");
                }

                System.out.println("6. Inventory");
                System.out.println("7. Quit");
                System.out.println("8. Encounter Catalog");

                System.out.print("\nChoice: ");

                while (!scan.hasNextInt()) {
                    System.out.println("\nPlease enter a valid number!");
                    scan.nextLine();
                }

                choice = scan.nextInt();
                scan.nextLine();


                switch (choice) {
                    case 1 -> { // Attack
                        if (!encounter) {
                            System.out.println("\nThere is no enemy to attack!");
                        }
                        else {
                            Encounter enemy = room.getEncounter();
                            player.dealDamage(enemy);

                            if (enemy.isDefeated()) {
                                defeatedCount++;

                                System.out.println("\nYou defeated the " + enemy.getName() + "!");
                                room.setEncounter(null);

                                if (room.getType() == Room.RoomType.BOSS) {
                                    won = true;
                                    playing = false;
                                }
                            }
                            else {
                                int healthBefore = player.getHealth();
                                player.takeDamage(enemy.getDamage());

                                int actualDamage = healthBefore - player.getHealth();
                                System.out.println("\nYou took " + actualDamage + " damage!");
                            }
                        }
                    }

                    case 2 -> { // Flee
                        if (!encounter) {
                            System.out.println("\nThere is no enemy to flee from!");
                        }
                        else if (room.getType() == Room.RoomType.BOSS) {
                            System.out.println("\nYou cannot flee from the boss!");
                        }
                        else {
                            Encounter enemy = room.getEncounter();

                            if (Math.random() < 0.5) {
                                int healthBefore = player.getHealth();
                                player.takeDamage(enemy.getDamage());

                                System.out.println("\nYou were hit while fleeing for "
                                    + (healthBefore - player.getHealth()) + " damage!");
                            }
                            else {
                                System.out.println("\nYou escaped without being hit!");
                            }

                            if (player.isAlive()) {
                                room.setEncounter(null);
                                fledCount++;

                                if (room.getType() == Room.RoomType.SIDE) {
                                    dungeon.leaveSideRoom();
                                }
                                else if (dungeon.canMoveToNextRoom()) {
                                    dungeon.moveToNextRoom();
                                }
                            }
                        }
                    }

                    case 3 -> { // Collect loot
                        if (loot && !encounter && player.getInventorySize() < 10) {
                            Item item = room.getLoot();
                            player.addItem(item);
                            room.setLoot(null);

                            System.out.println("\nYou picked up " + item.getName());
                        }
                        else {
                            System.out.println("\nYou cannot collect loot right now.");
                        }
                    }

                    case 4 -> { // Explore side room
                        if (!encounter && sideRoom
                                && room.getType() == Room.RoomType.MAIN) {
                            dungeon.moveToSideRoom();
                        }
                        else {
                            System.out.println("\nYou cannot explore a side room right now.");
                        }
                    }

                    case 5 -> { // Next room,
                        if (encounter) {
                            System.out.println("\nYou must defeat or flee from the enemy first!");
                        }
                        else if (room.getType() == Room.RoomType.SIDE) {
                            dungeon.leaveSideRoom();
                        }
                        else if (dungeon.canMoveToNextRoom()) {
                            dungeon.moveToNextRoom();
                        }
                        else if (room.getType() == Room.RoomType.BOSS) {
                            System.out.println("\nCongratulations! You conquered the dungeon!");
                            playing = false;
                        }
                        else {
                            System.out.println("\nThere are no more rooms.");
                        }
                    }

                    case 6 -> { // Inventory
                        manageInventory(player, scan);
                    }

                    case 7 -> { // Quit
                        System.out.println("\nThanks for playing!");
                        playing = false;
                    }

                    case 8 -> { // Catalog
                        displayEncounterCatalog(dungeon);
                    }

                    default -> {
                        System.out.println("\nInvalid choice. Please choose a menu option.");
                    }
                }
                System.out.println();
            }

            System.out.println("\n===== GAME SUMMARY =====");

            if (won) {
                System.out.println("VICTORY! You conquered Linked Dungeons!");
            }
            else if (!player.isAlive()) {
                System.out.println("GAME OVER! You perished in the dungeon.");
            }
            else {
                System.out.println("You abandoned the dungeon.");
            }

            System.out.println("Player: " + player.getName());
            System.out.println("Final Health: " + player.getHealth());
            System.out.println("Enemies Encountered: " + dungeon.getCatalogSize());
            System.out.println("Enemies Defeated: " + defeatedCount);
            System.out.println("Encounters Escaped: " + fledCount);
        }
    }

    private static void manageInventory(Player player, Scanner scan) {
        System.out.println("\n===== INVENTORY =====");

        if (player.getInventorySize() == 0) {
            System.out.println("Your inventory is empty.");
        }
        else {
            for (int i = 0; i < player.getInventorySize(); i++) {
                Item item = player.getInventoryItem(i);

                System.out.println(
                    (i + 1) + ". " + item.getName()
                    + " | " + item.getType()
                    + " | Value: " + item.getValue()
                    + " | Durability: " + item.getDurability()
                );
            }

            System.out.println("0. Back");
            System.out.print("Select an item: ");

            int invChoice = scan.nextInt();
            scan.nextLine();

            if (invChoice == 0) {
                return;
            }

            if (invChoice < 1 || invChoice > player.getInventorySize()) {
                System.out.println("Invalid item selection.");
                return;
            }

            Item selectedItem = player.getInventoryItem(invChoice - 1);

            System.out.println("\nSelected: " + selectedItem.getName());
            System.out.println(selectedItem.getDescription());
            System.out.println("1. " + (selectedItem.getType() == Item.ItemType.CONSUMABLE ? "Use item" : "Equip item"));
            System.out.println("2. Delete item");
            System.out.println("3. Back");

            System.out.print("Choice: ");
            int action = scan.nextInt();
            scan.nextLine();

            switch (action) {
                case 1 -> {
                    switch (selectedItem.getType()) {
                        case WEAPON -> {
                            player.equipWeapon(selectedItem);
                            System.out.println("Equipped " + selectedItem.getName());
                        }

                        case ARMOR -> {
                            player.equipArmor(selectedItem);
                            System.out.println("Equipped " + selectedItem.getName());
                        }

                        case CONSUMABLE -> {
                            int healing = selectedItem.getValue();

                            int newHealth = Math.min(
                                player.getMaxHealth(),
                                player.getHealth() + healing
                            );

                            int restored = newHealth - player.getHealth();

                            player.setHealth(newHealth);
                            player.removeItem(selectedItem);

                            System.out.println(
                                "Used " + selectedItem.getName()
                                + " and restored " + restored + " health."
                            );
                        }
                    }
                }

                case 2 -> {
                    player.removeItem(selectedItem);
                    System.out.println("Deleted " + selectedItem.getName());
                }

                case 3 -> {
                    System.out.println("Returning to dungeon.");
                }

                default -> {
                    System.out.println("Invalid action.");
                }
            }
        }
    }
    private static void displayEncounterCatalog(Dungeon dungeon) {
        System.out.println("\n===== ENCOUNTER CATALOG =====");

        if (dungeon.getCatalogSize() == 0) {
            System.out.println("You haven't encountered any enemies yet.");
            return;
        }

        for (int i = 0; i < dungeon.getCatalogSize(); i++) {
            Encounter enemy = dungeon.getCatalogEncounter(i);

            System.out.println((i + 1) + ". " + enemy.getName());
            System.out.println("   " + enemy.getDescription());
        }
    }
}