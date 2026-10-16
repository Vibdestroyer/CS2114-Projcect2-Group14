import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest
{

    // ~ Fields ................................................................

    private Player player;
    private Item weapon;
    private Item armor;
    private Item potion;
    private Encounter encounter;
    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    @BeforeEach
    public void setUp()
    {
        player = new Player("Victor", 100);

        weapon =
            new Item("Sword", "A sharp sword.", 10, 5, Item.ItemType.WEAPON);

        armor = new Item("Helmet", "An iron helmet", 5, 5, Item.ItemType.ARMOR);

        potion = new Item(
            "Potion",
            "Restores health.",
            20,
            1,
            Item.ItemType.CONSUMABLE);

        encounter = new Encounter("Goblin", "A small goblin.", 30, 4);
    }


    @Test
    public void testConstructorAndGetters()
    {
        assertEquals("Victor", player.getName());
        assertEquals(100, player.getMaxHealth());
        assertEquals(100, player.getHealth());
        assertTrue(player.isAlive());
        assertNull(player.getEquippedWeapon());
        assertNull(player.getEquippedArmor());
        assertEquals(0, player.getInventorySize());
    }


    @Test
    public void testSetHealth()
    {
        player.setHealth(50);

        assertEquals(50, player.getHealth());
    }


    @Test
    public void testSetMaxHealth()
    {
        player.setMaxHealth(150);

        assertEquals(150, player.getMaxHealth());
    }


    @Test
    public void testAddItem()
    {
        player.addItem(weapon);

        assertEquals(1, player.getInventorySize());
        assertEquals(weapon, player.getInventoryItem(0));
    }


    @Test
    public void testRemoveItem()
    {
        player.addItem(weapon);

        assertEquals(1, player.getInventorySize());
        assertEquals(weapon, player.getInventoryItem(0));

        player.removeItem(weapon);

        assertEquals(0, player.getInventorySize());
    }


    @Test
    public void testDefaultAttackDamage()
    {
        assertEquals(1, player.getAttackDamage());
    }


    @Test
    public void testDefaultDefense()
    {
        assertEquals(0, player.getDefense());
    }


    @Test
    public void testEquipWeapon()
    {
        player.addItem(weapon);
        player.equipWeapon(weapon);

        assertSame(weapon, player.getEquippedWeapon());
        assertEquals(10, player.getAttackDamage());
    }


    @Test
    public void testCannotEquipWeaponNotInInventory()
    {
        player.equipWeapon(weapon);

        assertNull(player.getEquippedWeapon());
    }


    @Test
    public void testCannotEquipNonWeapon()
    {
        player.addItem(potion);
        player.equipWeapon(potion);

        assertNull(player.getEquippedWeapon());
    }


    @Test
    public void testUnequipWeapon()
    {
        player.addItem(weapon);
        player.equipWeapon(weapon);
        player.unequipWeapon();

        assertNull(player.getEquippedWeapon());
        assertEquals(1, player.getAttackDamage());
    }


    @Test
    public void testEquipArmor()
    {
        player.addItem(armor);
        player.equipArmor(armor);

        assertSame(armor, player.getEquippedArmor());
        assertEquals(5, player.getDefense());
    }


    @Test
    public void testCannotEquipArmorNotInInventory()
    {
        player.equipArmor(armor);

        assertNull(player.getEquippedArmor());
    }


    @Test
    public void testCannotEquipNonArmor()
    {
        player.addItem(weapon);
        player.equipArmor(weapon);

        assertNull(player.getEquippedArmor());
    }


    @Test
    public void testUnequipArmor()
    {
        player.addItem(armor);
        player.equipArmor(armor);
        player.unequipArmor();

        assertNull(player.getEquippedArmor());
        assertEquals(0, player.getDefense());
    }


    @Test
    public void testRemoveEquippedWeapon()
    {
        player.addItem(weapon);
        player.equipWeapon(weapon);
        player.removeItem(weapon);

        assertNull(player.getEquippedWeapon());
        assertEquals(0, player.getInventorySize());
    }


    @Test
    public void testRemoveEquippedArmor()
    {
        player.addItem(armor);
        player.equipArmor(armor);
        player.removeItem(armor);

        assertNull(player.getEquippedArmor());
        assertEquals(0, player.getInventorySize());
    }


    @Test
    public void testDealDamageWithoutWeapon()
    {
        player.dealDamage(encounter);

        assertEquals(29, encounter.getHealth());
    }


    @Test
    public void testDealDamageWithWeapon()
    {
        player.addItem(weapon);
        player.equipWeapon(weapon);

        player.dealDamage(encounter);

        assertEquals(20, encounter.getHealth());
        assertEquals(4, weapon.getDurability());
    }


    @Test
    public void testWeaponBreaksAfterAttacking()
    {
        Item weakWeapon = new Item(
            "Weak Sword",
            "A fragile sword.",
            10,
            1,
            Item.ItemType.WEAPON);

        player.addItem(weakWeapon);
        player.equipWeapon(weakWeapon);

        player.dealDamage(encounter);

        assertEquals(20, encounter.getHealth());
        assertNull(player.getEquippedWeapon());
        assertEquals(0, player.getInventorySize());
    }


    @Test
    public void testTakeDamageWithoutArmor()
    {
        player.takeDamage(20);

        assertEquals(80, player.getHealth());
    }


    @Test
    public void takeDamageWithArmor()
    {
        player.addItem(armor);
        player.equipArmor(armor);

        player.takeDamage(8);

        assertEquals(97, player.getHealth());
        assertEquals(4, armor.getDurability());
    }


    @Test
    public void testArmorPreventsAllDamage()
    {
        player.addItem(armor);
        player.equipArmor(armor);

        player.takeDamage(3);

        assertEquals(100, player.getHealth());
    }


    @Test
    public void testArmorBreaksAfterTakingDamage()
    {
        Item weakArmor = new Item(
            "Weak Helmet",
            "A fragile helmet.",
            5,
            1,
            Item.ItemType.ARMOR);

        player.addItem(weakArmor);
        player.equipArmor(weakArmor);

        player.takeDamage(10);

        assertNull(player.getEquippedArmor());
        assertEquals(0, player.getInventorySize());
        assertEquals(95, player.getHealth());
    }


    @Test
    public void testHealthCannotGoBelowZero()
    {
        player.takeDamage(150);

        assertEquals(0, player.getHealth());
        assertFalse(player.isAlive());
    }


    @Test
    public void testIsAliveAtZeroHealth()
    {
        player.setHealth(0);

        assertFalse(player.isAlive());
    }


    @Test
    public void testTakeNoEffectiveDamage()
    {
        player.takeDamage(0);

        assertEquals(100, player.getHealth());
    }


    @Test
    public void testGetInventoryItem()
    {
        player.addItem(weapon);
        player.addItem(potion);

        assertSame(weapon, player.getInventoryItem(0));
        assertSame(potion, player.getInventoryItem(1));
    }


    @Test
    public void testEquipWeaponWithNull()
    {
        player.equipWeapon(null);

        assertNull(player.getEquippedWeapon());
    }


    @Test
    public void testEquipWeaponWrongType()
    {
        Item armor =
            new Item("Helmet", "An iron helmet.", 5, 5, Item.ItemType.ARMOR);

        player.addItem(armor);
        player.equipWeapon(armor);

        assertNull(player.getEquippedWeapon());
    }


    @Test
    public void testEquipWeaponNotInInventory()
    {
        Item weapon =
            new Item("Sword", "A sword.", 10, 5, Item.ItemType.WEAPON);

        player.equipWeapon(weapon);

        assertNull(player.getEquippedWeapon());
    }


    @Test
    public void testEquipWeaponSuccessfully()
    {
        Item weapon =
            new Item("Sword", "A sword.", 10, 5, Item.ItemType.WEAPON);

        player.addItem(weapon);
        player.equipWeapon(weapon);

        assertSame(weapon, player.getEquippedWeapon());
    }


    @Test
    public void testEquipArmorWithNull()
    {
        player.equipArmor(null);

        assertNull(player.getEquippedArmor());
    }


    @Test
    public void testEquipArmorWrongType()
    {
        Item weapon =
            new Item("Sword", "A sword.", 10, 5, Item.ItemType.WEAPON);

        player.addItem(weapon);
        player.equipArmor(weapon);

        assertNull(player.getEquippedArmor());
    }


    @Test
    public void testEquipArmorNotInInventory()
    {
        Item armor = new Item("Shield", "A shield.", 5, 5, Item.ItemType.ARMOR);

        player.equipArmor(armor);

        assertNull(player.getEquippedArmor());
    }


    @Test
    public void testEquipArmorSuccessfully()
    {
        Item armor = new Item("Shield", "A shield.", 5, 5, Item.ItemType.ARMOR);

        player.addItem(armor);
        player.equipArmor(armor);

        assertSame(armor, player.getEquippedArmor());
    }
}
