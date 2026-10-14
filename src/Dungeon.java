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
    
    public int getRoomCount()
    {
        return rooms.size();
    }

    public void makeDungeon() {
        rooms.clear();
        currentMainRoomIndex = 0;
        sideRoom = null;

        GameDataLoader loader = new GameDataLoader();

        
        for (int i = 0; i < 6; i++){

            Item loot = null;
            Encounter encounter = null;
            Room.RoomType type = Room.RoomType.MAIN;
            boolean sideRoomAvailable = true;
            String[] roomNameAndDescription = loader.mainRoomsNamesAndDescriptions();

            if(Math.random()<.4){
                loot = loader.getRandomMainItem();
            }
            if(Math.random()<.2){
                encounter = loader.getRandomMainEncounter();
            }
            if (Math.random()<.25){
                sideRoomAvailable = false;
            }
            


            Room room = new Room(loot, encounter, roomNameAndDescription[0], type, roomNameAndDescription[1], sideRoomAvailable);
            rooms.addToEnd(room);
        }
        currentRoom = rooms.get(0);
    }
    public void moveToNextRoom() {
        if (canMoveToNextRoom()) {
            currentMainRoomIndex++;
            currentRoom = rooms.get(currentMainRoomIndex);
        }
    }
    public boolean canMoveToNextRoom() {
        return currentMainRoomIndex < rooms.size() - 1;
    }
}
