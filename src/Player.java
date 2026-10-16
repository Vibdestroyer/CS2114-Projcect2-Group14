public class Player
{
    private final String name;
    private int maxHealth;
    private int health;
    private final LinkedChain<Item> inventory;
    private Item equippedWeapon;
    private Item equippedArmor;

    public Player(String n, int mH)
    {
        this.name = n;
        this.maxHealth = mH;
        this.health = mH;
        this.inventory = new LinkedChain<>();
        this.equippedWeapon = null;
        this.equippedArmor = null;
    }


    public String getName()
    {
        return name;
    }


    public int getMaxHealth()
    {
        return maxHealth;
    }


    public int getHealth()
    {
        return health;
    }


    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }


    public void setHealth(int health)
    {
        this.health = health;
    }


    public void addItem(Item item)
    {
        inventory.addToEnd(item);
    }


    public void removeItem(Item item)
    {
        if (item == equippedWeapon)
        {
            unequipWeapon();
        }
        if (item == equippedArmor)
        {
            unequipArmor();
        }
        inventory.remove(item);

    }


    public int getAttackDamage()
    {
        if (equippedWeapon != null)
        {
            return equippedWeapon.getValue();
        }
        return 1;
    }


    public int getDefense()
    {
        if (equippedArmor != null)
        {
            return equippedArmor.getValue();
        }
        return 0;
    }


    public void dealDamage(Encounter encounter)
    {
        int damage = getAttackDamage();
        if (equippedWeapon != null)
        {
            equippedWeapon.decreaseDurability();
            if (equippedWeapon.getDurability() <= 0)
            {
                removeItem(equippedWeapon);
            }
        }
        encounter.decreaseHealth(damage);
    }


    public void takeDamage(int damage)
    {
        int effectiveDamage = damage - getDefense();
        if (equippedArmor != null)
        {
            equippedArmor.decreaseDurability();
            if (equippedArmor.getDurability() <= 0)
            {
                removeItem(equippedArmor);
            }
        }
        if (effectiveDamage > 0)
        {
            health -= effectiveDamage;
            if (health < 0)
            {
                health = 0;
            }
        }
    }


    public boolean isAlive()
    {
        return health > 0;
    }


    public void equipWeapon(Item weapon)
    {
        if (weapon != null && weapon.getType() == Item.ItemType.WEAPON
            && inventory.contains(weapon))
        {
            equippedWeapon = weapon;
        }
    }


    public void equipArmor(Item armor)
    {
        if (armor != null && armor.getType() == Item.ItemType.ARMOR
            && inventory.contains(armor))
        {
            equippedArmor = armor;
        }
    }


    public void unequipWeapon()
    {
        equippedWeapon = null;
    }


    public void unequipArmor()
    {
        equippedArmor = null;
    }


    public Item getEquippedWeapon()
    {
        return equippedWeapon;
    }


    public Item getEquippedArmor()
    {
        return equippedArmor;
    }


    public int getInventorySize()
    {
        return inventory.size();
    }


    public Item getInventoryItem(int index)
    {
        return inventory.get(index);
    }
}
