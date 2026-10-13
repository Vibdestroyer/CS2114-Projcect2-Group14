import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
public class GameDataLoader {
    public Item getRandomMainItem(){
        return getRandomItem("data/main_room_items.txt");
    }
    public Item getRandomSideItem(){
        return getRandomItem("data/side_room_items.txt");
    }
    private Item getRandomItem(String file){
        ArrayList<String> itemData = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                itemData.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Cannot load items from: " + file, e);
        }
        Random rand = new Random();
        String randomItemData = itemData.get(rand.nextInt(itemData.size()));
        String[] parts = randomItemData.split(","); 

        String name = parts[0];
        String description = parts[1];
        int value = Integer.parseInt(parts[2]);
        int durability = Integer.parseInt(parts[3]);
        Item.ItemType type = Item.ItemType.valueOf(parts[4]);
        return new Item(name, description, value, durability, type);        
    }
}
