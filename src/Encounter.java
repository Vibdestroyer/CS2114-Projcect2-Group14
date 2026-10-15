public class Encounter {
    private final String name;
    private final String description;
    private int health;
    private final int damage;
    private Item armor;
    public Encounter(String n, String d, int h, int dmg){
        this.name = n;
        this.description = d;
        this.health = h;
        this.damage = dmg;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getHealth() {
        return health;
    }
    public int getDamage() {
        return damage;
    }
    public Item getArmor() {
        return armor;
    }
    public void decreaseHealth(int amount) {
        this.health -= amount;
    }
    public void decreaseBossHealth(int amount) {
        if (amount-armor.getValue() < 0) {
            this.health -= 0;
        } else {
            this.health -= amount - armor.getValue();
        }
    }
    public boolean isDefeated() {
        return health <= 0;
    }
    public void setArmor() {
        GameDataLoader loader = new GameDataLoader();
        this.armor = loader.getRandomMainItem();
        while (armor.getType() != Item.ItemType.ARMOR) {
            armor = loader.getRandomMainItem();
        }
    }
}
