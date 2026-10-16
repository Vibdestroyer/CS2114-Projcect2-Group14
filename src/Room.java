public class Room
{
    private Item loot;
    private Encounter encounter;
    private String name;
    private RoomType type;
    private String description;
    private boolean sideRoomAvailable;

    public enum RoomType
    {
        MAIN,
        SIDE,
        BOSS
    }

    public Room(
        Item l,
        Encounter e,
        String n,
        RoomType t,
        String d,
        boolean sra)
    {
        this.loot = l;
        this.encounter = e;
        this.name = n;
        this.type = t;
        this.description = d;
        this.sideRoomAvailable = sra;
    }


    public Item getLoot()
    {
        return loot;
    }


    public Encounter getEncounter()
    {
        return encounter;
    }


    public String getName()
    {
        return name;
    }


    public RoomType getType()
    {
        return type;
    }


    public String getDescription()
    {
        return description;
    }


    public boolean isSideRoomAvailable()
    {
        return sideRoomAvailable;
    }


    public void setLoot(Item loot)
    {
        this.loot = loot;
    }


    public void setEncounter(Encounter encounter)
    {
        this.encounter = encounter;
    }


    public boolean hasLoot()
    {
        return loot != null;
    }


    public boolean hasEncounter()
    {
        return encounter != null;
    }
}
