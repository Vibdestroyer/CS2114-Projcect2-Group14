import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest
{

    // ~ Fields ................................................................

    private Item item;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    @BeforeEach
    public void setUp()
    {
        item = new Item("Sword", "A sharp sword.", 20, 5, Item.ItemType.WEAPON);
    }


    @Test
    public void testConstructorAndGetters()
    {
        assertEquals("Sword", item.getName());
        assertEquals("A sharp sword.", item.getDescription());
        assertEquals(20, item.getValue());
        assertEquals(5, item.getDurability());
        assertEquals(Item.ItemType.WEAPON, item.getType());
    }


    @Test
    public void testSetDurability()
    {
        item.setDurability(10);
        assertEquals(10, item.getDurability());
    }


    @Test
    public void testDecreaseDurability()
    {
        item.decreaseDurability();
        assertEquals(4, item.getDurability());
    }


    @Test
    public void testDecreaseDurabilityMultipleTimes()
    {
        item.decreaseDurability();
        item.decreaseDurability();
        assertEquals(3, item.getDurability());
    }
}
