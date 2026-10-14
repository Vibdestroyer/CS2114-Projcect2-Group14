public class Encounter {
    private final String name;
    private final String description;
    private int health;
    private final int damage;
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
    public void decreaseHealth(int amount) {
        this.health -= amount;
    }
    public boolean isDefeated() {
        return health <= 0;
    }
}
