import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;

class GameDataLoaderTest
{

    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    @Test
    public void testGetRandomMainItem()
    {
        GameDataLoader loader = new GameDataLoader();

        Item item = loader.getRandomMainItem();

        assertNotNull(item);
        assertNotNull(item.getName());
        assertNotNull(item.getDescription());
        assertTrue(item.getValue() >= 0);
        assertTrue(item.getDurability() >= 0);
        assertNotNull(item.getType());
    }


    @Test
    public void testGetRandomSideItem()
    {
        GameDataLoader loader = new GameDataLoader();

        Item item = loader.getRandomSideItem();

        assertNotNull(item);
        assertNotNull(item.getName());
        assertNotNull(item.getDescription());
        assertNotNull(item.getType());
    }


    @Test
    public void testMissingFile()
    {
        File file = new File("data/main_room_items.txt");

        assertTrue(
            file.exists(),
            "The test requires the original item file to exist.");

        File backup = new File("data/main_room_items_backup.txt");

        assertTrue(
            file.renameTo(backup),
            "Could not temporarily rename the item file.");

        try
        {
            GameDataLoader loader = new GameDataLoader();

            assertThrows(IllegalStateException.class, () -> {
                loader.getRandomMainItem();
            });
        }
        finally
        {
            assertTrue(
                backup.renameTo(file),
                "Could not restore the original item file.");
        }
    }


    @Test
    public void testGetRandomMainEncounter()
    {
        GameDataLoader loader = new GameDataLoader();

        Encounter encounter = loader.getRandomMainEncounter();

        assertNotNull(encounter);
        assertNotNull(encounter.getName());
        assertNotNull(encounter.getDescription());
        assertTrue(encounter.getHealth() > 0);
        assertTrue(encounter.getDamage() >= 0);
    }


    @Test
    public void testGetRandomSideEncounter()
    {
        GameDataLoader loader = new GameDataLoader();

        Encounter encounter = loader.getRandomSideEncounter();

        assertNotNull(encounter);
        assertNotNull(encounter.getName());
        assertNotNull(encounter.getDescription());
    }


    @Test
    public void testGetRandomBossEncounter()
    {
        GameDataLoader loader = new GameDataLoader();

        Encounter encounter = loader.getRandomBossEncounter();

        assertNotNull(encounter);
        assertNotNull(encounter.getName());
        assertNotNull(encounter.getDescription());
    }


    @Test
    public void testSideRoomsNamesAndDescriptions()
    {
        GameDataLoader loader = new GameDataLoader();

        String[] result = loader.sideRoomsNamesAndDescriptions();

        assertEquals(2, result.length);
        assertNotNull(result[0]);
        assertNotNull(result[1]);
    }


    @Test
    public void testMainRoomsNamesAndDescriptions()
    {
        GameDataLoader loader = new GameDataLoader();

        String[] result = loader.mainRoomsNamesAndDescriptions();

        assertEquals(2, result.length);
        assertNotNull(result[0]);
        assertNotNull(result[1]);
    }


    @Test
    public void testGetRandomEncounter()
    {
        GameDataLoader loader = new GameDataLoader();

        Encounter encounter =
            loader.getRandomEncounter("Data/boss_encounters.txt");

        assertNotNull(encounter);
        assertNotNull(encounter.getName());
        assertNotNull(encounter.getDescription());
    }


    @Test
    public void testGetRandomEncounterMissingFile()
    {
        GameDataLoader loader = new GameDataLoader();

        assertThrows(IllegalStateException.class, () -> {
            loader.getRandomEncounter("Data/nonexisten_file.txt");
        });
    }


    @Test
    public void testDungeonNameAndDescriptionMissingFile()
    {
        GameDataLoader loader = new GameDataLoader();

        assertThrows(IllegalStateException.class, () -> {
            loader.dungeonNameAndDescription("Data/nonexisten_file.txt");
        });
    }


    @Test
    public void testGetRandomEncounterEmptyFile()
        throws Exception
    {
        File emptyFile = new File("Data/empty_test_file.txt");

        assertTrue(emptyFile.createNewFile());

        try
        {
            GameDataLoader loader = new GameDataLoader();

            assertThrows(IllegalStateException.class, () -> {
                loader.getRandomEncounter("Data/empty_test_file.txt");
            });
        }
        finally
        {
            assertTrue(emptyFile.delete());
        }
    }


    @Test
    public void testEmptyItemFile()
        throws Exception
    {
        File file = new File("Data/empty_test.txt");
        assertTrue(file.createNewFile());

        try
        {
            GameDataLoader loader = new GameDataLoader();

            java.lang.reflect.Method method = GameDataLoader.class
                .getDeclaredMethod("getRandomItem", String.class);
            method.setAccessible(true);

            java.lang.reflect.InvocationTargetException exception =
                assertThrows(
                    java.lang.reflect.InvocationTargetException.class,
                    () -> method.invoke(loader, "Data/empty_test.txt"));

            assertTrue(exception.getCause() instanceof IllegalStateException);
            assertTrue(
                exception.getCause().getMessage().contains("No data found"));
        }
        finally
        {
            assertTrue(file.delete());
        }
    }


    @Test
    public void testEmptyDungeonNameFile()
        throws Exception
    {
        File file = new File("Data/empty_rooms_test.txt");

        assertTrue(file.createNewFile());

        try
        {
            GameDataLoader loader = new GameDataLoader();

            assertThrows(IllegalStateException.class, () -> {
                loader.dungeonNameAndDescription("Data/empty_rooms_test.txt");
            });
        }
        finally
        {
            assertTrue(file.delete());
        }
    }
}
