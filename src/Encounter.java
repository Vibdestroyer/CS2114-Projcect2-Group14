public class Encounter
{
    private final String name;
    private final String description;
    private int health;
    private final int damage;
    private Item armor;

    public Encounter(String n, String d, int h, int dmg)
    {
        this.name = n;
        this.description = d;
        this.health = h;
        this.damage = dmg;
    }


    public String getName()
    {
        return name;
    }


    public String getDescription()
    {
        return description;
    }


    public int getHealth()
    {
        return health;
    }


    public int getDamage()
    {
        return damage;
    }


    public Item getArmor()
    {
        return armor;
    }


    public void decreaseHealth(int amount)
    {
        int effectiveDamage = amount;

        if (armor != null)
        {
            effectiveDamage = Math.max(0, amount - armor.getValue());

            armor.decreaseDurability();

            if (armor.getDurability() <= 0)
            {
                armor = null;
            }
        }

        health = Math.max(0, health - effectiveDamage);
    }


    public boolean isDefeated()
    {
        return health <= 0;
    }


    public void setArmor()
    {
        GameDataLoader loader = new GameDataLoader();
        this.armor = loader.getRandomMainItem();
        while (armor.getType() != Item.ItemType.ARMOR)
        {
            armor = loader.getRandomMainItem();
        }
    }
}
