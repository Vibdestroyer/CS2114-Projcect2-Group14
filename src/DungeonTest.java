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

        assertEquals(7, dungeon.getRoomCount());
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
    public void testConstructorRoomCount()
    {
        assertEquals(0, dungeon.getRoomCount());
    }
    
    @Test
    public void testBossRoomIsLost()
    {
        dungeon.makeDungeon();
        
        assertEquals(7, dungeon.getRoomCount());
        
        for (int i = 0; i < 6; i++)
        {
            dungeon.moveToNextRoom();
        }
        
        assertEquals(Room.RoomType.BOSS, dungeon.getCurrentRoom().getType());
    }
    
    @Test
    public void testMoveToNextRoom()
    {
        dungeon.makeDungeon();
        
        Room firstRoom = dungeon.getCurrentRoom();
        
        dungeon.moveToNextRoom();
        
        assertNotNull(dungeon.getCurrentRoom());
        assertNotSame(firstRoom, dungeon.getCurrentRoom());
        assertEquals(Room.RoomType.MAIN, dungeon.getCurrentRoom().getType());
    }
    
    @Test
    public void testCannotMovePastBossRoom()
    {
        dungeon.makeDungeon();
        
        for (int i = 0; i < 6; i++)
        {
            dungeon.moveToNextRoom();
        }
        
        Room bossRoom = dungeon.getCurrentRoom();
        
        assertFalse(dungeon.canMoveToNextRoom());
        
        dungeon.moveToNextRoom();
        
        assertSame(bossRoom, dungeon.getCurrentRoom());
    }
    
    @Test
    public void testMakeDungeonCanBeCalledAgain()
    {
        dungeon.makeDungeon();
        dungeon.makeDungeon();
        
        assertEquals(7, dungeon.getRoomCount());
        assertNotNull(dungeon.getCurrentRoom());
        assertEquals(Room.RoomType.MAIN, dungeon.getCurrentRoom().getType());
    }
    
    @Test
    public void testMakeSideRoom()
    {
        dungeon.makeDungeon();
        
        Room sideRoom = dungeon.makeSideRoom();
        
        assertNotNull(sideRoom);
        assertEquals(Room.RoomType.SIDE, sideRoom.getType());
    }
    
    @Test
    public void testMoveToSideRoom()
    {
        dungeon.makeDungeon();
        
        dungeon.moveToSideRoom();
        
        assertNotNull(dungeon.getCurrentRoom());
        assertEquals(Room.RoomType.SIDE, dungeon.getCurrentRoom().getType());
    }
    
    @Test
    public void testMakeSideRoomOnlyOnce()
    {
        dungeon.makeDungeon();
        
        Room firstSideRoom = dungeon.makeSideRoom();
        Room secondSideRoom = dungeon.makeSideRoom();
        
        assertSame(firstSideRoom, secondSideRoom);
    }
    
    @Test
    public void testLeaveSideRoom()
    {
        dungeon.makeDungeon();
        
        dungeon.moveToSideRoom();
        dungeon.leaveSideRoom();
        
        assertEquals(Room.RoomType.MAIN, dungeon.getCurrentRoom().getType());
    }
    
    @Test
    public void testCatalogInitiallyEmpty()
    {
        assertEquals(0, dungeon.getCatalogSize());
    }
    
    @Test
    public void testCatalogSizeAfterMakeDungeon()
    {
        dungeon.makeDungeon();
        
        assertEquals(0, dungeon.getCatalogSize());
    }
    
    @Test
    public void testMakeSideRoomWhenAvailable()
    {
        dungeon.makeDungeon();
        
        Room sideRoom = dungeon.makeSideRoom();
        
        assertNotNull(sideRoom);
        assertEquals(Room.RoomType.SIDE, sideRoom.getType());
    }
    
    @Test
    public void testMakeSideRoomWhenAlreadyCreated()
    {
        dungeon.makeDungeon();
        
        Room firstSideRoom = dungeon.makeSideRoom();
        Room secondSideRoom = dungeon.makeSideRoom();
        
        assertSame(firstSideRoom, secondSideRoom);
    }
    
    @Test
    public void testMakeSideRoomWhenUnavailable()
    {
        dungeon.makeDungeon();
        
        for (int i = 0; i < 6; i++)
        {
            dungeon.moveToNextRoom();
        }
        
        Room sideRoom = dungeon.makeSideRoom();
        
        assertNull(sideRoom);
    }
    
    @Test
    public void testGetCatalogEncounter()
    {
        dungeon.makeDungeon();

        for (int i = 0; i < 6; i++)
        {
            dungeon.moveToNextRoom();
        }

        assertTrue(dungeon.getCatalogSize() > 0);

        Encounter encounter = dungeon.getCatalogEncounter(0);

        assertNotNull(encounter);
        assertNotNull(encounter.getName());
        assertNotNull(encounter.getDescription());
    }
    
    @Test
    public void testMakeSideRoomWhenSideRoomAlreadyExists()
    {
        dungeon.makeDungeon();
        
        Room firstSideRoom = dungeon.makeSideRoom();
        
        assertNotNull(firstSideRoom);
        
        Room secondSideRoom = dungeon.makeSideRoom();
        
        assertSame(firstSideRoom, secondSideRoom);
    }
}
