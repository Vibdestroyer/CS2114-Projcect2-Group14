import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedChainTest
{

    // ~ Fields ................................................................

    private LinkedChain<String> chain;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    @BeforeEach
    public void setUp()
    {
        chain = new LinkedChain<>();
    }


    @Test
    public void testConstructor()
    {
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
    }


    @Test
    public void testAdd()
    {
        chain.add("A");
        chain.add("B");

        assertEquals(2, chain.size());
        assertEquals("B", chain.get(0));
        assertEquals("A", chain.get(1));
    }


    @Test
    public void testAddToEnd()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertEquals("A", chain.get(0));
        assertEquals("B", chain.get(1));
        assertEquals("C", chain.get(2));
    }


    @Test
    public void testRemoveFirst()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        assertEquals("A", chain.remove());
        assertEquals("B", chain.get(0));
        assertEquals(1, chain.size());
    }


    @Test
    public void testRemoveFromEmptyChain()
    {
        assertNull(chain.remove());
    }


    @Test
    public void testRemoveSpecificEntry()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");

        assertTrue(chain.remove("B"));
        assertEquals(2, chain.size());
        assertEquals("C", chain.get(1));
    }


    @Test
    public void testRemoveMissingEntry()
    {
        chain.addToEnd("A");

        assertFalse(chain.remove("B"));
        assertEquals(1, chain.size());
    }


    @Test
    public void testClear()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        assertEquals(2, chain.size());

        chain.clear();

        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
    }


    @Test
    public void testContains()
    {
        chain.addToEnd("A");

        assertTrue(chain.contains("A"));
        assertFalse(chain.contains("B"));
    }


    @Test
    public void testCount()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("A");

        assertEquals(2, chain.count("A"));
        assertEquals(1, chain.count("B"));
        assertEquals(0, chain.count("C"));
    }


    @Test
    public void testGetInvalidIndex()
    {
        chain.addToEnd("A");

        try
        {
            chain.get(1);
            fail("Expected IndexOutOfBoundsException");
        }
        catch (IndexOutOfBoundsException e)
        {
            // Exception
        }
    }


    @Test
    public void testToString()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");

        assertEquals("[A, B]", chain.toString());
    }
    
    @Test
    public void testAddNull()
    {
        assertThrows(IllegalArgumentException.class, () -> chain.add(null));
    }
    
    @Test
    public void testAddToEndNull()
    {
        assertThrows(IllegalArgumentException.class, () -> chain.addToEnd(null));
    }
    
    @Test
    public void testRemoveNull()
    {
        chain.addToEnd("A");
        
        assertFalse(chain.remove(null));
        assertEquals(1, chain.size());
    }
    
    @Test
    public void testRemoveFromEmptyChainSpecific()
    {
        assertFalse(chain.remove("A"));
    }
    
    @Test
    public void testRemoveFirstSpecificEntry()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        assertTrue(chain.remove("A"));
        assertEquals("[B]", chain.toString());
    }
    
    @Test
    public void testRemoveLastEntry()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        assertTrue(chain.remove("B"));
        assertEquals("[A]", chain.toString());
    }
    
    @Test
    public void testRemoveOnlyEntry()
    {
        chain.addToEnd("A");
        
        assertTrue(chain.remove("A"));
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
    }
    
    @Test
    public void testGetNegativeIndex()
    {
        chain.addToEnd("A");
        
        assertThrows(IndexOutOfBoundsException.class, () -> chain.get(-1));
    }
    
    @Test
    public void testCountNull()
    {
        chain.addToEnd("A");
        
        assertEquals(0, chain.count(null));
    }
    
    @Test
    public void testToArrayNull()
    {
        assertThrows(IllegalArgumentException.class, () -> chain.toArray(null));
    }
    
    @Test
    public void testToArrayTooSmall()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        String[] values = new String[1];
        
        assertThrows(IllegalArgumentException.class, () -> chain.toArray(values));
    }
    
    @Test
    public void testToArrayCorrectSize()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        String[] values = new String[2];
        
        assertArrayEquals(new String[] {"A", "B"}, chain.toArray(values));
    }
    
    @Test
    public void testToArrayLargerThanChain()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        
        String[] values = new String[3];
        
        String[] result = chain.toArray(values);
        
        assertSame(values, result);
        assertEquals("A", result[0]);
        assertEquals("B", result[1]);
        assertNull(result[2]);
    }
    
    @Test
    public void testToArrayEmptyChain()
    {
        String[] values = new String[0];
        
        assertArrayEquals(values, chain.toArray(values));
    }
    
    @Test
    public void testToStringEmptyChain()
    {
        assertEquals("[]", chain.toString());
    }
    
    @Test
    public void testClearEmptyChain()
    {
        chain.clear();
        
        assertTrue(chain.isEmpty());
        assertEquals(0, chain.size());
    }
    
    @Test
    public void testIsEmptyWithEntries()
    {
        chain.addToEnd("A");
        
        assertFalse(chain.isEmpty());
    }
    
    @Test
    public void testRemoveEntryAtEnd()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");
        
        assertTrue(chain.remove("C"));
        assertEquals("[A, B]", chain.toString());
        assertEquals(2, chain.size());
    }
    
    @Test
    public void testRemoveEntryNotFoundAfterLoop()
    {
        chain.addToEnd("A");
        chain.addToEnd("B");
        chain.addToEnd("C");
        
        assertFalse(chain.remove("D"));
        assertEquals("[A, B, C]", chain.toString());
    }
}
