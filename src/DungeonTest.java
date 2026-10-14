import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DungeonTest
{

    // ~ Fields ................................................................

    private Dungeon dungeon;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    @BeforeEach
    public void setUp()
    {
        dungeon = new Dungeon();
    }


    @Test
    public void testConstructor()
    {
        assertNull(dungeon.getCurrentRoom());
    }


    @Test
    public void testMakeDungeonCreatesSixRoom()
    {
        dungeon.makeDungeon();

        assertEquals(6, dungeon.getRoomCount());
    }


    @Test
    public void testMakeDungeonSetsCurrentRoom()
    {
        dungeon.makeDungeon();

        assertNotNull(dungeon.getCurrentRoom());
    }


    @Test
    public void testCurrentRoomIsMainRoom()
    {
        dungeon.makeDungeon();

        assertEquals(Room.RoomType.MAIN, dungeon.getCurrentRoom().getType());
    }


    @Test
    public void testMakeDungeonCanBeCalledAgain()
    {
        dungeon.makeDungeon();
        dungeon.makeDungeon();

        assertEquals(6, dungeon.getRoomCount());
        assertNotNull(dungeon.getCurrentRoom());
    }
}
