import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EncounterTest
{

    // ~ Fields ................................................................

    private Encounter encounter;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    @BeforeEach
    public void setUp()
    {
        encounter = new Encounter("Goblin", "A small goblin.", 10, 2);
    }


    @Test
    public void testConstructorAndGetters()
    {
        assertEquals("Goblin", encounter.getName());
        assertEquals("A small goblin.", encounter.getDescription());
        assertEquals(10, encounter.getHealth());
        assertEquals(2, encounter.getDamage());
    }


    @Test
    public void testIsDefeatedWhenAlive()
    {
        assertFalse(encounter.isDefeated());
    }


    @Test
    public void testIsDefeatedAtZeroHealth()
    {
        encounter.decreaseHealth(10);
        assertTrue(encounter.isDefeated());
    }


    @Test
    public void testIsDefeatedBelowZeroHealth()
    {
        encounter.decreaseHealth(15);
        assertTrue(encounter.isDefeated());
    }


    @Test
    public void testGetArmor()
    {
        assertNull(encounter.getArmor());
    }


    @Test
    public void testDecreaseHealthWithArmor()
    {
        encounter.setArmor();

        Item armor = encounter.getArmor();
        int armorValue = armor.getValue();
        int initialDurability = armor.getDurability();

        encounter.decreaseHealth(5);

        assertEquals(
            Math.max(0, 10 - Math.max(0, 5 - armorValue)),
            encounter.getHealth());

        assertEquals(initialDurability - 1, armor.getDurability());
    }


    @Test
    public void testArmorBreaks()
    {
        encounter.setArmor();

        Item armor = encounter.getArmor();
        armor.setDurability(1);

        encounter.decreaseHealth(5);

        assertNull(encounter.getArmor());
    }
}
