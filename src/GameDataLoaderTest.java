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
}
