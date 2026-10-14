import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomTest
{

    // ~ Fields ................................................................

    private Room room;
    private Item item;
    private Encounter encounter;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................
    @BeforeEach
    public void setUp()
    {
        item = new Item(
            "Health Potion",
            "Restores health.",
            10,
            1,
            Item.ItemType.CONSUMABLE);

        encounter =
            new Encounter("Skeleton", "A skeleton with a sword.", 10, 2);

        room = new Room(
            item,
            encounter,
            "Entrance",
            Room.RoomType.MAIN,
            "The entrance to the dungeon.",
            true);
    }


    @Test
    public void testConstructorAndGetters()
    {
        assertSame(item, room.getLoot());
        assertSame(encounter, room.getEncounter());
        assertEquals("Entrance", room.getName());
        assertEquals(Room.RoomType.MAIN, room.getType());
        assertEquals("The entrance to the dungeon.", room.getDescription());
        assertTrue(room.isSideRoomAvailable());
    }


    @Test
    public void testHasLoot()
    {
        assertTrue(room.hasLoot());
    }


    @Test
    public void testHasNoLoot()
    {
        room.setLoot(null);
        assertFalse(room.hasLoot());
    }


    @Test
    public void testHasEncounter()
    {
        assertTrue(room.hasEncounter());
    }


    @Test
    public void testHasNoEncounter()
    {
        room.setEncounter(null);
        assertFalse(room.hasEncounter());
    }


    @Test
    public void testSetLoot()
    {
        Item newItem =
            new Item("Helmet", "An iron helmet.", 15, 8, Item.ItemType.ARMOR);

        room.setLoot(newItem);

        assertSame(newItem, room.getLoot());
    }


    @Test
    public void testSetEncounter()
    {
        Encounter newEncounter = new Encounter("Orc", "A large orc.", 20, 5);

        room.setEncounter(newEncounter);

        assertSame(newEncounter, room.getEncounter());
    }
}
