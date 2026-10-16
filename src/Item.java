public class Item
{
    private final String name;
    private final String description;
    private final int value;
    private int durability;
    private final ItemType type;

    public enum ItemType
    {
        ARMOR,
        CONSUMABLE,
        WEAPON
    }

    public Item(String n, String d, int v, int dur, ItemType t)
    {
        this.name = n;
        this.description = d;
        this.value = v;
        this.durability = dur;
        this.type = t;
    }


    public String getName()
    {
        return name;
    }


    public String getDescription()
    {
        return description;
    }


    public int getValue()
    {
        return value;
    }


    public int getDurability()
    {
        return durability;
    }


    public ItemType getType()
    {
        return type;
    }


    public void setDurability(int durrability)
    {
        this.durability = durrability;
    }


    public void decreaseDurability()
    {
        this.durability -= 1;
    }
}
