public class Dungeon {
    private LinkedChain<Room> rooms;
    private int currentMainRoomIndex;
    private Room currentRoom;
    private Room sideRoom;

    public Dungeon() {
        rooms = new LinkedChain<>();
        currentMainRoomIndex = 0;
        currentRoom = null;
        sideRoom = null;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void makeDungeon() {
        rooms.clear();
        currentMainRoomIndex = 0;
        sideRoom = null;

        GameDataLoader loader = new GameDataLoader();

        
        for (int i = 0; i < 6; i++){

            Item loot = null;
            Encounter encounter = null;
            String name ="";
            Room.RoomType type = Room.RoomType.MAIN;
            String description = "";
            boolean sideRoomAvailable = true;

            if(Math.random()<.4){
                loot = loader.getRandomMainItem();
            }
            if(Math.random()<.2){
                encounter = new Encounter("Goblin", "A small goblin.", 10, 2);
            }
            if (Math.random()<.3){
                sideRoomAvailable = false;
            }
            Room room = new Room(loot, encounter, name, type, description, sideRoomAvailable);
            rooms.addToEnd(room);
        }
        currentRoom = rooms.get(0);
    }
}
