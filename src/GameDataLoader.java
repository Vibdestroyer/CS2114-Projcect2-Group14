import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameDataLoader
{
    public Item getRandomMainItem()
    {
        return getRandomItem("Data/main_room_items.txt");
    }


    public Item getRandomSideItem()
    {
        return getRandomItem("Data/side_room_items.txt");
    }


    private Item getRandomItem(String file)
    {
        ArrayList<String> itemData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(file)))
        {
            while (scanner.hasNextLine())
            {
                itemData.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            throw new IllegalStateException(
                "Cannot load items from: " + file,
                e);
        }
        if (itemData.isEmpty())
        {
            throw new IllegalStateException("No data found in: " + file);
        }
        Random rand = new Random();
        String randomItemData = itemData.get(rand.nextInt(itemData.size()));
        String[] parts = randomItemData.split(",");

        String name = parts[0];
        String description = parts[1];
        int value = Integer.parseInt(parts[2]);
        int durability = Integer.parseInt(parts[3]);
        Item.ItemType type = Item.ItemType.valueOf(parts[4].trim());
        return new Item(name, description, value, durability, type);
    }


    public Encounter getRandomMainEncounter()
    {
        return getRandomEncounter("Data/main_room_encounters.txt");
    }


    public Encounter getRandomSideEncounter()
    {
        return getRandomEncounter("Data/side_room_encounters.txt");
    }


    public Encounter getRandomBossEncounter()
    {
        return getRandomEncounter("Data/boss_encounters.txt");
    }


    public Encounter getRandomEncounter(String file)
    {
        ArrayList<String> encounterData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(file)))
        {
            while (scanner.hasNextLine())
            {
                encounterData.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            throw new IllegalStateException(
                "Cannot load encounters from: " + file,
                e);
        }
        if (encounterData.isEmpty())
        {
            throw new IllegalStateException("No data found in: " + file);
        }
        Random rand = new Random();
        String randomEncounterData =
            encounterData.get(rand.nextInt(encounterData.size()));
        String[] parts = randomEncounterData.split(",");

        String name = parts[0];
        String description = parts[1];
        int health = Integer.parseInt(parts[2]);
        int damage = Integer.parseInt(parts[3]);
        return new Encounter(name, description, health, damage);
    }


    public String[] mainRoomsNamesAndDescriptions()
    {
        return dungeonNameAndDescription("Data/main_room_names.txt");
    }


    public String[] sideRoomsNamesAndDescriptions()
    {
        return dungeonNameAndDescription("Data/side_room_names.txt");
    }


    public String[] dungeonNameAndDescription(String file)
    {
        Random rand = new Random();
        ArrayList<String> dungeonData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(file)))
        {
            while (scanner.hasNextLine())
            {
                dungeonData.add(scanner.nextLine());
            }
        }
        catch (FileNotFoundException e)
        {
            throw new IllegalStateException(
                "Cannot load dungeon name and description from: " + file,
                e);
        }
        if (dungeonData.isEmpty())
        {
            throw new IllegalStateException("No data found in: " + file);
        }
        String randomNameData =
            dungeonData.get(rand.nextInt(dungeonData.size()));
        return randomNameData.split(",", 2);
    }
}
